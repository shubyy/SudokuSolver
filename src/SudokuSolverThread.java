import java.lang.Runnable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class SudokuSolverThread implements Runnable {
    SudokuBoard board;
    SudokuSolver solver;
    SudokuSolverThread parent;

    boolean dead;

    public synchronized void SetDead() {
        this.dead = true;
    }

    public synchronized boolean isDead() {
        return dead;
    }

    public SudokuSolverThread(SudokuSolverThread parent, SudokuSolver solver, SudokuBoard board) {
        this.solver = solver;
        this.board = new SudokuBoard(board);
        this.parent = parent;
        dead = false;
    }

    Vector2 FindEmptyCell() {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                Vector2 pos = new Vector2(j, i);
                if (board.GetCell(pos) == 0) {
                    return pos;
                }
            }
        }
        return new Vector2(-1, -1);
    }

    public void TrySolveBoard() {
        if(board.IsFull()) {
            solver.SetSolution(board);
            return;
        }

        Vector2 firstCell = FindEmptyCell();
        if (firstCell.x == -1) {
            return;
        }

        ArrayList<Byte> possibleValues = board.GetALlPossibleValuesForCell(firstCell);
        if (possibleValues.isEmpty() || solver.solved) {
            return;
        }

        ArrayList<Thread> solverThreads = new ArrayList<Thread>(possibleValues.size());
        for (int value : possibleValues) {
            board.SetCell(firstCell, value);
            //Spawn new Thread
            SudokuSolverThread sThread = new SudokuSolverThread(this, solver, board);
            Thread t1 = new Thread(sThread);
            solverThreads.add(t1);
            t1.start();
        }

        try {
            for(Thread t1 : solverThreads) {
                t1.join();
            }
        } catch (InterruptedException e) {
            for(Thread t1 : solverThreads) {
                t1.interrupt();
            }
            return;
        }
        return;

    }

    public void run() {
        TrySolveBoard();
    }
}
