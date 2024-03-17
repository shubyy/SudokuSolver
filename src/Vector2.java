public class Vector2 {
    public int x;
    public int y;

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2 another) {
        this.x = another.x;
        this.y = another.y;
    }
}
