import java.util.LinkedList;


public class Snake {

    private LinkedList<Position> body; 
    private int dx = 1;
    private int dy = 0;
    private boolean growing = false;


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

    public void move(){
        Position head = getHead();
        Position newHead = new Position(head.row + dy, head.col + dx);

        body.addFirst(newHead);

        if(!growing){
            body.removeLast();
        } else {
            growing = false;
        }

    }

    public void grow() {
        growing = true;
    }

    public boolean checkCollision() {
        return false;

    }

    public void setVelocity(int newDX, int newDY) {
        if (dx == -newDX && dy == -newDY) {
            return;
        }

        dx = newDX;
        dy = newDY;
        
    }

}