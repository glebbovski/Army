package com.solvd.army;

import com.solvd.army.connection.ConnectionUtil;
import com.solvd.army.dao.jdbc.mysql.hangar.AircraftDAO;
import com.solvd.army.models.hangar.Aircraft;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

// dom parser packages
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;


public class Executor {

    private static final Logger logger = LogManager.getLogger(Executor.class);

    public static void main(String[] args) {
          try (Connection connection = ConnectionUtil.getConnection()) {
              DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
              DocumentBuilder builder = factory.newDocumentBuilder();
              Document document = builder.parse(new File("src/main/resources/domExample.xml"));
              document.getDocumentElement().normalize();

              Element root = document.getDocumentElement();
              logger.info(root.getNodeName()); // cars

              //Get all cars
              NodeList nList = document.getElementsByTagName("car"); // Object

              for (int temp = 0; temp < nList.getLength(); temp++)
              {
                  Node node = nList.item(temp);
                  if (node.getNodeType() == Node.ELEMENT_NODE)
                  {
                      Element eElement = (Element) node;
                      logger.info("============================");
                      logger.info("id = "    + eElement.getAttribute("id"));
                      logger.info("name = "  + eElement.getElementsByTagName("name").item(0).getTextContent());
                      logger.info("author = "   + eElement.getElementsByTagName("author").item(0).getTextContent());
                      logger.info("location = "    + eElement.getElementsByTagName("location").item(0).getTextContent());
                      logger.info("============================");
                  }
              }


              logger.info(connection.getCatalog() + " – connected!");
          } catch (SQLException | ParserConfigurationException |IOException | SAXException e) {
              logger.error(e);
          }

          AircraftDAO aircraftDAO = new AircraftDAO();
          List<Aircraft> aircrafts = aircraftDAO.getAllRows();
          for(Aircraft aircraft : aircrafts) {
              logger.info(aircraft);
          }

    }
}
