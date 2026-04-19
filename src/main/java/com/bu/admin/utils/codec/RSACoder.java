
package com.bu.admin.utils.codec;

import com.bu.admin.extend.exception.BasicException;
import com.bu.admin.extend.exception.ErrorCodes;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Slf4j
public class RSACoder {
    private RSACoder() {
        throw new IllegalStateException("Utility class");
    }

    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    /**
     * RSA签名
     *
     * @param content      待签名数据
     * @param publicKey    私钥
     * @param inputCharset 编码格式
     * @return 签名值
     */
    public static String signByPub(String content, String publicKey, String inputCharset) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] encodedKey = Base64.decode(publicKey);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-256ANDMGF1PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            return Base64.encode(cipher.doFinal(content.getBytes(inputCharset)));
        } catch (Exception e) {
            log.error("sign by pub error :", e);
        }

        return null;
    }

    /**
     * RSA签名
     *
     * @param content       待签名数据
     * @param privateKey    私钥
     * @param inputCharset  编码格式
     * @return 签名值
     */
    public static String sign(String content, String privateKey, String inputCharset) {
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decode(privateKey));
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PrivateKey priKey = keyf.generatePrivate(priPKCS8);

            java.security.Signature signature = java.security.Signature
                    .getInstance(SIGN_ALGORITHMS);

            signature.initSign(priKey);
            signature.update(content.getBytes(inputCharset));

            byte[] signed = signature.sign();

            return Base64.encode(signed);
        } catch (Exception e) {
            log.error("sign error message is :", e);
        }

        return null;
    }

    /**
     * RSA验签名检查
     *
     * @param content      待签名数据
     * @param sign         签名值
     * @param publicKey    公钥
     * @param inputCharset 编码格式
     * @return 布尔值
     */
    public static boolean verify(String content, String sign, String publicKey, String inputCharset) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] encodedKey = Base64.decode(publicKey);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));


            java.security.Signature signature = java.security.Signature
                    .getInstance(SIGN_ALGORITHMS);

            signature.initVerify(pubKey);
            signature.update(content.getBytes(inputCharset));

            return signature.verify(Base64.decode(sign));

        } catch (Exception e) {
            log.error("sign verify message is :", e);
        }

        return false;
    }

    /**
     * 解密
     *
     * @param content      密文
     * @param privateKey    私钥
     * @param inputCharset 编码格式
     * @return 解密后的字符串
     */
    public static String decrypt(String content, String privateKey, String inputCharset) {
        try {
            PrivateKey prikey = getPrivateKey(privateKey);


            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-256ANDMGF1PADDING");
            cipher.init(Cipher.DECRYPT_MODE, prikey);

            InputStream ins = new ByteArrayInputStream(Base64.decode(content));
            ByteArrayOutputStream writer = new ByteArrayOutputStream();
            //rsa解密的字节大小最多是128，将需要解密的内容，按128位拆开解密
            byte[] buf = new byte[128];
            int bufl;

            while ((bufl = ins.read(buf)) != -1) {
                byte[] block = null;

                if (buf.length == bufl) {
                    block = buf;
                } else {
                    block = new byte[bufl];
                    System.arraycopy(buf, 0, block, 0, bufl);
                }

                writer.write(cipher.doFinal(block));
            }

            return writer.toString(inputCharset);
        } catch (Exception e) {
            log.error("decrypt message is :", e);
            throw new BasicException(ErrorCodes.ApiEntrance.RSA_DECRYPT_ERROR);
        }
    }

    /**
     * 得到私钥
     *
     * @param key 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String key) {

        try {

            byte[] keyBytes;

            keyBytes = Base64.decode(key);

            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            return keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            log.error("getPrivateKey error :", e);
            return null;
        }

    }


    public static void main(String[] args) throws NoSuchAlgorithmException {
        //do something
    }


}
