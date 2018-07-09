package Sudoku;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class LoggerUtility {

	boolean run = false;
	PrintStream o = null;

	public LoggerUtility(boolean run) {
		this.run = run;

//		try {
//			o = new PrintStream(new FileOutputStream("output.txt"));
//
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	public void sleepAndShowStatus(String s, String furtherValue1, int row, int col) {

		if (run) {
			// try {
			// Thread.sleep(1000);
			//
			// } catch (InterruptedException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }

//			System.setOut(o);
			System.out.println(s + "----" + furtherValue1 + "----row--" + row + "---col--" + col);
		}
	}

	public void printGridB(Cell grid[][]) {
//		System.setOut(o);
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				System.out.print(" " + grid[row][col].cellValue);
			}
			System.out.println("");
		}

		System.out.println("===========================================");
		System.out.println("===========================================");
	}

}
