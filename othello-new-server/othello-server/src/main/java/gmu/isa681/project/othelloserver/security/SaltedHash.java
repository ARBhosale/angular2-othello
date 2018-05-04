package gmu.isa681.project.othelloserver.security;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class SaltedHash {
    static final int IT = 65536;
    static final int LENGTH = 128;
    static final String ALGO="PBKDF2WithHmacSHA1";
    public static String getSaltHashedPassword(String password,byte[] salt){
        SecretKeyFactory key=null;
        String pwd=null;
        try {
            char[] passwordChars = password.toCharArray();
            PBEKeySpec spec = new PBEKeySpec(passwordChars,salt,IT,LENGTH);
            key = SecretKeyFactory.getInstance(ALGO);
            byte[] hashedPassword = key.generateSecret(spec).getEncoded();
            Base64.Encoder enc=Base64.getEncoder();
            pwd=enc.encodeToString(hashedPassword);
            System.out.println(pwd);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return pwd;
    }
}