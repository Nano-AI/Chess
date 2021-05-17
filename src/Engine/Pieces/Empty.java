package Engine.Pieces;

import java.util.List;

public class Empty extends Piece {
    public Empty(int x, int y) {
        super(x, y);
        this.piece = '.';
    }

    @Override
    public List<int[]> get_moves(boolean... king_check) throws CloneNotSupportedException {
        return null;
    }

    @Override
    public String get_icon() {
        return "";
    }

    @Override
    public void on_move(int to_x, int to_y) { }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Empty(x, y);
    }

    public boolean can_move(int x, int y) {
        return false;
    }
}
