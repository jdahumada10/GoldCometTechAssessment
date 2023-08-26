package com.juan.ahumada.techassessment.services;

import com.juan.ahumada.techassessment.exceptions.EncryptionException;
import java.security.Key;
import java.security.KeyPair;

public interface IEncryptionService {
	KeyPair generateKeyPair() throws EncryptionException;

	String getStringFromKey(Key key);

	String encryptWithPublicKey(String publicKey,
	                            String textToEncrypt) throws EncryptionException;

	String decryptWithPrivateKey(String privateKey,
	                             String encryptedText) throws EncryptionException;
}
