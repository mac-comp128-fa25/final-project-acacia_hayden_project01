import java.util.LinkedList;


public class Snake {
    private LinkedList<Position> body; 
    public Snake(int row, int col) {
        body = new LinkedList<>();
        body.add(new Position(row, col));

    }

    public Position getHead() {
        return body.getFirst();
    }

    public LinkedList<Position> getBody() {
        return body;
    }

}