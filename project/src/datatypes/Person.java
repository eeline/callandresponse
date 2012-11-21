package datatypes;

public class Person{
	private String output = "";
	private Account account;
	
	public Person(String... inputs){
		for (int i=0; i<inputs.length; i++){
			output += inputs[i];
		}
	}
	
	public void addAccount(Account account){
		this.account = account;
	}
	
    @Override
	public String toString(){
		String tempString = output;
		
		if(account != null){
			tempString += account.toString();
		}
		
		return tempString;
	}
}
