import org.json.*;
import net.sf.json.JSONSerializer;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONObject;
import org.json.JSONException;



import org.json.JSONString;
import sun.plugin.javascript.ocx.JSObject;

public class InputReader
{
	public synchronized void ProcessInput(String inString, Character character, Map map) throws JSONException{

        inString.toUpperCase();

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


            //input.charAt(xValStart + 2);

            //Integer.parseInt(playerXPos);
            character.setPosition(Integer.parseInt(playerXPos), Integer.parseInt(playerYPos));

            TCPServer.getInstance().outputCommands = inString;
			
			/* NO NEED TO PROCESS NAME AS CLIENT ONLY SENDS THEIR X AND Y
			nameStartPoint = input.indexOf("<");
			nameEndPoint = input.indexOf(">");

			
			for(int c = 0; c < TCPServer.getInstance().numOfConnectedClients; c++)
				if(playerName != TCPServer.getInstance().)
			playerName = String.copyValueOf(input.toCharArray(), nameStartPoint, nameEndPoint);
			character.setName(playerName);
			*/
        }
        else if (inString.startsWith("["))
        {
            String jsonData = JSONParser.quote(inString);
            JSONObject jsonObj = (JSONObject) JSONSerializer.toJSON(jsonData);
            JSONObject cellData = jsonObj.getJSONObject("CellData");

            int cellX = jsonObj.getInt("X");
            int cellY = jsonObj.getInt("Y");

            System.out.println(cellX + " " + cellY);




        }
    }

	
}
