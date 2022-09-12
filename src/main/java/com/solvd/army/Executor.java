package com.solvd.army;

import com.solvd.army.connection.ConnectionUtil;
import com.solvd.army.dao.jdbc.mysql.barrack.BarrackDAO;
import com.solvd.army.dao.jdbc.mysql.hangar.AircraftDAO;
import com.solvd.army.dao.jdbc.mysql.jettie.BoatDAO;
import com.solvd.army.models.barrack.Barrack;
import com.solvd.army.models.hangar.Aircraft;
import com.solvd.army.models.jettie.Boat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class Executor {

    private static final Logger logger = LogManager.getLogger(Executor.class);
    public static void main(String[] args) {
          try (Connection connection = ConnectionUtil.getConnection()) {
            logger.info(connection.getCatalog() + " – connected!");
          } catch (SQLException e) {
              logger.error(e);
          }

          AircraftDAO aircraftDAO = new AircraftDAO();
          List<Aircraft> aircrafts = aircraftDAO.getAllRows();
          for(Aircraft aircraft : aircrafts) {
              logger.info(aircraft);
          }

    }
}
