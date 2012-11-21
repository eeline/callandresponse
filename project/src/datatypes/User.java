package datatypes;

public interface User {
	String toString();
	String getSSNumHash();
	boolean compareHash(String inputHash);
}
