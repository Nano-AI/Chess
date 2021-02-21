package Engine.Pieces;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    public static final String photo_name = "Bishop.png";
    public Bishop(int x, int y, char side, Piece[][] board) {
        /*When reading an array, x is the first one. Example: board[x][y] (the roles are switched)*/
        super(x, y, side, board);
        this.piece = 'b';
    }

    @Override
    public List<int[]> get_moves() {
        List<int[]> spots = new ArrayList<>();

        if (this.x - 1 >= 0) {
            for (int i = 1; i < Math.min(Math.abs(board.length - (Math.abs(board.length - x))) + 1, Math.abs(board[y].length - y)); i++) {
                if (board[this.x - i][this.y + i].side == this.side)
                    break;
                if (!(board[this.x - i][this.y + i] instanceof King))
                    spots.add(new int[] {this.x - i, this.y + i});
                if (!(board[this.x - i][this.y + i] instanceof Empty))
                    break;
            }
        }

        if (this.x + 1 < board.length) {
            for (int i = 1; i < Math.min(Math.abs(board.length - x), Math.abs(board[y].length - (board[y].length - y)) + 1); i++) {
                if (board[this.x + i][this.y - i].side == this.side)
                    break;
                if (!(board[this.x + i][this.y - i] instanceof King))
                    spots.add(new int[] {this.x + i, this.y - i});
                if (!(board[this.x + i][this.y - i] instanceof Empty))
                    break;
            }
        }

        if (this.y + 1 < board[this.y].length) {
            for (int i = 1; i < Math.min(Math.abs(board.length - x), Math.abs(board[y].length - y)); i++) {
                if (board[this.x + i][this.y + i].side == this.side)
                    break;
                if (!(board[this.x + i][this.y + i] instanceof King))
                    spots.add(new int[] {this.x + i, this.y + i});
                if (!(board[this.x + i][this.y + i] instanceof Empty))
                    break;
            }
        }

        if (this.y - 1 >= 0) {
            for (int i = 1; i < Math.min(Math.abs(board.length - (board.length - x)) + 1, Math.abs(board[y].length - (board[y].length - y)) + 1); i++) {
                if (board[this.x - i][this.y - i].side == this.side)
                    break;
                if (!(board[this.x - i][this.y - i] instanceof King))
                    spots.add(new int[] {this.x - i, this.y - i});
                if (!(board[this.x - i][this.y - i] instanceof Empty))
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
    public void on_move(int to_x, int to_y) {

    }
}
