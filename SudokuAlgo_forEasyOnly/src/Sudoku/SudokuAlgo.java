package Sudoku;

public class SudokuAlgo {

	/* Driver Program to test above functions */
	public static void main(String[] args) {
		// assignment only
		 SudokuAlgoExplained sudoku = new SudokuAlgoExplained();
		// AnotherWaySudoku sudoku = new AnotherWaySudoku();
		//GeekForGeekSolution gfgSolution = new GeekForGeekSolution();

		LoggerUtility log = new LoggerUtility(true);

		System.out.println(
				"###### Step 1 Creating actual sudoku ... in production , this might be filled from an applet  ######");

		// This is DIFFICULT one
		int[][] grid = { { 3, 0, 6, 5, 0, 8, 4, 0, 0 }, 
				{ 5, 2, 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 8, 7, 0, 0, 0, 0, 3, 1 },
				{ 0, 0, 3, 0, 1, 0, 0, 8, 0 },
				{ 9, 0, 0, 8, 6, 3, 0, 0, 5 },
				{ 0, 5, 0, 0, 9, 0, 6, 0, 0 },
				{ 1, 3, 0, 0, 0, 0, 2, 5, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 7, 4 }, 
				{ 0, 0, 5, 2, 0, 6, 3, 0, 0 } };

		// This is EASY one
		// int[][] grid = {{8, 0, 6, 3, 0, 4, 0, 0, 1},
		// {0, 0, 0, 6, 0, 0, 3, 0, 0},
		// {0, 0, 1, 9, 7, 0, 0, 0, 6},
		// {1, 0, 2, 0, 4, 0, 5, 6, 0},
		// {3, 0, 0, 0, 0, 0, 0, 0, 4},
		// {0, 4, 9, 0, 5, 0, 8, 0, 3},
		// {6, 0, 0, 0, 3, 9, 4, 0, 0},
		// {0, 0, 4, 0, 0, 8, 0, 0, 0},
		// {2, 0, 0, 4, 0, 7, 9, 0, 8}};

		// // Toughest sudoku
		// int[][] grid = {{8, 0, 0, 0, 0, 0, 0, 0, 0},
		// {0, 0, 3, 6, 0, 0, 0, 0, 0},
		// {0, 7, 0, 0, 9, 0, 2, 0, 0},
		// {0, 5, 0, 0, 0, 7, 0, 0, 0},
		// {0, 0, 0, 0, 4, 5, 7, 0, 0},
		// {0, 0, 0, 1, 0, 0, 0, 3, 0},
		// {0, 0, 1, 0, 0, 0, 0, 6, 8},
		// {0, 0, 8, 5, 0, 0, 0, 1, 0},
		// {0, 9, 0, 0, 0, 0, 4, 0, 0}};
		//
		System.out.println(
				"###### Step 2 -- create matrix with cell objects having values from grid . So that we can apply algorithm logics ######");
		 sudoku.createOperationalMatrix(grid);

		System.out.println("###### Step 3. Running the Algo  to fill the operational matrix  ######");
		 sudoku.runAlgo();

		// if (gfgSolution.SolveSudoku(grid) == true)
		// printGrid(grid);
		// else
		// System.out.println("No solution exists");
		//
	}

	/* A utility function to print grid */
	static void printGrid(int[][] grid) {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++)
				System.out.println(grid[row][col]);
			System.out.println("\n");
		}
	}

}
