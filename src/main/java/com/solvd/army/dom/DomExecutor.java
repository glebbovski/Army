package com.solvd.army.dom;

import com.solvd.army.Executor;
import com.solvd.army.connection.ConnectionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import java.sql.Connection;
import java.sql.SQLException;

public class DomExecutor {

    private static final Logger logger = LogManager.getLogger(DomExecutor.class);

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("src/main/java/com/solvd/army/dom/domExample.xml"));
            document.getDocumentElement().normalize();

            Element root = document.getDocumentElement();
            logger.info(root.getNodeName()); // cars

            //Get all cars
            NodeList nList = document.getElementsByTagName("car"); // Object

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node node = nList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    logger.info("============================");
                    logger.info("id = " + eElement.getAttribute("id"));
                    logger.info("name = " + eElement.getElementsByTagName("name").item(0).getTextContent());
                    logger.info("author = " + eElement.getElementsByTagName("author").item(0).getTextContent());
                    logger.info("location = " + eElement.getElementsByTagName("location").item(0).getTextContent());
                    logger.info("============================");
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            logger.error(e);
        }

    }
}
