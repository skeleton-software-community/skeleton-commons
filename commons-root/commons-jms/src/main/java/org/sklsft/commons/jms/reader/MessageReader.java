package org.sklsft.commons.jms.reader;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.sklsft.commons.api.exception.TechnicalError;

public class MessageReader {

	public static boolean isMessage(Object arg) {
		return arg instanceof Message;
	}
	
	public static String getMessageContent(Message arg) {
		try {
			if (arg instanceof TextMessage) {
				return (String)(((TextMessage)arg).getText());
			}
			if (arg instanceof BytesMessage) {
				BytesMessage message = (BytesMessage)arg;
				int lentgh = (int)message.getBodyLength();
				byte[] content = new byte[lentgh];
				message.readBytes(content);
				return new String(content);
			}
		} catch (JMSException e) {
			throw new TechnicalError("failed to read message : " + e.getMessage(), e);
		}
		throw new UnsupportedMessageTypeException("Could not read message due to unsupported message type");
	}
}
