
public interface IDirectoryProcessor {

	public String[] getFilesNameFromDirectory(String directory);
	public void encryptFiles(String[] files, String keyPath, String directory);
	public void decryptFiles(String[] files, String keyPath, String directory);
}
