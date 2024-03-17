import java.lang.Math;
public class Cell {
    private byte value;

    public Cell(int value) {
        SetValue(value);
    }

    public void SetValue(int value) {
        this.value = (byte) Math.clamp(value, 0, 9);
    }

    public Cell(Cell another) {
        this.value = another.value;
    }

    public boolean IsEmpty() {
        return value == 0;
    }

    public int Get() {
        return value;
    }
}
