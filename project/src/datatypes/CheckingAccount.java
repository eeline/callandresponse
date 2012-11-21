package datatypes;

public class CheckingAccount implements Account {
	private String output ="";
	
	CheckingAccount(String input, int... inputs){
		for(int i=0; i<inputs.length; i++)
			input += inputs[i];
		
		this.output = input;
	}
	
	@Override
	public String toString(){
		return output;
	}
}
