import org.json.JSONObject;
import org.json.JSONException;

public class InputReader
{
    public synchronized void ProcessJson(String inString, JSONObject localPlayerData, Character character, World world) throws JSONException
{
    // Add the characters position to the network send pool
    try
    {
        localPlayerData.put("name",character.getName());
        localPlayerData.put("rotation", character.getRotation());
        localPlayerData.put("X", character.getX());
        localPlayerData.put("Y", character.getY());
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
        boolean fence = obj.getBoolean("Fence");
        System.out.println(x + " " + y + " ");
        Pair cellLocation = new Pair(x, y);
        world.setCell(cellLocation,obj.toString());
    }

    // IF the client wants to send an update of their characters position
    if (inString.startsWith("[PlayerUpdate]"))
    {
        inString = inString.substring(14); //Remove [PlayerUpdate]

        JSONObject obj = new JSONObject(inString);
        int x = obj.getInt("X");
        int y = obj.getInt("Y");
        int rotation = obj.getInt("rotation");


        //update player position
        character.setPosition(x,y);
        character.setRotation(rotation);
    }

    //IF the client wants an update of all the cells that have changed in the map
    if (inString.startsWith("[RequestMapUpdate]"))
    {
        // old code
        //returnString = world.getMapDataToString();


        TCPServer.getInstance().globalNetworkData.put("MapData", World.getInstance().getMapDataToString());
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
