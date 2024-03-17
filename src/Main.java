public class Main {

    public static void SetInitialBoard(SudokuBoard board) {
        //Set values for board
        board.SetCell(new Vector2(0, 0), 9);
        board.SetCell(new Vector2(1, 0), 2);
        board.SetCell(new Vector2(8, 0), 5);
        board.SetCell(new Vector2(2, 1), 3);
        board.SetCell(new Vector2(4, 1), 7);
        board.SetCell(new Vector2(5, 1), 8);
        board.SetCell(new Vector2(6, 1), 4);
        board.SetCell(new Vector2(3, 2), 6);
        board.SetCell(new Vector2(3, 3), 1);
        board.SetCell(new Vector2(6, 3), 9);
        board.SetCell(new Vector2(2, 4), 6);
        board.SetCell(new Vector2(3, 4), 4);
        board.SetCell(new Vector2(1, 5), 3);
        board.SetCell(new Vector2(4, 5), 9);
        board.SetCell(new Vector2(5, 5), 6);
        board.SetCell(new Vector2(7, 5), 2);
        board.SetCell(new Vector2(0, 6), 7);
        board.SetCell(new Vector2(7, 6), 4);
        board.SetCell(new Vector2(2, 7), 8);
        board.SetCell(new Vector2(4, 7), 3);
        board.SetCell(new Vector2(5, 7), 9);
        board.SetCell(new Vector2(6, 7), 7);
        board.SetCell(new Vector2(4, 8), 1);
    }

    public static void main(String[] args) {
        //SudokuBoard board = SudokuGenerator.Generate();
        //board.PrintBoard();


        SudokuBoard board = new SudokuBoard();
        //board.SetCell(new Vector2(4, 8), 1);
        SetInitialBoard(board);
        board.PrintBoard();

        SudokuUI ui = new SudokuUI(board);

        //SudokuSolver solver = new SudokuSolver(board);
        //long start = System.nanoTime();
        //SudokuBoard solvedBoard = solver.StartSolve();
        //double timeTaken =  (System.nanoTime() - start) / 1000000000.0;
        //System.out.println("\nSolved Board in: " + timeTaken  + "s");
        //solvedBoard.PrintBoard();
    }
}