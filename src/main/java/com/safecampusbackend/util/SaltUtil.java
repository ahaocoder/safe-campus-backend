package com.safecampusbackend.util;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * 盐类公共方法
 */
public class SaltUtil {
    /**
     * 产盐
     *
     * @return salt
     */
    public static String generateSalt() {
        byte[] salt = new byte[32];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        return Base64.getEncoder().withoutPadding().encodeToString(salt);
    }

    /**
     * 加盐
     *
     * @param password 密码
     * @param salt     盐值
     * @return salt
     */
    public static String generateAddSalt(String password, String salt) {
        String passwordAddSalt = password + salt;
        return HashUtil.hashSHA256(passwordAddSalt);
    }
}
