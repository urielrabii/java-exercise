import java.io.File;
import java.io.FilenameFilter;

public class SyncDirectoryProcessor implements IDirectoryProcessor {

	private FileEncryptor fe;
	
	public SyncDirectoryProcessor(FileEncryptor f) {
		fe = f;
	}

	@Override
	public String[] getFilesNameFromDirectory(String directory) {
		
		File file = new File(directory);
		String[] files = file.list(new FilenameFilter() {
			
			@Override public boolean accept(File dir, String name) {
				if(name.toLowerCase().endsWith(".txt")){
					return true;
				} else { 
					return false; 
				  } 
			} 
	    });
		
		
		
		return files;
	}

	@Override
	public void encryptFiles(String[] files, String keyPath, String directory) {
		String originalFilePath = null;
		String outputFilePath = null;
		
	    File encrypteFileDir = new File(directory + "\\encrypted");
	    if (!encrypteFileDir.exists()) {
	        if (encrypteFileDir.mkdir()) {
	            System.out.println("directory created successfully");
	        } else {
	            System.out.println("directory is not created");
	            return;
	        }
	    }
	    
		for(String file:files){
			System.out.println(file);
			originalFilePath = directory + "\\" + file;
			outputFilePath = directory + "\\encrypted\\" + file;
			fe.encryptFile(originalFilePath, outputFilePath, keyPath);
		}
		
		String key = fe.readKeyFromFile(keyPath);
		fe.writeKeyToFile(directory  + "\\key.txt", key);
	}

	@Override
	public void decryptFiles(String[] files, String keyPath, String directory) {
		String originalFilePath = null;
		String outputFilePath = null;
		
		//creates sub-directory decrypted
	    File encrypteFileDir = new File(directory + "\\decrypted");
	    if (!encrypteFileDir.exists()) {
	        if (encrypteFileDir.mkdir()) {
	            System.out.println("directory created successfully");
	        } else {
	            System.out.println("directory is not created");
	            return;
	        }
	    }
	    
		for(String file:files){
			System.out.println(file);
			originalFilePath = directory + "\\" + file;
			outputFilePath = directory + "\\decrypted\\" + file;
			fe.decryptFile(originalFilePath, outputFilePath, keyPath);
		}
		
	}


}
