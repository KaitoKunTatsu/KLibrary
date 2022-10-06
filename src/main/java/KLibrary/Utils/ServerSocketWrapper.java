package KLibrary.Utils;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * Thread accepting new clients connecting to the KMes Server
 *
 * @version	v1.1.1 | last edit: 06.10.2022
 * @author Joshua H. | KaitoKunTatsu#3656
 * */
abstract class ServerSocketWrapper {

    private final java.net.ServerSocket serverSocket;
    private final List<SocketWrapper> clients;

    private boolean accepting;

    public ServerSocketWrapper(int pPort) throws IOException
    {
        clients = new ArrayList<>();
        serverSocket = new java.net.ServerSocket(pPort);
        accepting = false;
    }

    private void acceptSockets()
    {
        if (accepting) return;

        accepting = true;
        new Thread(() ->
        {
            try
            {
                while (true)
                {
                    final SocketWrapper lNewClient = new SocketWrapper(serverSocket.accept());
                    clients.add(lNewClient);
                    onClientConnect(lNewClient);
                    new ClientLifeTimeHandler(lNewClient);
                }
            }
            catch (IOException ignored) {}

        }).start();

    }

    private class ClientLifeTimeHandler extends Thread {

        private SocketWrapper client;

        private boolean active;

        public ClientLifeTimeHandler(SocketWrapper pClient) {
            this.client = pClient;
            active = false;
            if (client != null) {
                active = true;
                start();
            }
        }

        @Override
        public void run() {
            while (active)
            {
                try
                {
                    String lMessage = client.readAES();
                    if (lMessage == null)
                        onReceivingError(client, "unable to decrypt message");
                    else
                        onMessage(client, lMessage);
                }
                catch (Exception ex) {
                    onReceivingError(client, ex.getMessage());
                }
            }
        }

        private void close() {
            active = false;
            clients.remove(client);
            client.close();
        }
    }

    public abstract void onClientConnect(SocketWrapper pClient);

    public abstract void onClientDisconnect(SocketWrapper pClient);

    public abstract void onMessage(SocketWrapper pClient, String pMessage);

    public abstract void onReceivingError(SocketWrapper pClient, String pErrorMessage);

    public java.net.ServerSocket getServerSocket() {return serverSocket;}

    public List<SocketWrapper> getClients() {return clients;}
}
