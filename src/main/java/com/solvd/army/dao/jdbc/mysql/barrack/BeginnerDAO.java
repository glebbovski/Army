package com.solvd.army.dao.jdbc.mysql.barrack;

import com.solvd.army.connection.ConnectionUtil;
import com.solvd.army.dao.IBeginnerDAO;
import com.solvd.army.dao.jdbc.mysql.hangar.AircraftDAO;
import com.solvd.army.models.barrack.Beginner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.management.AttributeNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BeginnerDAO implements IBeginnerDAO {
    private static final Logger logger = LogManager.getLogger(AircraftDAO.class);
    private static final Scanner scanner = new Scanner(System.in);

    private static final String GET = "SELECT * FROM army.beginners WHERE id=?";
    private static final String GET_ALL = "SELECT * FROM army.beginners";
    private static final String UPDATE = "UPDATE army.beginners SET army.beginners.name=?, army.beginners.surname=?, " +
            "army.beginners.beginDate=?, army.beginners.endDate=? WHERE army.beginners.id=?";
    private static final String INSERT = "INSERT INTO army.beginners (army.beginners.name, army.beginners.surname, " +
            "army.beginners.beginDate, army.beginners.endDate, army.beginners.strength, army.beginners.Barracks_id)\n" +
            "VALUES (?, ?, ?, ?, 1, ?)";
    private static final String DELETE = "DELETE FROM army.beginners WHERE id=?";
    private static final String GET_ALL_BY_BARRACK_ID = "SELECT * FROM army.beginners WHERE Barracks_id=?";

    public BeginnerDAO() {
    }

    @Override
    public void create(Beginner object) throws AttributeNotFoundException{
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT);
            ps.setString(1, object.getName());
            ps.setString(2, object.getSurname());
            ps.setDate(3, object.getBeginDate());
            ps.setDate(4, object.getEndDate());
            ps.setLong(5, object.getBarracksId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
    }

    @Override
    public Beginner getById(long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                Beginner beginner = new Beginner();
                beginner.setId(rs.getInt("id"));
                beginner.setName(rs.getString("name"));
                beginner.setSurname(rs.getString("surname"));
                beginner.setBeginDate(rs.getDate("beginDate"));
                beginner.setEndDate(rs.getDate("endDate"));
                beginner.setBarracksId(rs.getInt("Barracks_id"));
                return beginner;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
        return null;
    }

    @Override
    public List<Beginner> getAllRows() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);
            List<Beginner> beginners = new ArrayList<>();
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Beginner beginner = new Beginner();
                beginner.setId(rs.getInt("id"));
                beginner.setName(rs.getString("name"));
                beginner.setSurname(rs.getString("surname"));
                beginner.setBeginDate(rs.getDate("beginDate"));
                beginner.setEndDate(rs.getDate("endDate"));
                beginner.setBarracksId(rs.getInt("Barracks_id"));
                beginners.add(beginner);
            }
            return beginners;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
    }

    @Override
    public List<Beginner> getAllByBarrackId(long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL_BY_BARRACK_ID);
            List<Beginner> beginners = new ArrayList<>();
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Beginner beginner = new Beginner();
                beginner.setId(rs.getInt("id"));
                beginner.setName(rs.getString("name"));
                beginner.setSurname(rs.getString("surname"));
                beginner.setBeginDate(rs.getDate("beginDate"));
                beginner.setEndDate(rs.getDate("endDate"));
                beginner.setBarracksId(rs.getInt("Barracks_id"));
                beginners.add(beginner);
            }
            return beginners;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
    }

    @Override
    public void update(long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);

            logger.info("New name = ");
            ps.setString(1, scanner.nextLine());
            logger.info("New surname = ");
            ps.setString(2, scanner.nextLine());
            logger.info("New beginDate (like 2002-07-26) = ");
            try {
                String str = scanner.nextLine();
                java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(str);
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                ps.setDate(3, sqlDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            logger.info("New endDate = ");
            try {
                String str = scanner.nextLine();
                java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(str);
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                ps.setDate(4, sqlDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            ps.setLong(5, id);
            scanner.close();

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
    }

    @Override
    public void update(Beginner object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);

            ps.setString(1, object.getName());
            ps.setString(2, object.getSurname());
            ps.setDate(3, object.getBeginDate());
            ps.setDate(4, object.getEndDate());
            ps.setLong(5, object.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }


    }

    @Override
    public void remove(long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(DELETE);
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
    }

    @Override
    public long getObjectId(Beginner object) throws AttributeNotFoundException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Beginner beginner = new Beginner();
                beginner.setId(rs.getInt("id"));
                beginner.setName(rs.getString("name"));
                beginner.setSurname(rs.getString("surname"));
                beginner.setBeginDate(rs.getDate("beginDate"));
                beginner.setEndDate(rs.getDate("endDate"));
                beginner.setBarracksId(rs.getInt("Barracks_id"));
                if (object.equals(beginner)) {
                    return beginner.getId();
                }
            }
            throw new AttributeNotFoundException();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
    }
}
