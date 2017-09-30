package com.kou.util;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class SHAUtil {
	
		private static final String ENCRYPT_NAME = "SHA-256";
		
		private static final String HMAC_SHA256_NAME = "HmacSHA256";
		
		private static final String HMAC_SHA1_NAME = "HmacSHA1";
	
	  public static String encrypt(String src) {
	        MessageDigest md = null;
	        String strDes = null;
	        byte[] bt = src.getBytes();
	        try {
	            md = MessageDigest.getInstance(ENCRYPT_NAME);
	            md.update(bt);
	            strDes = bytes2Hex(md.digest()); // to HexString
	            return strDes;
	        } catch (NoSuchAlgorithmException e) {
	            return null;
	        }
	    }
	  
	  public static String HMACSHA256(String data, String key) {
		  return HMACSHA256(data.getBytes(StandardCharsets.UTF_8), key.getBytes(StandardCharsets.UTF_8));
	  } 
	  
	  public static String HMACSHA256(byte[] data, byte[] key) {
	        try  {
	           SecretKeySpec signingKey = new SecretKeySpec(key, HMAC_SHA256_NAME);
	           Mac mac = Mac.getInstance(HMAC_SHA256_NAME);
	           mac.init(signingKey);
	           return bytes2Hex(mac.doFinal(data));
	        } catch (NoSuchAlgorithmException e) {
	           e.printStackTrace();
	        } catch (InvalidKeyException e) {
	          e.printStackTrace();
	        }
	        return null;
	  } 
	  
	  public static byte[] HMACSHA1(byte[] data, byte[] key) {
	        try  {
	           SecretKeySpec signingKey = new SecretKeySpec(key, HMAC_SHA1_NAME);
	           Mac mac = Mac.getInstance(HMAC_SHA1_NAME);
	           mac.init(signingKey);
	           return mac.doFinal(data);
	        } catch (NoSuchAlgorithmException e) {
	           e.printStackTrace();
	        } catch (InvalidKeyException e) {
	          e.printStackTrace();
	        }
	        return null;
	  } 
	  
	   public static String bytes2Hex(byte[] bts) {
	        StringBuilder sb = new StringBuilder();
	        String tmp = null;
	        for (int i = 0; i < bts.length; i++) {
	            tmp = (Integer.toHexString(bts[i] & 0xFF));
	            if (tmp.length() == 1) {
	            	sb.append("0");
	            }
	            sb.append(tmp);
	        }
	        return sb.toString();
	    }
	   
	   public static void main(String[] args) {
		  String string = encrypt("test");
		  System.out.println(string);
		  String string1 = HMACSHA256("123456", "123456" + RSAUtil.PRIVATE_KEY);
		  System.out.println(string1);
	   }

}
