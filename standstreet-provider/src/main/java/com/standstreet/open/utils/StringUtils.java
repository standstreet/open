package com.standstreet.open.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * Created xiaolong.zhu@changhong.com on 2015-11-04.
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static String generateUuidString() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        str = str.replaceAll("-", "");
        return str;
    }

    /**
     * 生成指定位数的随机数字符串
     */
    public static String getRandomStr(int size) {
        if (size <= 0) {
            return "";
        }
        Random ran = new Random();
        StringBuffer numStr = new StringBuffer();
        for (int i = 0; i < size; i++) {
            int p = ran.nextInt(10);
            numStr.append(p);
        }
        return numStr.toString();
    }

    public static boolean isGB2312(char c) {
        Character ch = new Character(c);
        String sCh = ch.toString();
        try {
            byte[] bb = sCh.getBytes("gb2312");
            if (bb.length > 1) {
                return true;
            }
        } catch (UnsupportedEncodingException ex) {
            return false;
        }
        return false;
    }


    public static String getEncoding(String str) {
        String encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s1 = encode;
                return s1;
            }
        } catch (Exception exception1) {
        }
        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s2 = encode;
                return s2;
            }
        } catch (Exception exception2) {
        }
        encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s = encode;
                return s;
            }
        } catch (Exception exception) {
        }

        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s3 = encode;
                return s3;
            }
        } catch (Exception exception3) {
        }
        return "";
    }

    public static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static String encodeBase64URLSafeString(String data) throws UnsupportedEncodingException {
        String base64str = Base64.encodeBase64String(data.getBytes());
        if (base64str.contains("+")) {
            base64str = base64str.replaceAll("\\+", "-");
        }
        if (base64str.contains("/")) {
            base64str = base64str.replaceAll("/", "_");
        }

        return base64str;
    }

    public static String encodeBase64URLSafeString(byte[] bytes) throws UnsupportedEncodingException {
        String base64str = Base64.encodeBase64String(bytes);
        if (base64str.contains("+")) {
            base64str = base64str.replaceAll("\\+", "-");
        }
        if (base64str.contains("/")) {
            base64str = base64str.replaceAll("/", "_");
        }
        return base64str;
    }

    public static String decodeBase64URLSafeString(String data) throws UnsupportedEncodingException {
        if (data.contains("-")) {
            data = data.replaceAll("-", "+");
        }
        if (data.contains("_")) {
            data = data.replaceAll("_", "/");
        }
        return new String(Base64.decodeBase64(data));
    }


    public static String hmac_sha1_base64_url_safe(String data, String key) throws UnsupportedEncodingException {
        byte[] hmac_sha1 = hmac_sha1(data, key);
        return StringUtils.encodeBase64URLSafeString(hmac_sha1);
    }

    public static byte[] hmac_sha1(String data, String key) {
        byte[] byteHMAC = null;
        try {
            Mac mac = Mac.getInstance("HmacSHA1");
            SecretKeySpec spec = new SecretKeySpec(key.getBytes(), "HmacSHA1");
            mac.init(spec);
            byteHMAC = mac.doFinal(data.getBytes());
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException ignore) {
        }
        return byteHMAC;
    }

    public static String generatToken(String apiKey, String secretKey, JSONObject policy) throws UnsupportedEncodingException {
        StringBuffer token = new StringBuffer(encodeBase64URLSafeString(apiKey));
        token.append(":");
        token.append(hmac_sha1_base64_url_safe(policy.toJSONString(), secretKey));
        token.append(":");
        token.append(encodeBase64URLSafeString(policy.toJSONString()));
        return token.toString();
    }


    /**
     * 获取毫秒级时间
     * create by ht
     *
     * @return
     */
    public static synchronized String getMsTime() {
        return String.valueOf(new Date().getTime());
    }

    public static void main(String args[]) throws UnsupportedEncodingException {
//        String so = "eyJleHBpcmVkVGltZSI6MTQzNjc3MDIyNjMzNiwibWV0aG9kIjoiUE9TVCIsInNjb3BlIjoic21zIn0=";
//
//        System.out.println(decodeBase64URLSafeString(so));
//        long i = 1436761864348L;
//        Date d = new Date(i);
////        Date eD = new Date(JSONObject.parseObject(decodeBase64URLSafeString(so)).getLongValue("expiredTime"));
////        System.out.println("eee:" + JSONObject.parseObject(decodeBase64URLSafeString(so)).getLongValue("expiredTime"));
//        System.out.println(eD);
//        System.out.println(eD.before(new Date()));

        JSONObject policy = new JSONObject();
        JSONArray apis = new JSONArray();
        Date date = new Date();
        Date ex = new Date(date.getTime() + 1000 * 3600 * 24);
        policy.put("expiredTime", ex);
        policy.put("scope", "USER");
        apis.add("login");
        apis.add("registerByEmail");
        policy.put("apis", apis);
        System.out.println(generatToken("1e30d88ee4f97411", "test", policy));


    }

}
