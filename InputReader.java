import org.json.JSONObject;
import org.json.JSONException;

import java.awt.*;

public class InputReader
{
    public synchronized void ProcessJson(String inString, JSONObject localPlayerData, Character character, World world) throws JSONException
{
    // Add the characters position to the network send pool
    try
    {
        //Data
        localPlayerData.put("name",character.getName());
        localPlayerData.put("rotation", character.getRotation());
        localPlayerData.put("X", character.getX());
        localPlayerData.put("Y", character.getY());
        localPlayerData.put("isMoving", character.isMoving);
        localPlayerData.put("headWear", character.getHearWear());
        localPlayerData.put("bodyWear", character.getBodyWear());
        localPlayerData.put("legWear", character.getLegWear());

        //Colours
        localPlayerData.put("bodyColour", character.getBodyColourJson());
        localPlayerData.put("legColour", character.getLegColourJson());
        localPlayerData.put("hairColour", character.getHairColourJson());
        localPlayerData.put("eyeColour", character.getEyeColourJson());

        NetworkManager.getInstance().AddToPlayerUpdateList(localPlayerData, character.getID());

    } catch (JSONException e)
    {
        e.printStackTrace();
    }
    // IF the client wants to send an update of any cell they have changed
    if (inString.startsWith("[CellData]"))
    {
        // Erase the [CellData] before the json
        inString = inString.substring(10);


        JSONObject obj = new JSONObject(inString);
        int x = obj.getInt("X");
        int y = obj.getInt("Y");
        int plantStage = obj.getInt("PlantStage");

        //boolean fence = obj.getBoolean("Fence");
        //boolean dirt = obj.getBoolean("Dirt");
        //boolean wheat = obj.getBoolean("Wheat");
        //int plantStage = obj.getInt("PlantStage");

        System.out.println("Cell at X:" + x + ", Y:" + y + " changed.");
        Pair cellLocation = new Pair(x, y);
        world.getMap().get(cellLocation).setPlantGrowthStage(plantStage);
        world.setCell(cellLocation,obj.toString());
    }

    // IF the client wants to send an update of their characters position
    if (inString.startsWith("[PlayerUpdate]"))
    {
        inString = inString.substring(14); //Remove [PlayerUpdate]

        JSONObject playerData = new JSONObject(inString);


        JSONObject obj = playerData.getJSONObject("PlayerData");
        int x = obj.getInt("X");
        int y = obj.getInt("Y");
        int rotation = obj.getInt("rotation");
        boolean isMoving = obj.getBoolean("isMoving");

        int headWear = obj.getInt("headWear");
        int bodyWear = obj.getInt("bodyWear");
        int legWear = obj.getInt("legWear");

        // Eye and hair colour
        JSONObject eyecolour = obj.getJSONObject("eyeColour");
        int eR = eyecolour.getInt("r");
        int eG = eyecolour.getInt("g");
        int eB = eyecolour.getInt("b");
        Color eyeColour = new Color(eR,eG,eB);

        JSONObject haircolour = obj.getJSONObject("hairColour");
        int hR = haircolour.getInt("r");
        int hG = haircolour.getInt("g");
        int hB = haircolour.getInt("b");
        Color hairColour = new Color(hR,hG,hB);

        // Body and leg colour
        JSONObject bodycolour = obj.getJSONObject("bodyColour");
        int bR = bodycolour.getInt("r");
        int bG = bodycolour.getInt("g");
        int bB = bodycolour.getInt("b");
        Color bodyColour = new Color(bR,bG,bB);

        JSONObject legcolour = obj.getJSONObject("legColour");
        int lR = legcolour.getInt("r");
        int lG = legcolour.getInt("g");
        int lB = legcolour.getInt("b");
        Color legColour = new Color(lR,lG,lB);

        //set character deets
        character.isMoving = isMoving;
        character.setEyeColour(eyeColour);
        character.setHairColour(hairColour);
        character.setBodyColour(bodyColour);
        character.setLegColour(legColour);
        character.setPlayerClothes(headWear, bodyWear, legWear);
        character.setPosition(x,y);
        character.setRotation(rotation);
    }

    //IF the client wants an update of all the cells that have changed in the map
    if (inString.startsWith("[RequestMapUpdate]"))
    {
        // old code
        //returnString = world.getMapDataToString();


        //TCPServer.getInstance().globalNetworkData.put("MapData", World.getInstance().getMapDataToString());
    }
}
    public synchronized Pair GetCellPoint(String jsonString) throws JSONException
    {
            JSONObject obj = new JSONObject(jsonString);
            int x = obj.getInt("X");
            int y = obj.getInt("Y");
            Pair cellLocation = new Pair(x, y);
            return cellLocation;
    }

}
