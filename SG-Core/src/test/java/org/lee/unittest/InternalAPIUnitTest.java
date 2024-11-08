package org.lee.unittest;

import org.json.XML;
import org.lee.common.config.InternalConfigs;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringWriter;

public class InternalAPIUnitTest {

    public Element findElement(Element element, String targetName){
        NodeList nodeList = element.getElementsByTagName(targetName);
        if(nodeList.getLength() > 0){
            return (Element) nodeList.item(0);
        }
        return null;
    }

    @Test
    public void testInternalAPIModifyLogger(){
        // get source
        // System.out.println(InternalConfigs.getLoggingConfigSourceTemplate());
        try {
            // todo fix it
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(InternalConfigs.getLoggingConfigSourceTemplate());
            Element rootElement = document.getDocumentElement();
            Element targetConfigurationNode = findElement(rootElement, "Configuration");
            Element targetPropertiesNode = findElement(targetConfigurationNode, "Properties");
            NodeList list = targetPropertiesNode.getElementsByTagName("Property");
            for(int i = 0; i < list.getLength(); i++){
                Element current = (Element) list.item(i);
                if (current.hasAttribute("name") && current.getAttribute("name").equals("filename")){
                    current.setTextContent("AAAAAAAAAAAAAAAAAAAAA!");
                }
            }
            StringWriter stringWriter = new StringWriter();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.transform(new DOMSource(document), new StreamResult(stringWriter));
            System.out.println(stringWriter.toString());
        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            throw new RuntimeException(e);
        }
    }
}
