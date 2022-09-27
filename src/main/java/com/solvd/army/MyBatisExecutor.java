package com.solvd.army;

import com.solvd.army.dao.mybatis.AircraftMapperDAO;
import com.solvd.army.models.hangar.Aircraft;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.management.AttributeNotFoundException;
import java.util.List;

public class MyBatisExecutor {
    private static final Logger logger = LogManager.getLogger(MyBatisExecutor.class);

    public static void main(String[] args) throws AttributeNotFoundException {
        AircraftMapperDAO mapperDAO = new AircraftMapperDAO();
        Aircraft aircraft = mapperDAO.getById(3);
        logger.info(aircraft);
        logger.info("---------------------------------------");
        List<Aircraft> allCrafts = mapperDAO.getAllRows();
        for(Aircraft craft : allCrafts) {
            logger.info(craft);
        }
        logger.info("--------------CREATED---------------");
        mapperDAO.create(new Aircraft("Mineska",
                new java.sql.Date(106, 7, 27), 2, 1, 6));
        allCrafts = mapperDAO.getAllRows();
        for(Aircraft craft : allCrafts) {
            logger.info(craft);
        }
        logger.info("-------------UPDATED----------------");
        Aircraft frat = mapperDAO.getById(14);
        frat.setName("Bolevanna");
        frat.setStrength(0);
        mapperDAO.update(frat);
        allCrafts = mapperDAO.getAllRows();
        for(Aircraft craft : allCrafts) {
            logger.info(craft);
        }
        logger.info("----------DELETED--------------");
        mapperDAO.remove(14);
        allCrafts = mapperDAO.getAllRows();
        for(Aircraft craft : allCrafts) {
            logger.info(craft);
        }
    }
}
