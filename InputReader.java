
public class InputReader 
{
	public synchronized void ProcessInput(String input, Character character)
	{

		input.toUpperCase();
		int nameEndPoint = 0;
		int nameStartPoint = 0;
		int xValStart = 0;
		int yValStart = 0;
		String playerName = null;
		System.out.println("Processing Input..");
		if(input.startsWith("{"))
		{
			nameStartPoint = input.indexOf("<");
			nameEndPoint = input.indexOf(">");

			playerName = String.copyValueOf(input.toCharArray(), nameStartPoint, nameEndPoint);
			//character.setName(playerName);
			
			
			
		}
	}
	
}
