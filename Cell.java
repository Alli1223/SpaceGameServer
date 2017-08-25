
public class Cell {
	// the smallest form of area
	public Cell(){
	}
	// Whether there is anything in the cell
	private String cellContent;
	public int setPlantGrowthStage(int newStage) {return growthStage = newStage;}
	public int getPlantGrowthStage() {return growthStage; }
	private int growthStage = 0;
	
	// Bool for whether a character is in the cell
	public boolean cellTaken = false;
	// Setter
	public void setCellContent(String content) {cellContent = content;}
	// Getter
	public String getCellContent() {return cellContent;}

}
