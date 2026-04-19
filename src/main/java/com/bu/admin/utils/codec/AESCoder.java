package com.bu.admin.utils.codec;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by ghostWu on 2017/7/12.
 */
@Slf4j
public class AESCoder {
    private AESCoder() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 密钥算法
     */
    public static final String KEY_ALGORITHM = "AES";

    /**
     * 加密/解密算法/工作模式/填充方式
     * <p>
     * JAVA21 update to AES/GCM/NoPadding
     */
    public static final String CIPHER_ALGORITHM = "AES/GCM/NoPadding";
    /**
     * 128 bit key
     */
    private static final int KEY_LENGTH = 128;
    /**
     * 迭代次数，一般在10000到100000之间
     */
    private static final int ITERATIONS = 65536;

    /**
     *
     * @return byte[] 二进制密钥
     */
    public static byte[] initkey() {
        try {
            //实例化密钥生成器
            KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
            //初始化密钥生成器，AES要求密钥长度为128位、192位、256位
            kg.init(128);
            //生成密钥
            SecretKey secretKey = kg.generateKey();
            //获取二进制密钥编码形式
            return secretKey.getEncoded();
        } catch (Exception e) {
            log.error("init key error :", e);
        }
        return new byte[0];
    }

    /**
     * 转换密钥
     *
     * @param key 二进制密钥
     * @return Key 密钥
     */
    public static Key toKey(byte[] key) {
        //实例化DES密钥
        //生成密钥
        return new SecretKeySpec(key, KEY_ALGORITHM);
    }

    public static byte[] encrypt(String content, String encryptPass, byte[] saltData) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(encryptPass, saltData));
            byte[] iv = cipher.getIV();
            assert iv.length == 12;
            byte[] encryptData = cipher.doFinal(content.getBytes());
            if (encryptData.length == content.getBytes().length + 16) {
                byte[] message = new byte[12 + content.getBytes().length + 16];
                System.arraycopy(iv, 0, message, 0, 12);
                System.arraycopy(encryptData, 0, message, 12, encryptData.length);
                return message;
            }
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException |
                 BadPaddingException | InvalidKeySpecException e) {
            log.error(e.getMessage(), e);
        }
        return new byte[0];
    }

    public static byte[] decrypt(byte[] content, String encryptPass, byte[] saltData) {
        if (content.length < 12 + 16) {
            throw new IllegalArgumentException();
        }
        GCMParameterSpec params = new GCMParameterSpec(128, content, 0, 12);
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(encryptPass, saltData), params);
            return cipher.doFinal(content, 12, content.length - 12);
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException |
                 InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException |
                 InvalidKeySpecException e) {
            log.error(e.getMessage(), e);
        }
        return new byte[0];
    }


    private static SecretKeySpec getSecretKey(String encryptPass, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        char[] chars = encryptPass.toCharArray();
        // 128 bit pass 迭代次数 65536
        PBEKeySpec spec = new PBEKeySpec(chars, salt, ITERATIONS, KEY_LENGTH);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] key = skf.generateSecret(spec).getEncoded();
        return new SecretKeySpec(key, KEY_ALGORITHM);
    }


}
