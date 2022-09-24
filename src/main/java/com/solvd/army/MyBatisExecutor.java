package com.solvd.army;

import com.mysql.cj.Session;
import com.solvd.army.dao.IAircraftDAO;
import com.solvd.army.models.hangar.Aircraft;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.management.AttributeNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class MyBatisExecutor {
    private static final Logger logger = LogManager.getLogger(MyBatisExecutor.class);

    public static void main(String[] args) throws AttributeNotFoundException {
        SqlSessionFactory sqlSessionFactory;
        IAircraftDAO aircraftDAO;
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            SqlSession session = sqlSessionFactory.openSession();
            aircraftDAO = session.getMapper(IAircraftDAO.class);
//            logger.info("------------BEFORE CREATION-------------");
//            List<Aircraft> aircrafts = aircraftDAO.getAllRows();
//            for(Aircraft f : aircrafts) {
//                logger.info(f);
//            }
//            aircraftDAO.create(new Aircraft("Let4ik", new java.sql.Date(106, 7, 27), 4, 1, 16));
//            logger.info("------------AFTER CREATION-------------");
//            aircrafts = aircraftDAO.getAllRows();
//            for(Aircraft f : aircrafts) {
//                logger.info(f);
//            }

            Aircraft aircraft = aircraftDAO.getById(2);
            logger.info("----------------------------------------");
            for(Aircraft f : aircraftDAO.getAllRows()) {
                logger.info(f);
            }
            aircraft.setNumberOfFlights(15);
            aircraft.setName("Let4iuk");

            aircraftDAO.update(2);
            logger.info("++++++++++++++++++++++++++++++++++++++++++++++");
            for(Aircraft f : aircraftDAO.getAllRows()) {
                logger.info(f);
            }





        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
