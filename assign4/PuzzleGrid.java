import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class PuzzleGrid 
{
    	
	private int[][] grid;
	private int width;
	private int height;
	
	private int x0;
	private int y0; 
	
	// Constructor
	public PuzzleGrid( String filename)
	{
	    loadGridFromFile( filename );
	}
	
	public PuzzleGrid( int w, int h )
	{

		width = w; height = h;
		grid = new int[w][h];
		int count = 0;
		
		for (int j = 0; j < h; j++) 
		{
			for ( int i = 0; i < w; i++)
			{
				grid[i][j] = count;
				if ( count == 0 )
				{
					x0 = i;
					y0 = j;
				}
				count++;
			}
							
		}
	}
	
	public PuzzleGrid( PuzzleGrid pg )
	{
		width = pg.getWidth();
		height = pg.getHeight();
		grid = new int[width][height];
		
		for ( int y = 0; y < height; y++ )
		{
			for ( int x = 0; x < width; x++ )
			{
				grid[x][y] = pg.getRawGrid()[x][y];
			}
		}
		
		x0 = pg.getEmptyX();
		y0 = pg.getEmptyY();
	}
	

	public void loadGridFromFile( String filename )
	{
		try {
			BufferedReader br = new BufferedReader( new FileReader( filename ));
			String[] data = new String[width];
			String delimiter = ",", line = null;
			int i = 0, j = 0;
			
			try 
			{ 
				line = br.readLine(); 
				width = line.length() / 2 + 1;
				height = line.length() / 2 + 1;
				grid = new int[width][height];
				
				while ( line != null )
				{
					data = line.split( delimiter );
					for (i = 0; i < width; i++) 
					{
						grid[i][j] = Integer.parseInt( data[i] );
						if ( grid[i][j] == 0 ) 
						{
							x0 = i;
							y0 = j;
						}
					}
				
					j++;
					line = br.readLine();
				}
			}
			catch (IOException e) 
			{ 
				System.out.println( "Unable to read from file " + filename + ". Closing..." ); 
				System.exit(0);
			}
		}
		catch ( FileNotFoundException e)
		{
			System.out.println( "File with name " + filename + " not found. Closing..." );
			System.exit(0);
		}
	}
	
	public boolean translateSpace( int x, int y )
	{
		boolean success = false;
		
		// If translation is horizontal
		if ( x != 0 )
		{
			if ( x > 0 && x0 < 2) // Translate right, can't if x0 > 1
			{
				grid[x0][y0] = grid[x0+1][y0];
				grid[x0+1][y0] = 0;
				
				x0 = x0+1;
				success = true;
			}
			else if ( x < 0 && x0 > 0 ) // Translate left, can't if x0 < 1
			{
				grid[x0][y0] = grid[x0-1][y0];
				grid[x0-1][y0] = 0;
				
				x0 = x0-1;
				success = true;
			}
		} // If translation is vertical
		else if ( y != 0 ) 
		{				   
			if ( y > 0 && y0 < 2) // Translate down, can't if y0 > 1
			{
				grid[x0][y0] = grid[x0][y0+1];
				grid[x0][y0+1] = 0;
				
				y0 = y0+1;
				success = true;
			}
			else if ( y < 0 && y0 > 0 ) // Translate up, cant if y0 < 1
			{
				grid[x0][y0] = grid[x0][y0-1];
				grid[x0][y0-1] = 0;
				
				y0 = y0-1;
				success = true;
			}
		}
		
		return success;
	}
	
	public void printGrid()
	{
		for ( int y = 0; y < height; y++)
		{
			for ( int x = 0; x < width; x++ )
			{
				if ( x == x0 && y == y0 ) System.out.print( "  " );
				else System.out.print( grid[x][y] + " " );
			}
			System.out.print( "\n" );
		}
		System.out.print( "\n" );
	}
	
	public boolean equalTo( PuzzleGrid pg )
	{
		boolean equal = true;
		
		
		for ( int y = 0; y < height && equal == true; y++ )
		{
			for ( int x = 0; x < width && equal == true; x++ )
			{
				if ( grid[x][y] != pg.getRawGrid()[x][y] ) 
				{
					equal = false;
				}
			}
		}
		
		return equal;
	}
	

	public int[] getPositionInGrid( int tile )
	{
		int[] position = new int[2];
		
		for ( int y = 0; y < height; y++ )
		{
			for ( int x = 0; x < width; x++ )
			{
				if ( grid[x][y] == tile ) 
				{
					position[0] = x;
					position[1] = y;
					
					// Should break but meh
				}
			}
		}
		
		return position;
	}
	

	public int[][] getRawGrid()
	{
		return grid;
	}
	

	public int getWidth()
	{
		return width;
	}
	

	public int getHeight()
	{
		return height;
	}


	public int getEmptyX()
	{
		return x0;
	}
	
	public int getEmptyY()
	{
		return y0;
	}
	
}
