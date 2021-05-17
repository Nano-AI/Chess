package Engine.Pieces;

import Engine.Board;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    public final String photo_name = "Knight.png";
    private final int[][] movable_spots = new int[][]{
            new int[]{ 2, 1 },
            new int[]{ 2, -1 },
            new int[]{ -2, 1 },
            new int[]{ -2, -1 },
            new int[]{ 1, 2 },
            new int[]{ 1, -2 },
            new int[]{ -1, -2 },
            new int[]{ -1, 2 },
    };

    public Knight(int x, int y, char side, Piece[][] board, Board reference) {
        /*When reading an array, x is the first one. Example: board[x][y] (the roles are switched)*/
        super(x, y, side, board, reference);
        this.reference = reference;
        this.piece = 'n';
    }

    @Override
    public List<int[]> get_moves(boolean... king_check) throws CloneNotSupportedException {
        List<int[]> spots = new ArrayList<>();
        King side_king = (this.side == 'b') ? this.reference.black_king : this.reference.white_king;
        for (int[] cord : movable_spots) {
            int new_x = cord[0] + this.x;
            int new_y = cord[1] + this.y;
            if (new_x >= 0 && new_x < board.length && new_y >= 0 && new_y < board.length &&
                    board[new_x][new_y].side != this.side && !(board[new_x][new_y] instanceof King) &&
                    (king_check.length != 0 ? !side_king.check_illegal_move(this.x, this.y, new_x, new_y) : true)
            ) {
                spots.add(new int[]{new_x, new_y});
            }
        }
        return spots;
    }

    @Override
    public String get_icon() {
        return this.photo_name;
    }

    @Override
    public void on_move(int to_x, int to_y) { }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Knight(x, y, side, board, reference);
    }
}
