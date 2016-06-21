package com.mypro.Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class CheckSignature {

	public static boolean check(String signature, String timestamp,
			String nonce, String token) {
		// 校验字符串signature，确认请求来自微信服务器，确认返回echostr
		// 1.建立数组
		String args[] = new String[] { token, timestamp, nonce };
		// 2.对数组进行排序
		try {
			Arrays.sort(args);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// 3.将三个字符串拼成一个字符串进行SHA-1加密
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < args.length; i++) {
			sb.append(args[i]);
		}
		MessageDigest md = null;
		String byteToStr = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
			byte[] digest = md.digest(sb.toString().getBytes());
			byteToStr = byteToStr(digest);
			if (byteToStr != null) {
				byteToStr.equals(signature.toUpperCase());
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return false;
	}

	private static String byteToStr(byte[] digest) {
		String strDigest = "";
		for (int i = 0; i < digest.length; i++) {
			strDigest += byteToHexStr(digest[i]);
		}
		return strDigest;
	}

	/**
	 * 将字节转换为十六进制字符串
	 * 
	 * @param b
	 * @return
	 */
	private static String byteToHexStr(byte b) {
		// TODO Auto-generated method stub
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
				'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(b >>> 4) & 0X0F];
		tempArr[1] = Digit[b & 0X0F];
		String s = new String(tempArr);
		return s;
	}
}
