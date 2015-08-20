package PublisherSubscribers;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="QUOTE")
public class Message {
	String product;
	String source;
	String publisher;
	String attribute;
	String price;
	String volume;
	String timestamp;

	public String getProduct() {
		return product;
	}

	@XmlElement(name="PRODUCT")
	public void setProduct(String product) {
		this.product = product;
	}


	public String getSource() {
		return source;
	}

	@XmlElement(name="SOURCE")
	public void setSource(String source) {
		this.source = source;
	}


	public String getPublisher() {
		return publisher;
	}

	@XmlElement(name="PUBLISHER")
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}


	public String getAttribute() {
		return attribute;
	}

	@XmlElement(name="ATTRIBUTE")
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getPrice() {
		return price;
	}

	@XmlElement(name="PRICE")
	public void setPrice(String price) {
		this.price = price;
	}

	public String getVolume() {
		return volume;
	}

	@XmlElement(name="VOLUME")
	public void setVolume(String volume) {
		this.volume = volume;
	}


	public String getTimestamp() {
		return timestamp;
	}
	
	@XmlElement(name="TIMESTAMP")
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public static void main(String[] args) throws JAXBException{
		Message message = new Message();
		message.setAttribute("attr");
		message.setPrice("12");
		message.setProduct("prod");
		message.setPublisher("");
		message.setSource("");
		message.setTimestamp("2015-08-18");
		message.setVolume("10");
		convertObjectToXML(message);
//		System.out.println(xml);
//		Message message = convertXMLToObject(xml);
//		System.out.println(message.getAttribute());
//		System.out.println(message.getPrice());
	}
	
	public static String convertObjectToXML(Message message){
		JAXBContext jaxbContext;
		StringWriter sw = new StringWriter();
		try {
			jaxbContext = JAXBContext.newInstance(Message.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();			
			jaxbMarshaller.marshal(message, sw);
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return sw.toString();
	}

	public static Message convertXMLToObject(String xml){
		JAXBContext jaxbContext;
		Message message = null;
		try {
			jaxbContext = JAXBContext.newInstance(Message.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(xml);
			message = (Message)jaxbUnmarshaller.unmarshal(reader);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return message;
	}
}
