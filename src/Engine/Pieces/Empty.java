package Engine.Pieces;

public class Empty extends Piece {
    public Empty(int x, int y) {
        super(x, y);
        this.piece = '.';
    }
    public boolean can_move(int x, int y) {
        return false;
    }
}
