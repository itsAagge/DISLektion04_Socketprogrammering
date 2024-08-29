package Chat;

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
        } else {
            socket = new Socket(ip, port);
        }
        ThreadIn threadIn = new ThreadIn(socket);
        ThreadOut threadOut = new ThreadOut(socket, name);
        threadIn.start();
        threadOut.start();
    }
}
