package Sudoku;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class SudokuAlgoExplained {

	// Initiating logger
	LoggerUtility log = new LoggerUtility(false);

	// this will be the operational matrix
	Cell[][] operationalSudokuMatrix = new Cell[9][9];

	// if greater then 9*9 = 81 , that means cell's candidates are not able to
	// reduced further.
	int deadloackCounter = 0;

	/*
	 * starting 
	 */
	int[][] testmatrix;
	SudokuAlgoExplained (int[][] sudokuMatrix){
		this.testmatrix = sudokuMatrix;
		createOperationalMatrix(testmatrix);
		runAlgo();
	}
	
	// initially need to fill the status of each cell as provided in main
	void createOperationalMatrix(int[][] sudokuMatrix) {

		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				this.operationalSudokuMatrix[row][col] = sudokuMatrix[row][col] > 0
						? new Cell(true, sudokuMatrix[row][col])
						: new Cell(false, 0);
			}

		}

	}

	void runAlgo() {

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
		log.printPossibleCandidates(possibleCandiates);
		// 1. with row candidates
		possibleCandiates = scanRow(matrix, row, possibleCandiates);

		// 2. scan column
		possibleCandiates = scanColumn(matrix, col, possibleCandiates);

		// 3. scan Cube
		possibleCandiates = scanCube(row - row % 3, col - col % 3, possibleCandiates, matrix);

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
		log.printPossibleCandidates(candidates);
		return candidates;

	}

	LinkedList<Integer> scanColumn(Cell[][] matrix, int col, LinkedList<Integer> candidates) {

		for (int row = 0; row < 9; row++) {
			if (matrix[row][col].status) {
				candidates.remove((Integer) matrix[row][col].cellValue);
			}

		}
		log.sleepAndShowStatus("<<column scan done >>", "", 11, col);
		log.printPossibleCandidates(candidates);
		return candidates;

	}

	LinkedList<Integer> scanCube(int boxStartRow, int boxStartCol, LinkedList<Integer> candidates, Cell[][] matrix) {
		for (int row = 0; row < 3; row++)
			for (int col = 0; col < 3; col++)
				candidates.remove((Integer) matrix[row + boxStartRow][col + boxStartCol].cellValue);

		return candidates;
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
				// checkUniquenessInBlock(matrix, row, col);
				scanCubeForUniqeElement(matrix, row - row % 3, col - col % 3, matrix[row][col]);
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
		try {
			matrix[row][col].cellValue = matrix[row][col].possibleCandidtes.getFirst();
			matrix[row][col].isUniqueCandidateAvailable = true;
			log.sleepAndShowStatus("<<only one uniqe element present for this cell >>", "" + matrix[row][col].cellValue,
					row, col);
			matrix[row][col].status = true;
		} catch (NoSuchElementException e) {
			
			/*
			 * skip  -- is not working , we need to reset in case of exception 
			 */
		System.out.println("==== you are running difficult sudoku =====");
			
		}

	}

	void scanCubeForUniqeElement(Cell[][] matrix, int boxStartRow, int boxStartCol, Cell blockElement) {
		for (int row = 0; row < 3; row++)
			for (int col = 0; col < 3; col++) {
				if (!matrix[row + boxStartRow][col + boxStartCol].status) {

					// for concurrent modification exception handling , creating a new list type
					for (int pe : new LinkedList<Integer>(
							matrix[row + boxStartRow][col + boxStartCol].possibleCandidtes)) {
						blockElement.possibleCandidtes.remove((Integer) pe);
					}
				}
			}
	}

}
