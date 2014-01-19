package ma.ecosiam.mb;


public class AbstractMB {

	public AbstractMB() {
		super();
	}

	protected void displayErrorMessageToUser(String message) {
		JSFMessage messageUtil = new JSFMessage();
		messageUtil.sendErrorMessageToUser(message);
	}

	protected void displayInfoMessageToUser(String message) {
		JSFMessage messageUtil = new JSFMessage();
		messageUtil.sendInfoMessageToUser(message);
	}

}