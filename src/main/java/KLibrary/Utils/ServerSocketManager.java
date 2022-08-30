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
 * @version	v1.1.1 | last edit: 19.08.2022
 * @author Joshua H. | KaitoKunTatsu#3656
 */
public class ServerSocketManager {

    private final ServerSocket serverSocket;
    // [ [Socket, OutStream, InStream], [...] ]
    private final List<List<Object>> clientConnections;

    /**
     * @param pPort port on which the server will run and listen for inputs
     * @param pIOTimeout how long the server tries to receive an input from a socket, in milliseconds
     * */
    public ServerSocketManager(int pPort, int pIOTimeout) throws IOException {
        serverSocket = new ServerSocket(pPort);
        clientConnections = new ArrayList<>();
        serverSocket.setSoTimeout(pIOTimeout);
    }


    /**
     * Listens for a new socket and adds that new socket and it's streams to the list of connections, <br>
     * timeout after a certain amount of milliseconds defined in the class constructor
     * */
    public void acceptSocket() throws IOException {
        Socket newSocket = serverSocket.accept();
        DataOutputStream outStream = new DataOutputStream(newSocket.getOutputStream());
        DataInputStream inStream = new DataInputStream(newSocket.getInputStream());
        clientConnections.add(new ArrayList<>() {{ add(newSocket); add(outStream); add(inStream); }});
    }

    /**
     * @param pIndex index of the socket you want to receive
     * @return the socket on the specified index
     * */
    public Socket getClientSocket(int pIndex)
    {
        return (Socket) clientConnections.get(pIndex).get(0);
    }

    /**
     * @return the list of connections: [ [socket, output steam, input stream], [...], ... ]
     * */
    public List<List<Object>> getAllConnections() { return clientConnections; }

    /**
     * @param pIndex index of the socket you want to read the input from
     * @return The received input from the socket, empty string if nothing was received before the timeout.
     * */
    public String readInputFromSocket(int pIndex) {
        try {
            return ((DataInputStream)clientConnections.get(pIndex).get(2)).readUTF();
        }
        catch (IOException e) {
            return "";
        }
    }

    /**
     * @param pIndex index of the socket you want to read the input from
     * @param pMessage message you want to write to the socket
     * */
    public void writeToSocket(int pMessage, int pIndex) throws IOException {
        ((DataOutputStream) clientConnections.get(pIndex).get(1)).writeInt(pMessage);
    }

    /**
     * @param pIndex index of the socket you want to read the input from
     * @param pMessage message you want to write to the socket
     * */
    public void writeToSocket(byte[] pMessage, int pIndex) throws IOException {
        ((DataOutputStream) clientConnections.get(pIndex).get(1)).write(pMessage);
    }

    /**
     * @param pIndex index of the socket you want to read the input from
     * @param pMessage message you want to write to the socket
     * */
    public void writeToSocket(long pMessage, int pIndex) throws IOException {
        ((DataOutputStream) clientConnections.get(pIndex).get(1)).writeLong(pMessage);
    }

    /**
     * @param pIndex index of the socket you want to read the input from
     * @param pMessage message you want to write to the socket
     * */
    public void writeToSocket(String pMessage, int pIndex) throws IOException {
        ((DataOutputStream) clientConnections.get(pIndex).get(1)).writeUTF(pMessage);
    }

    /**
     * @param pIndex index of the socket you want to read the input from
     * @param pMessage message you want to write to the socket
     * */
    public void writeToSocket(double pMessage, int pIndex) throws IOException {
        ((DataOutputStream) clientConnections.get(pIndex).get(1)).writeDouble(pMessage);
    }

    /**
     * @param pIndex index of the socket you want to read the input from
     * @param pMessage message you want to write to the socket
     * */
    public void writeToSocket(boolean pMessage, int pIndex) throws IOException {
        ((DataOutputStream) clientConnections.get(pIndex).get(1)).writeBoolean(pMessage);
    }

    /**
     * @param pIndex index of the socket you want to read the input from
     * @param pMessage message you want to write to the socket
     * */
    public void writeToSocket(float pMessage, int pIndex) throws IOException {
        ((DataOutputStream) clientConnections.get(pIndex).get(1)).writeFloat(pMessage);
    }


    /**
     * Clears the list of connections and disconnects them
     * */
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

    /**
     * @return the port on which the server listens
     * */
    public int getPort() {return serverSocket.getLocalPort();}

    /**
     * @return the server's InetAddress
     * */
    public InetAddress getIP() {return serverSocket.getInetAddress();}
}
