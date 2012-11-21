package message;

import java.io.Serializable;

import serialization.CustomerDAO;


public class Message implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4998309036364799474L;
	private int theSwitch;
	private CustomerDAO cdao;

	public Message(int theSwitch, CustomerDAO cdao) {
		this.theSwitch = theSwitch;
		this.cdao = cdao;
	}

	public Message(int theSwitch) {
		this.theSwitch = theSwitch;
	}

	public int getMessage() {
		return this.theSwitch;
	}

	public CustomerDAO getData() {
		return this.cdao;
	}

}
