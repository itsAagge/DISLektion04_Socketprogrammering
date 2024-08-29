package MultiChat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ThreadOutServer extends Thread {
    BufferedReader inFromUser;
    DataOutputStream outToServer;
    String messageFromUser;
    String name;
    ServerInfo serverInfo;

    public ThreadOutServer(String name, ServerInfo serverInfo) {
        this.inFromUser = new BufferedReader(new InputStreamReader(System.in));
        this.name = name;
        this.serverInfo = serverInfo;
    }

    public void run() {
        while (true) {
            try {
                messageFromUser = inFromUser.readLine();
                for (Socket clientSocket : serverInfo.getClients()) {
                    this.outToServer = new DataOutputStream(clientSocket.getOutputStream());
                    outToServer.writeBytes(name + ": " + messageFromUser + '\n');
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
