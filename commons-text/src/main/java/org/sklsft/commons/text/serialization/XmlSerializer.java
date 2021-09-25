package org.sklsft.commons.text.serialization;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;

import org.sklsft.commons.text.StringUtils;
import org.sklsft.commons.text.serialization.exceptions.SerializationException;

/**
 * 
 * Implementation of a {@link Serializer} in Xml that uses Jaxb
 * 
 * @author Nicolas Thibault
 *
 */
public class XmlSerializer implements Serializer {
	
	private Map<Class<?>, Marshaller> marshallers = new HashMap<>();
	
	private Map<Class<?>, Unmarshaller> unmarshallers = new HashMap<>();

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })	
	public String serialize(Object object) {
		if (object == null) {
			return null;
		}
		try {
			Class<?> clazz = object.getClass();
			Marshaller marshaller = marshallers.get(clazz);
			if (marshaller == null) {
				JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
				marshaller = jaxbContext.createMarshaller();
				marshallers.put(clazz, marshaller);
			}
			StringWriter stringWriter = new StringWriter();
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
			Marshaller marshaller = marshallers.get(clazz);
			if (marshaller == null) {
				JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
				marshaller = jaxbContext.createMarshaller();
				marshallers.put(clazz, marshaller);
			}
			StringWriter stringWriter = new StringWriter();
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
			Unmarshaller unmarshaller = unmarshallers.get(targetClass);
			if (unmarshaller == null) {
				JAXBContext jaxbContext = JAXBContext.newInstance(targetClass);
				unmarshaller = jaxbContext.createUnmarshaller();
				unmarshallers.put(targetClass, unmarshaller);
			}
			if (targetClass.isAnnotationPresent(XmlRootElement.class)) {
				return (T)unmarshaller.unmarshal(new StringReader(arg));
			} else {
				return unmarshaller.unmarshal(new StreamSource(new StringReader(arg)), targetClass).getValue();				
			}
		} catch (Exception e) {
			throw new SerializationException("failed to deserialize object : " + e.getMessage(), e);
		}
	}
}
