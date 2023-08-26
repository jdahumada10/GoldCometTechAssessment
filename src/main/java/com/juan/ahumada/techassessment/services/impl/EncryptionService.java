package com.juan.ahumada.techassessment.services.impl;

import com.juan.ahumada.techassessment.exceptions.EncryptionException;
import com.juan.ahumada.techassessment.services.IEncryptionService;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;

@Service
public class EncryptionService implements IEncryptionService {

	public static final String DEFAULT_ALGORITHM_RSA = "RSA";

	@Override
	public KeyPair generateKeyPair() throws EncryptionException {
		try {
			final KeyPairGenerator generator = KeyPairGenerator.getInstance(DEFAULT_ALGORITHM_RSA);
			generator.initialize(4096);
			return generator.generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			throw new EncryptionException("Error generating key pair: " + e.getMessage());
		}
	}

	@Override
	public String getStringFromKey(final Key key) {
		return Base64.getEncoder().encodeToString(key.getEncoded());
	}

	@Override
	public String encryptWithPublicKey(final String publicKey,
	                                   final String plaintext) throws EncryptionException {
		try {
			final PublicKey key = (PublicKey) stringToPublicKey(publicKey);
			final Cipher cipher = Cipher.getInstance(DEFAULT_ALGORITHM_RSA);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			final byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
			return bytesToHex(encryptedBytes);
		} catch (Exception e) {
			throw new EncryptionException("Error encrypting data: " + e.getMessage());
		}
	}

	@Override
	public String decryptWithPrivateKey(String privateKey, String encryptedText) throws EncryptionException {
		try {
			final PrivateKey key = (PrivateKey) stringToPrivateKey(privateKey);
			final Cipher cipher = Cipher.getInstance(DEFAULT_ALGORITHM_RSA);
			cipher.init(Cipher.DECRYPT_MODE, key);
			final byte[] decryptedBytes = cipher.doFinal(hexToBytes(encryptedText));
			return new String(decryptedBytes);
		} catch (Exception e) {
			throw new EncryptionException("Error decrypting data: " + e.getMessage());
		}
	}

	private Key stringToPublicKey(final String keyString) throws NoSuchAlgorithmException, InvalidKeySpecException {
		final byte[] publicKeyBytes = Base64.getDecoder().decode(keyString);
		final KeyFactory keyFactory = KeyFactory.getInstance(DEFAULT_ALGORITHM_RSA);
		final X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
		return keyFactory.generatePublic(keySpec);
	}

	private Key stringToPrivateKey(final String keyString) throws NoSuchAlgorithmException, InvalidKeySpecException {
		final byte[] publicKeyBytes = Base64.getDecoder().decode(keyString);
		final KeyFactory keyFactory = KeyFactory.getInstance(DEFAULT_ALGORITHM_RSA);
		final PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(publicKeyBytes);
		return keyFactory.generatePrivate(keySpec);
	}

	private String bytesToHex(byte[] bytes) {
		StringBuilder result = new StringBuilder();
		for(byte b : bytes) {
			result.append(String.format("%02X", b));
		}
		return result.toString();
	}

	private byte[] hexToBytes(String hex) {
		int len = hex.length();
		byte[] data = new byte[len / 2];
		for(int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
					+ Character.digit(hex.charAt(i + 1), 16));
		}
		return data;
	}
}