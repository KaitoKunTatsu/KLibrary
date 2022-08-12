package Utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerSocketManager {

    private final ServerSocket serverSocket;
    // [ [Socket, OutStream, InStream], [...] ]
    private final List<List<Object>> clientSockets;

    public ServerSocketManager(int pPort, int pIOTimeout) throws IOException {
        serverSocket = new ServerSocket(pPort);
        clientSockets = new ArrayList<>();
        serverSocket.setSoTimeout(pIOTimeout);
    }

    public void acceptSocket() throws IOException {
        Socket newSocket = serverSocket.accept();
        DataOutputStream outStream = new DataOutputStream(newSocket.getOutputStream());
        DataInputStream inStream = new DataInputStream(newSocket.getInputStream());
        clientSockets.add(new ArrayList<>() {{ add(newSocket); add(outStream); add(inStream); }});
    }

    public Socket getClientSocket(int pIndex)
    {
        return (Socket) clientSockets.get(pIndex).get(0);
    }

    public String readInputFromSocket(int pIndex) {
        try {
            return ((DataInputStream)clientSockets.get(pIndex).get(2)).readUTF();
        }
        catch (IOException e) {
            return "";
        }
    }

    public void writeToSocket(String pMessage, int pIndex) throws IOException {
        ((DataOutputStream) clientSockets.get(pIndex).get(1)).writeUTF(pMessage);
    }

    public void close() throws IOException {
        for (int i = 0; i < clientSockets.size(); i++)
        {
            try {
                ((Socket)clientSockets.get(i).get(0)).close();
                ((DataOutputStream)clientSockets.get(i).get(1)).close();
                ((DataInputStream)clientSockets.get(i).get(2)).close();
            }
            catch (IOException ignored) {}
            finally {clientSockets.remove(i);}
        }
        serverSocket.close();
    }
}
