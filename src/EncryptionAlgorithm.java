import java.util.List;

public interface EncryptionAlgorithm {

	public List<String> encrypt(List<String> txtLines, String key) throws InvalidEncryptionKeyExeption;
	public List<String> decrypt(List<String> txtLines, String key) throws InvalidEncryptionKeyExeption;
	public int getKeyStrength();
}
