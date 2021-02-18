package GUI;

import Engine.Board;
import GUI.Pieces.GUIBox;
import GUI.Pieces.GUIPiece;

import javax.swing.*;
import java.awt.*;

public class BoardUI extends JPanel {
    public Board engine;
    public GUIPiece[][] gui_pieces;
    public GUIBox[][] gui_boxes;

    private int box_size;

    public BoardUI (Board engine) {
        Rectangle window = getBounds();
        Insets insets = getInsets();
        setSize(box_size * engine.board.length + insets.left + insets.right, box_size * engine.board.length + insets.top + insets.bottom);
        this.engine = engine;
        gui_pieces = new GUIPiece[engine.board.length][engine.board[0].length];
        gui_boxes = new GUIBox[engine.board.length][engine.board[0].length];
        box_size = (window.height - (insets.top + insets.bottom + insets.left + insets.right)) / engine.board.length;
    }

    public void draw_boxes() {
//        Rectangle window = getBounds();
        Insets insets = getInsets();
//        int box_size = (window.height - (insets.top + insets.bottom + insets.left + insets.right)) / engine.board.length;
        // Separate loops are needed to make sure that the boxes are on top of each other
        // These loops add the pieces and boxes to the JFrame
        for (int x = 0; x < engine.board.length; x++) {
            for (int y = 0; y < engine.board[x].length; y++) {
//                gui_pieces[x][y] = new GUIPiece((box_size * x), (box_size * y), box_size, box_size, this);
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
        System.out.printf("%d %d %d %d %d", box_size, insets.top, insets.right, insets.bottom, insets.left);
        setSize(box_size * engine.board.length + insets.left + insets.right, box_size * engine.board.length + insets.top + insets.bottom);
        System.out.println(getHeight());
    }
}
