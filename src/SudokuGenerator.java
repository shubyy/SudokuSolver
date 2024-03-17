import java.util.*;

public class SudokuGenerator {

    private static boolean SpawnRandomNumbers(SudokuBoard board) {
        Random r = new Random();
        for (int i = 0; i < 35;) {
            Vector2 pos = new Vector2(r.nextInt(9), r.nextInt(9));
            ArrayList<Byte> values = board.GetALlPossibleValuesForCell(pos);
            if(values.isEmpty()) {
                return false;
            }

            if (board.GetCell(pos) == 0 && !values.isEmpty()) {
                int value = values.get(r.nextInt(values.size()));
                board.SetCell(pos, value);
                i++;
            }
        }

        return true;
    }

    public static SudokuBoard Generate() {
        SudokuBoard board = new SudokuBoard();
        SudokuBoard solvedBoard = null;
        do {
            System.out.println("Generating new board");
            while(!SpawnRandomNumbers(board)) {
                board.Reset();
                System.out.println("Failed to spawn random numbers, trying again");
            }
            solvedBoard = new SudokuSolver(board).StartSolve();
        } while(solvedBoard == null);

        return solvedBoard;
    }
}
