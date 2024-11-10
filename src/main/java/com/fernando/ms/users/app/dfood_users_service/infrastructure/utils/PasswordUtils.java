package com.fernando.ms.users.app.dfood_users_service.infrastructure.utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public interface PasswordUtils {
    String generateSalt() ;
    String hashPassword(String password, String salt);
    boolean validatePassword(String password, String salt, String hash) ;
}
