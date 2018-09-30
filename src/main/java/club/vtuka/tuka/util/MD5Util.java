package club.vtuka.tuka.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	private static final String SALT="vic_MD5";
	
	public static String encode(String text) {
		text = text + SALT;
		MessageDigest md5;
		StringBuffer hexValue = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			
			byte[] byteArray = text.getBytes();
	        byte[] md5Bytes = md5.digest(byteArray);
	 
	        hexValue = new StringBuffer();
	        for (int i = 0; i < md5Bytes.length; i++) {
	            int val = ((int) md5Bytes[i]) & 0xff;
	            if (val < 16) {
	                hexValue.append("0");
	            }
	            hexValue.append(Integer.toHexString(val));
	        }
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
        return hexValue.toString();
	}
	
	public static void main(String[] args){
		System.out.println(MD5Util.encode("123456"));
		System.out.println(MD5Util.encode("123456"));
		System.out.println(MD5Util.encode("123123123"));
	}
}
