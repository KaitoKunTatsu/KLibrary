package KLibrary.Utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;


/**
 * This class provides hash methods and encryption/decryption (todo)
 *
 * @version 22.06.2022
 * @author Joshua H. | KaitoKunTatsu#3656
 * */
public class EncryptionUtils {


    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";

    private static final int SIZE = 512;

    private static final SecureRandom random = new SecureRandom();;

    private static String getHash(String pString, byte[] pSalt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(pString.toCharArray(), pSalt, 65536, SIZE);
        SecretKeyFactory key_factory = SecretKeyFactory.getInstance(ALGORITHM);
        byte[] hash_value = key_factory.generateSecret(spec).getEncoded();
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(hash_value);
    }

    public static String getHash(String pString, int pCost) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] lSalt = new byte[pCost];
        random.nextBytes(lSalt);

        return getHash(pString, lSalt);
    }

    public static String[] getHashAndSalt(String pString, int pCost) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] lSalt = new byte[pCost];
        random.nextBytes(lSalt);

        return new String[] {getHash(pString, lSalt), Arrays.toString(lSalt)};
    }

    public static boolean validate(String pToHash, String pHash, byte[] pSalt)
    {
        try {
            return pHash.equals(getHash(pToHash, pSalt));
        }
        catch (Exception ex) { return false; }
    }
}
