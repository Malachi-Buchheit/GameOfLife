import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class MyWindow {
    static MyWindow window;
    static BufferStrategy bs;
    static boolean rendering = false;
    static int scale = 10;
    Canvas canvas;
    JFrame frame;

    public MyWindow(int w, int h) {
        canvas = new Canvas();
        frame = new JFrame();
        canvas.setSize(new Dimension(w, h));
        frame.add(canvas);
        frame.pack();
        frame.setFocusable(true);
        canvas.addKeyListener(new MyKeyListener());
        canvas.addMouseListener(new MyMouseListener());
        canvas.createBufferStrategy(2);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void renderer() {
        rendering = true;
        bs = window.canvas.getBufferStrategy();
        long timeThen = System.currentTimeMillis();
        while(rendering) {
            long timeNow = System.currentTimeMillis();
            Graphics2D g = (Graphics2D) bs.getDrawGraphics();
            g.setColor(Color.darkGray);
            g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
            g.dispose();
            for(int y = 0; y < GameOfLife.height; y++) {
                for (int x = 0; x < GameOfLife.width; x++) {
                    g = (Graphics2D) bs.getDrawGraphics();
                    if(GameOfLife.grid[y][x])
                        g.setColor(Color.blue);
                    else
                        g.setColor(Color.gray);
                    g.fillRect(x*scale+1, y*scale+1, scale-2, scale-2);
                    g.dispose();
                }
            }
            synchronized (window) {
                if (GameOfLife.getCopy() != null) {
                    for (int y = 0; y < GameOfLife.getCopy().length; y++) {
                        for (int x = 0; x < GameOfLife.getCopy()[0].length; x++) {
                            g = (Graphics2D) bs.getDrawGraphics();
                            if (GameOfLife.getCopy()[y][x])
                                g.setColor(Color.blue);
                            else
                                g.setColor(Color.lightGray);
                            g.fillRect((x + canvas.getMousePosition().x / scale) * scale + 1, (y + canvas.getMousePosition().y / scale) * scale + 1, scale - 2, scale - 2);
                            g.dispose();
                        }
                    }
                }
            }
            bs.show();
            if(timeNow > timeThen + 100) {
                timeThen = timeNow;
                GameOfLife.update();
            }
        }
    }

    public static int getMouseXGrid() {
        return window.canvas.getMousePosition().x/MyWindow.scale;
    }

    public static int getMouseYGrid() {
        return window.canvas.getMousePosition().y/MyWindow.scale;
    }
}
