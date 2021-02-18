package GUI.Pieces;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GUIBox extends JPanel {
    public GUIBox (int x, int y, int height, int width, Color bg) {
//        setBorder(new LineBorder(bg, 3));
        setBackground(bg);
        setBounds(x, y, width, height);
        setOpaque(true);
    }
}
