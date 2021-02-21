package Engine.Pieces;

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

    public Knight(int x, int y, char side, Piece[][] board) {
        /*When reading an array, x is the first one. Example: board[x][y] (the roles are switched)*/
        super(x, y, side, board);
        this.piece = 'n';
    }

    @Override
    public List<int[]> get_moves() {
        List<int[]> spots = new ArrayList<>();
        for (int[] cord : movable_spots) {
            int new_x = cord[0] + this.x;
            int new_y = cord[1] + this.y;
            if (new_x >= 0 && new_x < board.length && new_y >= 0 && new_y < board.length &&
                    board[new_x][new_y].side != this.side && !(board[new_x][new_y] instanceof King)) {
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
}
