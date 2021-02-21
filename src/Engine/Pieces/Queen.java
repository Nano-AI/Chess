package Engine.Pieces;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    public static final String photo_name = "Queen.png";
    public Queen(int x, int y, char side, Piece[][] board) {
        /*When reading an array, x is the first one. Example: board[x][y] (the roles are switched)*/
        super(x, y, side, board);
        this.piece = 'q';
    }

    @Override
    public List<int[]> get_moves() {
        // Adding bishop spots
        List<int[]> spots = new Bishop(x, y, side, board).get_moves();
        // Adding all elements returned from rook.get_moves() to the spots array
        spots.addAll(new Rook(x, y, side, board).get_moves());
        return spots;
    }

    @Override
    public String get_icon() {
        return photo_name;
    }

    @Override
    public void on_move(int to_x, int to_y) { }
}
