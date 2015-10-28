import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class Menu {


	public static void main(String[] args) {
		
		EncryptionAlgorithm enc= new ShiftUpEncryption();
		FileEncryptor fe = new FileEncryptor(enc);
		SyncDirectoryProcessor syncDir = new SyncDirectoryProcessor(fe);
		String[] files = syncDir.getFilesNameFromDirectory("D:\\USER\\Desktop\\files");
		syncDir.encryptFiles(files, "D:\\USER\\Desktop\\a\\key2.txt", "D:\\USER\\Desktop\\files");
		syncDir.decryptFiles(files,"D:\\USER\\Desktop\\a\\key2.txt", "D:\\USER\\Desktop\\files\\encrypted");
		
	}

}
