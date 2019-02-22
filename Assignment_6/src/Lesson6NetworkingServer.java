import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Lesson6NetworkingServer {

	//Default portNumber
	private static int portNum = 1;
	
	//Move to external file if it grows
	private static String output = "HTTP/1.0 200 OK\r\n" + 
			"Content-Type: text/html\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"<html>\r\n" + 
			"<head><title>Java Networking</title></head>\r\n" + 
			"<body>\r\n" + 
			"<h1>Java Networking</h1>\r\n" + 
			"</body>\r\n" + 
			"</html>";
	
	
	public static void main(String[] args) {
		
		//Parse arguments
		if( args[0].contentEquals("--port")) {
			try{
				portNum = Integer.parseInt(args[1]);
			} catch(NumberFormatException e) {
				System.out.println("Port argument must be an integer.");
			}
		}
		else {
			showHelp();
		}
		
		
		try{
			//Start the sever
			ServerSocket server = new ServerSocket(portNum);
			
			while(true) {
				//Wait for a connection
				Socket incoming = server.accept();
				
				//Send output
				OutputStream os = incoming.getOutputStream();
				PrintWriter out = new PrintWriter(new OutputStreamWriter(os, "UTF-8"), true);
				out.println(output);
				
				//Close connection
				out.close();
				incoming.close();
			}
			
		} catch(IOException e) {
			System.out.println("Server connection failed");
		} catch(IllegalArgumentException e) {
			System.out.println("Invalid port number, must be between 0 and 65535 inclusive");
		}

		
		
		
	}

	/*
	 * Private method displays help dialog.
	 */
	private static void showHelp() {
		System.out.println("Use --port followed by an integer to start the sever.");
	}
}
