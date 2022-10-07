package KLibrary.Utils;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.net.Socket;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
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
    private final EncryptionUtils encryptionUtils;

    private boolean accepting;

    public ServerSocketWrapper(int pPort) throws IOException
    {
        clients = new ArrayList<>();
        encryptionUtils = new EncryptionUtils();
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

    private boolean establishAES(SocketWrapper pClient)
    {
        try
        {
            byte[] lInput = pClient.readAllBytes();
            PublicKey lForeignPubKey = EncryptionUtils.decodeRSAKey(lInput);
            if (lForeignPubKey == null)
                return false;

            byte[] lEncodedOwnKey = encryptionUtils.getPublicKey().getEncoded();
            pClient.writeUnencrypted(lEncodedOwnKey);

            lInput = new byte[128];
            pClient.getInStream().read(lInput);
            SecretKey lSocketsAESKey = EncryptionUtils.decodeAESKey(
                    encryptionUtils.decryptRSAToBytes(lInput));

            pClient.setAESKey(lSocketsAESKey);

            return true;
        }
        catch(Exception ignored) {}
        return false;
    }

    private class ClientLifeTimeHandler extends Thread {

        private SocketWrapper client;

        private boolean active;

        public ClientLifeTimeHandler(SocketWrapper pClient) {
            this.client = pClient;
            this.active = false;
            if (this.client != null) {
                this.active = true;
                start();
            }
        }

        @Override
        public void run()
        {
            if (!establishAES(this.client))
            {
                client.writeUnencrypted("error:encryption error occcured");
                this.close();
                return;
            }

            while (this.active)
            {
                String lMessage = this.client.readAES();
                if (lMessage == null)
                    onReceivingError(this.client, "unable to decrypt message");
                else
                    onMessage(this.client, lMessage);
            }
        }

        private void close()
        {
            this.active = false;
            this.client.close();
            clients.remove(client);
        }
    }

    public abstract void onClientConnect(SocketWrapper pClient);

    public abstract void onClientDisconnect(SocketWrapper pClient);

    public abstract void onMessage(SocketWrapper pClient, String pMessage);

    public abstract void onReceivingError(SocketWrapper pClient, String pErrorMessage);

    public java.net.ServerSocket getServerSocket() {return serverSocket;}

    public List<SocketWrapper> getClients() {return clients;}
}
