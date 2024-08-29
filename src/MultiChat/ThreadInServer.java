package MultiChat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ThreadInServer extends Thread {
    BufferedReader inFromServer;
    String messageFromServer;
    ServerInfo serverInfo;
    DataOutputStream forwardMessage;

    public ThreadInServer(Socket clientSocket, ServerInfo serverInfo) throws IOException {
        this.inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.serverInfo = serverInfo;
    }

    public void run() {
        while (true) {
            try {
                messageFromServer = inFromServer.readLine();
                System.out.println(messageFromServer);
                for (Socket clientSocket : serverInfo.getClients()) {
                    this.forwardMessage = new DataOutputStream(clientSocket.getOutputStream());
                    forwardMessage.writeBytes(messageFromServer + '\n');
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
