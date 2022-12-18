package KLibrary.wrapper;

// Own Library https://github.com/KaitoKunTatsu/KLibrary

import KLibrary.utils.EncryptionUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

/**
 * This class wraps a {@link Socket} and provides read, write, etc. methods
 *
 * @version 1.2.0 | last edit: 07.10.2022
 * @author Joshua H. | KaitoKunTatsu#3656
 * */
public class SocketWrapper {

    private Socket client;
    private DataInputStream reader;
    private DataOutputStream writer;
    private SecretKey AESKey;

    private final EncryptionUtils encryptionUtils;

    /**
     * @param pSocket Socket to wrap
     * @param pAESKey {@link SecretKey} used to encrypt messages via AES
     * */
    public SocketWrapper(Socket pSocket, SecretKey pAESKey) throws IOException
    {
        client = pSocket;
        AESKey = pAESKey;
        encryptionUtils = new EncryptionUtils();
        try {
            reader = new DataInputStream(client.getInputStream());
            writer = new DataOutputStream(client.getOutputStream());
        }
        catch(SocketException socketException) {close();}
    }

    public SocketWrapper(Socket pSocket) throws IOException {
        this(pSocket, null);
    }

    public SocketWrapper(String pIp, int pPort, SecretKey pAESKey) throws IOException {
        this(new Socket(pIp, pPort),pAESKey);
    }

    public SocketWrapper(String pIp, int pPort) throws IOException {
        this(new Socket(pIp, pPort), null);
    }

    public void establishAES() throws IOException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException
    {
        if (AESKey != null) return;
        // RSA
        byte[] lEncodedRSAKey = encryptionUtils.getPublicKey().getEncoded();
        writer.writeInt(lEncodedRSAKey.length);
        writer.write(lEncodedRSAKey);

        byte[] lKeyBytes = readAllBytes();
        PublicKey lServerRSAKey = EncryptionUtils.decodeRSAKey(lKeyBytes);

        // AES
        AESKey = EncryptionUtils.generateSymmetricKey();
        byte[] lEncryptedAESKey = encryptionUtils.encryptRSA(AESKey.getEncoded(), lServerRSAKey);

        writer.writeInt(lEncryptedAESKey.length);
        writer.write(lEncryptedAESKey);
    }

    public void writeAES(String pMessage) throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException {
        if (AESKey == null) throw new InvalidKeyException();

        byte[] lEncrpytedMessage = EncryptionUtils.encryptAES(pMessage, AESKey);
        byte[] lMessageSizeAsBytes = ByteBuffer.allocate(4).putInt(lEncrpytedMessage.length).array();
        byte[] lConcatenated = ByteBuffer.allocate(lMessageSizeAsBytes.length+lEncrpytedMessage.length).put(lMessageSizeAsBytes).put(lEncrpytedMessage).array();
        writer.write(lConcatenated, 0, lConcatenated.length);
    }

    public void writeUnencrypted(String pMessage) throws IOException {
        writer.writeUTF(pMessage);
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

    public String readUnencrypted() throws IOException {
        return reader.readUTF();
    }

    public String readAES() throws IOException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        if (AESKey == null) return null;

        int lSize = reader.readInt();
        if (lSize <= 0) return "";
        byte[] lEncryptedInput = new byte[lSize];

        reader.readFully(lEncryptedInput);

        return EncryptionUtils.decryptAES(lEncryptedInput, AESKey);
    }

    public void close() {
        try {
            reader.close();
            writer.close();
            client.close();
        }
        catch (IOException ignored) {
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
