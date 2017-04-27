//Attempted singleton class

public class Map {

	private static Map map = new Map();
	private int cellSize = 50;

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

	// private Region[][] map = new Region[4][4];

	// public Region[][] getRegions() { return map; }

}
