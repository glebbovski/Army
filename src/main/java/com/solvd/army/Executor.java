package com.solvd.army;

import com.solvd.army.dao.jdbc.mysql.hangar.AircraftDAO;
import com.solvd.army.models.hangar.Aircraft;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.management.AttributeNotFoundException;
import java.util.List;

public class Executor {

    private static final Logger logger = LogManager.getLogger(Executor.class);

    public static void main(String[] args) throws AttributeNotFoundException {
          AircraftDAO aircraftDAO = new AircraftDAO();
          List<Aircraft> aircrafts = aircraftDAO.getAllRows();
          for(Aircraft aircraft : aircrafts) {
              logger.info(aircraft);
          }

          Aircraft syllabus = new Aircraft("Buntori",
                  new java.sql.Date(106, 7, 27), 4, 1, 16);

          aircraftDAO.create(syllabus);
          logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
          logger.info("CREATED");
          logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
          aircrafts = aircraftDAO.getAllRows();
          for(Aircraft aircraft : aircrafts) {
              logger.info(aircraft);
          }
          aircraftDAO.update(syllabus.getId());
          logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
          logger.info("UPDATED");
          logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
          aircrafts = aircraftDAO.getAllRows();
          for(Aircraft aircraft : aircrafts) {
            logger.info(aircraft);
          }

          aircraftDAO.remove(syllabus.getId());
          logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
          logger.info("DELETED");
          logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
          aircrafts = aircraftDAO.getAllRows();
          for(Aircraft aircraft : aircrafts) {
              logger.info(aircraft);
          }





    }
}
