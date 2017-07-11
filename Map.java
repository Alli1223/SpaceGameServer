//Attempted singleton class

import javafx.scene.control.*;

import java.util.Collection;
import java.util.NavigableMap;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Map {

	private static Map map = new Map();
	private int cellSize = 50;
	private int EditableMapSize = 1000;

	// Old cell system
    //private Cell[][] cells = new Cell[3000][3000];

    ConcurrentMap<Pair, Cell> tiles = new ConcurrentMap<Pair, Cell>() {
        @Override
        public Cell putIfAbsent(Pair key, Cell value) {
            return null;
        }

        @Override
        public boolean remove(Object key, Object value) {
            return false;
        }

        @Override
        public boolean replace(Pair key, Cell oldValue, Cell newValue) {
            return false;
        }

        @Override
        public Cell replace(Pair key, Cell value) {
            return null;
        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean containsKey(Object key) {
            return false;
        }

        @Override
        public boolean containsValue(Object value) {
            return false;
        }

        @Override
        public Cell get(Object key) {
            return null;
        }

        @Override
        public Cell put(Pair key, Cell value) {
            return null;
        }

        @Override
        public Cell remove(Object key) {
            return null;
        }

        @Override
        public void putAll(java.util.Map<? extends Pair, ? extends Cell> m) {

        }

        @Override
        public void clear() {

        }

        @Override
        public Set<Pair> keySet() {
            return null;
        }

        @Override
        public Collection<Cell> values() {
            return null;
        }

        @Override
        public Set<Entry<Pair, Cell>> entrySet() {
            return null;
        }
    };


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
		for (int x = -EditableMapSize; x < EditableMapSize; x++) {
			System.out.println("Generating Map: " + x + " OF " + EditableMapSize);
			for (int y = -EditableMapSize; y < EditableMapSize; y++) {
                tiles.put(new Pair(x, y), new Cell());
			}
		}
		System.out.println("Map Complete");
	}

	//private Cell[][] cells = new Cell[3000][3000];

	protected void setCell(int x, int y, String content) {
        tiles.get(new Pair(x, y)).setCellContent(content);
	}


	// uncomment if we go back into a tree based system:

	// private Chunk[][] map = new Chunk[4][4];

	//public Chunk[][] getRegions() { return map; }

}
