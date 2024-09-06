package ClientTilTorbensNavneservice;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class TCPClient {

	public static void main(String[] args) throws Exception, IOException {
		
		String sentence = "";
		String modifiedSentence;
		
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

		Socket clientSocket = new Socket("192.168.0.133", 7777);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		//while (true) {
            sentence = inFromUser.readLine();
            outToServer.writeBytes(sentence + '\n');
			//if (sentence.equals("stop")) break;
            modifiedSentence = inFromServer.readLine();
            System.out.println("FROM SERVER: " + modifiedSentence);
			//if (modifiedSentence.equals("stop")) break;
        //}
		clientSocket.close();
			
	}

}
