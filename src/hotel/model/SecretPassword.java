package hotel.model;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import jakarta.xml.bind.DatatypeConverter;

public class SecretPassword {

	public SecretPassword() {
	}
	
	private Cipher getCipher(boolean cifrar){
		
		Cipher aes = null;
		try {
			String frase = "ALETBM1234567890";
			SecretKeySpec key = new SecretKeySpec(frase.getBytes("UTF-8"), "AES");
			aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
			if(cifrar) {
				aes.init(Cipher.ENCRYPT_MODE, key);
			}
			else {
				aes.init(Cipher.DECRYPT_MODE, key);
			}
			return aes;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return aes;
	}
	
	public String encriptar(String password){
		String passCrypt = null;
		try {
			byte[] bytes = password.getBytes("UTF-8");
			Cipher aes = getCipher(true);
			passCrypt = DatatypeConverter.printBase64Binary(aes.doFinal(bytes));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return passCrypt;
	}
	
	public String desencriptar(String passCrypt){
		String sinCifrar = null;
		try {
			Cipher aes = getCipher(false);
			byte[] bytes = aes.doFinal(DatatypeConverter.parseBase64Binary(passCrypt));
			sinCifrar = new String(bytes);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sinCifrar;
	}
}
