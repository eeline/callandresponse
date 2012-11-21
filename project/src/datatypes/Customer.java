package datatypes;

import java.io.Serializable;

public class Customer implements User, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6424566264088275772L;
	private String name;
	private String lastName;
	private String ssNumber;
	private String ssHash;
	
	public Customer(String name, String ssNumber){
		this.name = name;
		this.ssNumber = ssNumber;
		this.genSSNumHash();
	}
	
	Customer(String name, String lastName, String ssNumber){
		this.name = name;
		this.lastName = lastName;
		this.ssNumber = ssNumber;
		this.genSSNumHash();
	}
	
	@Override
	public boolean compareHash(String inputHash) {
		if(this.ssHash.equals(inputHash))
			return true;
		return false;
	}

	@Override
	public String toString() {
		return this.name + " " + this.lastName + " " + this.ssNumber;
	}
	
	@Override
	public String getSSNumHash(){
		return this.ssHash;
	}
	
	private void genSSNumHash(){
		this.ssHash = this.ssNumber + "abba";
	}

}
