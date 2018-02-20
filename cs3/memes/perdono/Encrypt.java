package memes.perdono;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public final class Encrypt {

    enum Pad {

    }

    public static String aesEncrypt(String in, Pad p) {
        try {
            KeyGenerator KeyGen = KeyGenerator.getInstance("AES");
            KeyGen.init(128);

            SecretKey SecKey = KeyGen.generateKey();

            Cipher AesCipher = Cipher.getInstance("AES");
            AesCipher.init(Cipher.ENCRYPT_MODE, SecKey);
            
        } catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        return "";
    }
}
