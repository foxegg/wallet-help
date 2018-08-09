package com.wallethelp.utils;

public class EncryptUtils {

    /**
     * 字符串加密
     * @param str 待加密字符串
     * @return 加密后的字符串
     */
    public static String encrypt(String str) {
        String rstStr = "";
        if (str != null && str.length()>0) {
            char[] charArray = str.toCharArray();
            for (int i = 0; i < charArray.length; i++) {
                charArray[i] = (char) (charArray[i] ^ (666 + i));
            }
            rstStr = new String(charArray);
        }

        return rstStr;
    }

    /**
     * 字符串解密
     * @param str 待解密字符串
     * @return 解密后的字符串
     */
    public static String decrypt(String str) {
        return encrypt(str);
    }
}
