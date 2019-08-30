package others;

public class Sudoku {

	public Sudoku() {
		// TODO Auto-generated constructor stub
	}

	public void solveSudoku(int[][] board, int x, int y) {
		//System.out.println("solveSudoku ["+ x +"]["+y+"]");
        if (y == 9) {
            if (x == 8) {
                printSudoku(board);
                return;
            } else {
                solveSudoku(board, x + 1, 0);  
            }
        } else if (board[x][y] == 0) {
            for (int val = 1; val <= 9; val++) {
                boolean isValid = checkValidity(board, x, y, val);
                if (isValid) {
                    board[x][y] = val;
                    solveSudoku(board, x, y + 1);
                    board[x][y] = 0;
                }
            }
        } else {
            solveSudoku(board, x, y + 1);
        }
    }
    
    private boolean checkValidity(int[][] board, int x, int y, int val) {
       for (int i=0; i<9; i++) {
            if (board[i][y] == val) {
                return false;
            } 
        }
        
        for (int j=0; j<9; j++) {
            if (board[x][j] == val) {
                return false;
            } 
        }
        
        int a = x/3; 
        int b = y/3;
        for (int i=3*a; i<3*a+3; i++) {
            for (int j=3*b; j<3*b+3; j++) {
                if (board[i][j] == val)
                    return false;
            }
        }
        
        return true;
    }
    
    public void printSudoku(int[][] board) 
    { 
        for (int i = 0; i<9; i++) 
        { 
            for (int j = 0; j<9; j++) 
                System.out.print(board[i][j] + " "); 
            System.out.println(); 
        } 
        System.out.println(); 
    }
    
    // Driver code 
    public static void main(String[] args) 
    { 
        int[][] board = {
            { 5, 3, 0, 0, 7, 0, 0, 0, 0 },
            { 6, 0, 0, 1, 9, 5, 0, 0, 0 },
            { 0, 9, 8, 0, 0, 0, 0, 6, 0 },
            { 8, 0, 0, 0, 6, 0, 0, 0, 3 },
            { 4, 0, 0, 8, 0, 3, 0, 0, 1 },
            { 7, 0, 0, 0, 2, 0, 0, 0, 6 },
            { 0, 6, 0, 0, 0, 0, 2, 8, 0 },
            { 0, 0, 0, 4, 1, 9, 0, 0, 5 },
            { 0, 0, 0, 0, 8, 0, 0, 7, 9 },
        };
        
        Sudoku sudoku = new Sudoku(); 
        sudoku.solveSudoku(board, 0, 0);
        //sudoku.printSudoku(board);
        int a = 5;
    } 
}
