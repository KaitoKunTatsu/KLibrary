package KLibrary.wrapper;

import KLibrary.utils.EncryptionUtils;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

/**
 * Thread accepting new clients connecting to the KMes Server
 *
 * @version 1.3.0 | last edit: 18.12.2022
 * @author Joshua H. | KaitoKunTatsu#3656
 * */
public abstract class ServerSocketWrapper {

    private final java.net.ServerSocket serverSocket;
    private final List<SocketWrapper> clients;
    private final EncryptionUtils encryptionUtils;
    private final boolean aesRequired;

    private boolean accepting;

    public ServerSocketWrapper(int pPort, boolean pEncryptionRequired) throws IOException {
        clients = new ArrayList<>();
        encryptionUtils = new EncryptionUtils();
        serverSocket = new java.net.ServerSocket(pPort);
        accepting = false;
        aesRequired = pEncryptionRequired;
    }

    public void acceptSockets() {
        if (accepting) return;

        accepting = true;
        new Thread(() -> {
            try {
                while (accepting) {
                    final SocketWrapper lNewClient = new SocketWrapper(serverSocket.accept());
                    new ClientLifeTimeHandler(lNewClient);
                }
            }
            catch (IOException ignored) {}

        }).start();

    }

    private boolean establishAES(SocketWrapper pClient)
    {
        try {
            byte[] lInput = pClient.readAllBytes();
            PublicKey lForeignPubKey = EncryptionUtils.decodeRSAKey(lInput);
            if (lForeignPubKey == null)
                return false;

            byte[] lEncodedOwnKey = encryptionUtils.getPublicKey().getEncoded();
            pClient.getOutStream().writeInt(lEncodedOwnKey.length);
            pClient.writeUnencrypted(lEncodedOwnKey);

            lInput = pClient.readAllBytes();
            SecretKey lSocketsAESKey = EncryptionUtils.decodeAESKey(encryptionUtils.decryptRSAToBytes(lInput));
            pClient.setAESKey(lSocketsAESKey);

            return true;
        }
        catch(Exception ex) {ex.printStackTrace();return false;}
    }

    private class ClientLifeTimeHandler extends Thread
    {
        private final SocketWrapper client;

        private boolean active;

        public ClientLifeTimeHandler(SocketWrapper pClient)
        {
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
            if (aesRequired && !establishAES(this.client)) {
                try {
                    this.client.writeUnencrypted("error:Encryption error occcured");
                }
                catch (IOException ignored) {}

                this.close();
                onClientDisconnect(this.client);
                return;
            }

            clients.add(this.client);
            onClientConnect(this.client);

            while (this.active) {
                String lMessage;
                try {
                    if (aesRequired)
                        lMessage = this.client.readAES();
                    else
                        lMessage = this.client.getInStream().readUTF();
                }
                catch (InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException | IOException ex) {
                    this.close();
                    onClientDisconnect(this.client);
                    continue;
                }

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

    public void broadcast(String pMessage) {
        if (aesRequired) {
            for (SocketWrapper wrapper : clients) {
                try {
                    wrapper.writeAES(pMessage);
                }
                catch (IOException | InvalidKeyException | InvalidAlgorithmParameterException |
                       IllegalBlockSizeException | BadPaddingException ex) {
                    onClientDisconnect(wrapper);
                }
            }
        }
        else {
            for (SocketWrapper wrapper : clients) {
                try {
                    wrapper.writeUnencrypted(pMessage);
                }
                catch (IOException ioEx) {
                    onClientDisconnect(wrapper);
                }
            }
        }
    }

    public abstract void onClientConnect(SocketWrapper pClient);

    public abstract void onClientDisconnect(SocketWrapper pClient);

    public abstract void onMessage(SocketWrapper pClient, String pMessage);

    public java.net.ServerSocket getServerSocket() {return serverSocket;}

    public List<SocketWrapper> getClients() {return clients;}

    public void stopAcceptingSockets() {accepting = false;}

    public boolean encryptionRequired() {return aesRequired;}
}
