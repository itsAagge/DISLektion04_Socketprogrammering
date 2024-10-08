package MultiChat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        Socket socket;
        String name = "August";
        String ip = "";
        int port = 6789;

        if (ip.isEmpty()) {
            ServerSocket welcomSocket = new ServerSocket(port);
            ServerInfo serverInfo = new ServerInfo();
            ThreadConnectionServer serverConnection = new ThreadConnectionServer(welcomSocket, name, serverInfo);
            serverConnection.start();
        } else {
            socket = new Socket(ip, port);
            ThreadConnectionClient clientConnection = new ThreadConnectionClient(socket, name);
            clientConnection.start();
        }
    }
}
