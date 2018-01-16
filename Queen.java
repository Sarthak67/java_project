/* Java program to solve N Queen Problem using
backtracking */
public class Queen
{
	

	
	void printBoard (int board[][])
	{
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
				System.out.print(" " + board[i][j]
								+ " ");
			System.out.println();
		}
	}

	
	boolean constrain(int board[][], int row, int col)
	{
		int i, j;
        boolean d=true;
		/* Check this row on left side */
		for (i = 0; i < row; i++)
			{
			if (board[i][col] == 1)
				return !d;
			}

		/* Check upper diagonal on left side */
		for (i=row, j=col; i>=0 && j>=0; i--, j--)
			{
			if (board[i][j] == 1)
				return !d;
			}

		/* Check lower diagonal on left side */
		for (i=row, j=col; j<8 && i>=0; i--, j++)
			
			{
			
			if (board[i][j] == 1)
			return !d;
			}

		return d;
	}

	
	boolean solve(int board[][], int row)
	{
		
		if (row >= 8)
			{return true;}

		
		for (int i = 0; i < 8; i++)
		{
			
			if (constrain(board, row,i))
			{
				
				board[row][i] = 1;

				
				if (solve(board, row + 1) )
				{	return true;
				
				}

				else
				{
				board[row][i] = 0; // BACKTRACK
			
				}
				
			}
		}

		return false;
	}

	
	boolean solveIT()
	{
		int board[][] = {{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
	 			{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0}
		};

		if (solve(board, 0) == false)
		{
			System.out.print("Solution does not exist");
			return false;
		}

		printBoard(board);
		return true;
	}

	// driver program to test above function
	public static void main(String args[])
	{
		Queen Queen = new Queen();
		Queen.solveIT();
	}
}

