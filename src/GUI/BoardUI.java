package GUI;

import Engine.Board;
import Engine.Pieces.Empty;
import GUI.Pieces.GUIBox;
import GUI.Pieces.GUIPiece;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class BoardUI extends JLayeredPane {
    public Board engine;
    public GUIPiece[][] gui_pieces;
    public GUIBox[][] gui_boxes;

    private int box_size;
    private boolean dropped;

    private int selected_x_index = -1, selected_y_index = -1;

    public BoardUI (Board engine, BoardWindow reference) {
        setLayout(null);
        setPreferredSize(new Dimension(reference.getWidth(), reference.getHeight()));
        setBounds(0, 0, reference.getWidth(), reference.getHeight());

        this.engine = engine;
        gui_pieces = new GUIPiece[engine.board.length][engine.board[0].length];
        gui_boxes = new GUIBox[engine.board.length][engine.board[0].length];
        box_size = reference.box_size;

        Insets insets = getInsets();
        setSize(box_size * engine.board.length + insets.left + insets.right, box_size * engine.board.length + insets.top + insets.bottom);
        // Dropped is the variable for if a piece has been dropped
        // Setting it to true so it will draw the first time
        dropped = true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!dropped)
            return;
        else
            dropped = false;

        g.clearRect(0, 0, getWidth(), getHeight());

        // Separate loops are needed to make sure that the boxes are on top of each other
        // These loops add the pieces and boxes to the JFrame
        for (int x = 0; x < engine.board.length; x++) {
            for (int y = 0; y < engine.board[x].length; y++) {
                GUIPiece old_piece = gui_pieces[x][y];
                try {
                    gui_pieces[x][y] = new GUIPiece((box_size * x), (box_size * y), box_size, box_size, this, y, x);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (selected_y_index != y || selected_x_index != x) {
                    if (old_piece != null)
                        remove(old_piece);
                    add(gui_pieces[x][y], 2, 0);
                    setLayer(gui_pieces[x][y], 2);
                } else {
                    setLayer(gui_pieces[x][y], 3);
                }
            }
        }

        for (int x = 0; x < engine.board.length; x++) {
            for (int y = 0; y < engine.board[x].length; y++) {
                if (gui_boxes[x][y] != null)
                    remove(gui_boxes[x][y]);

                GUIBox gui_box = new GUIBox((box_size * x), (box_size * y), box_size, box_size, (x % 2 == 0) == (y % 2 == 0) ? Color.GRAY : Color.WHITE);

                gui_boxes[x][y] = gui_box;
                add(gui_boxes[x][y], 1, 0);
                setLayer(gui_boxes[x][y], 1);
            }
        }

        List<int[]> spots = null;

        if (selected_x_index != -1 && selected_y_index != -1) {
            try {
                spots = engine.board[selected_y_index][selected_x_index].get_moves();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }

        if (spots != null) {
            for (int[] cords : spots) {
                gui_boxes[cords[1]][cords[0]].setBackground(blend((cords[1] % 2 == 0) == (cords[0] % 2 == 0) ? Color.GRAY : Color.WHITE, Color.GREEN));
            }
        }
    }

    public void reset_drawing(boolean remove_all) {
        dropped = true;
        if (remove_all)
            removeAll();
        revalidate();
        repaint();
    }

    public void picked_piece(int y_index, int x_index) {
        // This function makes sure selected piece is always on top
        selected_x_index = x_index;
        selected_y_index = y_index;
        reset_drawing(false);
    }

    public void move_piece(int x_index, int y_index, int to_x_index, int to_y_index) throws Exception {
        engine.move_piece(x_index, y_index, to_x_index, to_y_index);
    }

    public static Color blend(Color c0, Color c1) {
        /* Code from here: http://www.java2s.com/Code/Java/2D-Graphics-GUI/Blendtwocolors.htm */
        double totalAlpha = c0.getAlpha() + c1.getAlpha();
        double weight0 = c0.getAlpha() / totalAlpha;
        double weight1 = c1.getAlpha() / totalAlpha;

        double r = weight0 * c0.getRed() + weight1 * c1.getRed();
        double g = weight0 * c0.getGreen() + weight1 * c1.getGreen();
        double b = weight0 * c0.getBlue() + weight1 * c1.getBlue();
        double a = Math.max(c0.getAlpha(), c1.getAlpha());

        return new Color((int) r, (int) g, (int) b, (int) a);
    }
}
