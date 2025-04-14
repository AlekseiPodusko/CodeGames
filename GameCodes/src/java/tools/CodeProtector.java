package tools;

import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class CodeProtector {

    private static final String ALGORITHM = "AES";
    private static final int KEY_LENGTH = 16; // 128 бит

    // 🔐 Генерация соли
    public String getSalt() {
        byte[] salt = new byte[KEY_LENGTH];
        new SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    // 🔐 Шифровка кода
    public String encrypt(String rawCode, String salt) {
        try {
            SecretKeySpec secretKey = getKeyFromSalt(salt);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(rawCode.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при шифровании кода", e);
        }
    }

    // 🔓 Расшифровка кода
    public String decrypt(String encryptedCode, String salt) {
        try {
            SecretKeySpec secretKey = getKeyFromSalt(salt);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decodedBytes = Base64.getDecoder().decode(encryptedCode);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при расшифровке кода", e);
        }
    }

    // 🔧 Получаем ключ из соли (берём первые 16 байт)
    private SecretKeySpec getKeyFromSalt(String salt) {
        byte[] decodedSalt = Base64.getDecoder().decode(salt);
        byte[] key = new byte[KEY_LENGTH];
        System.arraycopy(decodedSalt, 0, key, 0, Math.min(decodedSalt.length, KEY_LENGTH));
        return new SecretKeySpec(key, ALGORITHM);
    }

    public String getDecryptedPassword(String code, String salt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
