package GUI;

import Engine.Board;
import Engine.Pieces.Piece;

import javax.swing.*;
import java.awt.*;

public class BoardWindow extends JFrame {
    //    public Piece[][] board;
    private ChessLabel[] labels;

    public Board engine;

    public BoardWindow(Board engine) {
        this.engine = engine;
        labels = new ChessLabel[engine.board.length * engine.board[0].length];
        for (int i = 0; i < engine.board.length * engine.board[0].length; i++) {
            labels[i] = new ChessLabel("");
        }
    }

    public void display() {
        setTitle("Chess - Nano-AI");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Container contentPane = getContentPane();
        GridLayout gridLayout = new GridLayout(engine.board.length, engine.board[0].length);

        contentPane.setLayout(gridLayout);

        int row = -1;
        for (int i = 0; i < labels.length; i++) {
            if (i % 8 == 0) row++; // increment row number
            labels[i].set(i, row);
            String text = String.valueOf(
                    engine.board[i / engine.board[0].length][i % engine.board[0].length]
            );
            labels[i].setText((text.equals(".")) ? " " : text);
            contentPane.add(labels[i]);
        }

        setSize(600, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

class ChessLabel extends JLabel {

    Font font = new Font("Ariel", Font.PLAIN, 24);
    Color bgLight = new Color(222, 184, 135);
    Color bgDark = new Color(139, 69, 19);

    ChessLabel(String s) {
        super(s);
    }

    void set(int idx, int row) {
        setFont(font);
        setOpaque(true);
        setBackground((idx + row) % 2 == 0 ? bgDark : bgLight);
        setHorizontalAlignment(SwingConstants.CENTER);
    }
}

//
// = new ChessLabel[]{
//
//         // white
//         new ChessLabel("\u2656"), new ChessLabel("\u2658"), new ChessLabel("\u2657"),
//         new ChessLabel("\u2655"), new ChessLabel("\u2654"), new ChessLabel("\u2657"),
//         new ChessLabel("\u2658"), new ChessLabel("\u2656"), new ChessLabel("\u2659"),
//         new ChessLabel("\u2659"), new ChessLabel("\u2659"), new ChessLabel("\u2659"),
//         new ChessLabel("\u2659"), new ChessLabel("\u2659"), new ChessLabel("\u2659"),
//         new ChessLabel("\u2659"),
//         // empty
//         new ChessLabel(" "), new ChessLabel(" "), new ChessLabel(" "),
//         new ChessLabel(" "), new ChessLabel(" "), new ChessLabel(" "),
//         new ChessLabel(" "), new ChessLabel(" "), new ChessLabel(" "),
//         new ChessLabel(" "), new ChessLabel(" "), new ChessLabel(" "),
//         new ChessLabel(" "), new ChessLabel(" "), new ChessLabel(" "),
//         new ChessLabel(" "), new ChessLabel(" "), new ChessLabel(" "),
//         new ChessLabel(" "), new ChessLabel(" "), new ChessLabel(" "),
//         new ChessLabel(" "), new ChessLabel(" "), new ChessLabel(" "),
//         new ChessLabel(" "), new ChessLabel(" "), new ChessLabel(" "),
//         new ChessLabel(" "), new ChessLabel(" "), new ChessLabel(" "),
//         new ChessLabel(" "), new ChessLabel(" "),
//         // black
//         new ChessLabel("\u265F"), new ChessLabel("\u265F"), new ChessLabel("\u265F"),
//         new ChessLabel("\u265F"), new ChessLabel("\u265F"), new ChessLabel("\u265F"),
//         new ChessLabel("\u265F"), new ChessLabel("\u265F"), new ChessLabel("\u265C"),
//         new ChessLabel("\u265E"), new ChessLabel("\u265D"), new ChessLabel("\u265B"),
//         new ChessLabel("\u265A"), new ChessLabel("\u265D"), new ChessLabel("\u265E"),
//         new ChessLabel("\u265C")
//         }