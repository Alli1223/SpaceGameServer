//Attempted singleton class
import com.sun.javafx.collections.MappingChange;
import javafx.util.*;

import java.util.HashMap;
import java.util.Map;

public class World {

	public static World world = new World();
	//private static World map = new World();

	private int worldSize = 100;
	//private tiles = new tiles.map(new Pair(1,1)).setCellContent("test");

	private static Map<Integer, Map<Integer, Cell>> map;
	//MappingChange.Map<Pair, Cell> tiles;

	private World() {
		for(int x = -worldSize; x < worldSize; x++)
			for (int y = -worldSize; y < worldSize; y++)
			{
				Cell newCell = new Cell();

				//map.put(1, newCell.setCellContent("1"));
			}
	}

	public static World getInstance()
	{
		return world;
	}
	public Map<Integer, Map<Integer, Cell>> getTiles()
	{
		return map;
	}


	protected  synchronized void setCell(int x, int y, String content) {
        map.get(1).get(2).setCellContent(content);
	}


	// uncomment if we go back into a tree based system:

	// private Chunk[][] map = new Chunk[4][4];

	//public Chunk[][] getRegions() { return map; }

}
