package com.allenwtl.util;

import com.alibaba.fastjson.JSON;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * RSA 加密解密
 */
public class RSAUtil extends Coder{
	
	public static final String KEY_ALGORITHM = "RSA";
	
	public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

	private static final String RSA_PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAK2cXQU6r5yqMxho"
												+ "HEw5sjLxpctHRmOc2HuBM9hlt3dF/wBKEoIAfaHQuditD+gVZzmxM9XKoYPuNAVy"
												+ "EDJzatHqftkFnUjvNDqqDOi0fUwf6QEFNFRnsBOyoLD5cFMuOQ9yBFyiEOf2UJNj"
												+ "hj2wMuhF3W4YQcI34vpJWj4brUYFAgMBAAECgYBWxmIEPBoMr1GMsHT8cJYuOnRJ"
												+ "D54SKhJWYPT+A5FZL4ZOqbdofUBOQhqyP4ZE1XXb80NaZ8alP8mGs0V/Qgswf+Aw"
												+ "V99OpHyJIHQuClVZ4AGhWyK7pQQbtOouQ1FXv/HUP7bzo2nK3jH5RIH53DCsp9XJ"
												+ "bl09b4IDTkxEnTgxnQJBAOB5KYxdh70n7rIyDQ/LyPVxjqTXUUhVjzcbTtdX+7xV"
												+ "xeWhK7UA4dSMOjl88n1BBRqdLZ53kMu2tnbEe32WFEMCQQDF/nZqOSmZbtM1f4rO"
												+ "uFdV/C0O39TN4AKCTjEI4PaM/HVTD90PIBbzPKqUEes0XPCtZI0QEcP5RX5c/oHL"
												+ "i3wXAkBtfcwtYmXWd0bv9of2f4Fbb3OTpk9IiCteRtyzH4B0AiaQdogaNv7wrSGt"
												+ "buvc+r0hmS9qT4n6Q/pGflp6DJ1/AkEAnpfAJD/baXJTLeQWvJT+J/rR4Ls7yxin"
												+ "Cdc2AcWo16+VDs6DJI1wtK8gd3CO9SeJBWqob3m7f1zB5h1avD7jSwJAT8txOTIH"
												+ "CLONqtthNIGzwywut3s9pmDDfzuw1zWD7MhDcTKrORAdXIKUZic5peZfUf9jvz2v"
												+ "c+XFZfAdQpNOkA==";
										
	private static final String RSA_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCtnF0FOq+cqjMYaBxMObIy8aXL"
												+ "R0ZjnNh7gTPYZbd3Rf8AShKCAH2h0LnYrQ/oFWc5sTPVyqGD7jQFchAyc2rR6n7Z"
												+ "BZ1I7zQ6qgzotH1MH+kBBTRUZ7ATsqCw+XBTLjkPcgRcohDn9lCTY4Y9sDLoRd1u"
												+ "GEHCN+L6SVo+G61GBQIDAQAB";

	 /** 
     * RSA最大加密明文大小 
     */  
    private static final int MAX_ENCRYPT_BLOCK = 117;  
      
    /** 
     * RSA最大解密密文大小 
     */  
    private static final int MAX_DECRYPT_BLOCK = 128;  
    
	/**
	 * 用私钥对信息生成数字签名
	 * @param data 加密数据
	 * @throws Exception
	 */
	public static String sign(byte[] data) throws Exception {
		// 解密私钥
		byte[] keyBytes = decryptBASE64(RSA_PRIVATE_KEY);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);

		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		RSAPrivateKey priKey = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);

		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initSign(priKey);
		signature.update(data);
		return encryptBASE64(signature.sign());
	}

	/**
     * 校验数字签名
	 * @param data 加密数据
	 * @param sign 数字签名
	 * @return 校验成功返回true 失败返回false
	 * @throws Exception
	 */
	public static boolean verify(byte[] data , String sign)throws Exception {
		byte[] keyBytes = decryptBASE64(RSA_PUBLIC_KEY);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

		PublicKey pubKey = keyFactory.generatePublic(keySpec);
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initVerify(pubKey);
		signature.update(data);
		
		// 验证签名是否正常
		return signature.verify(decryptBASE64(sign));
	}

	/**
	 * 用私钥解密
	 * @param input 加密数据
	 * @throws Exception
	 */
	public static String decryptByPrivateKey(String input) {
		try {
			byte[] keyBytes = decryptBASE64(RSA_PRIVATE_KEY);

			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			RSAPrivateKey privateKey = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);

			Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, privateKey);

			BASE64Decoder decoder = new BASE64Decoder();
			byte[] data = decoder.decodeBuffer(input);

			int inputLen = data.length;
			int offSet = 0 , i = 0;
			byte[] cache;
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			// 对数据分段解密
			while (inputLen - offSet > 0) {
				if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
					cache = cipher.doFinal(data, offSet, MAX_DECRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(data, offSet, inputLen - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_DECRYPT_BLOCK;
			}
			out.close();
			return new String(out.toByteArray());
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 用公钥加密
	 * @param input 加密数据
	 * @throws Exception
	 */
	public static String encryptByPublicKey(String input) {
		try{
			byte[] keyBytes = decryptBASE64(RSA_PUBLIC_KEY);

			X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			RSAPublicKey publicKey = (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);

			Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);

			byte[] data = input.getBytes();
			int inputLen = data.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0 , i = 0;
			byte[] cache;
			
			// 对数据分段加密
			while (inputLen - offSet > 0) {
				if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
					cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(data, offSet, inputLen - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_ENCRYPT_BLOCK;
			}
			byte[] encryptedData = out.toByteArray();
			out.close();
			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(encryptedData);
		} catch (Exception e) {
			return "";
		}
	}
	
	/**
	 * 加密
	 * 用私钥加密
	 * @param input
	 * @throws Exception
	 */
	public static String encryptByPrivateKey(String input){
		try{
			// 对密钥解密
			byte[] keyBytes = decryptBASE64(RSA_PRIVATE_KEY);
	
			// 取得私钥
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			RSAPrivateKey privateKey = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
	
			// 对数据加密
			Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
			
			byte[] data = input.getBytes();
			int inputLen = data.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0 , i = 0;
			byte[] cache;
			
			// 对数据分段加密
			while (inputLen - offSet > 0) {
				if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
					cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(data, offSet, inputLen - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_ENCRYPT_BLOCK;
			}
			byte[] encryptedData = out.toByteArray();
			out.close();
			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(encryptedData);
		} catch (Exception e) {
			return "";
		}
	}
	
	/**
	 * 用公钥解密
	 * @param input
	 * @throws Exception
	 */
	public static String decryptByPublicKey(String input){
		try{
			// 对密钥解密
			byte[] keyBytes = decryptBASE64(RSA_PUBLIC_KEY);
	
			// 取得公钥
			X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			RSAPublicKey publicKey = (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
	
			// 对数据解密
			Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, publicKey);
			
			BASE64Decoder decoder = new BASE64Decoder();
	        byte[] data = decoder.decodeBuffer(input);
	        
			int inputLen = data.length;  
	        ByteArrayOutputStream out = new ByteArrayOutputStream();  
	        int offSet = 0;  
	        byte[] cache;  
	        int i = 0;  
	        // 对数据分段解密  
	        while (inputLen - offSet > 0) {  
	            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {  
	                cache = cipher.doFinal(data, offSet, MAX_DECRYPT_BLOCK);  
	            } else {  
	                cache = cipher.doFinal(data, offSet, inputLen - offSet);  
	            }  
	            out.write(cache, 0, cache.length);  
	            i++;  
	            offSet = i * MAX_DECRYPT_BLOCK;  
	        }
	        out.close();
	        return new String(out.toByteArray());
		} catch (Exception e) {
			return "";
		}
	}
	
	public static void main(String[] args) throws IOException {
//		String str = "ke591zQUGrPVsaSQjaIysIYVW3jzGTgpzIZLAFJZxSs68ifPlPMgLphL3HzuJhbEY7fBSXsLZspn\n5ku/1a5IM0Mw8RIrQYDR+eu1qHlg6nQwkDFPIHtfQ5nIwXRL8yRzrr9zt2f+AtX9bEhxaBA2BXwb\nkdqLLeBx3qRo0TIBTpE=";
////		String publicStr = encryptByPublicKey(str);
////		String decodedData = decryptByPrivateKey(publicStr);
////		System.out.println("公钥加密 - 私钥解密 : " + decodedData);
//		//String publicStr = encryptByPrivateKey(str);
//		String decodedData = decryptByPublicKey(str);
//		System.out.println("私钥加密 - 公钥解密 : " + decodedData);

		String filePath = "D:\\text.txt";
		BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(filePath)));

		StringBuilder stringBuilder = new StringBuilder();
		String temp = null ;
		while ( (temp = bufferedReader.readLine()) !=null){
			stringBuilder.append(temp+"\n");
		}

		//String str = encryptByPublicKey(stringBuilder.toString());
		String str = stringBuilder.toString();
		System.out.println(str);
		System.out.println("---------------------------------------------------------");


		System.out.println(decryptByPrivateKey(str));


	}
	
	/**
	 * 私钥解密，将参数放入request
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	public static void tidyEncryptParam(HttpServletRequest request){
		//String args = CryptUtils.urlDecodeBase64(request.getParameter("args"));
		String args = "";
		if(null != args){
			String decodedData = decryptByPrivateKey(args);
			Object obj= JSON.parse(CommonUtils.emptyIfNull(decodedData));
			Map<String, Object> param = null;
			if(obj instanceof Map){
				param=((Map<String, Object>) obj);
			}
			if(null != param){
				Set<String> set = param.keySet();
				for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {
					String key = (String) iterator.next();
					request.setAttribute(key, param.get(key));
				}
			}
		}
	}
}
