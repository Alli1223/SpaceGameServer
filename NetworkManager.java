import net.sf.json.JSON;
import org.json.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class NetworkManager //Singleton class
{
    private static NetworkManager NetworkManager = new NetworkManager();

    public static NetworkManager getInstance() {
        return NetworkManager;
    }

    private NetworkManager() {
    }

    private String[] listOfCurrentPlayers = new String[TCPServer.getInstance().maxClientsCount];

    public ConcurrentHashMap<Integer, JSONObject> allPlayers = new ConcurrentHashMap<Integer, JSONObject>();



    // Check the list of players to see if they exist
    private boolean DoesPlayerExist(String name) {
        for (int i = 0; i < listOfCurrentPlayers.length; i++) {
            if (listOfCurrentPlayers[i] == name) {
                return true;
            }

        }
        return false;
    }

    private void AddPlayerToList(String name) {
        for (int i = 0; i < TCPServer.getInstance().maxClientsCount; i++) {

            if (listOfCurrentPlayers[i] == null) {
                listOfCurrentPlayers[i] = name;
                return;
            }
        }
    }


    public synchronized void AddToPlayerUpdateList(JSONObject playerJsonObject, int id) {
        try {
            // Make sure that the players data hasn't aready been added
            String name = playerJsonObject.getString("name");

            // IF Player does not exist add to pending network pool
            if (!DoesPlayerExist(name)) {
                allPlayers.put(id, playerJsonObject);
                AddPlayerToList(name);
            }

        } catch (Exception e) {
            System.out.println("Error adding player data to network pool: " + e);
        }
    }

    public synchronized String RequestAllPlayerData() {
        JSONObject allPlayerDataToReturn = new JSONObject();
        JSONArray arrayOfPlayerData = new JSONArray();
        for (int i = 0; i < TCPServer.getInstance().maxClientsCount; i++) {
            if (allPlayers.get(i) != null)
                arrayOfPlayerData.put(allPlayers.get(i));
        }
        try {
            allPlayerDataToReturn.put("PlayerData", arrayOfPlayerData);
            return allPlayerDataToReturn.toString();
        } catch (Exception e) {
            System.out.println("Error in creating list of players to send." + e);
            return "noPlayersToSend";
        }
        //Return nothing if there are no players
    }
    public synchronized String RequestAllMapData()
    {
        JSONObject allWorldJsonData = new JSONObject();

       JSONArray worldData = World.getInstance().getMapDataToJsonArray();
       try {
           allWorldJsonData.put("MapData", worldData);
           return allWorldJsonData.toString();
       }
       catch (Exception e)
       {
           System.out.println("Error in adding cell data to json object" + e);
           return "mapIsEmpty";
       }
    }

}
