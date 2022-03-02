package edu.sru.booser.datastore;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class SAXy_Converter extends DefaultHandler {

	private StringBuilder currentValue = new StringBuilder();
	DirectionsHolder Holder = new DirectionsHolder();
	
	@Override
	public void startDocument() {
		System.out.println("System Started Parsing XML");
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) {
		if (qName.equalsIgnoreCase("summary")) {
			Holder.setSummary(attributes.getValue("summary"));
		}
		
		if (qName.equalsIgnoreCase("html_instructions")) {
			Holder.directions.add(attributes.getValue("html_instructions"));
			
		}
	}
	
	@Override
	public void endDocument() {
		System.out.println("System Finished Parsing XML");
	}
	
}
