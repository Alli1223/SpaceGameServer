import org.json.JSONObject;
import org.json.JSONException;

public class InputReader
{
    public synchronized void ProcessJson(String inString, Character character, World world) throws JSONException
{
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
    if (inString.startsWith("[PlayerUpdate]"))
    {
        inString = inString.substring(14); //Remove [PlayerUpdate]

        JSONObject obj = new JSONObject(inString);
        int x = obj.getInt("X");
        int y = obj.getInt("Y");
        System.out.println(x + " " + y + " ");
        //update player position
        character.setPosition(x,y);
    }

    if (inString.startsWith("[RequestMapUpdate]"))
    {
        // old code
        //returnString = world.getMapDataToString();


        TCPServer.getInstance().globalNetworkData.put("MapData", "nested CellData goes here");
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
