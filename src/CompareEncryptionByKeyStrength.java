import java.util.Comparator;

public class CompareEncryptionByKeyStrength implements Comparator <EncryptionAlgorithm>{

	public CompareEncryptionByKeyStrength() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(EncryptionAlgorithm enc1, EncryptionAlgorithm enc2) {
		if(enc1.getKeyStrength() > enc2.getKeyStrength()) {
			return 1;
		} else if(enc1.getKeyStrength() < enc2.getKeyStrength()) {
			return -1;
		}		
		return 0;
	}

}
