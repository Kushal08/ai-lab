import java.util.ArrayList;

public interface SolutionFinder 
{
	public ArrayList<PuzzleGrid> findSolution( PuzzleGrid startState, PuzzleGrid goalState );
	public void printSearchData();
}
