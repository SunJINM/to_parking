package sun.project.to_parking.util;

import org.apache.commons.codec.digest.DigestUtils;
import sun.project.to_parking.constant.Constant;

/**
 * MD5加密算法
 */
public class MD5Util {

    /**
     * MD5方法
     * @param text 明文
     * @return 密文
     * @throws Exception
     */
    public static String md5(String text){
        //加密后的字符串
        String encode= DigestUtils.md5Hex(text);
        return encode;
    }

    /**
     * MD5方法
     * @param text 明文
     * @param key 盐
     * @return 密文
     * @throws Exception
     */
    public static String md5(String text, String key){
        //加密后的字符串
        String encode= DigestUtils.md5Hex(text + key);
        return encode;
    }

    /**
     * MD5验证方法
     * @param text 明文
     * @param key 密钥
     * @param md5 密文
     * @return true/false
     * @throws Exception
     */
    public static boolean verify(String text, String key, String md5) throws Exception {
        //根据传入的密钥进行验证
        String md5Text = md5(text, key);
        return md5Text.equalsIgnoreCase(md5);
    }


    public static void main(String[] args) {
        //生成
        try {
            String var = md5("123456", Constant.KEY_MD5);
            System.out.println(var);
            boolean verify=verify("123456",Constant.KEY_MD5,"6eede222b0855e578b2c3813d2836f2d");
            System.out.println(verify);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
