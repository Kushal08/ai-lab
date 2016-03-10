import java.util.ArrayList;


public class TestClass 
       {	
	private SolutionFinder search;
	
	public static void main(String[] args) 
       {		
		TestClass solver = new TestClass();
		solver.Run("input.txt", "output.txt", args[0]);			
	}
	
	// Constuctor
	public TestClass() { }
	
	public void Run( String startFilename, String goalFilename, String searchAlgorithm )
	{

		ArrayList<PuzzleGrid> solution = new ArrayList<PuzzleGrid>();
		PuzzleGrid gridStart = new PuzzleGrid( startFilename ); 
		PuzzleGrid gridGoal = new PuzzleGrid( goalFilename );
		

		System.out.println( "The Starting State");
		gridStart.printGrid();
		

		System.out.println( "The Goal State" );
		gridGoal.printGrid();
		

		switch( searchAlgorithm )
		{
		case "bfs":
			search = new BreadthFirstSearch();
			break;
		case "dfs":
			search = new DepthFirstSearch();
			break;
		default:
			break;
		}	
		

		if ( search != null )
		{
		
			solution = search.findSolution(gridStart, gridGoal);
			
			if ( solution != null )
			{
				// If solution found, then print out solution path
				System.out.println( "Solution" );
				for ( int i = 0; i < solution.size(); i++ )
				{
				solution.get(i).printGrid();
				}
			}
			else // If solution not found
			{
				System.out.println( "Could not find Solution :(" );
			}
			

			search.printSearchData();
		
		}
		else // If search algorithm code not recognised
		{
			System.out.println( "Search term not recognised." );
			System.out.println( "Try again with one of the following terms:" );
			System.out.println( "bfs \ndfss" );
		}
	}

}
