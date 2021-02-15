package Engine;
import Engine.Pieces.*;

import java.util.Arrays;
import java.util.List;

public class Board {
    public static final String board_setup_str = "" +
            ". . . . . . . ." +
            "P P P P P P P P" +
            ". . . . . . . ." +
            ". . . . . . . ." +
            ". . . . . . . ." +
            ". . . . . . . ." +
            "p p p p p p p p" +
            ". . . . . . . .";

    public static int board_height = 8;
    public static int board_width = 8;

    public static Piece[][] board;

    public Board() throws Exception {
        // Declaring board variables
        board = new Piece[board_height][board_width];
        char[][] stringBoardSetup = new char[board_height + 1][board_width + 1];
        String boardString = board_setup_str.replaceAll("\\s", "");

        // Check if the board length works
        if (boardString.length() / board_height != board_width || boardString.length() / board_width != board_height) {
            throw new Exception("Board height does not work with given board.");
        }

        // Converting board from String to char[][]
        for (int columnCount = 0; columnCount < board_height; columnCount++) {
            char[] column = boardString.substring(columnCount * board_width, (columnCount * board_width) + board_width).toCharArray();
            for (int rowCount = 0; rowCount < board_width; rowCount++) {
                stringBoardSetup[columnCount][rowCount] = column[rowCount];
                board[columnCount][rowCount] = get_piece(column[rowCount], columnCount, rowCount);
            }
        }
    }

    public void print_board() {
        // Iterate through and print board
        for (int row = 0; row < board_height; row ++) {
            for (int spot = 0; spot < board[row].length; spot++)
                System.out.print(board[row][spot]);
            System.out.print("\n");
        }
    }

    public boolean move_piece(int pieceX, int pieceY, int toX, int toY) {
        // This takes ARRAY coordinates
        boolean can_move = board[pieceX][pieceY].can_move(toX, toY);
        if (!can_move)
            return false;
        board[toX][toY] = board[pieceX][pieceY];
        board[pieceX][pieceY] = new Empty(pieceX, pieceY);
        return true;
    }

    public static int[] array_cords_to_board_cords(int x, int y) {
        // This converts array positions to actual chess positions
        return new int[] { y, board_width - x - 1 };
    }

    private Piece get_piece(char character, int x, int y) {
        // Get piece from char
        // Black is upper case, white is lower case
        char side = (Character.isUpperCase(character)) ? 'b' : 'w';
        switch (Character.toLowerCase(character)) {
            case 'p':
                return new Pawn(x, y, side, board);
        }
        return new Empty(x, y);
    }
}