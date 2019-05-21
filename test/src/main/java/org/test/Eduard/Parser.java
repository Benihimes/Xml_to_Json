package org.test.Eduard;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Parser{

    public static Document parse(String name) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("src/main/xml/" + name));
        return document;
    }
    public Element getRootElement(Document document) {
        Element root = document.getDocumentElement();
        return root;
    }

    public XmlModel getTree(Element root){
        XmlModel element = new XmlModel(root.getNodeName(), null);
        readNode(root,element);
        return element;
    }
    public ObjectMapper objectMapper(String name) throws ParserConfigurationException,IOException, SAXException{
        ObjectMapper objectMapper = new ObjectMapper();
        Document document = parse(name);
        Element root = getRootElement(document);
        return objectMapper;
    }

    private  void readNode(Node node, XmlModel element) {
        if (node.hasChildNodes()) {
            NodeList nodeList = node.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    String value = "";
                    if (nodeList.item(i).hasChildNodes()) {
                        NodeList sublist = nodeList.item(i).getChildNodes();
                        for (int j = 0; j <sublist.getLength(); j++) {
                            if (sublist.item(j).getNodeType()== Node.TEXT_NODE)
                                value += sublist.item(j).getNodeValue();
                        }
                        if (value == "" || value.matches("[' ''\n']+"))
                            value = null;
                    }
                    else value = null;
                    XmlModel child = element.addChild(nodeList.item(i).getNodeName(), value);
                    readNode(nodeList.item(i), child);
                }
            }
        }
    }
    public void getResult(String name) throws ParserConfigurationException,IOException, SAXException {
        ObjectMapper object = objectMapper(name);
    }
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException{
        String name = "testxml.xml";
        new Parser().getResult(name);

    }
}
