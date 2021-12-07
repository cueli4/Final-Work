package model;

public class UserOperation {
	String nameOperation;
	String operations;
	UserOperation(){
		nameOperation="";
		operations=null;
	}
	
	public UserOperation(String nameOperation,String operationsString){
		operations=operationsString;
		this.nameOperation=nameOperation;
	}

	public String getNameOperation() {
		return nameOperation;
	}

	public void setNameOperation(String nameOperation) {
		this.nameOperation = nameOperation;
	}

	public String getOperations() {
		return operations;
	}

	public void setOperations(String operations) {
		this.operations = operations;
	}
	
}
