import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameOfLife {
    static boolean paused = true;
    static int width = 100;
    static int height = 100;
    static boolean[][] grid = new boolean[height][width];
    static boolean[][] copy;

    public static void loadCopy(String file) {
        boolean[][] buff = new boolean[0][0];
        int w = 0;
        int h = 0;
        try {
            Scanner scan = new Scanner(new File(file));
            char alive = 'O';
            StringBuilder str = new StringBuilder();
            while(scan.hasNext()) {
                str.append(scan.nextLine()).append("\n");
                h++;
            }
            scan.close();

            while(str.charAt(w) != '\n') w++;
            buff = new boolean[h][--w];

            for(int i = 0; i < str.length(); i++) {
                buff[i/h][i%h] = str.charAt(i) == alive;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        copy = buff;
    }

    public static void clear() {
        grid = new boolean[height][width];
    }

    public static void toggle(int x, int y) {
        grid[y][x] = !grid[y][x];
    }

    private static int numLiveNeighbors(int x, int y) {
        int num = 0;
        for(int dy = -1; dy <= 1; dy++) {
            for(int dx = -1; dx <= 1; dx++) {
                if((0 <= x+dx) && (x+dx < width) && (0 <= y+dy) && (y+dy < height) && !(dy == 0 && dx == 0)) {
                    if(grid[y+dy][x+dx]) num++;
                }
            }
        }
        return num;
    }

    public static void update() {
        if(!paused) {
            boolean[][] newGrid = new boolean[height][width];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int num = numLiveNeighbors(x, y);
                    newGrid[y][x] = false;
                    if(grid[y][x] && (num == 2 || num == 3)) newGrid[y][x] = true;
                    else if(!grid[y][x] && num == 3) newGrid[y][x] = true;
                }
            }
            grid = newGrid;
        }
    }

    public static void main(String[] args) {
        MyWindow.window = new MyWindow(width*MyWindow.scale, height*MyWindow.scale);
        MyWindow.window.renderer();
    }
}
