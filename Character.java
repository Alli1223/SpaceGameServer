public class Character {
	
	Character(){
		
	}
	
	// Character X location
	private int x = 0;
	// Character Y location
	private int y = 0;
	private String name;
	
	// Potential next X
	private int nextX = 0;
	//Potential next Y
	private int nextY = 0;
	
	private int speed = 3;

	private String playerDataJson;

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
	//Get and set player name
	public void setName(String newName)
	{
		name = newName;
	}
	public String getName()
	{
		return name;
	}

	//Get and set player json data
	public String getPlayerDataJson() { return playerDataJson;}
	public String setPlayerDataJson(String newJsonData){ return playerDataJson = newJsonData; }
	
}
