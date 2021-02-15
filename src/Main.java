import Engine.Board;
import GUI.Pieces.ChessWindow;

public class Main {
    public static void main(String[] args) throws Exception {
        Board engine = new Board();
        engine.move_piece(6, 0, 4, 0);
        engine.print_board();
        ChessWindow.main(args);
    }
}
