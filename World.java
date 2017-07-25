// World class contains the map of cells and get and set functions for setting what the world looks like
// Is a Singleton Class
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class World
{
    public static World world = new World();
    public static World getInstance()
    {
        return world;
    }

    private InputReader ir;

	private ConcurrentHashMap<Pair, Cell> map;
    public ConcurrentHashMap<Pair, Cell> getMap()
    {
        return map;
    }

	// Constructor creates the start area for the map to save processing later in the game
	private World() {
        int startAreaSize = 10;
		map = new ConcurrentHashMap<Pair, Cell>();
		for(int x = -startAreaSize; x < startAreaSize; x++) {
            for (int y = -startAreaSize; y < startAreaSize; y++) {
                Cell newCell = new Cell();
                newCell.setCellContent("null");
                Pair newPair = new Pair(x, y);

                map.put(newPair, newCell);
            }
            //System.out.println("Creating start area " + x + " of " + startAreaSize);
        }
	}
	public synchronized String getCellDataToSring(int x, int y)
    {
        String returnString = "null";
       Cell cl =  map.get(new Pair(x,y));
        returnString = cl.getCellContent();
        return returnString;
    }

	//Not Used
    public  synchronized String getMapDataToString()
    {
        String mapData = "null";

        //Loop through all the cells and get the cells that contain data and concat them to mapData
        for (Cell value : map.values()) {
            // If the cell contains data
            if(value.getCellContent() != "null") {

                mapData = mapData.concat(value.getCellContent());
            }
        }


        return mapData;
    }
	public  synchronized void setCell(Pair point, String content)
    {
        // If map contains a value and map value is not the same as the new content
        if(map.containsKey(point))
        {
            if(map.get(point).getCellContent() != content)
            {
                // put the new cell content into the cell
                map.put(point, map.get(point)).setCellContent(content);
            }
        }

        //TODO: Create the cells when the players leave the starting area
        /*
        // Create the cell
        else
        {
            Pair cellLocation = new Pair(0,0);
            try
            {
                cellLocation = ir.GetCellPoint(content);
            }
            catch (Exception e)
            {
                System.out.println("Error in getting cellPoint: " + e);
            }
            map.put(cellLocation, new Cell());
        }
        */
	}


}
