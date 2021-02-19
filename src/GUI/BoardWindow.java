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

    public int box_size;

    public BoardWindow(Board engine) {
        this.engine = engine;
    }

    public void display() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int smaller_dimension = Math.min(screenSize.height, screenSize.width);

        // Initialize windows
        setTitle("Chess - Nano-AI");
        setLayout(null);

        // Force window to keep aspect ratio
        // TODO: Work on making window resizing smoother and better
        //  https://stackoverflow.com/questions/44490655/how-to-maintain-the-aspect-ratio-of-a-jframe

        // Initial size of the window
        setSize((int) (smaller_dimension * screen_to_window_scale), (int) (smaller_dimension * screen_to_window_scale) - getInsets().top);
        Rectangle window = getBounds();
        Insets insets = getInsets();
        box_size = (window.height - (insets.top + insets.bottom + insets.left + insets.right)) / engine.board.length;

        BoardUI boardUI = new BoardUI(engine, this);
        add(boardUI);

        // Rest of window settings
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // More properties for the window
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        insets = getInsets();
        setSize(box_size * engine.board.length + insets.left + insets.right, box_size * engine.board.length + insets.top + insets.bottom);
    }
}
