package Engine.Pieces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class King extends Piece {
    public static final String photo_name = "King.png";
    public King(int x, int y, char side, Piece[][] board) {
        /*When reading an array, x is the first one. Example: board[x][y] (the roles are switched)*/
        super(x, y, side, board);
        this.piece = 'k';
    }

    @Override
    public List<int[]> get_moves() {
        List<int[]> spots = new ArrayList<>();
        List<int[]> illegal_spots = new ArrayList<>();
        for (Piece[] pieces : board)
            for (int j = 0; j < pieces.length; j++)
                if (pieces[j].side == side)
                    continue;
                else if (pieces[j] instanceof King)
                    illegal_spots.addAll(((King) pieces[j]).king_moves());
                else if (!(pieces[j] instanceof Empty))
                    illegal_spots.addAll(pieces[j].get_moves());

        for (int x_add = -1; x_add <= 1; x_add++)
            for (int y_add = -1; y_add <= 1; y_add++)
                if (x + x_add >= 0 && x + x_add < board.length && y + y_add >= 0 && y + y_add < board[y].length &&
                        board[x + x_add][y + y_add].side != side && !(board[x + x_add][y + y_add] instanceof King) &&
                        !(contains_illegal_move(illegal_spots, new int[] {x + x_add, y + y_add})))
                    spots.add(new int[]{x + x_add, y + y_add});

        return spots;
    }

    public List<int[]> king_moves() {
        List<int[]> spots = new ArrayList<>();
        for (int x_add = -1; x_add <= 1; x_add++)
            for (int y_add = -1; y_add <= 1; y_add++)
                if (x + x_add >= 0 && x + x_add < board.length && y + y_add >= 0 && y + y_add < board[y].length &&
                        board[x + x_add][y + y_add].side != side && !(board[x + x_add][y + y_add] instanceof King))
                    spots.add(new int[]{x + x_add, y + y_add});

        return spots;
    }

    public static boolean contains_illegal_move(List<int[]> illegal_spots, int[] position) {
        if (position.length > 2)
            return false;

        for (int[] spot : illegal_spots)
            if (spot[0] == position[0] && spot[1] == position[1])
                return true;

        return false;
    }

    @Override
    public String get_icon() {
        return photo_name;
    }

    @Override
    public void on_move(int to_x, int to_y) { }
}
