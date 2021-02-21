package Engine;

import Engine.Pieces.*;

import java.util.Arrays;
import java.util.List;

public class Board {
    public static final String board_setup_str = "" +
            "R N B K Q B N R" +
            "P P P P P P P P" +
            ". . . . . . . ." +
            ". . . . . . . ." +
            ". . . . . . . ." +
            ". . . . . . . ." +
            "p p p p p p p p" +
            "r n b k q b n r";

    public static int board_height = 8;
    public static int board_width = 8;
    private boolean white_king = false;
    private boolean black_king = false;

    public Piece[][] board;

    private char turn = 'w';

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

        if (!white_king || !black_king)
            throw new Exception("The board is missing a white and/or black king!");
    }

    public void print_board() {
        // Iterate through and print board
        for (int row = 0; row < board_height; row++) {
            for (int spot = 0; spot < board[row].length; spot++)
                System.out.print(board[row][spot]);
            System.out.print("\n");
        }
    }

    public boolean move_piece(int pieceX, int pieceY, int toX, int toY) throws Exception {
        // This takes ARRAY coordinates
        // Get moves from piece
        if (board[toX][toY] instanceof King)
            return false;
        if (board[pieceX][pieceY].side != turn)
            return false;
        List<int[]> spots = board[pieceX][pieceY].get_moves();
        if (spots == null)
            return false;
        // Check if the given coordinates are part of the given moves/spots
        for (int[] cords : spots) {
            if (cords[0] == toX && cords[1] == toY) {
                board[toX][toY] = board[pieceX][pieceY];
                board[pieceX][pieceY] = new Empty(toX, toY);
                board[toX][toY].x = toX;
                board[toX][toY].y = toY;
                board[toX][toY].on_move(toX, toY);
                turn = (turn == 'b') ? 'w' : 'b';
                return true;
            }
        }
        return false;
    }

    public static int[] array_cords_to_board_cords(int x, int y) {
        // This converts array positions to actual chess positions
        return new int[]{y, board_width - x - 1};
    }

    private Piece get_piece(char character, int x, int y) throws Exception {
        // Get piece from char
        // Black is upper case, white is lower case
        char side = (Character.isUpperCase(character)) ? 'b' : 'w';
        switch (Character.toLowerCase(character)) {
            case 'p':
                return new Pawn(x, y, side, board);
            case 'r':
                return new Rook(x, y, side, board);
            case 'b':
                return new Bishop(x, y, side, board);
            case 'n':
                return new Knight(x, y, side, board);
            case 'q':
                return new Queen(x, y, side, board);
            case 'k':
                if (side == 'b') {
                    if (black_king)
                        throw new Exception("More than 1 black king is trying to be placed on the chess board.");
                    black_king = true;
                }
                if (side == 'w') {
                    if (white_king)
                        throw new Exception("More than 1 white king is trying to be placed on the chess board.");
                    white_king = true;
                }

                return new King(x, y, side, board);
        }
        return new Empty(x, y);
        /*
        R.I.P I can't use this because of my Java version ;-;
        return switch (Character.toLowerCase(character)) {
            case 'p' -> new Pawn(x, y, side, board);
            case 'r' -> new Rook(x, y, side, board);
            default -> new Empty(x, y);
        };
         */
    }

    public char get_turn() {
        return turn;
    }
}
