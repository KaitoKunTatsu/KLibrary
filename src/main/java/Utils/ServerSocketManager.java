package Utils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerSocketManager {

    private final ServerSocket serverSocket;
    private final List<Socket> clientSockets;

    public ServerSocketManager(int pPort) throws IOException {
        serverSocket = new ServerSocket(pPort);
        clientSockets = new ArrayList<>();
    }

    public void close() {
        for (Socket socket : clientSockets){
        }
    }
}
