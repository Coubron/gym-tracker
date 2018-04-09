package naimaier.gymtracker.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MD5 {
    public static String convert(String senha){
        MessageDigest md = null;
        try {
             md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MD5.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (md != null) {
            return (new BigInteger(1, md.digest(senha.getBytes())).toString(16));
        }
        return null;
    }
}
