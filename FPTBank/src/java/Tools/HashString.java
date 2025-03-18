package Tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashString {
    
    public String hashString(String stringToHash) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(stringToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < messageDigest.length; i++) {
                sb.append(Integer.toString((messageDigest[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
