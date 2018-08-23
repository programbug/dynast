package com.augustars.xmall.util;
//MD5加密算法，不可逆的
import java.security.MessageDigest;

public class EncryptionUtil {
	private static final String KEY_MD5 = "MD5";
	public static String encrypt(String message) {
		StringBuffer sb = new StringBuffer();
		try {
			//获得MD5摘要算法的MessageDigest对象
			MessageDigest md = MessageDigest.getInstance(KEY_MD5);
			byte[] inputData = message.getBytes("UTF-8");
			//获得密文
			byte[] encryptionData = md.digest(inputData);
			for (int i = 0; i < encryptionData.length; i++) {
				int value =((int)encryptionData[i]) & 0xff;
				if (value < 16) {
					sb.append("0");
				}
				sb.append(Integer.toHexString(value));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(encrypt("admin"));;
	}
}
