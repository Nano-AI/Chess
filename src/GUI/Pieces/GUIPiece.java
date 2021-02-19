package GUI.Pieces;

import Engine.Pieces.Empty;
import Engine.Pieces.Piece;
import GUI.BoardUI;
import GUI.BoardWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;

public class GUIPiece extends JComponent {
    private volatile int screenX = 0;
    private volatile int screenY = 0;
    private volatile int myX = 0;
    private volatile int myY = 0;

    BoardUI reference;

    private BufferedImage icon;

    private int x_index, y_index, box_height, box_width;

    public GUIPiece(int x, int y, int height, int width, BoardUI reference, int x_index, int y_index) throws IOException {
        // x and y are the locations on the screen, width is the width of the box, reference is a reference to BoardUI
        // to access some of it's vars/functions, and x_index and y_index are the index values for the arrays
//        setBorder(new LineBorder(Color.BLUE, 3));
        setBackground(Color.WHITE);
        setBounds(x, y, width, height);
        setOpaque(false);
        this.reference = reference;
        add_mouse_events();
        this.x_index = x_index;
        this.y_index = y_index;
        this.box_height = height;
        this.box_width = width;
        add_icon();
    }

    public void add_icon() throws IOException {
        Piece piece = reference.engine.board[x_index][y_index];
        if (!piece.get_icon().equals("")) {
            InputStream stream = getClass().getResourceAsStream("/" + ((piece.side == 'b') ? "Black/" : "White/") +  piece.get_icon());
            icon = ImageIO.read(stream);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (icon != null)
            g.drawImage(icon, 0, 0, box_width, box_height, null);
    }

    public void add_mouse_events() {
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) { }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("pressed " + x_index + y_index);
                reference.picked_piece(x_index, y_index);

                screenX = e.getXOnScreen();
                screenY = e.getYOnScreen();

                myX = getX();
                myY = getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.printf("Let go of piece. X: %d Y: %d\n", e.getXOnScreen(), e.getYOnScreen());
                reference.picked_piece(-1, -1);
                reference.reset_drawing(true);
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
