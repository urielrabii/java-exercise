import java.util.List;

public class ShiftUpEncryption implements EncryptionAlgorithm {

	String key;
	public ShiftUpEncryption() {
		this.key = null;
	}

	@Override
	public List<String> encrypt(List<String> txtLines, String keyFromFile) throws InvalidEncryptionKeyExeption{
		
		String newLine = "";
		String[] chars = null;    
		int key;
		
		//checking if the key is n the correct format
		try {
			key = Integer.parseInt(keyFromFile);
		} catch (NumberFormatException e) {
			throw new InvalidEncryptionKeyExeption("The key is not in the correct format!");
		}
		
		this.key = keyFromFile;
		
        //adding the value of the key to each character in the string
        for(int i=0; i<txtLines.size(); i++){
        	chars = txtLines.get(i).split("");
        	for(int j=0; j<chars.length; j++){
        		newLine += ((int)chars[j].charAt(0) + key) + " ";
        	}
        	//sets the line after adding the key
        	txtLines.set(i, newLine);
        	newLine = "";
        }

        return txtLines;
	}

	@Override
	public List<String> decrypt(List<String> txtLines, String keyFromFile) throws InvalidEncryptionKeyExeption {

		String newLine = "";
		String[] chars = null;
		int key;
		
		//checking if the key is n the correct format
		try {
			key = Integer.parseInt(keyFromFile);
		} catch (NumberFormatException e) {
			throw new InvalidEncryptionKeyExeption("The key is not in the correct format!");
		}
		
		this.key = keyFromFile;
		
	    //subtracting the value of the key from each character in the string
        for(int i=0; i<txtLines.size(); i++){
        	chars = txtLines.get(i).split(" ");
        	for(int j=0; j<chars.length; j++){
        		newLine += (char)(Integer.parseInt(chars[j]) - key);
        	}
        	//sets the line after adding the key
        	txtLines.set(i, newLine);
        	newLine = "";
        }
		
		return txtLines;

	}

	@Override
	public int getKeyStrength() {
		if(key == null) {
			return 0;
		}
		return key.length();
	}
	
}
