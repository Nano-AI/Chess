package Engine.Pieces;

import Engine.Board;
import sun.security.util.ArrayUtil;

import java.util.*;

public class King extends Piece {
    public static final String photo_name = "King.png";
    public King(int x, int y, char side, Piece[][] board, Board reference) {
        /*When reading an array, x is the first one. Example: board[x][y] (the roles are switched)*/
        super(x, y, side, board, reference);
        this.piece = 'k';
    }

    @Override
    public List<int[]> get_moves(boolean... king_check) throws CloneNotSupportedException {
        List<int[]> spots = new ArrayList<>();
        List<int[]> illegal_spots = new ArrayList<>();
        if (king_check.length != 0 && king_check[0]) {
            illegal_spots = get_illegal_spots();
        }

        for (int x_add = -1; x_add <= 1; x_add++)
            for (int y_add = -1; y_add <= 1; y_add++)
                if (x + x_add >= 0 && x + x_add < board.length && y + y_add >= 0 && y + y_add < board[y].length &&
                        board[x + x_add][y + y_add].side != side /*&& !(board[x + x_add][y + y_add] instanceof King)*/ &&
                        (king_check.length == 0 || !(contains_illegal_move(illegal_spots, new int[]{x + x_add, y + y_add})))
                )
                    spots.add(new int[]{x + x_add, y + y_add});

        return spots;
    }

    public List<int[]> king_moves() {
        List<int[]> spots = new ArrayList<>();
        for (int x_add = -1; x_add <= 1; x_add++)
            for (int y_add = -1; y_add <= 1; y_add++)
                if (x + x_add >= 0 && x + x_add < board.length && y + y_add >= 0 && y + y_add < board[y].length &&
                        board[x + x_add][y + y_add].side != side /*&& !(board[x + x_add][y + y_add] instanceof King)*/)
                    spots.add(new int[]{x + x_add, y + y_add});

        return spots;
    }

    public boolean contains_illegal_move(List<int[]> illegal_spots, int[] position) {
        if (position.length > 2)
            return false;

        for (int[] spot : illegal_spots) {
//            System.out.printf("(%d, %d) ?= (%d, %d)\n", spot[1], spot[0], position[0], position[1]);
            if (spot[0] == position[0] && spot[1] == position[1])
                return true;
        }

        return false;
    }

    public boolean in_check() throws CloneNotSupportedException {
//        for (int[] item : get_illegal_spots()) {
//            System.out.printf("%d == %d && %d == %d\n", item[0], x, item[1], y);
//            if (item[1] == x && item[0] == y)
//                return true;
//        }
        return false;
//        return contains_illegal_move(get_illegal_spots(), new int[]{x, y});
    }

    public List<int[]> get_illegal_spots() throws CloneNotSupportedException {
        List<int[]> illegal_spots = new ArrayList<>();
//        System.out.println("----" + side + "----");
        for (Piece[] pieces : board)
            for (int j = 0; j < pieces.length; j++) {
                int finalJ = j;
                if (pieces[j].side == side || pieces[j] instanceof Empty)
                    continue;
//                else if (pieces[j] instanceof King) {
//                    illegal_spots.addAll(((King) pieces[j]).king_moves());
//                    ((King) pieces[j]).king_moves().forEach((int[] item) -> System.out.println(pieces[finalJ].piece + pieces[finalJ].side + " " + Arrays.toString(item)));
////                    System.out.println(pieces[j].piece + " " + );
//                }
                else if (pieces[j] instanceof Pawn) {
                    illegal_spots.addAll(((Pawn) pieces[j]).get_king_illegal_moves());
                    ((Pawn) pieces[j]).get_king_illegal_moves().forEach((int[] item) -> System.out.println(pieces[finalJ].piece + "" + pieces[finalJ].side + " " + Arrays.toString(item)));
//                    System.out.println(pieces[j].piece + " " + ((Pawn) pieces[j]).get_king_illegal_moves());
                }
                else {
                    illegal_spots.addAll(pieces[j].get_moves());
                    pieces[j].get_moves().forEach((int[] item) -> System.out.println(pieces[finalJ].piece + "" + pieces[finalJ].side + " " + Arrays.toString(item)));
//                    System.out.println(pieces[j].piece + " " + pieces[j].get_moves());
                }
            }
        Set<int[]> set = new HashSet<>(illegal_spots);
        illegal_spots.clear();
        illegal_spots.addAll(set);
        return illegal_spots;
    }

    public boolean check_illegal_move(int pieceX, int pieceY, int toX, int toY) throws CloneNotSupportedException {
        Piece[][] copy = duplicate_array(board);
        copy[toX][toY] = copy[pieceX][pieceY];
        copy[pieceX][pieceY] = new Empty(pieceX, pieceY);
        copy[toX][toY].x = toX;
        copy[toX][toY].y = toY;
        copy[toX][toY].on_move(toX, toY);
        // Iterate through and print board
        System.out.println(((King) copy[x][y]).in_check());
        for (int row = 0; row < copy.length; row++) {
            for (int spot = 0; spot < copy[row].length; spot++)
                System.out.print(copy[row][spot]);
            System.out.print("\n");
        }
        return ((King) copy[x][y]).in_check();
    }

    @Override
    public String get_icon() {
        return photo_name;
    }

    @Override
    public void on_move(int to_x, int to_y) { }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new King(x, y, side, board, reference);
    }

    public static Piece[][] duplicate_array(Piece[][] matrix) throws CloneNotSupportedException {
        Piece[][] dupe = new Piece[matrix.length][];
        for(int i = 0; i < matrix.length; i++) {
            dupe[i] = new Piece[matrix[i].length];
            for (int j = 0; j < matrix[i].length; j++)
                dupe[i][j] = (Piece) matrix[i][j].clone();
        }
        return dupe;
    }
}
