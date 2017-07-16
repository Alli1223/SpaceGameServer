public class Character {
	
	Character(){
		
	}
	
	// Character X location
	private int x = 0;
	// Character Y location
	private int y = 0;
	private String name;
	
	// Potential next X
	public int nextX = 0;
	//Potential next Y
	private int nextY = 0;
	
	private int speed = 3;
	
	// Possible movement commands
	private static String movementCommands[] = {
	        "NORTH", "SOUTH", "EAST", "WEST"
	    };
	
	//private World map = World.getInstance();

	// Sets character x and y on initialisation
	public Character(int initialX, int initialY) {
		setPosition(initialX, initialY);
		nextX = x;
		nextY = y;
	}

	// Gets character position
	public int[] getPosition() {
		int[] array = new int[2];
		array[0] = x;
		array[1] = y;
		return array;
	}
	
	//Gets X value
	public int getX(){
		return x;
	}
	// Gets Y value
	public int getY(){
		return y;
	}

	// Sets character position
	public void setPosition(int newX, int newY) {
		x = newX;
		y = newY;
	}
	public void setName(String newName)
	{
		name = newName;
	}
	public String getName()
	{
		return name;
	}

	/*
	// Checks to see which cell on the map the character is currently on
	public Cell getCurrentCell(){
		return map.getCells()[x][y];
	}
	
	// Checks a cell in the map to see if it's free
	public boolean checkForFreeCell()
	{
		if (map.getCells()[nextX][nextY].getCellContent() == null 
				&& !(map.getCells()[nextX ][nextY].cellTaken))
			return true;
		else
			return false;
	}	
	*/
	// Checks string is valid and updates character position
	public synchronized void moveCharacter(String inputMovement){
		inputMovement = inputMovement.toUpperCase();
		
		if (inputMovement.equals("MOVE_EAST"))
		{
			//if(nextX + speed <= map.getCells().length * map.getCellSize())
				nextX = nextX + speed;
		}
		else if (inputMovement.equals("MOVE_WEST"))
		{
			//if(nextX - speed / map.getCellSize() >= 0)
				nextX = nextX - speed;
		}
		else if (inputMovement.equals("MOVE_NORTH"))
		{
			//if(nextY - speed / map.getCellSize() >= 0)
			nextY = nextY - speed;
		}
		else if (inputMovement.equals("MOVE_SOUTH"))
		{
			//if(nextY + speed <= map.getCells().length * map.getCellSize())
			nextY = nextY + speed;
		}

		setPosition(nextX, nextY);
		/*
		if (checkForFreeCell())
		{
			getCurrentCell().cellTaken = false;
			setPosition(nextX, nextY);
			getCurrentCell().cellTaken = true;
		}
		*/
	}
	
}
