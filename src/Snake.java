import java.util.LinkedList;
import edu.macalester.graphics.*;
import java.awt.Color;

public class Snake {

    private LinkedList<Segment> body; 
    private int dx = 1;
    private int dy = 0;
    private boolean growing = false;
    private Color nextSegmentColor;


    public Snake(int row, int col) {
        body = new LinkedList<>();
        body.add(new Segment(new Position(row,col), Color.GREEN));

    }

    public Segment getHead() {
        return body.getFirst();
    }

    public Position getHeadPos() {
        return body.getFirst().pos;
    }

    public LinkedList<Segment> getBody() {
        return body;
    }

    public void move(){

        Segment head = getHead();
        Position newHeadPos = new Position(head.pos.row + dy, head.pos.col + dx);

        if (growing) {
            Segment newHead = new Segment(newHeadPos, nextSegmentColor);
            body.addFirst(newHead);
            growing = false;
        } else {
            Segment tail = body.removeLast();
            tail.pos = newHeadPos;
            body.addFirst(tail);
        }


    }

    public void grow(Color foodColor) {
        growing = true;
        nextSegmentColor = foodColor;
    }

    public boolean checkCollision() {
        Position head = getHeadPos();
        for (int i=1; i < body.size(); i++) {
            if (head.equals(body.get(i).pos)){
                return true;
            }
        }
        return false;

    }

    public void setVelocity(int newDX, int newDY) {
        if (dx == -newDX && dy == -newDY) {
            return;
        }

        dx = newDX;
        dy = newDY;
        
    }

    public boolean ateFood(Position foodPos, Color foodColor) {
        if (getHeadPos().equals(foodPos)) {
            grow(foodColor);
            return true;
        }
        return false;
    }

}
