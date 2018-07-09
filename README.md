# SudokuAlgoritham
I am working on this programm to solve any level of sudoku . But right now its workes only for Easy and medium level . 
I tried GeekForGeek solution also 
https://www.geeksforgeeks.org/backtracking-set-7-suduku/
But unfortunately for me that also not working 

# How to RUN this Code 
======================
1. SudokuAlgo.java is the Main file 
2. Here i provided 3 level of sudoku's grid .
3. Uncommit or create any grid to solve  and then run below code line 

      SudokuAlgoExplained sudoku = new SudokuAlgoExplained();
      System.out.println("###### Step 2 -- create matrix with cell objects having values from grid . So that we can apply algorithm logics ######");
      sudoku.createOperationalMatrix(grid);
	   
	    System.out.println("###### Step 3. Running the Algo  to fill the operational matrix  ######");
	   sudoku.runAlgo();
     
Note:- Keep logger utelity as false in both Main and SudokuAlgoExplained.java file  if you dont want to see all the steps of execution 

# Structure of Code
===================
1. Cell.java . is the base cell structure to maintain the status of each cell in the sudoku grid/matrix 
2. LoggerUtelity.java . -- is for loggin purpose only 
3. SudokuAlgoExplained.java  --  this file runs compleate logic 
4. GeekForGeek...java  -- is a geekforGeek code in c++ converted to java  (URL of the same is given in the code file )

# Logic of the code
====================

1. Create a matrix with cell object with value and status . In case of empty cell fill value as zero and status as false.
2. then keep checking the matrix till all cell will not have status as true.
3. While checking each cell  . 
    1. scan for a row / column and grid and reduce the possible candidates from the list having values 1 to 9 
    2. once each cell will be having no# of possible candidates then keep checking each cell's possible candidates with othere grid pears only 
    3. if peer have the same possible candidates then reduce from this cell and move . 
    4.  keep removing untill the cell will be having only one value  (For easy sudoku )
    
    5. (For medium sudoku ) if still after removing possible candidates none of the cell will reach to have only one value then after certain repeatition , assign first value to the cell from possible candidate's list  and move on.
    
    6. Now for difficult level -- I need to create a machanish to either reset all previous filled cell and try with another value OR a machanism to skip the error cell and move to next fill (Working on that )
    
# Commit History 
=================
1. first commit is only able to solve EASY sudoku where tleast one cell will reach to one possible andidates only 
2.  second commit is able to solve both easy and medium level sudoku , present in the main file 
  the logic introduced is that . if there will not any cell with one possible candidates then after 81 attempts (this can be fine grained) assign any cell the first possible candidates and rescan . repeate this till the sudoku get filled 
  
# Code improvments areas 
========================
1.  code for reaching each grid can be reduced further by using mathmatical formula to find left and right boundries 
2.  multiple for loops can be reduced to improve performance 
3.  have not used logger library just becuase i want to use Println and print feature to control the /n for printing sudoku matrix 

