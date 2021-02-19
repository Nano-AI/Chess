package Engine.Pieces;

import Engine.Board;

import java.util.List;

public abstract class Piece {
    public int x, y;
    public char side;
    public char piece;
    public Piece[][] board;
    public static String photo_name;

    public Piece(int x, int y, char side, Piece[][] board) {
        // This is the basic class for pieces in the chess board.
        this.x = x;
        this.y = y;
        this.side = side;
        this.board = board;
    }

    public Piece(int x, int y) {
        // This is for empty spots only
        this.x = x;
        this.y = y;
        this.piece = ' ';
    }

//    public abstract boolean can_move(int x, int y);

//    public boolean can_move(int x, int y, boolean board_coordinates) {
//        int[] cords = Board.array_cords_to_board_cords(x, y);
//        return can_move(cords[0], cords[1]);
//    }

    public abstract List<int[]> get_moves();
    public abstract String get_icon();

    @Override
    public String toString() {
        return String.valueOf((this.side == 'b') ? Character.toUpperCase(this.piece) : Character.toLowerCase(this.piece));
    }
}
