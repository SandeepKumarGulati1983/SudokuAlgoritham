# SudokuAlgoritham
This solution is not a simple BACKTRACKING algo as explained by  professor Das  of MIT or similar java solution provided at bellow Geek-- link .

This is an object oriented solution where each cell has  a memory to keep the possible element list , which he keeps improving with each scan. 

But right now it is working only for Easy and medium level, because for difficult level,  cell has to guess and sometime that guess is not right which lead to reset the all previous guess (working on this )

GeekForGeek solution.
https://www.geeksforgeeks.org/backtracking-set-7-suduku/
But unfortunately for me that is not working. 
Java conversion of the same c++ code is present in my code files.

# How to Run this Code 
1. SudokuAlgo.java is the Main file 
2. Here I provided example matrixes of 3 type mid/ high / low difficulty .
3. Uncomment or create any matrix to solve and then run below code line 

      SudokuAlgoExplained sudoku = new SudokuAlgoExplained();
      sudoku.createOperationalMatrix(grid);
      sudoku.runAlgo();
     
Note:- Keep logger utility as false in both Main and SudokuAlgoExplained.java file  if you don’t want to see all the steps of execution 

# Structure of Code
1. Cell.java . is the base cell structure to maintain the status of each cell in the sudoku grid/matrix 
2. LoggerUtelity.java . -- is for login purpose only 
3. SudokuAlgoExplained.java  --  this file runs complete logic 
4. GeekForGeek...java  -- is a geekforGeek code in c++ converted to java  (URL of the same is given in the code file )

# Logic of the code
1. Create a matrix with cell object with value and status. In case of empty cell fill value as zero and status as false.
2. then keep checking the matrix till all cell will not have status as true.
3. While checking each cell . 
    1. scan for a row / column and grid and reduce the possible candidates from the list having values 1 to 9 
    2. once each cell will be having no# of possible candidates then keep checking each cell's possible candidates with other grid pears only 
    3. if peer have the same possible candidates then reduce from this cell and move . 
    4.  keep removing until the cell will be having only one value  (For easy sudoku )
    
    5. (For medium sudoku ) if still after removing possible candidates none of the cell will reach to have only one value then after certain repetition , assign first value to the cell from possible candidate's list  and move on.
    
    6. Now for difficult level -- I need to create a mechanism to either reset all previous filled cell and try with another value OR a mechanism to skip the error cell and move to next fill (Working on that )
    
# Commit History 
1. first commit is only able to solve EASY sudoku where at least one cell will reach to one possible candidates only 
2.  second commit is able to solve both easy and medium level sudoku , present in the main file 
  the logic introduced is that . if there will not any cell with one possible candidates then after 81 attempts (this can be fine grained) assign any cell the first possible candidates and rescan . repeat this till the sudoku get filled 
  
# Code improvements areas 
1.  code for reaching each grid can be reduced further by using mathematical formula to find left and right boundaries (Done - taken idea from GeekForGeek code (row - row%3 ))
2.  multiple for loops can be reduced to improve performance 
3.  have not used logger library just because I want to use Println and print feature to control the /n for printing sudoku matrix 

