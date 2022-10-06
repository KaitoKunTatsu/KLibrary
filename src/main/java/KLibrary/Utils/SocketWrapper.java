package KLibrary.Utils;

// Own Library https://github.com/KaitoKunTatsu/KLibrary

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

/**
 * This class wraps a {@link Socket} and provides read, write, etc. methods
 *
 * @version v2.0.0 | last edit: 26.09.2022
 * @author Joshua H. | KaitoKunTatsu#3656
 * */
class SocketWrapper {

    private Socket client;
    private DataInputStream reader;
    private DataOutputStream writer;

    private SecretKey AESKey;

    /**
     * @param pSocket Socket to wrap
     * @param pAESKey {@link SecretKey} used to encrypt messages via AES
     * */
    public SocketWrapper(Socket pSocket, SecretKey pAESKey) throws IOException
    {
        client = pSocket;
        AESKey = pAESKey;

        try {
            reader = new DataInputStream(client.getInputStream());
            writer = new DataOutputStream(client.getOutputStream());
        }
        catch(SocketException socketException) {close();}
    }

    public SocketWrapper(Socket pSocket) throws IOException {
        this(pSocket, null);
    }

    protected void writeAES(String pMessage) throws InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, IOException {
        if (AESKey == null) return;

        byte[] lEncrpytedMessage = EncryptionUtils.encryptAES(pMessage, AESKey);
        byte[] lMessageSizeAsBytes = ByteBuffer.allocate(4).putInt(lEncrpytedMessage.length).array();
        byte[] lConcatenated = ByteBuffer.allocate(lMessageSizeAsBytes.length+lEncrpytedMessage.length).put(lMessageSizeAsBytes).put(lEncrpytedMessage).array();
        writer.write(lConcatenated, 0, lConcatenated.length);
    }

    protected void writeUnencrypted(String pMessage) throws IOException {
        writer.writeUTF(pMessage);
    }

    protected void writeUnencrypted(byte[] pMessage) throws IOException {
        writer.write(pMessage);
    }

    public byte[] readAllBytes() throws IOException {
        try
        {
            int lSize = reader.readInt();
            if (lSize <= 0) return new byte[0];

            byte[] lInput = new byte[lSize];
            reader.readFully(lInput);

            return lInput;
        }
        catch(SocketException socketException) {close(); return null;}
    }

    public String readAES() throws IOException
    {
        if (AESKey == null) return "";

        try
        {
            int lSize = reader.readInt();
            if (lSize <= 0) return "";
            byte[] lEncryptedInput = new byte[lSize];

            reader.readFully(lEncryptedInput);

            return EncryptionUtils.decryptAES(lEncryptedInput, AESKey);
        }
        catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException | InvalidAlgorithmParameterException ex) {return null;}
        catch(SocketException socketException) {close(); return null;}
    }

    protected void close() {
        try
        {
            reader.close();
            writer.close();
            client.close();
        }
        catch (IOException ignored)
        {
            reader = null;
            writer = null;
            client = null;
        }
    }

    protected void setAESKey(SecretKey pKey) { AESKey = pKey; }

    protected boolean isClosed() { return client == null || client.isClosed();}

    protected SecretKey getAESKey() {return AESKey;}

    protected Socket getSocket() {return client;}

    protected DataOutputStream getOutStream() {return writer;}

    protected DataInputStream getInStream() {return reader;}
}
