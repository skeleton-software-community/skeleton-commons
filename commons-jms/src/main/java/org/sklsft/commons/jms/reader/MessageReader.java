package org.sklsft.commons.jms.reader;

import java.nio.charset.StandardCharsets;

import org.sklsft.commons.api.exception.TechnicalError;

import jakarta.jms.BytesMessage;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;

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
				message.reset();
				return new String(content, StandardCharsets.UTF_8);
			}
		} catch (JMSException e) {
			throw new TechnicalError("failed to read message : " + e.getMessage(), e);
		}
		throw new UnsupportedMessageTypeException("Could not read message due to unsupported message type");
	}
}
