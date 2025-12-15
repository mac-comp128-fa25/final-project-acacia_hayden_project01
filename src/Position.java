/**
 * Represents a position on the game grid using row and column coordinates.
 *
 * <p>This class is used to track the location of snake segments, food, and
 * other elements on the grid. It provides proper {@code equals} and
 * {@code hashCode} implementations for comparison and usage in collections.</p>
 */
public class Position {
    public int row;
    public int col;

    /**
     * Constructs a new Position with the specified row and column.
     *
     * @param row the row index
     * @param col the column index
     */
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

     /**
     * Determines whether this position is equal to another object.
     *
     * <p>Two positions are considered equal if they have the same row and column.</p>
     *
     * @param o the object to compare with
     * @return {@code true} if the object is a Position with the same coordinates; {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position p = (Position) o;
        return row == p.row && col == p.col;
    }

     /**
     * Returns a hash code for this position.
     *
     * <p>Consistent with {@code equals}, used for collections such as HashMap or HashSet.</p>
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return 31 * row + col;
    }
}
