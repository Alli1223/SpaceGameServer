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
}
    public synchronized Pair GetCellPoint(String jsonString) throws JSONException
    {
            JSONObject obj = new JSONObject(jsonString);
            int x = obj.getInt("X");
            int y = obj.getInt("Y");
            Pair cellLocation = new Pair(x, y);
            return cellLocation;
    }


    //TODO: refactor this.
	public synchronized void ProcessPlayerMovement(String inString, Character character) {

        int x = 0;
        int y = 0;

        String playerXPos = null;
        String playerYPos = null;
        //System.out.println("Processing Input..");
        if (inString.startsWith("{")) {
            int xValStart = 0;
            int xValEnd = 0;
            int yValStart = 0;
            int yValEnd = 0;
            xValStart = inString.indexOf("X") + 2;
            xValEnd = inString.indexOf(".") - 1;
            yValStart = inString.indexOf("Y") + 2;
            yValEnd = inString.lastIndexOf(".") - 1;


            playerXPos = String.copyValueOf(inString.toCharArray(), xValStart, xValEnd - xValStart + 1);
            playerYPos = String.copyValueOf(inString.toCharArray(), yValStart, yValEnd - yValStart + 1);

            character.setPosition(Integer.parseInt(playerXPos), Integer.parseInt(playerYPos));

            TCPServer.getInstance().outputCommands = inString;

        }
    }


}
