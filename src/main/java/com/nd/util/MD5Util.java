package com.nd.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

/**
 * 类名称：
 * 类描述：
 * 创建人：newtonk
 * 创建日期：2016/9/6 0006
 */
public class MD5Util {
    private static final  Logger logger = LoggerFactory.getLogger(MD5Util.class);
    /**
     * 混淆MD5加密
     * @param content
     * @return
     */
    public static String encryptMD5_ND(String content) {
        String resultString = "";
        String appkey = "fdjf,jkgfkl";

        byte[] a = appkey.getBytes();
        byte[] datSource = content.getBytes();
        byte[] b = new byte[a.length + 4 + datSource.length];

        int i;
        for (i = 0; i < datSource.length; i++)
        {
            b[i] = datSource[i];
        }

        b[i++] = (byte)163;
        b[i++] = (byte)172;
        b[i++] = (byte)161;

        b[i++] = (byte)163;

        for (int k = 0; k < a.length; k++)
        {
            b[i] = a[k];
            i++;
        }

        try {
            MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
            md5.update(b);

            // 将得到的字节数组变成字符串返回
            resultString = byteArrayToHexString(md5.digest());
        } catch (Exception e) {
            logger.error("encryptMD5 fail", e);
        }

        return resultString.toLowerCase();
    }

    public static final String KEY_MD5 = "MD5";
    /**
     * 转换字节数组为十六进制字符串
     * @param     字节数组
     * @return    十六进制字符串
     */
    private static String byteArrayToHexString(byte[] b){
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++){
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }
    /** 将一个字节转化成十六进制形式的字符串     */
    private static String byteToHexString(byte b){
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    //十六进制下数字到字符的映射数组
    private final static String[] hexDigits = {"0", "1", "2", "3", "4",
            "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public static void main(String[] args) {
        System.out.println(MD5Util.encryptMD5_ND("123456"));
    }

}
