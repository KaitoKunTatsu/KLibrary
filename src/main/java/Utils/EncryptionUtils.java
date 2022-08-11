package Utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;


/**
 * This class provides hash methods and encryption/decryption (todo)
 *
 * @version 22.06.2022
 * @author Joshua H. | KaitoKunTatsu#3656
 * */
public class EncryptionUtils {

    private final int cost;

    private final int keyLength = 512;

    private final static String algorithmName = "PBKDF2WithHmacSHA512";

    private final byte[] salt;

    public EncryptionUtils(byte[] pSalt)
    {
            cost = pSalt.length;
            salt = pSalt;
    }

    public EncryptionUtils()
    {
        cost = 16;
        SecureRandom random = new SecureRandom();
        salt = new byte[cost];
        random.nextBytes(salt);
    }

    public String hash(String pPassword) {

        KeySpec spec = new PBEKeySpec(pPassword.toCharArray(), salt, 65536, keyLength);
        String hashedString;
        try
        {
            SecretKeyFactory key_factory = SecretKeyFactory.getInstance(algorithmName);
            byte[] hash_value = key_factory.generateSecret(spec).getEncoded();
            Base64.Encoder encoder = Base64.getEncoder();
            hashedString = encoder.encodeToString(hash_value);
        }
        catch (Exception ex)
        {
            hashedString = ex.getMessage();
        }
        return hashedString;
    }

}
