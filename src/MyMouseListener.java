import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyMouseListener implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            GameOfLife.paused = true;
            if(GameOfLife.getCopy() == null) {
                GameOfLife.toggle(e.getX() / MyWindow.scale, e.getY() / MyWindow.scale);
            } else {
                GameOfLife.pasteCopy();
                if(!MyKeyListener.shift)
                    GameOfLife.clearCopy();
            }
        } else if(e.getButton() == MouseEvent.BUTTON3) {
            GameOfLife.paused = !GameOfLife.paused;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
