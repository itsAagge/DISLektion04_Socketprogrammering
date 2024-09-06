package Chat;

import java.io.DataOutputStream;
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
            socket = welcomSocket.accept();
            sendConfirmationServer(socket);
        } else {
            socket = new Socket(ip, port);
            sendConfirmationClient(socket, name);
        }
        ThreadIn threadIn = new ThreadIn(socket);
        ThreadOut threadOut = new ThreadOut(socket, name);
        threadIn.start();
        threadOut.start();
    }

    public static void sendConfirmationClient(Socket socket, String name) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataOutputStream.writeBytes(name + "\n");
    }

    public static void sendConfirmationServer(Socket socket) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataOutputStream.writeBytes("OK\n");
    }
}