package KLibrary.Utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * This class kinda wrapps the {@link ServerSocket} class and provides new methods for handling connected clients <br>
 * A part of the KLibrary (https://github.com/KaitoKunTatsu/KLibrary)
 *
 * @version 18.08.2022
 * @author Joshua H. | KaitoKunTatsu#3656
 * */
public class ServerSocketManager {

    private final ServerSocket serverSocket;
    // [ [Socket, OutStream, InStream], [...] ]
    private final List<List<Object>> clientConnections;

    public ServerSocketManager(int pPort, int pIOTimeout) throws IOException {
        serverSocket = new ServerSocket(pPort);
        clientConnections = new ArrayList<>();
        serverSocket.setSoTimeout(pIOTimeout);
    }

    public void acceptSocket() throws IOException {
        Socket newSocket = serverSocket.accept();
        DataOutputStream outStream = new DataOutputStream(newSocket.getOutputStream());
        DataInputStream inStream = new DataInputStream(newSocket.getInputStream());
        clientConnections.add(new ArrayList<>() {{ add(newSocket); add(outStream); add(inStream); }});
    }

    public Socket getClientSocket(int pIndex)
    {
        return (Socket) clientConnections.get(pIndex).get(0);
    }

    public List<List<Object>> getAllConnections() { return clientConnections; }

    public String readInputFromSocket(int pIndex) {
        try {
            return ((DataInputStream)clientConnections.get(pIndex).get(2)).readUTF();
        }
        catch (IOException e) {
            return "";
        }
    }

    public void writeToSocket(String pMessage, int pIndex) throws IOException {
        ((DataOutputStream) clientConnections.get(pIndex).get(1)).writeUTF(pMessage);
    }

    public void close() throws IOException {
        for (int i = 0; i < clientConnections.size(); i++)
        {
            try {
                ((Socket)clientConnections.get(i).get(0)).close();
                ((DataOutputStream)clientConnections.get(i).get(1)).close();
                ((DataInputStream)clientConnections.get(i).get(2)).close();
            }
            catch (IOException ignored) {}
            finally {clientConnections.remove(i);}
        }
        serverSocket.close();
    }

    public int getPort() {return serverSocket.getLocalPort();}

    public InetAddress getIP() {return serverSocket.getInetAddress();}
}
