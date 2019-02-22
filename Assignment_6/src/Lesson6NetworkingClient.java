import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Lesson6NetworkingClient {

	private static String serverName = "";
	private static int portNum = 1;
	
	public static void main(String[] args) {
		
		
		//Parse arguments
		if(args[0].contentEquals("--server")) {
			serverName = args[1];
		}
		else { showHelp(); }
		
		if(args[2].contentEquals("--port")) {
			try{
				portNum = Integer.parseInt(args[3]);
			} catch(NumberFormatException e) {
				System.out.println("Port argument must be an integer.");
			}
		}
		else { showHelp(); }
		
		//Connect to server and retrieve response
		try{
			Socket socket = new Socket(serverName, portNum);
			Scanner in = new Scanner(socket.getInputStream(), "UTF-8"); 
			
			while(in.hasNextLine()) {
				System.out.println(in.nextLine());
			}
		
		} catch(UnknownHostException e) {
			System.out.println("Host is not valid.");
		} catch(IllegalArgumentException e) {
			System.out.println("Invalid port number, must be between 0 and 65535 inclusive");
		} catch (IOException e) {
			System.out.println("Server connection failed");
		}
		

	}
	
	/*
	 * Private method displays help dialog.
	 */
	private static void showHelp() {
		System.out.println("Use --server followed by the hostname,\r\n" +
						   "then --port followed by an integer to start the sever.\r\n" +
						   "Example: --server localhost --port 8080");
	}

}
