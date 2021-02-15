package Engine.Pieces;

import java.util.List;

public class Empty extends Piece {
    public Empty(int x, int y) {
        super(x, y);
        this.piece = '.';
    }

    @Override
    public List<int[]> get_moves() {
        return null;
    }

    public boolean can_move(int x, int y) {
        return false;
    }
}
