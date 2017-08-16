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
	private int rotation = 0;
	private int ID = 0;
	private int speed = 3;

	public boolean isMoving = false;
	private int headWear = 0;
    private int hairColour = 0;
    private int eyeColour = 0;
    private int bodyWear = 0;
    private int legWear = 0;

    public int getHearWear() { return headWear; }
    public int getHairColour() { return hairColour; }
    public int getEyeColour() { return eyeColour; }
    public int getBodyWear() { return bodyWear; }
    public int getLegWear() { return legWear; }


    public void setPlayerClothes(int newHeadWear, int newHairColour, int newEyeColour, int newBodyWear, int newLegWear)
    {
        headWear = newHeadWear;
        hairColour = newHairColour;
        eyeColour = newEyeColour;
        bodyWear = newBodyWear;
        legWear = newLegWear;
    }

	private String playerDataJson;

	// Sets character x and y on initialisation
	public Character(int initialX, int initialY) {
		setPosition(initialX, initialY);
		nextX = x;
		nextY = y;
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

	public int getID() { return ID;}
	public int setID(int newID){ return ID = newID; }

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
	// Get rotation value
	public int getRotation(){ return rotation; }
	// Set rotation value
	public int setRotation(int newRotation){ return rotation = newRotation; }

	// Sets character position
	public void setPosition(int newX, int newY) {
		x = newX;
		y = newY;
	}

	
}
