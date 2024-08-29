package MultiChat;

import java.io.IOException;
import java.net.Socket;

public class ThreadConnectionClient extends Thread {
    Socket socket;
    String clientName;

    public ThreadConnectionClient(Socket socket, String clientName) {
        this.socket = socket;
        this.clientName = clientName;
    }

    public void run() {
        try {
            ThreadIn threadIn = new ThreadIn(socket);
            ThreadOut threadOut = new ThreadOut(socket, clientName);
            threadIn.start();
            threadOut.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
