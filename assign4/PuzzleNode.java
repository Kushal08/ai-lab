import java.util.ArrayList;


public class PuzzleNode {
	
	private PuzzleGrid grid;
	private Heuristic heuristic;
	private ArrayList<PuzzleNode> children = new ArrayList<PuzzleNode>();
	private PuzzleNode parent;
	
	public PuzzleNode( PuzzleGrid grid, Heuristic h )
	{
		this.grid = grid;
		this.heuristic = h;
	}
	
	
	public ArrayList<PuzzleNode> createChildren()
	{
		PuzzleNode pn;
		PuzzleGrid pg;
	
		pg = new PuzzleGrid( grid );
		if ( pg.translateSpace(0, -1) ) 
		{ 
			try {
				pn = new PuzzleNode( pg, heuristic.getClass().newInstance() );
				pn.setParent( this );
				children.add( pn ); 
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		// Left
		pg = new PuzzleGrid( grid );
		if ( pg.translateSpace(-1, 0) ) 
		{ 
			try {
				pn = new PuzzleNode( pg, heuristic.getClass().newInstance() );
				pn.setParent( this );
				children.add( pn ); 
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		// Right
		pg = new PuzzleGrid( grid );
		if ( pg.translateSpace(1, 0) ) 
		{ 
			try {
				pn = new PuzzleNode( pg, heuristic.getClass().newInstance() );
				pn.setParent( this );
				children.add( pn ); 
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
				
		// Down
		pg = new PuzzleGrid( grid );
		if ( pg.translateSpace(0, 1) ) // True if it is possible to move empty square
		{ 
			try {
				pn = new PuzzleNode( pg, heuristic.getClass().newInstance() );
				pn.setParent( this );
				children.add( pn ); 
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		return children;
	}
	

	public void determineHeuristic( PuzzleGrid goalState )
	{
	    if ( heuristic != null )
	    { 
		heuristic.calculateHeuristic( this, goalState);
	    }
	}	
	
	
	public float getHeuristicValue()
	{
	    if ( heuristic != null )
	    {
		return heuristic.getHeuristicValue();
	    } else {
		return 9999.f;
	    }
	}
	
	public boolean equalTo( PuzzleNode pn )
	{
	    return grid.equalTo( pn.getGrid() );
	}
	
	
	public ArrayList<PuzzleNode> getChildren()
	{
	    return children;
	}
	
	public void setParent( PuzzleNode pn )
	{
	    this.parent = pn;
	}


	public PuzzleNode getParent()
	{
	    return parent;
	}
	
	
	public PuzzleGrid getGrid()
	{
	    return grid;
	}
	
	public void setGrid( PuzzleGrid grid )
	{
	    this.grid = grid;
	}
	
}
