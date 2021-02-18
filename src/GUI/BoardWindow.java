package GUI;

import Engine.Board;
import GUI.Pieces.GUIBox;
import GUI.Pieces.GUIPiece;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Callable;

public class BoardWindow extends JFrame {
    public Board engine;
    public final float screen_to_window_scale = 2.f/3.f;

    public GUIPiece[][] gui_pieces;
    public GUIBox[][] gui_boxes;

    private int box_size;

    public BoardWindow(Board engine) {
        this.engine = engine;
    }

    public void display() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int smaller_dimension = Math.min(screenSize.height, screenSize.width);

        gui_pieces = new GUIPiece[engine.board.length][engine.board[0].length];
        gui_boxes = new GUIBox[engine.board.length][engine.board[0].length];

        // Initialize windows
        setTitle("Chess - Nano-AI");
        setLayout(null);

        // Force window to keep aspect ratio
        // TODO: Work on making window resizing smoother and better
        //  https://stackoverflow.com/questions/44490655/how-to-maintain-the-aspect-ratio-of-a-jframe

        // Initial size of the window
        setSize((int) (smaller_dimension * screen_to_window_scale), (int) (smaller_dimension * screen_to_window_scale) - getInsets().top);

        // Rest of window settings
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // More properties for the window
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        Rectangle window = getBounds();
        Insets insets = getInsets();
        box_size = (window.height - (insets.top + insets.bottom + insets.left + insets.right)) / engine.board.length;

        // Draw the boxes
        draw_boxes();
//        repaint();
    }

    void draw_boxes() {
//        Rectangle window = getBounds();
        Insets insets = getInsets();
//        int box_size = (window.height - (insets.top + insets.bottom + insets.left + insets.right)) / engine.board.length;
        // Separate loops are needed to make sure that the boxes are on top of each other
        // These loops add the pieces and boxes to the JFrame
        for (int x = 0; x < engine.board.length; x++) {
            for (int y = 0; y < engine.board[x].length; y++) {
                gui_pieces[x][y] = new GUIPiece((box_size * x), (box_size * y), box_size, box_size, this);
                add(gui_pieces[x][y]);
            }
        }

        for (int x = 0; x < engine.board.length; x++) {
            for (int y = 0; y < engine.board[x].length; y++) {
                gui_boxes[x][y] = new GUIBox((box_size * x), (box_size * y), box_size, box_size, (x % 2 == 0) == (y % 2 == 0) ? Color.BLACK : Color.WHITE);
                add(gui_boxes[x][y]);
            }
        }

        // Set the size so it's properly framed
        setSize(box_size * engine.board.length + insets.left + insets.right, box_size * engine.board.length + insets.top + insets.bottom);
    }

    public void move_piece() {
        getGraphics().clearRect(0, 0, getWidth(), getHeight());
        draw_boxes();
    }

//    @Override
//    public void paint(Graphics g) {
////        g.clearRect(0, 0, getWidth(), getHeight());
//        draw_boxes();
//    }
}
