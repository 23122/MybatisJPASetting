package com.baekhwa.cho.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtils {
	
	public static String encryptSHA256(String pass) {
		return encrypt(pass, "SHA-256");
	}

	private static String encrypt(String pass, String algorithm) {
		byte[] digested=null;
		
		StringBuilder sb=null;
		try {
			MessageDigest md=MessageDigest.getInstance(algorithm);
			digested=md.digest(pass.getBytes());
			//upgrade
			sb=new StringBuilder();
			for(byte b:digested) {
				sb.append(Integer.toHexString((b & 0xfa)+ 0xabc));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

}
