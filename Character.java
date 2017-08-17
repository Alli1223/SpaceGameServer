import org.json.JSONObject;

import java.awt.*;

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
	private Color hairColour = new Color(0,0,0);
    private Color eyeColour = new Color(0,0,0);
    private int bodyWear = 0;
    private int legWear = 0;

    public int getHearWear() { return headWear; }
    public int getBodyWear() { return bodyWear; }
    public int getLegWear() { return legWear; }
    public Color getHairColour() { return hairColour; }
    public Color getEyeColour() { return eyeColour; }
    public Color setHairColour(Color newHairColor) { return  hairColour = newHairColor; }
    public Color setEyeColour(Color newEyeColor) { return eyeColour = newEyeColor; }

    public void setPlayerClothes(int newHeadWear, int newBodyWear, int newLegWear)
    {
        headWear = newHeadWear;
        bodyWear = newBodyWear;
        legWear = newLegWear;
    }
    public JSONObject getHairColourJson()
    {
        JSONObject returnjson = new JSONObject();
        try {
            returnjson.put("r", getHairColour().getRed());
            returnjson.put("g", getHairColour().getGreen());
            returnjson.put("b", getHairColour().getBlue());

        }
        catch(Exception e)
        {
            System.out.println("Error getting hair colour to json " + e);
        }
        return returnjson;
    }
    public JSONObject getEyeColourJson()
    {
        JSONObject returnjson = new JSONObject();
        try {
            returnjson.put("r", getEyeColour().getRed());
            returnjson.put("g", getEyeColour().getGreen());
            returnjson.put("b", getEyeColour().getBlue());

        }
        catch(Exception e)
        {
            System.out.println("Error getting eye colour to json " + e);
        }
        return returnjson;
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
