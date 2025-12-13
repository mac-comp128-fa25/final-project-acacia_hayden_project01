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
        Segment oldTail = body.getLast();
        Position oldTailPos = new Position(oldTail.pos.row, oldTail.pos.col);

        Segment head = getHead();
        Position newHeadPos = new Position(head.pos.row + dy, head.pos.col + dx);

   
        for (int i = body.size() - 1; i >= 1; i--) {
            body.get(i).pos = body.get(i - 1).pos;
        }

        head.pos = newHeadPos;


        if (growing) {
            body.addLast(new Segment(oldTailPos, nextSegmentColor));
            growing = false;
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
}
