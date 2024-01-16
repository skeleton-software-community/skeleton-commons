package org.sklsft.commons.text.serialization;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;

import org.sklsft.commons.text.StringUtils;
import org.sklsft.commons.text.serialization.exceptions.SerializationException;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * 
 * Implementation of a {@link Serializer} in Xml that uses Jaxb
 * 
 * @author Nicolas Thibault
 *
 */
public class XmlSerializer implements Serializer {
	
	private Map<Class<?>, JAXBContext> contexts = new HashMap<>();

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })	
	public String serialize(Object object) {
		if (object == null) {
			return null;
		}
		try {
			Class<?> clazz = object.getClass();
			JAXBContext context = getContext(clazz);
			StringWriter stringWriter = new StringWriter();
			Marshaller marshaller = context.createMarshaller();
			if (clazz.isAnnotationPresent(XmlRootElement.class)) {
				marshaller.marshal(object, stringWriter);
				return stringWriter.toString();
			} else {
				QName qName = new QName(clazz.getPackage().getName(), clazz.getName());
				marshaller.marshal(new JAXBElement(qName, clazz, object), stringWriter);
				return stringWriter.toString();
			}
		} catch (Exception e) {
			throw new SerializationException("failed to serialize object : " + e.getMessage(), e);
		}
	}

	
	public <T> String serialize(JAXBElement<T> element) {
		if (element == null) {
			return null;
		}
		try {
			Class<T> clazz = element.getDeclaredType();
			JAXBContext context = getContext(clazz);
			StringWriter stringWriter = new StringWriter();
			Marshaller marshaller = context.createMarshaller();
			marshaller.marshal(element, stringWriter);
			return stringWriter.toString();

		} catch (Exception e) {
			throw new SerializationException("failed to serialize object : " + e.getMessage(), e);
		}
	}
	

	
	@Override
	@SuppressWarnings("unchecked")
	public <T> T deserialize(String arg, Class<T> targetClass) {
		if (StringUtils.isEmpty(arg)) {
			return null;
		}
		try {
			JAXBContext context = getContext(targetClass);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			if (targetClass.isAnnotationPresent(XmlRootElement.class)) {
				return (T)unmarshaller.unmarshal(new StringReader(arg));
			} else {
				return unmarshaller.unmarshal(new StreamSource(new StringReader(arg)), targetClass).getValue();				
			}
		} catch (Exception e) {
			throw new SerializationException("failed to deserialize object : " + e.getMessage(), e);
		}
	}
	
	
	private JAXBContext getContext(Class<?> clazz) throws JAXBException {
		JAXBContext context = contexts.get(clazz);
		
		if (context == null) {
			context = JAXBContext.newInstance(clazz);
			contexts.put(clazz, context);
		}
		return context;
	}
}
