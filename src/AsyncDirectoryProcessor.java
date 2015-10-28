import java.io.File;
import java.io.FilenameFilter;

public class AsyncDirectoryProcessor implements IDirectoryProcessor {

	private String directory;
	private FileEncryptor fe;
	
	public AsyncDirectoryProcessor(FileEncryptor f) {
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
		// TODO Auto-generated method stub

	}

	@Override
	public void decryptFiles(String[] files, String keyPath, String directory) {
		// TODO Auto-generated method stub
		
	}

}
