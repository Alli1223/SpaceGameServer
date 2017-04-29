
public class InputReader 
{
	public synchronized void ProcessInput(String input, Character character)
	{

		input.toUpperCase();

		int x = 0;
		int y = 0;
		int xValStart = 0;
		int xValEnd = 0;
		int yValStart = 0;
		int yValEnd = 0;
		String playerXPos = null;
		String playerYPos = null;
		//System.out.println("Processing Input..");
		if(input.startsWith("{"))
		{
			
			xValStart = input.indexOf("X") + 2;
			xValEnd = input.indexOf(".") - 1;
			yValStart = input.indexOf("Y") + 2;
			yValEnd = input.lastIndexOf(".") - 1;
			
			
			playerXPos = String.copyValueOf(input.toCharArray(), xValStart, xValEnd - xValStart + 1);
			playerYPos = String.copyValueOf(input.toCharArray(), yValStart, yValEnd - yValStart + 1);
			
			
			//input.charAt(xValStart + 2);
			
			//Integer.parseInt(playerXPos);
			character.setPosition(Integer.parseInt(playerXPos), Integer.parseInt(playerYPos));
			
			TCPServer.getInstance().outputCommands = input;
			
			/* NO NEED TO PROCESS NAME AS CLIENT ONLY SENDS THEIR X AND Y
			nameStartPoint = input.indexOf("<");
			nameEndPoint = input.indexOf(">");

			
			for(int c = 0; c < TCPServer.getInstance().numOfConnectedClients; c++)
				if(playerName != TCPServer.getInstance().)
			playerName = String.copyValueOf(input.toCharArray(), nameStartPoint, nameEndPoint);
			character.setName(playerName);
			*/ 
			
			
			
		}
	}
	
}
