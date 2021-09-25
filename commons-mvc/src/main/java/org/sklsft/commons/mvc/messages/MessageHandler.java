package org.sklsft.commons.mvc.messages;

/**
 * This interface defines 3 methods to raise a message that will be displayed in the view<br>
 * <li>INFO
 * <li>WARNING
 * <li>ERROR
 * Its implementations should be in charge of the internationalization of the message
 * 
 * @author Nicolas Thibault
 */
public interface MessageHandler {
	
	void displayInfo(String message);
	
	void displayWarning(String message);
	
	void displayError(String message);

}
