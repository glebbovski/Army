package com.solvd.army;

import com.mysql.cj.Session;
import com.solvd.army.dao.IAircraftDAO;
import com.solvd.army.dao.ISubmarineDAO;
import com.solvd.army.models.hangar.Aircraft;
import com.solvd.army.models.jettie.Submarine;
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
        ISubmarineDAO submarineDAO;
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            SqlSession session = sqlSessionFactory.openSession();
            submarineDAO = session.getMapper(ISubmarineDAO.class);

            // TODO READ
            for(Submarine s : submarineDAO.getAllRows()) {
                logger.info(s);
            }
            // TODO CREATE
            logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            logger.info("CREATED");
            logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            Submarine submarine = new Submarine("Mineska",
                    new java.sql.Date(106, 7, 27), 5, 2, 7, 2);
            submarineDAO.create(submarine);
            for(Submarine s : submarineDAO.getAllRows()) {
                logger.info(s);
            }
            session.commit();

            submarine.setId(submarineDAO.getAllRows().get(submarineDAO.getAllRows().size() - 1).getId()); // getting ID

            // TODO UPDATE
            logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            logger.info("UPDATED");
            logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            submarine.setName("Nieve");
            submarine.setNumberOfBombs(0);
            submarineDAO.update(submarine);

            session.commit();

            for(Submarine s : submarineDAO.getAllRows()) {
                logger.info(s);
            }

            // TODO DELETE
            logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            logger.info("DELETED");
            logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            submarineDAO.remove(submarine.getId());
            session.commit();

            for(Submarine s : submarineDAO.getAllRows()) {
                logger.info(s);
            }

            session.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
