package com.solvd.army;

import com.solvd.army.connection.ConnectionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;


public class Executor {

    private static final Logger logger = LogManager.getLogger(Executor.class);

    public static void main(String[] args) {
      try (Connection connection = ConnectionUtil.getConnection()) {
        logger.info(connection.getCatalog() + " – connected!");
      } catch (SQLException e) {
          logger.error(e);
      }
    }
}
