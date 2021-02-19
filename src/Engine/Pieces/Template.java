package Engine.Pieces;

import java.util.List;

public class Template extends Piece {
    public Template(int x, int y, char side, Piece[][] board) {
        /*When reading an array, x is the first one. Example: board[x][y] (the roles are switched)*/
        super(x, y, side, board);
        this.piece = 'p';
    }

    @Override
    public List<int[]> get_moves() {
        return null;
    }

    @Override
    public String get_icon() {
        return "";
    }

    @Override
    public void on_move(int to_x, int to_y) { }
}
