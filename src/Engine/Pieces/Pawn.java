package Engine.Pieces;

public class Pawn extends Piece {
    public static boolean first_move;
    public Pawn(int x, int y, char side, Piece[][] board) {
        super(x, y, side, board);
        this.piece = 'p';
        first_move = true;
    }

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
        if (first_move)
            first_move = false;
        return true;
    }
}
