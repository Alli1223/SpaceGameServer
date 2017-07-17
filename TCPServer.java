import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import org.json.JSONException;

//Source From http://makemobiapps.blogspot.co.uk/p/multiple-client-server-chat-programming.html

/*
 * A chat server that delivers public and private messages.
 */
public class TCPServer {
	StringBuilder AllActions = new StringBuilder();
	private static TCPServer tCPServer = new TCPServer();
	
	public String outputCommands = null;

	// The server socket.
	private static ServerSocket serverSocket = null;
	// The client socket.
	private static Socket clientSocket = null;
	
	public int numOfConnectedClients = 0;

	// This chat server can accept up to maxClientsCount clients' connections.
	private static final int maxClientsCount = 40;
	private static final clientThread[] threads = new clientThread[maxClientsCount];

	//Our Code////////////////////////////////////////////////////////////
	public clientThread[] GetThreads() {
		return threads;
	}
	
	//Get the instance of this server
	public static TCPServer getInstance(){
		return tCPServer;
	}
	
	private TCPServer(){}
	
	//Our code ends////////////////////////////////////////////////////////
	public static void main(String args[]) throws JSONException {

		// The default port number.
		int portNumber = 2222;
		if (args.length < 1) {
			System.out.println(
					"Server running on port number: " + portNumber);
		}
		//Run on other specified port
		else {
			portNumber = Integer.valueOf(args[0]).intValue();
		}
		
		

		/*
		 * Open a server socket on the portNumber (default 2222). Note that we
		 * can not choose a port less than 1023 if we are not privileged users
		 * (root).
		 */
		try {
			serverSocket = new ServerSocket(portNumber);
		} catch (IOException e) {
			System.out.println(e);
		}

		/*
		 * Create a client socket for each connection and pass it to a new
		 * client thread.
		 */
		while (true) {
			try {
				clientSocket = serverSocket.accept();
				int i = 0;
				for (i = 0; i < maxClientsCount; i++) 
				{
					if (threads[i] == null) 
					{
						// Create new client thread
						(threads[i] = new clientThread(clientSocket, threads, i)).start();
						break;
					}
				}
				if (i == maxClientsCount) {
					PrintStream os = new PrintStream(clientSocket.getOutputStream());
					os.println("Server too busy. Try later.");
					os.close();
					clientSocket.close();
				}
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}
}

// Client thread //////////////////////////////
class clientThread extends Thread {

	private String clientName = null;
	private DataInputStream inStream = null;
	private PrintStream outStream = null;
	private Socket clientSocket = null;
	private final clientThread[] threads;
	//Our Code////////////////////////////////////////////////////////////

	private InputReader ir = new InputReader();
	private int maxClientsCount;
	private Character character = new Character(100, 100);
	private World map = World.getInstance();
	private final int ID;
	

	public clientThread(Socket clientSocket, clientThread[] threads, int ID) {
		this.clientSocket = clientSocket;
		this.threads = threads;
		this.ID = ID;
		maxClientsCount = threads.length;
	}

	public int getID(){
		return ID;
	}
	

	@SuppressWarnings("deprecation")
	public void run()  {
		int maxClientsCount = this.maxClientsCount;

		clientThread[] threads = this.threads;
		

		// Create streams and ask their name
		try {
			/*
			 * Create input and output streams for this client.
			 */
			inStream = new DataInputStream(clientSocket.getInputStream());
			outStream = new PrintStream(clientSocket.getOutputStream());
			String name = inStream.readLine();

			//TODO: send the client their id for communication


			
			/* Welcome the new the client. */
			outStream.println("{<" + name + "> X:" +  character.getX() + " Y:" +  character.getY()+"}");
			TCPServer.getInstance().numOfConnectedClients++;
			
			outStream.flush();
			//Database.insertIntoHighscoreDatabase(this.getID(), clientName, 10);
			//Database.printHighscoreDatabase();
			// Send player update info to all the other clients
			synchronized (this) 
			{
				for (int i = 0; i < maxClientsCount; i++) 
				{
					if (threads[i] != null && threads[i] == this) 
					{
						System.out.println("Player " + name + " Joined." );
						clientName = name;
						break;
					}
				}
				for (int i = 0; i < maxClientsCount; i++) 
				{
					if (threads[i] != null && threads[i] != this) 
					{
						threads[i].outStream.println("{<" + name + "> X:" +  character.getX() + " Y:" +  character.getY()+"}");
					}
				}
			}



			/* Each Thread will Process their command */
			while (true) 
			{
				String line = inStream.readLine().trim();



				//ir.ProcessInput(line, character, map);
				// Proccess the json
                try{
                    ir.ProcessJson(line, character, map);
                }
                catch(JSONException e)
                {
                    System.out.println("JSON ERRROR: " + e);
                }


				//System.out.println(line);

				
				// Move the character
				//character.moveCharacter(line);
				

				//System.out.println("Client: " + character.getX() + " " + character.getY());
				// Sends number of players ins session
				if(line.equals("NUMBER_OF_PLAYERS_REQUEST"))
				{
					int numberOfCurrentPlayers = 0;
					for (int j = 0; j < maxClientsCount; j++) 
					{
						if (threads[j] != null && threads[j].clientName != null) 
						{
							numberOfCurrentPlayers++;
						}
					}
					//Print player number
					outStream.println("[#:" + 0 + numberOfCurrentPlayers +"].");
					outStream.flush();
				}


				//Process actions
				if(line.equals("PLACE_BED") || line.equals("PLACE_BOX"))
				{
					//map.setCell(character.getX() / map.getCellSize(), character.getY() / map.getCellSize(), line);
				}


				

				// concurrently send strings of player positions to all the clients
				for (int i = 0; i < maxClientsCount; i++) 
				{
					if (threads[i] != null && threads[i].clientName != null) 
					{

						//threads[i].outStream.println(line);

						//Send individual command as well


							outStream.println("{<" + threads[i].clientName + "> X:" +  threads[i].character.getX() + ". Y:" +  threads[i].character.getY() + ".}\n");
						//threads[i].outStream.flush();


					}
				}
				

				// Disconnect the client if they send the quit message
				if(line.equals("QUIT"))
				{
					synchronized (this) 
					{
						for (int i = 0; i < maxClientsCount; i++) 
						{
							if (threads[i] != null && threads[i] != this && threads[i].clientName != null) 
							{
								threads[i].outStream.println("The player " + name + " is leaving");
							}
						}
					}
					System.out.println("Player " + name + " Leaving");


					synchronized (this) 
					{
						for (int i = 0; i < maxClientsCount; i++) {
							if (threads[i] == this) {
								threads[i] = null;
							}
						}
					}
					/*
					 * Close the output stream, close the input stream, close the
					 * socket.
					 */
					
					inStream.close();
					outStream.close();
					clientSocket.close();
				}
			}
		} catch (IOException e) 
		{
			System.out.println("Error in main block: " + e);
		}
	}

	// Function for getting the client ID for each client thread
	public long GetClientThread() {

		return Thread.currentThread().getId();
	}

	public Character GetCharacter() {
		return character;
	}
	//Our Code Ends////////////////////////////////////////////////////////

}
