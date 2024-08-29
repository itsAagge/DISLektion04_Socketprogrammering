package MultiChat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ThreadConnectionServer extends Thread {
    ServerSocket serverSocket;
    String clientName;
    ServerInfo serverInfo;


    public ThreadConnectionServer(ServerSocket serverSocket, String clientName, ServerInfo serverInfo) {
        this.serverSocket = serverSocket;
        this.clientName = clientName;
        this.serverInfo = serverInfo;
    }

    public void run() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                serverInfo.getClients().add(socket);
                ThreadInServer threadInServer = new ThreadInServer(socket, serverInfo);
                ThreadOutServer threadOutServer = new ThreadOutServer(clientName, serverInfo);
                threadInServer.start();
                threadOutServer.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
