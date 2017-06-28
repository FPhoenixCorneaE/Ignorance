package com.livelearn.ignorance.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    private static final String[] hexDigits =
            {
                    "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c",
                    "d", "e", "f"
            };

    public static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (byte aB : b) {
            resultSb.append(byteToHexString(aB));
        }
        return resultSb.toString();
    }

    public static String byteArrayToOneHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (byte aB : b) {
            resultSb.append(byteToOneHexString(aB));
        }
        return resultSb.toString();
    }

    private static String byteToOneHexString(byte b) {
        int n = b;
        if (n < 0) n = 256 + n;
        int d2 = n % 16;
        return hexDigits[d2];
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) n = 256 + n;

        int d1 = n / 16;

        int d2 = n % 16;

        return hexDigits[d1] + hexDigits[d2];
    }

    public static String MD5Encode(String origin)
            throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return byteArrayToHexString(md.digest(origin.getBytes()));
    }

    public static String MD5Encode16bit(String origin)
            throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return byteArrayToOneHexString(md.digest(origin.getBytes()));
    }

    public static String MD5Encode(byte[] origin)
            throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return byteArrayToHexString(md.digest(origin));
    }

    public static String MD5Encode16bit(byte[] origin)
            throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return byteArrayToOneHexString(md.digest(origin));
    }


    public static String hexString(byte[] bytes) {
        StringBuilder hexValue = new StringBuilder();

        for (byte aByte : bytes) {
            int val = ((int) aByte) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static byte[] eccrypt(String info) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] srcBytes = info.getBytes();
        md5.update(srcBytes);
        return md5.digest();
    }
}
