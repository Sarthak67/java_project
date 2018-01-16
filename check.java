import java.util.*;

public class check

{
	// I am Declaring the global variables 
	int aiCount=12;
	int userCount=12;
	int userQueen=0;
	int AiQueen=0;
	int tempuserqueen=0;
	int tempAiqueen=0;
	int tempAicount=12;
	int  tempusercount=12;
	Scanner reader = new Scanner(System.in);
	int board[][] ={{0,1,0,1,0,1,0,1},
			{1,0,1,0,1,0,1,0},
 			{0,1,0,1,0,1,0,1},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{3,0,3,0,3,0,3,0},
			{0,3,0,3,0,3,0,3},
			{3,0,3,0,3,0,3,0}};
	//this is tempboard which is declared just to use min() function of the algorithm
	int tempboard[][] = {
			{0,1,0,1,0,1,0,1},
			{1,0,1,0,1,0,1,0},
 			{0,1,0,1,0,1,0,1},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{3,0,3,0,3,0,3,0},
			{0,3,0,3,0,3,0,3},
			{3,0,3,0,3,0,3,0}};
	
	//This is print function to Display the board
	
public void print()
{
	System.out.println("   0  1  2  3  4  5  6  7");
	for (int i = 0; i < 8; i++)
	{System.out.print(i+" ");
		for (int j =0 ; j < 8; j++)
			System.out.print(" " + board[i][j]
							+ " ");
		System.out.println();
	}
}	

//These are boundary checking function which checks if the moves fall in bound or out bound


boolean isboundary2(int a,int b)
{
	if(a>7||a<0||b>7||b
			<0)
	{
		return true;
	}
	
	else if((a>=0&&a<7)&&(b==7))
	
	{
		return true;
	}
	else
	{return false;
}
	}

boolean isboundary1(int a,int b)
{
	if(a>7||a<0||b>7||b<0)
	{
		return true;
	}
	
else if((a>=0&&a<7)&&(b==0))
	
	{
		return true;
	}
else
{
	return false;
}
}

//These are move checking functions , if there is empty space then only a piece can move

boolean checkm(int a,int b)
{
	boolean x=false;
	if(a>7 ||a<0 ||b<0||b>7)
	{
		return false;
	}
	else if(board[a][b]==0)
	{
		x=true;
	}

	return x;
}
boolean checkmo(int a,int b)
{
	boolean x=false;
	if(a>7 ||a<0 ||b<0||b>7)
	{
		return false;
	}
	else if(tempboard[a][b]==0)
	{
		x=true;
	}
	return x;
	
}


//This for the normal Ai piece  to move it checks 2 one box move and 2 jump moves 

public void moveAi(int a,int b)
{int max=0;
	
	int []s={0,0,0,0};
	
	if(board[a][b]==4)
	{
		moveQueenAi(a,b);
		//if the piece contains queen sending it to Ai qeen function
	}
	
	
 if((!isboundary1(a,b))&&checkm(a-1,b-1))
	{
	 
	s[0]=computescore(a,b,a-1,b-1);
	
	 }
	
	else if((!isboundary2(a,b))&&checkm(a-1,b+1))
	{
	 
	s[1]=computescore(a,b,a-1,b+1);
	 
	}
	else
	{
	if((!isboundary1(a-1,b-1)))
	{
	if(jump1(a-1,b-1)&&checkm(a-2,b-2))
    {
	s[2]=computescore(a,b,a-2,b-2);
     }
	}
	else if((!isboundary2(a-1,b+1)))
	{
		if(jump1(a-1,b+1)&&checkm(a-2,b+2))
	{
		s[3]=computescore(a,b,a-2,b+2);
	}
	}
	}
for(int i=0;i<4;i++)
{
if(s[max]<s[i])
{
	max=i;
}

}
switch(max)
{
case 0:
{
	if((isAiQueen(a-1,b-1)))
	{
	board[a][b]=0;
	board[a-1][b-1]=4;
	AiQueen++;
	}
	else
	{
		board[a][b]=0;
		board[a-1][b-1]=3;
	}
	
	break;
}
case 1:
{
	if((isAiQueen(a-1,b+1)))
	{board[a][b]=0;
	board[a-1][b+1]=4;
	AiQueen++;
	}
	
	else
	{
		board[a][b]=0;
		board[a-1][b+1]=3;
	}
	break;
}
case 2:
{ 
	if((isAiQueen(a-2,b-2)))
	{
	board[a][b]=0;
	board[a-2][b-2]=4;
	board[a-1][b-1]=0;
			userCount--;
			AiQueen++;
	}
	else
	{
		board[a][b]=0;
		board[a-2][b-2]=3;
		board[a-1][b-1]=0;
				userCount--;
	}
	//if double move is poissible it recalls the function
	if(isDoubleMovePossible1(a-2,b-2))
	{
		moveAi(a-2,b-2);
	}
	break;
}
case 3:
{
	if((isAiQueen(a-2,b+2)))
	{
	board[a][b]=0;
	board[a-2][b+2]=4;
	board[a-1][b+1]=0;
			userCount--;
			AiQueen++;
	}
	else
	{
		board[a][b]=0;
		board[a-2][b+2]=3;
		board[a-1][b+1]=0;
				userCount--;
	}
	if(isDoubleMovePossible1(a-2,b+2))
	{
		moveAi(a-2,b+2);
	}
	
	break;
}

}

	
}


//This is for the queen Ai to move it checks for 4 plus 1 move and 4 jump moves


public void moveQueenAi(int a,int b)
{
	
int max=0;
	
	int []s={0,0,0,0,0,0,0,0};
	
	if(board[a][b]==4)
	{
		moveQueenAi(a,b);
		
	}
	
	
 if((!isboundary1(a,b))&&checkm(a-1,b-1))
	{
	 
	s[0]=computescore(a,b,a-1,b-1);
	
	 }
	
	else if((!isboundary2(a,b))&&checkm(a-1,b+1))
	{
	 
	s[1]=computescore(a,b,a-1,b+1);
	 
	}
	else if((!isboundary1(a-1,b-1)))
	{
	if(jump1(a-1,b-1)&&checkm(a-2,b-2))
    {
	s[2]=computescore(a,b,a-2,b-2);
     }
	}
	else if((!isboundary2(a-1,b+1)))
	{
		if(jump1(a-1,b+1)&&checkm(a-2,b+2))
	{
		s[3]=computescore(a,b,a-2,b+2);
	}
	}
	
 
	else if((!isboundary2(a,b)))
	{
	 if(checkmo(a+1,b+1))
	 {
	s[4]=computescore(a,b,a+1,b+1);
	 }
	}
	else if((!isboundary1(a,b)))
	{
	
	 if(checkmo(a+1,b-1))
	 {
	s[5]=computescore(a,b,a+1,b-1);
	 }
	}
	
	else if((!isboundary2(a+1,b+1)))
		{
	if(jump1(a+1,b+1)&&checkm(a+2,b+2))
{
	s[6]=computescore(a,b,a+2,b+2);
}
		}
		else if((!isboundary1(a+1,b-1)))
		{
	if(jump1(a+1,b-1)&&checkm(a+2,b-2))
	{
		s[7]=computescore(a,b,a+2,b-2);
	}
	}
 
 
for(int i=0;i<4;i++)
{
if(s[max]<s[i])
{
	max=i;
}

}
switch(max)
{
case 0:
{
	
	board[a][b]=0;
	board[a-1][b-1]=4;
	
	
	break;
}
case 1:
{
	
	board[a][b]=0;
	board[a-1][b+1]=4;
	
	break;
}
case 2:
{ 
	
	board[a][b]=0;
	board[a-2][b-2]=4;
	board[a-1][b-1]=0;
			userCount--;
			
	if(isDoubleMovePossible1(a-2,b-2))
	{
		moveAi(a-2,b-2);
	}
	break;
}
case 3:
{
	
	board[a][b]=0;
	board[a-2][b+2]=4;
	board[a-1][b+1]=0;
			userCount--;
		
	if(isDoubleMovePossible1(a-2,b+2))
	{
		moveAi(a-2,b+2);
	}
	
	break;
}

case 4:
{
		board[a][b]=0;
		board[a+1][b+1]=4;

	break;
}
case 5:
{

	board[a][b]=0;
    board[a+1][b-1]=4;
	
	break;
}
case 6:
{
	
		board[a][b]=0;
		board[a+2][b+2]=4;
		board[a+1][b+1]=0;
		
				tempAicount--;
	
	if(isDoubleMovePossible(a+2,b+2))
	{
		moveAiuser(a+2,b+2);
	}
	break;
}
case 7:
{
	
	
		board[a][b]=0;
		board[a+2][b-2]=4;
		board[a+1][b-1]=0;
	
				tempAicount--;
	
	
	if(isDoubleMovePossible(a+2,b-2))
	{
		moveAiuser(a+2,b-2);
	}
	break;
}


}
	
	
	
}

//This is empty Function , this checks if the game is over

boolean isEmpty()
{
if((userCount==0&&userQueen==0)||(aiCount==0&&AiQueen==0))
{
	return true;
}
return false;
}




//This is for moving queen user , this does similar function as moveAiQueen function
	public void moveQueenUser(int a ,int b)
	{
		int min=0;
		
		int []s={0,0,0,0,0,0,0,0};
		
		if((!isboundary2(a,b)))
		{
		 if(checkmo(a+1,b+1))
		 {
		s[0]=computescore1(a,b,a+1,b+1);
		 }
		}
		else if((!isboundary1(a,b)))
		{
		
		 if(checkmo(a+1,b-1))
		 {
		s[1]=computescore1(a,b,a+1,b-1);
		 }
		}
		
		else if((!isboundary2(a+1,b+1)))
			{
		if(jump(a+1,b+1)&&checkmo(a+2,b+2))
	{
		s[2]=computescore1(a,b,a+2,b+2);
	}
			}
			else if((!isboundary1(a+1,b-1)))
			{
		if(jump(a+1,b-1)&&checkmo(a+2,b-2))
		{
			s[3]=computescore1(a,b,a+2,b-2);
		}
		}
			
			
		

			else if((!isboundary2(a,b)))
			{
			 if(checkmo(a-1,b+1))
			 {
			s[4]=computescore1(a,b,a-1,b+1);
			 }
			}
			else if((!isboundary1(a,b)))
			{
			
			 if(checkmo(a-1,b-1))
			 {
			s[5]=computescore1(a,b,a-1,b-1);
			 }
			}
			
			else if((!isboundary2(a-1,b+1)))
				{
			if(jump(a-1,b+1)&&checkmo(a-2,b+2))
		{
			s[6]=computescore1(a,b,a-2,b+2);
		}
				}
				else if((!isboundary1(a-1,b-1)))
				{
			if(jump(a-1,b-1)&&checkmo(a-2,b=2))
			{
				s[7]=computescore1(a,b,a-2,b-2);
			}
				}
			
	for(int i=0;i<4;i++)
	{
	if(s[min]>s[i])
	{
		min=i;
	}

	}
	switch(min)
	{
	case 0:
	{
			tempboard[a][b]=0;
			tempboard[a+1][b+1]=2;
	
		break;
	}
	case 1:
	{
	
		tempboard[a][b]=0;
		tempboard[a+1][b-1]=2;
		
		break;
	}
	case 2:
	{
		
			tempboard[a][b]=0;
			tempboard[a+2][b+2]=2;
			tempboard[a+1][b+1]=0;
			
					tempAicount--;
		
		if(isDoubleMovePossible(a+2,b+2))
		{
			moveAiuser(a+2,b+2);
		}
		break;
	}
	case 3:
	{
		
		
			tempboard[a][b]=0;
			tempboard[a+2][b-2]=2;
			tempboard[a+1][b-1]=0;
		
					tempAicount--;
		
		
		if(isDoubleMovePossible(a+2,b-2))
		{
			moveAiuser(a+2,b-2);
		}
		break;
	}
	
	case 4:
	{
		
		
			tempboard[a][b]=0;
			
			tempboard[a-1][b+1]=2;
		
					tempAicount--;
		
		
		;
	}

	
	case 5:
	{
		
		
			tempboard[a][b]=0;
			tempboard[a-1][b-1]=2;
		
					tempAicount--;
		
		
		
		break;
	}

	
	case 6:
	{
		
		
			tempboard[a][b]=0;
			tempboard[a-2][b+2]=2;
			tempboard[a-1][b+1]=0;
		
					tempAicount--;
		
		
		if(isDoubleMovePossible(a+2,b-2))
		{
			moveAiuser(a+2,b-2);
		}
		break;
	}

	
	case 7:
	{
		
		
			tempboard[a][b]=0;
			tempboard[a-2][b-2]=2;
			tempboard[a-1][b-1]=0;
		
					tempAicount--;
		
		
		if(isDoubleMovePossible(a+2,b-2))
		{
			moveAiuser(a+2,b-2);
		}
		break;
	}

	
	
	}
		
		
	}

//This is move user , this intermediate stage , where the actions are performed in tempboard 
	//this also does similar function to moveAi
	
public void moveAiuser(int a,int b)
{int min=0;
	
	int []s={0,0,0,0};
	if(tempboard[a][b]==2)
	{
	moveQueenUser(a,b);	
	}
	if((!isboundary2(a,b)))
	{
	 if(checkmo(a+1,b+1))
	 {
	s[0]=computescore1(a,b,a+1,b+1);
	 }
	}
	else if((!isboundary1(a,b)))
	{
	
	 if(checkmo(a+1,b-1))
	 {
	s[1]=computescore1(a,b,a+1,b-1);
	 }
	}
	else
	{
		if((!isboundary2(a+1,b+1)))
		{
	if(jump(a+1,b+1)&&checkmo(a+2,b+2))
{
	s[2]=computescore1(a,b,a+2,b+2);
}
		}
		else if((!isboundary1(a+1,b-1)))
		{
	if(jump(a+1,b-1)&&checkmo(a+2,b-2))
	{
		s[3]=computescore1(a,b,a+2,b-2);
	}
	}
	}
for(int i=0;i<4;i++)
{
if(s[min]>s[i])
{
	min=i;
}

}
switch(min)
{
case 0:
{ 
	if(isHumanQueen(a+1,b+1))
	{
	tempboard[a][b]=0;
	tempboard[a+1][b+1]=2;
	userQueen++;
	}
	else
	{
		tempboard[a][b]=0;
		tempboard[a+1][b+1]=1;
	}
	
	break;
}
case 1:
{
	if(isHumanQueen(a+1,b-1))
	{
	tempboard[a][b]=0;
	tempboard[a+1][b-1]=2;
	userQueen++;
	}
	else
	{
		tempboard[a][b]=0;
		tempboard[a+1][b-1]=1;
	}
	break;
}
case 2:
{
	if(isHumanQueen(a+2,b+2))
	{
	tempboard[a][b]=0;
	tempboard[a+2][b+2]=2;
	tempboard[a+1][b+1]=0;
	userQueen++;
			tempAicount--;
	}
	else
	{
		tempboard[a][b]=0;
		tempboard[a+2][b+2]=1;
		tempboard[a+1][b+1]=0;
		
				tempAicount--;
	}
	if(isDoubleMovePossible(a+2,b+2))
	{
		moveAiuser(a+2,b+2);
	}
	break;
}
case 3:
{
	if(isHumanQueen(a+2,b-2))
	{
	tempboard[a][b]=0;
	tempboard[a+2][b-2]=2;
	tempboard[a+1][b-1]=0;
	userQueen++;
			tempAicount--;
	}
	else
	{
		tempboard[a][b]=0;
		tempboard[a+2][b-2]=1;
		tempboard[a+1][b-1]=0;
	
				tempAicount--;
	}
	
	if(isDoubleMovePossible(a+2,b-2))
	{
		moveAiuser(a+2,b-2);
	}
	break;
}

}

	
}



//this is to check if the user selected piece contains value 
boolean isvalue(int a,int b)
{
	if(board[a][b]==1||board[a][b]==2)
	{
		return true;
	}
	else
	{
		return false;
	}
	
}
//this is to check if the targted palce is playeable or not

boolean isPlayeable(int a,int b,int c,int d)
{
	boolean e=false;
	if((c==(a+1))&&((d==(b-1))||(d==(b+1))))
	{
		e=true;
	}
	return e;
	
}

//this is to compute the users score in the graph score map
public int computescore(int a,int b,int c,int d)
{
	int score=0;
	if((tempboard[a][b]==1)&&(c>(a+1)))
	{
	
	score=(tempAicount+1)+2*tempAiqueen;
	}
	else 
	{
		score=tempAicount+2*tempAiqueen;
	}
	
	
	return score;
}

//this is to compute the AIs score in the graph score map
public int computescore1(int a,int b, int c,int d)
{
	int score=0;
	if((tempboard[a][b]==3)&&(c<(a-1)))
	{
	
	score=(tempusercount+1)+2*tempuserqueen;
	}
	else
	{
		score=tempusercount+2*tempuserqueen;
		
	}
	return score;
}

//This is to check if the peice is  are human queens or Ai queens

boolean isHumanQueen(int a ,int b)
{
if(a==7)
{
	return true;
}
return false;	

}
boolean isAiQueen(int a ,int b)
{
if(a==0)
{
	return true;
}


return false;	

}


//this is to check if the double move is possible or not for both Ai and human

boolean isDoubleMovePossible(int a,int b)
{
	if(board[a+1][b+1]==3||board[a+1][b-1]==3)
	
	{
		return true;
	}
	return false;
}

boolean isDoubleMovePossible1(int a,int b)
{
	if(board[a+1][b+1]==1||board[a+1][b-1]==1)
	
	{
		return true;
	}
	return false;
}


//this is human action move where we ask the selected piece and the targeted move
public void human()
{





	
	System.out.println("Select move");
	int a=reader.nextInt();
	int b=reader.nextInt();
	while(!isvalue(a,b))
	{
	  System.out.println("Select target value");	
	  System.out.println("Select move");
		a=reader.nextInt();
	     b=reader.nextInt();
	}
	System.out.println("Targer move");
	int c=reader.nextInt();
	int d=reader.nextInt();
	while(!isPlayeable(a,b,c,d))
	{
		System.out.println("Targer move");
		 c=reader.nextInt();
		 d=reader.nextInt();
	
	
	}
	playermove(a,b,c,d);
	
	//at the end the actual board is being copied to temp board
copy();

	
	


}

//this is min of min max

public int[] min()

{
	int[] minx={0,0};
    
	int a=0,b=0;
	
	int score[][]={{100,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0}
	};
	for(int i=0;i<8;i++)
	{
		for(int j=0;j<8;j++)
			
		{
			if(((!isboundary2(i,j)&&!isboundary2(i+1,j+1))&&(board[i][j]==1 && board[i+1][j+1]==3)&&checkm(i+2,j+2)&&((!isboundary1(i,j))&&(!isboundary1(i+1,j-1))&&(board[i][j]==1 && board[i+1][j-1]==3)&&checkm(i+2,j-2))))
			{
				score[i][j]=-computescore(i,j,i+2,j+2)-computescore(i,j,i+2,j-2);
			}
		else if((!isboundary2(i,j)&&!isboundary2(i+1,j+1))&&(board[i][j]==1 && board[i+1][j+1]==3)&&checkm(i+2,j+2))
					{
				
				score[i][j]=-computescore(i,j,i+2,j+2);
		
				
				
	}
			else if((!isboundary1(i,j))&&(!isboundary1(i+1,j-1))&&(board[i][j]==1 && board[i+1][j-1]==3)&&checkm(i+2,j-2))
			{

	score[i][j]=-computescore(i,j,i+2,j-2);
	

			}
			
		else if ((!isboundary1(i,j)&&((board[i][j]==1) && checkm(i+1,j-1)))&&!isboundary2(i,j)&&((board[i][j]==1) && checkm(i+1,j+1)))
			{
		score[i][j]=-(computescore(i,j,i+1,j-1)+computescore(i,j,i+1,j+1));
			}
			
			
			
			else if(!isboundary2(i,j)&&((board[i][j]==1) && checkm(i+1,j+1)))
{
	
				score[i][j]=-computescore(i,j,i+1,j+1);
				
				
			}
			

else if(!isboundary1(i,j)&&((board[i][j]==1) && checkm(i+1,j-1)))
	{
			
				score[i][j]=-computescore(i,j,i+1,j-1);
				
			
		}

		
		
		}
			
			}
	//finding minimum
		
	for(int i=0;i<8;i++)
	{
		for(int j=0;j<8;j++)
		{
			if((score[i][j]!=0)&&(score[a][b]>score[i][j]))
			{
				a=i;
				b=j;
				
			}
			else
				{continue;}
		}
		
	}
	
	minx[0]=a;
	minx[1]=b;
	
	return minx;
}


//this is max of minmax

public int[] max()

{
	int[] maxx=new int[2];
	maxx[0]=7;
	maxx[1]=7;
	int a=0; int b=0;
	
	
	int score[][]={
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,-100}
	};
	for(int i=0;i<8;i++)
	{
		for(int j=0;j<8;j++)
			
		{
			if(((!isboundary2(i,j))&&(!isboundary2(i-1,j+1))&&(tempboard[i][j]==3 && tempboard[i-1][j+1]==1)&&checkmo(i-2,j+2))&&((!isboundary1(i,j))&&(!isboundary1(i-1,j-1))&&(tempboard[i][j]==3 && tempboard[i-1][j-1]==1)&&checkmo(i-2,j-2)))
				
			{
				score[i][j]=computescore1(i,j,i-2,j+2)+computescore1(i,j,i-2,j-2);;
			}
			
			else if((!isboundary2(i,j))&&(!isboundary2(i-1,j+1))&&(tempboard[i][j]==3 && tempboard[i-1][j+1]==1)&&checkmo(i-2,j+2))
			{
				score[i][j]=computescore1(i,j,i-2,j+2);
				
			}
			
			
			else if((!isboundary1(i,j))&&(!isboundary1(i-1,j-1))&&(tempboard[i][j]==3 && tempboard[i-1][j-1]==1)&&checkmo(i-2,j-2))
{
	score[i][j]+=computescore1(i,j,i-2,j-2);
	
}
			else if(!isboundary2(i,j)&&(tempboard[i][j]==3) && (checkmo(i-1,j+1))&&((!isboundary1(i,j))&&(tempboard[i][j]==3) && checkmo(i-1,j-1)))
				
			{
			score[i][j]=computescore1(i,j,i-1,j+1)+computescore1(i,j,i-1,j-1);
			}
			else if(!isboundary2(i,j)&&(tempboard[i][j]==3) && (checkmo(i-1,j-1)))

	
{
			
			
				score[i][j]=computescore1(i,j,i-1,j+1);
				
			
}
			
			else if((!isboundary1(i,j))&&(tempboard[i][j]==3) && checkmo(i-1,j-1))
	{
		
			{
				score[i][j]=computescore1(i,j,i-1,j-1);
				
			}
			
		}
	
	
		
		}
			}		
	
	
	//finding max

	for(int i=7;i>=0;i--)
	{
		for(int j=7;j>=0;j--)
		{
			if((score[a][b]<score[i][j])&&(score[i][j]!=0))
			{
				a=i;
				b=j;
			}
		}
		
	}
	maxx[0]=a;
	maxx[1]=b;
	
	return maxx;
}





//this is the main play function where game continues to execute untill the empty contion is met

public void play()
{
	int []a;
	int []b;
while(!isEmpty())
{
	human();
	print();
	
	tempAicount=aiCount;
	
	a=min();
	b=max();
	System.out.println(a[0]+" "+a[1]);
	System.out.println(b[0]+" "+b[1]);
	moveAiuser(a[0],a[1]);
	moveAi(b[0],b[1]);
	tempusercount=userCount;
	
	System.out.println(" ");
	print();
}

	
}

//copying real board with tempboard

void copy()
{
	for(int i=0;i<8;i++)
	{
		for(int j=0;j<8;j++)
		{
			tempboard[i][j]=board[i][j];
		}
	}
}
//checking if Ai or user jump is possible in these 2 functions

boolean jump1(int k ,int l)
{
	if((board[k][l]==1)||board[k][l]==2)
	{
		
		return true;
	}
	else
	{
	return false;
}
}

boolean jump(int k ,int l)
{
	if(board[k][l]==3||board[k][l]==4)
	{
		
		return true;
	}
	else
	{
	return false;
}
}

	//this is the action performed by the player 
	
public boolean playermove(int i,int j,int k,int l)
{
	if(isHumanQueen(i,j))
		
	{
		 if(checkm(k,l))
{
	board[k][l]=2;
	board[i][j]=0;
	
	
}
		 
else if(jump(k,l))
{
	if(checkm(k+1,l+1))
	{
	board[k+1][l+1]=2;
	board[i][j]=0;
	board[k][l]=0;
	aiCount--;
	if(isDoubleMovePossible(k+1,l+1))
	{
		human();
	}
	}
	else if((checkm(k+1,l-1)))
	{
		board[k+1][l-1]=2;
		board[i][j]=0;
		board[k][l]=0;
		aiCount--;
		if(isDoubleMovePossible(k+1,l-1))
		{
			human();
		}
	}
	
		 
else if(checkm(k-1,l+1))
			{
			board[k-1][l+1]=2;
			board[i][j]=0;
			board[k][l]=0;
			aiCount--;
			if(isDoubleMovePossible(k-1,l+1))
			{
				human();
			}}
			else if((checkm(k-1,l-1)))
			{
				board[k-1][l-1]=2;
				board[i][j]=0;
				board[k][l]=0;
				aiCount--;
				if(isDoubleMovePossible(k-1,l-1))
				{
					human();
				}
			}
			}
	}	 
		 
		
		

else if(checkm(k,l))
{
	board[k][l]=1;
	board[i][j]=0;
	
	
}
else if(jump(k,l))
{
	if(((j<l))&&checkm(k+1,l+1))
	{
	board[k+1][l+1]=1;
	board[i][j]=0;
	board[k][l]=0;
	aiCount--;}
	else if(((j>l))&&(checkm(k+1,l-1)))
	{
		board[k+1][l-1]=1;
		board[i][j]=0;
		board[k][l]=0;
		aiCount--;
	}
	else
	{
		System.out.println("This Move is not allowed");
	}
}
else
{
	System.out.println("This move is not allowed");
	play();
	
}
return true;
}


//this is main function 
	public static void main(String []args)
	{
		
		check check=new check();
		
		check.print();
		
		check.play();
	
	}

	
}