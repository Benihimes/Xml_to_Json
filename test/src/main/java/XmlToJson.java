import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;


import com.fasterxml.jackson.databind.ObjectMapper;


public class XmlToJson {
    public static String xmlToJson (String xmlFile){
        String output;
        Parser domParser = new Parser();
        try{
            XmlModel rootOfXmlElements = domParser.parse(new File(xmlFile));
            String json = new ObjectMapper().writeValueAsString(rootOfXmlElements);
            output = json;
        } catch (Exception ex) {
            output = "Incorrect input";
        }
        return output;
    }
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {


    }
}
