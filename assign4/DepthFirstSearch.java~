import java.util.ArrayList;
import java.util.Stack;


public class DepthFirstSearch implements SolutionFinder {
	
	private ArrayList<PuzzleNode> statesVisited = new ArrayList<PuzzleNode>();
	
	private Stack<PuzzleNode> openStates = new Stack<PuzzleNode>(); // pop = get from top; push = add to top;
	private ArrayList<PuzzleGrid> solution = new ArrayList<PuzzleGrid>();
	
	
	public DepthFirstSearch() { };	
	
	
	public ArrayList<PuzzleGrid> findSolution(PuzzleGrid startState, PuzzleGrid goalState) 
	{

		solution = new ArrayList<PuzzleGrid>();
		ArrayList<PuzzleNode> children = new ArrayList<PuzzleNode>();
		PuzzleNode currentState = null;
		
		boolean solutionFound = false, stateVisited = false;
		int nodeCounter = 0;
	
		openStates.push( new PuzzleNode( startState, new EmptyHeuristic() ) );
		
		while ( !openStates.isEmpty() && solutionFound == false )
		{
			currentState = openStates.pop();
			nodeCounter++;
			
	
			if ( currentState.getGrid().equalTo( goalState ) )
			{
				solutionFound = true;
				break;
			}
			
			children = currentState.createChildren();
			for ( int i=children.size()-1; i >= 0; i-- )
			{
				
				stateVisited = false;
				for ( PuzzleNode newNode : statesVisited )
				{
					if ( children.get(i).equalTo( newNode ) ) 
					{ 
						stateVisited = true;
						break;
					}
				}
				
				for ( PuzzleNode newNode : openStates )
				{
					if ( children.get(i).equalTo( newNode ) ) 
					{ 
						stateVisited = true;
						break;
					}
				}
				
				
				if ( stateVisited != true )
				{
					openStates.push( children.get(i) );
					
				}
			}
			
			statesVisited.add( currentState );	
			openStates.remove( currentState );		
		}
		
	
		if ( solutionFound == true )
		{
			boolean pathFound = false;
			// Recreate path to solution
			// At this point, currentState is the goal
			// Concatenate parent-child chain until startState is reached
			
			// Add current state to solution list
			// Recursively prepend parent of current state to solution list
			// End recursion when currentState = startState
			solution.add( currentState.getGrid() );
			while ( pathFound == false )
			{
				currentState = currentState.getParent();
				solution.add( 0, currentState.getGrid() );
				if ( currentState.getGrid().equalTo( startState ) ) pathFound = true;
			}
		} 
		else // Solution wasn't found
		{
			System.out.println( "Could not find Solution :(" );
		}
		
		return solution;
	}

public void printSearchData()
	{
		System.out.println( " Total Steps taken to solve given problems are " + solution.size() );
		
	}

	
}
