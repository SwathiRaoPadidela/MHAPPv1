package com.example.demo;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import java.util.Base64.Decoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class PasswordEncypDecrp {

	private static SecretKeySpec secretKey;
	private static byte[] key;
	
	
	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		
		
			String encyptedString=encrypt("Password9$","MHHUB");
			System.out.println("encyptedString " + encyptedString);
		//	String decrypedString=basicDecrypt("/5S8igzkniCD8MaGqLVJvTq4sL824kdl83/g/QRl8Pv0sy2INFqomycNP4V+NGoe");
			//System.out.println("decrypedString " + decrypedString);
	}

	private static String encrypt(String strToEncrypt,String secrete) {
  		try {
  			System.out.println("inside encypt method")
  			
  			setKey(secrete);
  			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
  			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
  			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
  		} catch (Exception e) {
  			System.out.println(e);
  			System.out.println("Error while encrypting: " + e.toString());
  			System.out.println(e.getMessage());
  		}
		return null;
  		
  	}
	
public static void setKey(String myKey) {
		
		MessageDigest sha = null;
		try {
			key = myKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, "AES");
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
}

private static String generatePassword() {
		final String dCase = "abcdefghijklmnopqrstuvwxyz";
		final String uCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		final String intChar = "0123456789";
		final Random r = new Random();
		String pass = "";
		while (pass.length() != 8) {
			int rPick = r.nextInt(3);
			if (rPick == 0) {
				int spot = r.nextInt(25);
				pass += dCase.charAt(spot);
			} else if (rPick == 1) {
				int spot = r.nextInt(25);
				pass += uCase.charAt(spot);
			} else if (rPick == 2) {
				int spot = r.nextInt(9);
				pass += intChar.charAt(spot);
			}
		}
		return pass;
	}

private static String basicDecrypt(String password) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
	//String data = "aK7+UX24ttBgfTnAndz9aQ==" ;
	System.out.println("pass"+password);
 String key = "1234567812345678";
    String iv = "1234567812345678";

    Decoder decoder = Base64.getDecoder();   
     byte[] encrypted1 = decoder.decode(password);

    Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
    SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
    IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

    cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);

    byte[] original = cipher.doFinal(encrypted1);
    String originalString = new String(original);
    System.out.println(originalString.trim());
	return originalString;



}
}