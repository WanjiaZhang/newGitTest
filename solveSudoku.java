import java.util.*;

public class solveSudoku{
	public static boolean success = false;
	public static boolean[][] rowValue;
	public static boolean[][] columnValue;
	public static boolean[][] squareValue;
	public static char[][] board = {"53..7....".toCharArray(),
									"6..195...".toCharArray(),
									".98....6.".toCharArray(),
									"8...6...3".toCharArray(),
									"4..8.3..1".toCharArray(),
									"7...2...6".toCharArray(),
									".6....28.".toCharArray(),
									"...419..5".toCharArray(),
									"....8..79".toCharArray()};

	public static void main(String[] args){		
		rowValue = new boolean[9][10];
		columnValue = new boolean[9][10];
		squareValue = new boolean[9][10];
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				if (board[i][j] != '.') {
					int curr = Character.digit(board[i][j], 10);
					rowValue[i][curr] = true;
					columnValue[j][curr] = true;
					squareValue[i / 3 * 3 + j / 3][curr] = true;
				}

		DFS(0);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}

	public static void DFS(int index){
		if (index == 81) {		
			success = true;
			return;
		}
		int row = index / 9, column = index % 9;
		if (board[row][column] == '.') {
			for (int i = 1; i <= 9; i++) {
				if (!rowValue[row][i] && !columnValue[column][i] && !squareValue[row / 3 * 3 + column / 3][i]) {
					rowValue[row][i] = true;
					columnValue[column][i] = true;
					squareValue[row / 3 * 3 + column / 3][i] = true;
					board[row][column] = (char)(i + 48);

					DFS(index + 1);
					if (success)
						return;

					rowValue[row][i] = false;
					columnValue[column][i] = false;
					squareValue[row / 3 * 3 + column / 3][i] = false;
					board[row][column] = '.';
				}
			}
		} else {
			DFS(index + 1);
		}
			
	}
}