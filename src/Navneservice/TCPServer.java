package Navneservice;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class TCPServer {

	private static Map<String, String> map = new HashMap<>();

	public static void main(String[] args) throws Exception {
		
		String clientSentence;
		ServerSocket welcomSocket = new ServerSocket(6789);

		Socket connectionSocket = welcomSocket.accept();
		BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
		DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

		outToClient.writeBytes("Please state your name\n");
		clientSentence = inFromClient.readLine().toLowerCase();
		map.put(clientSentence, connectionSocket.getInetAddress().toString());
		outToClient.writeBytes("Your IP has been added to the server under the name: " + clientSentence + ". To end the connection, write \"stop\", otherwise write a name for an IP request.\n");

		clientSentence = inFromClient.readLine().toLowerCase();
		while(!clientSentence.equals("stop")){
			String ip = map.get(clientSentence);
			if (ip != null) {
				outToClient.writeBytes(clientSentence + " has the IP: " + ip + ". ");
			} else {
				outToClient.writeBytes("The name was not found in our system. ");
			}
			outToClient.writeBytes("To end the connection, write \"stop\", otherwise write a name for an IP request\n");
			clientSentence = inFromClient.readLine().toLowerCase();
		}

	}

}
