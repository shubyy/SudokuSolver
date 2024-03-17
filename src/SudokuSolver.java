import java.util.ArrayList;

public class SudokuSolver {

    SudokuBoard board;
    Thread solverThread;

    SudokuBoard solvedBoard;
    public boolean solved = false;

    public SudokuSolver(SudokuBoard board) {
        this.board = board;
        solvedBoard = null;
    }

    public SudokuBoard StartSolve() {
        if (!board.IsFull()) {

            //Fill in solutions with only 1 possible value
            boolean initialFilled = true;
            while(initialFilled){
                initialFilled = false;
                for(int i = 0; i < 9; i++) {
                    for(int j = 0; j < 9; j++) {
                        Vector2 pos = new Vector2(j, i);
                        if(board.GetCell(pos) == 0) {
                            ArrayList<Byte> possibleValues = board.GetALlPossibleValuesForCell(pos);
                            if(possibleValues.size() == 1) {
                                board.SetCell(pos, possibleValues.getFirst());
                                initialFilled = true;
                            }
                        }
                    }
                }
            }

            SudokuSolverThread sThread = new SudokuSolverThread(null,this, board);
            solverThread = new Thread(sThread);
            solverThread.start();
            try {
                solverThread.join();
            } catch (InterruptedException e) {
                return solvedBoard;
            }
        }

        return solvedBoard;
    }

    public synchronized void SetSolution(SudokuBoard solution)
    {
        solvedBoard = new SudokuBoard(solution);
        solved = true;

    }

}
