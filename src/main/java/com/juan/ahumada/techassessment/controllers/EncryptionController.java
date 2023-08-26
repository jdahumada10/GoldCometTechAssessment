package com.juan.ahumada.techassessment.controllers;

import com.juan.ahumada.techassessment.exceptions.EncryptionException;
import com.juan.ahumada.techassessment.services.IEncryptionService;
import java.security.KeyPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/encryption")
public class EncryptionController {

	private static final String DEFAULT_VIEW = "encryption";
	private final IEncryptionService encryptionService;

	@Autowired
	public EncryptionController(IEncryptionService encryptionService) {
		this.encryptionService = encryptionService;
	}

	@GetMapping
	public String getMainPage() {
		return DEFAULT_VIEW;
	}

	@PostMapping("/generateRSAKeyPair")
	public String generateRSAKeyPair(Model model) {
		try {
			final KeyPair keyPair = encryptionService.generateKeyPair();
			model.addAttribute("publicKey", encryptionService.getStringFromKey(keyPair.getPublic()));
			model.addAttribute("privateKey", encryptionService.getStringFromKey(keyPair.getPrivate()));
		} catch (EncryptionException e) {
			model.addAttribute("publicKey", e.getMessage());
		}
		return DEFAULT_VIEW;
	}

	@PostMapping("/encryptRSA")
	public String encryptRSA(final @RequestParam String publicKey,
	                         final @RequestParam String textToEncrypt,
	                         Model model) {
		try {
			model.addAttribute("encryptedText", encryptionService.encryptWithPublicKey(publicKey, textToEncrypt));
		} catch (EncryptionException e) {
			model.addAttribute("encryptedText", "Error: " + e.getMessage());
		}
		return DEFAULT_VIEW;
	}

	@PostMapping("/decryptRSA")
	public String decryptRSA(final @RequestParam String privateKey,
	                         final @RequestParam String textToDecrypt,
	                         Model model) {
		try {
			model.addAttribute("decryptedText", encryptionService.decryptWithPrivateKey(privateKey, textToDecrypt));
		} catch (EncryptionException e) {
			model.addAttribute("decryptedText", "Error: " + e.getMessage());
		}
		return DEFAULT_VIEW;
	}
}