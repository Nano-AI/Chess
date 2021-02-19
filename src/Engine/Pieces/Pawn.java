package Engine.Pieces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pawn extends Piece {
    public static boolean first_move;
    public static final String photo_name = "Pawn.png";

    public Pawn(int x, int y, char side, Piece[][] board) {
        super(x, y, side, board);
        this.piece = 'p';
        first_move = true;
    }

    @Override
    public List<int[]> get_moves() {
        List<int[]> moves = new ArrayList<>();
        // Variable for what up is relative to the piece.
        int up = (this.side == 'b') ? 1 : -1;

        // Checks if piece above it one unit and right of it one unit isn't an empty piece,
        // and if the pieces side and the current pieces side is not the same, add the spot to the possible moves.
        if (this.x + up >= 0 && this.x + up < board.length && this.y + 1 < this.board[this.y].length &&
                !(board[this.x + up][this.y + 1] instanceof Empty) && board[this.x + up][this.y + 1].side != this.side)
            moves.add(new int[]{this.x + up, this.y + 1});

        // Checks if piece above it one unit and left of it one unit isn't an empty piece,
        // and if the pieces side and the current pieces side is not the same, add the spot to the possible moves.
        if (this.x + up >= 0 && this.x + up < board.length && this.y - 1 >= 0 &&
                !(board[this.x + up][this.y - 1] instanceof Empty) && board[this.x + up][this.y - 1].side != this.side)
            moves.add(new int[]{this.x + up, this.y - 1});

        // If the spot above the current piece is empty, then you can move up there.
        if (board[this.x + up][this.y] instanceof Empty) {
            moves.add(new int[]{this.x + up, this.y});
            // If there is nothing on top of the current piece, then move on.
            // If it's first move and there's nothing on the second spot, then you can move there.
            if (this.x + (up * 2) < this.board.length && first_move && board[this.x + (up * 2)][this.y] instanceof Empty)
                moves.add(new int[]{this.x + (up * 2), this.y});
        }
        return moves;
    }

    @Override
    public String get_icon() {
        return photo_name;
    }
}

/*
    public boolean can_move(int toX, int toY) {
        // Check if move is after 2 units
        if (Math.abs(this.x - toX) > 2)
            return false;
        // If trying to move 2 spots while it's not the first move
        if (Math.abs(this.x - toX) == 2 && !first_move)
            return false;
        // Makes sure that piece doesn't go behind or stay on spot for black pieces
        if (this.x - toX >= -1 && this.side == 'b')
            return false;
        // Makes sure that piece doesn't go behind or stay on spot for white pieces
        if (this.x - toX <= 1 && this.side == 'w')
            return false;
        // Makes sure you don't go too far off to the left/right
        if (Math.abs(this.y - toY) > 1)
            return false;
        // If the spot the piece is moving to an empty spot, and if it's moving to the right/left, return false
        if (board[toX][toY] instanceof Empty && Math.abs(this.y - toY) != 0)
            return false;
        // If you're moving to a spot that's not empty, you're moving left/right, and you're not moving up/down, return false
        if (!(board[toX][toY] instanceof Empty) && Math.abs(this.y - toY) != 0 && this.x - toX != 1)
            return false;
        // Check that if you're not capturing into a piece but you're moving into one
        if (Math.abs(this.y - toY) != 1 && Math.abs(this.x - toX) != 1 && !(board[toX][toY] instanceof Empty))
            return false;
        // Make sure that the pawn doesn't capture its own pieces
        if (board[toX][toY].side == this.side)
            return false;
        // Check if it's first move, if it is, set it to false
        if (first_move)
            first_move = false;
        return true;
    }
 */

