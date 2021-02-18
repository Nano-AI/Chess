package GUI.Pieces;

import GUI.BoardUI;
import GUI.BoardWindow;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.concurrent.Callable;

public class GUIPiece extends JComponent {
    private volatile int screenX = 0;
    private volatile int screenY = 0;
    private volatile int myX = 0;
    private volatile int myY = 0;

    public GUIPiece(int x, int y, int height, int width, BoardWindow reference) {
        setBorder(new LineBorder(Color.BLUE, 3));
        setBackground(Color.WHITE);
        setBounds(x, y, width, height);
        setOpaque(false);

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) { }

            @Override
            public void mousePressed(MouseEvent e) {
                screenX = e.getXOnScreen();
                screenY = e.getYOnScreen();

                myX = getX();
                myY = getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.printf("Let go of piece. X: %d Y: %d\n", e.getXOnScreen(), e.getYOnScreen());
                reference.move_piece();
            }

            @Override
            public void mouseEntered(MouseEvent e) { }

            @Override
            public void mouseExited(MouseEvent e) { }
        });

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int deltaX = e.getXOnScreen() - screenX;
                int deltaY = e.getYOnScreen() - screenY;

                setLocation(myX + deltaX, myY + deltaY);
            }

            @Override
            public void mouseMoved(MouseEvent e) { }
        });
    }
}
