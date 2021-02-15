import Engine.Board;
import GUI.BoardWindow;

public class Main {
    public static void main(String[] args) throws Exception {
        Board engine = new Board();
        engine.move_piece(7, 0, 0, 0);
        engine.print_board();
        BoardWindow boardWindow = new BoardWindow(engine);
        boardWindow.display();
    }
}
