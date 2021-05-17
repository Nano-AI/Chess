package Engine.Pieces;

import Engine.Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Pawn extends Piece {
    public boolean first_move;
    public static final String photo_name = "Pawn.png";
    private Board reference;

    public Pawn(int x, int y, char side, Piece[][] board, Board reference) {
        super(x, y, side, board, reference);
        this.piece = 'p';
        first_move = true;
        this.reference = reference;
    }

    @Override
    public List<int[]> get_moves(boolean... king_check) throws CloneNotSupportedException {
        List<int[]> moves = new ArrayList<>();
        // Variable for what up is relative to the piece.
        int up = (this.side == 'b') ? 1 : -1;

        // Checks if piece above it one unit and right of it one unit isn't an empty piece,
        // and if the pieces side and the current pieces side is not the same, add the spot to the possible moves.
        if (this.x + up >= 0 && this.x + up < board.length && this.y + 1 < this.board[this.y].length &&
                !(board[this.x + up][this.y + 1] instanceof Empty) && board[this.x + up][this.y + 1].side != this.side
                /*&& !(board[this.x + up][this.y + 1] instanceof King)*/)
            moves.add(new int[]{this.x + up, this.y + 1});

        // Checks if piece above it one unit and left of it one unit isn't an empty piece,
        // and if the pieces side and the current pieces side is not the same, add the spot to the possible moves.
        if (this.x + up >= 0 && this.x + up < board.length && this.y - 1 >= 0 &&
                !(board[this.x + up][this.y - 1] instanceof Empty) /*&& !(board[this.x + up][this.y + 1] instanceof King)*/
                && board[this.x + up][this.y - 1].side != this.side)
            moves.add(new int[]{this.x + up, this.y - 1});

        // If the spot above the current piece is empty, then you can move up there.
        if (board[this.x + up][this.y] instanceof Empty) {
            moves.add(new int[]{this.x + up, this.y});
            // If there is nothing on top of the current piece, then move on.
            // If it's first move and there's nothing on the second spot, then you can move there.
            if (this.x + (up * 2) < this.board.length && first_move && board[this.x + (up * 2)][this.y] instanceof Empty)
                moves.add(new int[]{this.x + (up * 2), this.y});
        }
        int kingX = (side == 'w') ? reference.white_king.x : reference.black_king.x;
        int kingY = (side == 'w') ? reference.white_king.y : reference.black_king.y;
//        System.out.println("------------");
//        moves.forEach((int[] thing) -> System.out.println(Arrays.toString(thing)));
        moves.removeIf((int[] spot) -> {
            try {
                return ((King) board[kingX][kingY]).check_illegal_move(x, y, spot[0], spot[1]);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return false;
        });
        moves.forEach((int[] thing) -> System.out.println(Arrays.toString(thing)));
//        System.out.println("------------");
        return moves;
    }

    public List<int[]> get_king_illegal_moves() {
        List<int[]> moves = new ArrayList<>();
        // Variable for what up is relative to the piece.
        int up = (this.side == 'b') ? 1 : -1;

        // Checks if piece above it one unit and right of it one unit isn't an empty piece,
        // and if the pieces side and the current pieces side is not the same, add the spot to the possible moves.
        if (this.x + up >= 0 && this.x + up < board.length && this.y + 1 < this.board[this.y].length &&
                board[this.x + up][this.y + 1].side != this.side)
            moves.add(new int[]{this.x + up, this.y + 1});

        // Checks if piece above it one unit and left of it one unit isn't an empty piece,
        // and if the pieces side and the current pieces side is not the same, add the spot to the possible moves.
        if (this.x + up >= 0 && this.x + up < board.length && this.y - 1 >= 0
                && board[this.x + up][this.y - 1].side != this.side)
            moves.add(new int[]{this.x + up, this.y - 1});

        return moves;
    }

    @Override
    public String get_icon() {
        return photo_name;
    }

    @Override
    public void on_move(int to_x, int to_y) {
        first_move = false;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Pawn(x, y, side, board, reference);
    }
}
