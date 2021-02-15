package GUI.Pieces;

import Engine.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ChessWindow extends JPanel {
    public int start_box_x = 0;
    public int start_box_y = 0;
    public int box_size = 100;
    public int box_width = box_size * Board.board_width;
    public int box_height = box_size * Board.board_height;


}
