/**
 *  2012-5-25 下午4:59:05
 * @create_user wangrenhui
 * @whattodo
 * @modify_time like:date1/date2
 * @modify_user like:user1/user2
 * @modify_content like:content1/content2
 */
package cn.dreampie.common.util.security;

import java.security.MessageDigest;

/**
 * @author wangrenhui
 *  加密工具
 *  2012-5-25 下午4:59:05
 */
public class EncriptionUtils {
    /**
     * md5加密
     *
     * @param srcStr input string
     * @return output encription string
     */
    public static final String encrypt(String srcStr) {
        try {
            String result = "";
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(srcStr.getBytes("utf-8"));
            for (byte b : bytes) {
                String hex = Integer.toHexString(b & 0xFF).toUpperCase();
                result += ((hex.length() == 1) ? "0" : "") + hex;
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 字符串转换为16进制
     *
     * @param s hex string
     * @return end string
     */
    public static String toHexString(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            str += Integer.toHexString(ch);
        }
        return str;
    }

    /**
     * Text加密
     *
     * @param text text
     * @param salt salt
     * @return string
     */
    public static String textEncrypt(String text, String salt) {
        try {
            EncryptionTextUtils textEncryptor = new EncryptionTextUtils(salt);
            String result = textEncryptor.encrypt(text);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Text解密
     *
     * @param text  encription string
     * @param salt salt
     * @return string
     */
    public static String textDecrypt(String text, String salt) {
        try {
            EncryptionTextUtils textEncryptor = new EncryptionTextUtils(salt);
            String result = textEncryptor.decrypt(text);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
