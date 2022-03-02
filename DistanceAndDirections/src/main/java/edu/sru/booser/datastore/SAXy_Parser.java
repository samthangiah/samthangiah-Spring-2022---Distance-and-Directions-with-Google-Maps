package edu.sru.booser.datastore;

//import com.mkyong.xml.sax.handler.MapStaffObjectHandlerSax;
//import com.mkyong.xml.sax.model.Staff;

import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class SAXy_Parser {
	
	static DirectionsHolder parseDirections(String xmlIN) throws IOException
	{
		DirectionsHolder DH = new DirectionsHolder();
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXy_Converter handler = new SAXy_Converter();
		try {
			
			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(xmlIN, handler);
			
		} catch (ParserConfigurationException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return DH;
		
	}
	
}
