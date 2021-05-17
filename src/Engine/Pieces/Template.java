package Engine.Pieces;

import Engine.Board;

import java.util.List;

public class Template extends Piece {
    public Template(int x, int y, char side, Piece[][] board, Board reference) {
        /*When reading an array, x is the first one. Example: board[x][y] (the roles are switched)*/
        super(x, y, side, board, reference);
        this.piece = 'p';
    }

    @Override
    public List<int[]> get_moves(boolean... king_check) {
        return null;
    }

    @Override
    public String get_icon() {
        return "";
    }

    @Override
    public void on_move(int to_x, int to_y) { }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Template(x, y, side, board, reference);
    }
}
