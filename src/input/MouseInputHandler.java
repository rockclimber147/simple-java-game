package input;

import javax.swing.event.MouseInputListener;
import javax.swing.SwingUtilities;
import java.awt.event.MouseEvent;

public class MouseInputHandler implements MouseInputListener {
    MouseInputState mouseState;

    public MouseInputHandler(MouseInputState mouseState) {
        this.mouseState = mouseState;
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)){
            mouseState.leftMousePressed = true;
        } else if (SwingUtilities.isRightMouseButton(e)) {
            mouseState.rightMousePressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)){
            mouseState.leftMousePressed = false;
        } else if (SwingUtilities.isRightMouseButton(e)) {
            mouseState.rightMousePressed = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        setVectorFromMouseCoordinates(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        setVectorFromMouseCoordinates(e);
    }
    private void setVectorFromMouseCoordinates(MouseEvent e){
        mouseState.mouseVector.setX(e.getX());
        mouseState.mouseVector.setY(e.getY());
    }
}
