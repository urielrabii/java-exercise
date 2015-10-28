import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class FileEncryptor {

	EncryptionAlgorithm enc; 
	
	public FileEncryptor(EncryptionAlgorithm encAlgo) {
		this.enc = encAlgo;
	}

	public int getKeyStrength() {
		return enc.getKeyStrength();
	}
	public void encryptFile(String originalFilePath, String outputFilePath, String keyPath){
		
        List<String> lines = new LinkedList<String>();
        List<String> encryptedLines = new LinkedList<String>();
        
		//getting the key from file for the encryption
		String key = readKeyFromFile(keyPath);
        
        lines = readFromFile(originalFilePath);
        
        try {
			encryptedLines = enc.encrypt(lines, key);
		} catch (InvalidEncryptionKeyExeption e) {
			System.out.println("The key is not in the correct format");
			e.printStackTrace();
			return;
		}
        
        writeToFile(outputFilePath, encryptedLines);
        
        int posOfPath= outputFilePath.lastIndexOf("\\");
        String locationPath = outputFilePath.substring(0, posOfPath);
        System.out.println("The encrypted file is located in: '" + locationPath + "'");
	}
	
	public void decryptFile(String originalFilePath, String outputFilePath, String keyPath) {
		
		//getting the key from file for the decryption
		String key = readKeyFromFile(keyPath);
		
        List<String> txtLines = new LinkedList<String>(); 
        List<String> decryptedLines = new LinkedList<String>();
        
        txtLines = readFromFile(originalFilePath);
        
        try {
			decryptedLines = enc.decrypt(txtLines, key);
		} catch (InvalidEncryptionKeyExeption e) {
			System.out.println("The key is not in the correct format");
			e.printStackTrace();
			return;
		}
        
        writeToFile(outputFilePath, decryptedLines);
        
        int posOfPath= outputFilePath.lastIndexOf("\\");
        String locationPath = outputFilePath.substring(0, posOfPath);
        System.out.println("The decrypted file is located in: '" + locationPath + "'");
		
	}
	
	private List<String> readFromFile(String path) {
		
		String line = null;
        List<String> lines = new LinkedList<String>();
        try {
            // FileReader reads text files in the default encoding
            FileReader fileReader = new FileReader(path);

            //Wrapping FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while( (line = bufferedReader.readLine()) != null) {
            	lines.add(line);
            }   

            //Close file
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + path + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + path + "'");                  
        }
		return lines;
	}
	
	private void writeToFile(String path, List<String> lines) {

		FileWriter fileWriter;
		BufferedWriter bufferedWriter;

        try {
            // Assume default encoding
            fileWriter = new FileWriter(path);
            
            //Wrapping FileWriter in BufferedWriter
            bufferedWriter = new BufferedWriter(fileWriter);
        	
            for(int i=0; i<lines.size(); i++){
            	bufferedWriter.write(lines.get(i));//writes the new line to the new file
            	bufferedWriter.newLine();   
            	System.out.println(lines.get(i));
            }

            //Close files
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println("Error writing to file '" + path + "'");
        }
                
	}
	
	public String readKeyFromFile(String keyPath) {
		
		String key = null;
        try {
            // FileReader reads text files in the default encoding
            FileReader fileReader = new FileReader(keyPath);

            //Wrapping FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            key = bufferedReader.readLine();
               
            //Close file
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + keyPath + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + keyPath + "'");                  
        }
		return key;		
	}
	
	public void writeKeyToFile(String keyPath, String key) {
		
		FileWriter fileWriter;
		BufferedWriter bufferedWriter;
        
        try {
            // Assume default encoding
            fileWriter = new FileWriter(keyPath);
            
            //Wrapping FileWriter in BufferedWriter
            bufferedWriter = new BufferedWriter(fileWriter);

            //writes the key to the key file 
            bufferedWriter.write(key);    	
            
            //Close files
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println("Error writing to file '" + keyPath + "'");
        }
		
	}

}
