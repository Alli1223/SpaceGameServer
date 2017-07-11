//Attempted singleton class

import javafx.scene.control.*;

import java.util.NavigableMap;
import java.util.concurrent.ConcurrentHashMap;

public class Map {

	private static Map map = new Map();
	private int cellSize = 50;

    ConcurrentHashMap<Integer,ConcurrentHashMap<Integer, Chunk>> World = new ConcurrentHashMap<Integer,ConcurrentHashMap<Integer, Chunk>>(1000);



	public static Map getInstance() {
		return map;
	}
	public int getCellSize()
	{
		return cellSize;
	}

	private Map() {



		// populates the map
		System.out.println("Starting Map Gen");
		for (int x = 0; x < cells.length; x++) {
			System.out.println("Generating Map: " + x + " OF " + cells.length);
			for (int y = 0; y < cells[0].length; y++) {
				cells[x][y] = new Cell();
			}
		}
		System.out.println("Map Complete");
	}

	private Cell[][] cells = new Cell[3000][3000];

	protected void setCell(int x, int y, String content) {
		getCells()[x][y].setCellContent(content);
	}

	protected Cell[][] getCells() {
		return cells;
	}

	// uncomment if we go back into a tree based system:

	// private Chunk[][] map = new Chunk[4][4];

	//public Chunk[][] getRegions() { return map; }

}
