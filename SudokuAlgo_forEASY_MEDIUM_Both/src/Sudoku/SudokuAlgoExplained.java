package Sudoku;

import java.util.LinkedList;

public class SudokuAlgoExplained {

	// Initiating logger
	LoggerUtility log = new LoggerUtility(false);

	// this will be the operational matrix
	Cell[][] operationalSudokuMatrix = new Cell[9][9];

	// if greater then 9*9 = 81 , that means cell's candidates are not able to
	// reduced further.
	int deadloackCounter = 0;

	// initially need to fill the status of each cell as provided in main
	public void createOperationalMatrix(int[][] sudokuMatrix) {

		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				this.operationalSudokuMatrix[row][col] = sudokuMatrix[row][col] > 0
						? new Cell(true, sudokuMatrix[row][col])
						: new Cell(false, 0);
			}

		}

	}

	public void runAlgo() {

		// it will run till the time any of cell in the sudoku matrix have zero value
		while (!isSudokuCompleated()) {

			log.printGridB(this.operationalSudokuMatrix);

			// Assign possible candidates to each cell - by scanning row , col and cubes
			// this will run for compleate cube having status = false
			fillPossibleCandidate(this.operationalSudokuMatrix);

			// reduce possible candidates by comparing with the peers (3*3 matrix) only
			fillUniqueCandidateAndFillCell(this.operationalSudokuMatrix);

		}
		// FINAL RESULT -- if none of the cell will be having zero value
		System.out.println("*******************FINAL RESULT ************************");
		log.printGridB(this.operationalSudokuMatrix);

	}

	// ==========================MAIN LOGIC
	// ==================================================

	public boolean isSudokuCompleated() {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				if (!this.operationalSudokuMatrix[row][col].status) {
					log.sleepAndShowStatus("<<Returning false  >>", "This cell is not filled ---", row, col);
					return false;
				}
			}

		}
		return true;

	}

	private void fillPossibleCandidate(Cell[][] matrix) {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {

				// if status is not true means cell have not filled yet
				if (!matrix[row][col].status) {
					log.sleepAndShowStatus("<<Filling possible candidate for cell >>", "", row, col);
					// then first find the possible candidates of the cell applying 3 rule .
					// check row , check column and check cube
					getPossibleCandidates(matrix, row, col); // for a particular cell

				}

			}

		}
	}

	public void getPossibleCandidates(Cell[][] matrix, int row, int col) {

		// check in row from 1 to 9
		// check in column from 1 to 9
		// check in cube from 1 to 9
		// assign final values as possible candidates to an array
		// change the status of ispossibleCandidateAvaialble

		// creating a possible candidate array and initially making all of its value as
		// 0
		LinkedList<Integer> possibleCandiates = new LinkedList<Integer>();
		for (int i = 1; i < 10; i++) {
			possibleCandiates.add(i);
		}
		printPossibleCandidates(possibleCandiates);
		// 1. with row candidates
		possibleCandiates = scanRow(matrix, row, possibleCandiates);

		// 2. scan column
		possibleCandiates = scanColumn(matrix, col, possibleCandiates);

		// 3. scan Cube
		possibleCandiates = scanCube(matrix, col, row, possibleCandiates);

		// 4. assign to cell's possible candidate
		matrix[row][col].isPossibleCandidatesAreAvailable = true;
		matrix[row][col].possibleCandidtes = possibleCandiates;
		log.sleepAndShowStatus("<<assigning finally to the cell >>", "", row, col);

	}

	// ============================= POSSIBLE CANDIDATES UTELITIES
	// ======================================================
	// ===================================================================================================================

	LinkedList<Integer> scanRow(Cell[][] matrix, int row, LinkedList<Integer> candidates) {

		for (int col = 0; col < 9; col++) {
			if (matrix[row][col].status) {
				candidates.remove((Integer) matrix[row][col].cellValue);
			}
		}
		log.sleepAndShowStatus("<<row scan done >>", "", row, 11);
		printPossibleCandidates(candidates);
		return candidates;

	}

	LinkedList<Integer> scanColumn(Cell[][] matrix, int col, LinkedList<Integer> candidates) {

		for (int row = 0; row < 9; row++) {
			if (matrix[row][col].status) {
				candidates.remove((Integer) matrix[row][col].cellValue);
			}

		}
		log.sleepAndShowStatus("<<column scan done >>", "", 11, col);
		printPossibleCandidates(candidates);
		return candidates;

	}

	LinkedList<Integer> scanCube(Cell[][] matrix, int col, int row, LinkedList<Integer> candidates) {

		// decision for understanding a grid is very dificult

		// there are 9 cube in a normal sudoku
		// if row < = 2 then that means top 3 cubes
		// if 3 >= row <= 5 then in middle 3 cubes
		// if 6 >= row <= 9 then in the bottom cubes .

		// condition for top 3 cubes
		if (row >= 0 && row <= 2) {

			if (col >= 0 && col <= 2) {
				// cube 1 of top
				scanDecidedCube(0, 2, 0, 2, candidates, matrix);

			}
			if (col >= 3 && col <= 5) {
				// cube 2 of top
				scanDecidedCube(0, 2, 3, 5, candidates, matrix);
			}
			if (col >= 6 && col <= 8) {

				// cube 3 of top
				scanDecidedCube(0, 2, 6, 8, candidates, matrix);
			}
		}

		// condition for middle 3 cubes
		if (row >= 3 && row <= 5) {

			if (col >= 0 && col <= 2) {

				// cube 1 of Middle
				scanDecidedCube(3, 5, 0, 2, candidates, matrix);

			}
			if (col >= 3 && col <= 5) {
				// cube 2 of Middle
				scanDecidedCube(3, 5, 3, 5, candidates, matrix);
			}
			if (col >= 6 && col <= 8) {

				// cube 3 of Middle
				scanDecidedCube(3, 5, 6, 8, candidates, matrix);
			}
		}

		// condition for bottom 3 cubes
		if (row >= 6 && row <= 8) {

			if (col >= 0 && col <= 2) {

				// cube 1 of bottom
				scanDecidedCube(6, 8, 0, 2, candidates, matrix);

			}
			if (col >= 3 && col <= 5) {

				// cube 2 of bottom
				scanDecidedCube(6, 8, 3, 5, candidates, matrix);
			}
			if (col >= 6 && col <= 8) {

				// cube 3 of bottom
				scanDecidedCube(6, 8, 6, 8, candidates, matrix);
			}
		}
		log.sleepAndShowStatus("<<scan cube is done >>", "", row, col);
		printPossibleCandidates(candidates);
		return candidates;

	}

	void scanDecidedCube(int rowMin, int rowMax, int colMin, int colMax, LinkedList<Integer> candidates,
			Cell[][] matrix) {

		// todo: this can be improve further as row and col are already scaned so we are
		// just left with corneres or left overs which will be near to 4 cell only out
		// of 9
		for (int r = rowMin; r <= rowMax; r++) {
			for (int c = colMin; c <= colMax; c++) {

				candidates.remove((Integer) matrix[r][c].cellValue);

			}
		}
	}

	// ===================================================UNIQENESS UTILITIES
	// ==================================================
	// =========================================================================================================================

	private void fillUniqueCandidateAndFillCell(Cell[][] matrix) {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {

				// if status is not true means cell have not filled yet
				if (!matrix[row][col].status && matrix[row][col].isPossibleCandidatesAreAvailable) {
					log.sleepAndShowStatus("<<oFilling unique candidate for cell >>", "" + matrix[row][col].cellValue,
							row, col);
					// once we have possible candidate for all , then need to compare them with
					// other possible candidates
					getUniqueCandidate(matrix, row, col);

				}

			}

		}
	}

	public void getUniqueCandidate(Cell[][] matrix, int row, int col) {

		// check one by one that possible candidates with same cube's cell's possible
		// candidates
		// if any of the candidate is unique then that is the value of cell
		// assign to cell value and make status of cell as true and
		// isUniqueCandiateAvailable = true

		// 1. condition - if list size is 1 , thats mean only one probability , which
		// means thats should be the uniqe element
		if (matrix[row][col].possibleCandidtes.size() == 1) {
			matrix[row][col].isUniqueCandidateAvailable = true;

			matrix[row][col].cellValue = matrix[row][col].possibleCandidtes.getFirst();
			log.sleepAndShowStatus("<<only one uniqe element present for this cell >>", "" + matrix[row][col].cellValue,
					row, col);
			matrix[row][col].status = true;

			// if a single cell is reducedd than we have to scan again a compleate cube for
			// reducing candidates which will maximum reach to 81.
			deadloackCounter = 0;
		} else {

			if (deadloackCounter < 81) {
				deadloackCounter++;
				// 2. while checking unique element , if element found in other cell's
				// candidates list then remove from this cell
				checkUniquenessInBlock(matrix, row, col);
				// getUniqueCandidate(matrix, row, col);
			} else {
				/*
				 * this else with deadloack is only for medium and hard sudoku , where there
				 * might be a situation that each cell will carry more than 2 candidate . so we
				 * have to assign any one no and then try to move ahead.
				 */
				System.out.println("dedloack counter size is ===" + deadloackCounter
						+ " == element uniqe candidates are == " + matrix[row][col].possibleCandidtes.size());
				reduceOneCandidate(matrix, row, col);
				deadloackCounter = 0;
			}
		}

	}

	void reduceOneCandidate(Cell[][] matrix, int row, int col) {

		matrix[row][col].cellValue = matrix[row][col].possibleCandidtes.getFirst();
		matrix[row][col].isUniqueCandidateAvailable = true;
		log.sleepAndShowStatus("<<only one uniqe element present for this cell >>", "" + matrix[row][col].cellValue,
				row, col);
		matrix[row][col].status = true;

	}

	void checkUniquenessInBlock(Cell[][] matrix, int row, int col) {

		// condition for top 3 cubes
		if (row >= 0 && row <= 2) {

			if (col >= 0 && col <= 2) {

				// Block 1 - top
				scanCubeForUniqeElement(0, 2, 0, 2, matrix, matrix[row][col]);

			}
			if (col >= 3 && col <= 5) {

				// block 2 - top
				scanCubeForUniqeElement(0, 2, 3, 5, matrix, matrix[row][col]);
			}
			if (col >= 6 && col <= 8) {

				// Block 3 - top
				scanCubeForUniqeElement(0, 2, 6, 8, matrix, matrix[row][col]);
			}
		}

		// condition for middle 3 cubes
		if (row >= 3 && row <= 5) {

			if (col >= 0 && col <= 2) {

				scanCubeForUniqeElement(3, 5, 0, 2, matrix, matrix[row][col]);

			}
			if (col >= 3 && col <= 5) {

				scanCubeForUniqeElement(3, 5, 3, 5, matrix, matrix[row][col]);
			}
			if (col >= 6 && col <= 8) {

				scanCubeForUniqeElement(3, 5, 6, 8, matrix, matrix[row][col]);
			}
		}

		// condition for bottom 3 cubes
		if (row >= 6 && row <= 8) {

			if (col >= 0 && col <= 2) {

				scanCubeForUniqeElement(6, 8, 0, 2, matrix, matrix[row][col]);

			}
			if (col >= 3 && col <= 5) {

				scanCubeForUniqeElement(6, 8, 3, 5, matrix, matrix[row][col]);
			}
			if (col >= 6 && col <= 8) {

				scanCubeForUniqeElement(6, 8, 6, 8, matrix, matrix[row][col]);
			}
		}
	}

	void scanCubeForUniqeElement(int rowMin, int rowMax, int colMin, int colMax, Cell[][] matrix, Cell blockElement) {
		for (int r = rowMin; r <= rowMax; r++) {
			for (int c = colMin; c <= colMax; c++) {

				// catch one block cell r-c location and fetch first all of its elements check
				// with target element list , if exist then remove from target
				if (!matrix[r][c].status) {

					// for concurrent modification exception handling , creating a new list type
					for (int pe : new LinkedList<Integer>(matrix[r][c].possibleCandidtes)) {
						blockElement.possibleCandidtes.remove((Integer) pe);
					}
				}
			}
		}
	}

	// ========================================= EXTRA UTELITIES
	// =======================================
	// =================================================================================================

	void printPossibleCandidates(LinkedList<Integer> list) {
		// for (int element : list) {
		// System.out.print(element);
		// }
		// System.out.println("*****************************");

	}

}
