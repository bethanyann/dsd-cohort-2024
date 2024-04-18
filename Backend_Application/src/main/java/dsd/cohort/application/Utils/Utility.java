package dsd.cohort.application.Utils;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

@Component
public class Utility {

    private String secret = "icantbelieveitsnotsecret";

    public String consolidateString(List<String> list) {

        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s);
        }
        return sb.toString();
    }

    public List<String> tokenizeString(String str) {

        // slice string into a list of string tokens that are 255 characters or less
        List<String> tokens = new ArrayList<>();
        int index = 0;
        while (index < str.length()) {
            int endIndex = Math.min(index + 255, str.length());
            tokens.add(str.substring(index, endIndex));
            index = endIndex;
        }

        return tokens;
    }

    public String encryptString(String str) {

        Key aesKey = new SecretKeySpec(secret.getBytes(), "AES");

        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);

            byte[] encrypted = cipher.doFinal(str.getBytes());
            str = new String(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return str;
    }

    public String decryptString(String str) {

        Key key = new SecretKeySpec(secret.getBytes(), "AES");

        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);

            String decrypted = new String(cipher.doFinal(str.getBytes()));

            str = decrypted;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return str;
    }
}
