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

    public boolean writeAES(String pMessage)
    {
        if (AESKey == null) return false;

        try
        {
            byte[] lEncrpytedMessage = EncryptionUtils.encryptAES(pMessage, AESKey);
            byte[] lMessageSizeAsBytes = ByteBuffer.allocate(4).putInt(lEncrpytedMessage.length).array();
            byte[] lConcatenated = ByteBuffer.allocate(lMessageSizeAsBytes.length+lEncrpytedMessage.length).put(lMessageSizeAsBytes).put(lEncrpytedMessage).array();
            writer.write(lConcatenated, 0, lConcatenated.length);
            return true;
        }
        catch (IllegalArgumentException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException |
               InvalidAlgorithmParameterException | IOException ex) {
            return false;
        }
    }

    public boolean writeUnencrypted(String pMessage)
    {
        try {
            writer.writeUTF(pMessage);
            return true;
        }
        catch(IOException ioEx) {return false;}
    }

    public boolean writeUnencrypted(byte[] pMessage)
    {
        try {
            writer.write(pMessage);
            return true;
        }
        catch(IOException ioEx) {return false;}
    }

    public byte[] readAllBytes()
    {
        try
        {
            int lSize = reader.readInt();
            if (lSize <= 0) return new byte[0];

            byte[] lInput = new byte[lSize];
            reader.readFully(lInput);

            return lInput;
        }
        catch(IOException ioEx) {return null;}
    }

    public String readAES()
    {
        if (AESKey == null) return null;

        try
        {
            int lSize = reader.readInt();
            if (lSize <= 0) return "";
            byte[] lEncryptedInput = new byte[lSize];

            reader.readFully(lEncryptedInput);

            return EncryptionUtils.decryptAES(lEncryptedInput, AESKey);
        }
        catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException |
               InvalidAlgorithmParameterException | IOException ex) {return null;}
    }

    public void close()
    {
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

    public void setAESKey(SecretKey pKey) { AESKey = pKey; }

    public boolean isClosed() { return client == null || client.isClosed();}

    public SecretKey getAESKey() {return AESKey;}

    public Socket getSocket() {return client;}

    public DataOutputStream getOutStream() {return writer;}

    public DataInputStream getInStream() {return reader;}
}
