package com.util;

import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * @author Pavel Neizhmak
 */
public class PasswordUtils {

    private static final String SECRET_KEY = "1Hbfh667adfDEJ78";
    private static final String ALGORITHM = "AES";

    public static Key generateSecretKey() throws Exception {
        return new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
    }

    public static String encryptPassword(String password) throws Exception {
        Key key = generateSecretKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] encryptedByteValue = cipher.doFinal(password.getBytes(Constants.UTF_8));

        return new BASE64Encoder().encode(encryptedByteValue);
    }
}
