// World class contains the map of cells and get and set functions for setting what the world looks like
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class World
{
	public static World world = new World();
    public static World getInstance()
    {
        return world;
    }

	private int startAreaSize = 1000;

	private Map<Pair, Cell> map;


	// Constructor creates the start area for the map to save processing later in the game
	private World() {
		map = new ConcurrentHashMap<Pair, Cell>();
		for(int x = -startAreaSize; x < startAreaSize; x++) {
            for (int y = startAreaSize; y < startAreaSize; y++) {
                Cell newCell = new Cell();
                newCell.setCellContent("NULL");
                Pair newPair = new Pair(x, y);

                map.put(newPair, newCell);
            }
            //System.out.println("Creating start area " + x + " of " + startAreaSize);
        }
	}

	public Map<Pair, Cell> getMap()
	{
		return map;
	}


	protected  synchronized void setCell(Pair point, String content)
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

	}


}
