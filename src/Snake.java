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

        LinkedList<Color> oldBodyColors = new LinkedList<>();
        for (Segment seg : body) {
            oldBodyColors.add(seg.color);
        }

        Segment head = getHead();
        Position newHeadPos = new Position(head.pos.row + dy, head.pos.col + dx);

        Color newHeadColor;
        if (growing) {
            newHeadColor = nextSegmentColor;
        } else {
            newHeadColor = head.color;
        }

        Segment newHead = new Segment(newHeadPos, newHeadColor);
        body.addFirst(newHead);

        for (int i = 1; i < body.size(); i++) {
            body.get(i).color = oldBodyColors.get(i -1);
        }

        if(growing) {
            growing = false;
        } else {
            body.removeLast();
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

    public void render(GraphicsGroup group) {
        group.removeAll();
        for (Segment seg: body) {
            Rectangle segment = new Rectangle(seg.pos.col * 20, seg.pos.row * 20, 20, 20);
            segment.setFillColor(seg.color);
            segment.setStrokeColor(Color.BLACK);
            group.add(segment);
        }
    }

    public boolean ateFood(Position foodPos, Color foodColor) {
        if (getHeadPos().equals(foodPos)) {
            grow(foodColor);
            return true;
        }
        return false;
    }

}
