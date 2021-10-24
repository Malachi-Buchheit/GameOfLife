import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyListener implements KeyListener {

    static boolean shift = false;

    @Override
    public void keyTyped(KeyEvent e) {
        //System.out.println("typed "+e.getKeyChar());
        if(e.getKeyChar() == 'c') {
            GameOfLife.clear();
        } else if(e.getKeyChar() == 'w') {
            GameOfLife.loadCopy("input.txt");
        } else if(e.getKeyChar() == 'q') {
            GameOfLife.rotateCopy(false);
        } else if(e.getKeyChar() == 'e') {
            GameOfLife.rotateCopy(true);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SHIFT)
            shift = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SHIFT)
            shift = false;
    }
}
