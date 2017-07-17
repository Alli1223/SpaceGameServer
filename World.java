//Attempted singleton class
import com.sun.javafx.collections.MappingChange;
import javafx.util.*;

import java.util.HashMap;
import java.util.Map;

public class World {

	public static World world = new World();
	//private static World map = new World();

	private int worldSize = 1000;
	//private tiles = new tiles.map(new Pair(1,1)).setCellContent("test");

	//Map<Integer, Map<Integer, Cell>> map;
	private static Map<Pair, Cell> tiles;

	private World() {
		tiles = new HashMap<Pair, Cell>();
		for(int x = -worldSize; x < worldSize; x++)
			for (int y = worldSize; y < worldSize; y++)
			{
				Cell newCell = new Cell();
				newCell.setCellContent("NULL");
				Pair newPair = new Pair(x,y);

				tiles.put(newPair, newCell);
				//map.put(1, newCell.setCellContent("1"));
			}
	}

	public static World getInstance()
	{
		return world;
	}
	public Map<Pair, Cell> getTiles()
	{
		return tiles;
	}


	protected  synchronized void setCell(int x, int y, String content) {
        //tiles.put(new Pair(x, y), tiles.get())
	}


	// uncomment if we go back into a tree based system:

	// private Chunk[][] map = new Chunk[4][4];

	//public Chunk[][] getRegions() { return map; }

}
