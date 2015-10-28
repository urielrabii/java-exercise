import java.util.List;
import java.util.Random;

public class DoubleEncryption implements EncryptionAlgorithm {

	EncryptionAlgorithm enc; 
	String key;
	int randNumber;
	
	public DoubleEncryption() {
		this.key = null;
	}

	@Override
	public List<String> encrypt(List<String> txtLines, String keyFromFile) throws InvalidEncryptionKeyExeption {
		
		String[] twoKeys = null;
    	int firstKey, secondKey;
    	int finalKey;
    	
		try {
	    	//getting the 2 key from the string key
	    	twoKeys = keyFromFile.split(" ");
	    	firstKey = Integer.parseInt(twoKeys[0]);
	    	secondKey = Integer.parseInt(twoKeys[1]);
		} catch (NumberFormatException e) {
			throw new InvalidEncryptionKeyExeption("The key is not in the correct format!");
		}
		catch (ArrayIndexOutOfBoundsException ex) {
			throw new InvalidEncryptionKeyExeption("The key is not in the correct format!");
		}
    	
    	//Getting random number between 0 to 1
    	Random random = new Random(); 
    	randNumber = random.nextInt(2); 
    	
    	if(randNumber ==0){ //if 0 choose ShiftUpEncryption
    		enc = new ShiftUpEncryption();
    		finalKey = firstKey + secondKey;
    	} else{ //if 1 choose ShiftMultiplyEncryption
    		 enc = new ShiftMultiplyEncryption();
    		 finalKey = firstKey * secondKey;
 			 if(finalKey == 0) {
				throw new InvalidEncryptionKeyExeption("The key can't be zero!");
 			 }
    	  }
    	String newKey = Integer.toString(finalKey);
    	this.key = newKey;
    	return enc.encrypt(txtLines, newKey); 
	}

	@Override
	public List<String> decrypt(List<String> txtLines, String keyFromFile) throws InvalidEncryptionKeyExeption {
		
		String[] twoKeys = null;
    	int firstKey, secondKey;
    	int finalKey;
    	
		try {
	    	//getting the 2 key from the string key
	    	twoKeys = keyFromFile.split(" ");
	    	firstKey = Integer.parseInt(twoKeys[0]);
	    	secondKey = Integer.parseInt(twoKeys[1]);
		} catch (NumberFormatException e) {
			throw new InvalidEncryptionKeyExeption("The key is not in the correct format!");
		}
		catch (ArrayIndexOutOfBoundsException ex) {
			throw new InvalidEncryptionKeyExeption("The key is not in the correct format!");
		}
    	
    	if(randNumber ==0){ //if 0 choose ShiftUpEncryption
    			finalKey = firstKey + secondKey;
    	} else{ //if 1 choose ShiftMultiplyEncryption
   		 		finalKey = firstKey * secondKey;
   	 			if(finalKey == 0) {
   					throw new InvalidEncryptionKeyExeption("The key can't be zero!");
   	 			}
    	  }
    	String newKey = Integer.toString(finalKey);
    	this.key = newKey;
    	return enc.decrypt(txtLines, newKey); 
	}

	@Override
	public int getKeyStrength() {
		if(key == null) {
			return 0;
		}
		return key.length();
	}

}
