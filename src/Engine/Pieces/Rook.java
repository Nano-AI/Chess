package Engine.Pieces;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    public static final String photo_name = "Rook.png";

    public Rook(int x, int y, char side, Piece[][] board) {
        super(x, y, side, board);
        this.piece = 'r';
    }

    @Override
    public List<int[]> get_moves() {
        List<int[]> spots = new ArrayList<>();
        // Iterating all spots above the piece
        if (this.x - 1 >= 0) {
            for (int i = this.x - 1; i >= 0; i--) {
                if (board[i][this.y].side == this.side)
                    break;
                spots.add(new int[] {i, this.y});
                if (!(board[i][this.y] instanceof Empty))
                    break;
            }
        }
        // Iterating all spots below the piece
        if (this.x + 1 < board.length) {
            for (int i = this.x + 1; i < board.length; i++) {
                if (board[i][this.y].side == this.side)
                    break;
                spots.add(new int[] {i, this.y});
                if (!(board[i][this.y] instanceof Empty))
                    break;
            }
        }
        // Iterating all spots to the right
        if (this.y + 1 < board[this.y].length) {
            for (int i = this.y + 1; i < board.length; i++) {
                if (board[this.x][i].side == this.side)
                    break;
                spots.add(new int[] {this.x, i});
                if (!(board[this.x][i] instanceof Empty))
                    break;
            }
        }

        // Iterating all spots to the left
        if (this.y - 1 >= 0) {
            for (int i = this.y - 1; i >= 0; i--) {
                if (board[this.x][i].side == this.side)
                    break;
                spots.add(new int[] {this.x, i});
                if (!(board[this.x][i] instanceof Empty))
                    break;
            }
        }

        return spots;
    }

    @Override
    public String get_icon() {
        return photo_name;
    }

    @Override
    public void on_move(int to_x, int to_y) { }
}
