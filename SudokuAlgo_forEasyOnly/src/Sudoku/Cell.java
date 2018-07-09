package Sudoku;

import java.util.LinkedList;

public class Cell{
	
	// status of a cell that its filled or not 
	boolean status ;
	
	// if status is false  -- then what are the possible candidates for that cell  -- means need to get possible candidates , if its false 
	boolean isPossibleCandidatesAreAvailable;
	LinkedList<Integer> possibleCandidtes;
	
	// if possible candidates are available  - true  -- then need to find unique candidate 
	boolean isUniqueCandidateAvailable;
	int uniueCandidate;
	
	// if status is true then there should be some value greater then 0 otherwise 0 
	int cellValue;
	
	
/*
 * for medium and difficult sudoku , there are high possibility that none of the left cell will be having single possible candidate.
 * in that case the programm will keep scanning and try to reduce possible candidate but fail.
 * for avoiding that deadloack , its better to assign any one value to possible candidate and move ahead  
 */
	
	public Cell (boolean status , int cellValue)
	{
		this.status = status;
		this.cellValue = cellValue;
		
		this.isPossibleCandidatesAreAvailable = false;
		this.isUniqueCandidateAvailable = false;
		this.uniueCandidate = 0 ; 
	}
	
}