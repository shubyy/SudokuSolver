import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class SudokuBoard {
    ArrayList<Byte> cells;

    public SudokuBoard() {
        cells = new ArrayList<Byte>(9 * 9);
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                cells.add((byte) 0);
            }
        }
    }

    public void Reset() {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                SetCell(new Vector2(j, i), 0);
            }
        }
    }

    public SudokuBoard(SudokuBoard another) {
        cells = new ArrayList<>(9 * 9);
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {

                cells.add( (byte) another.GetCell(new Vector2(j, i)) );
            }
        }
    }

    public int GetCell(Vector2 pos) {
        return cells.get(pos.y * 9 + pos.x);
    }

    public void SetCell(Vector2 pos, int value) {
        cells.set(pos.y * 9 + pos.x, (byte) value);
    }

    public ArrayList<Byte> GetALlPossibleValuesForCell(Vector2 pos) {
        ArrayList<Byte> possibleValues = new ArrayList<Byte>(9);
        for (byte k = 1; k <= 9; k++) {
            if (CheckValidValue(pos, k)) {
                possibleValues.add(k);
            }
        }

        return possibleValues;
    }

    public boolean CheckValidValue(Vector2 pos, int value) {
        for (int i = 0; i < 9; i++) {
            if(GetCell(new Vector2(pos.x, i)) == value) {
                return false;
            }
            if(GetCell(new Vector2(i, pos.y)) == value) {
                return false;
            }
        }

        int x0 = (pos.x / 3) * 3;
        int y0 = (pos.y / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Vector2 cellPos = new Vector2(x0 + i, y0 + j);
                if (GetCell(cellPos) == value) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean IsSolved() {

        for (int i = 0; i < 9; i++) {
            ArrayList<Integer> rowLeft = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
            ArrayList<Integer> columnLeft = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
            for(int j = 0; j < 9; j++) {
                //rows
                Vector2 rowPos = new Vector2(j, i);
                Vector2 columnPos = new Vector2(i, j);

                int rowValue = GetCell(rowPos);


                if(rowLeft.contains(rowValue)) {
                    rowLeft.remove(rowLeft.indexOf(rowValue));
                } else {
                    return false;
                }

                int columnValue = GetCell(columnPos);
                if(columnLeft.contains(columnValue)) {
                    columnLeft.remove(columnLeft.indexOf(columnValue));
                } else {
                    return false;
                }
            }
        }


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ArrayList<Integer> gridLeft = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
                for(int k = 0; k < 3; k++) {
                    for(int l = 0; l < 3; l++) {
                        Vector2 gridPos = new Vector2(i * 3 + k, j * 3 + l);

                        int gridValue = GetCell(gridPos);
                        if(gridLeft.contains(gridValue)) {
                            gridLeft.remove(gridLeft.indexOf(gridValue));
                        } else {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }


    public boolean IsFull() {
        return !cells.contains((byte) 0);

    }

    public void PrintBoard() {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                System.out.print(GetCell(new Vector2(j, i)) + "  ");
            }
            System.out.println();
        }
    }

}
