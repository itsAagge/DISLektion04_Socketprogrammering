package SocketEks2023;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

	public static void main(String[] args) throws Exception {
		
		String clientSentence;
		String capitalizedSentence = "";
		ServerSocket welcomSocket = new ServerSocket(6789);

		Socket connectionSocket = welcomSocket.accept();
		BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
		DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

		while(!capitalizedSentence.equals("stop")){
			clientSentence = inFromClient.readLine();
			String ip = connectionSocket.getInetAddress().toString();
			System.out.println("Connection from " + ip);
			System.out.println("Message: " + clientSentence);
			capitalizedSentence = clientSentence.toUpperCase() + '\n';
			outToClient.writeBytes(capitalizedSentence);
		}

	}

}
