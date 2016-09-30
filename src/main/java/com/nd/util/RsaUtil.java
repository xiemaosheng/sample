package com.nd.util;

import com.nd.gaea.core.Constant;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.PrivateKey;

/**
 * RSA服务端私钥解密
 */
public class RsaUtil {
    private final static java.math.BigInteger m = new java.math.BigInteger("bb8bfc5d796b195a307b86e9490105b8ed4b4722b53e75335e5f9319e6052e02fd8196f354e72e776128ef33c4c3be2825904e9cec1d8b99d718b3a683f2c0a5",16);
    private final static java.math.BigInteger d = new java.math.BigInteger("88f2ffa58234229f29280aabef13400a79bcae8539b4120120b8c9c1efa578a2962504090f6c127d21203f52e877c67b3d0ef25da1d7b8d7452af9f1c5174f41",16);
    private final static java.math.BigInteger e = new java.math.BigInteger("10001",16);
    private final static String RSA_ALGORITHM = "RSA";
    private final static String RSA_CIPHER_KEY="RSA/ECB/PKCS1Padding";

    public static String decrypt(String encryptTxt) throws Exception
    {
        byte[] eBytes = Base64.decodeBase64(encryptTxt);
        java.security.spec.RSAPrivateKeySpec keySpec = new java.security.spec.RSAPrivateKeySpec(m,d);
        java.security.KeyFactory keyFactory = java.security.KeyFactory.getInstance(RSA_ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        Cipher cipher = Cipher.getInstance(RSA_CIPHER_KEY);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] enStr = cipher.doFinal(eBytes);
        String outStr=new String(enStr, Constant.DEFAULT_CHARSET);
        return outStr;
    }

    public static String encrypt(String txt) throws Exception
    {
        java.security.spec.RSAPublicKeySpec keySpec = new java.security.spec.RSAPublicKeySpec(m,e);
        java.security.KeyFactory keyFactory = java.security.KeyFactory.getInstance(RSA_ALGORITHM);
        java.security.PublicKey publicKey = keyFactory.generatePublic(keySpec);
        Cipher cipher = Cipher.getInstance(RSA_CIPHER_KEY);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bStr = txt.getBytes(Constant.DEFAULT_CHARSET_NAME);
        byte[] eStr = cipher.doFinal(bStr);
        String outStr=Base64.encodeBase64String(eStr);
        return outStr;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(encrypt("123456"));
        System.out.println(encrypt("654321"));
        System.out.println(decrypt("eoiyMQDH08rCWzmW+X/M+K3tlYjcfQEK2/JDvfaceyxpBPkUEwKU7gGmXjVnqgsLtcFepy4J9fyjnOQZywAuWQ=="));
    }
}
