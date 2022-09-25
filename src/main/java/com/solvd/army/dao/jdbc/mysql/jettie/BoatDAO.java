package com.solvd.army.dao.jdbc.mysql.jettie;

import com.solvd.army.connection.ConnectionUtil;
import com.solvd.army.dao.IBaseDAO;
import com.solvd.army.dao.IBoatDAO;
import com.solvd.army.dao.jdbc.mysql.hangar.AircraftDAO;
import com.solvd.army.models.jettie.Boat;
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

public class BoatDAO implements IBoatDAO {
    private static final Logger logger = LogManager.getLogger(AircraftDAO.class);
    private static final Scanner scanner = new Scanner(System.in);

    private static final String GET = "SELECT * FROM army.boats WHERE id=?";
    private static final String GET_ALL = "SELECT * FROM army.boats";
    private static final String UPDATE = "UPDATE army.boats SET " +
            "army.boats.name=?, " +
            "army.boats.releaseDate=?, " +
            "army.boats.numberOfGuns=?, " +
            "army.boats.strength=? " +
            "WHERE army.boats.id=?";
    private static final String INSERT = "INSERT INTO army.boats " +
            "(army.boats.name, " +
            "army.boats.releaseDate, " +
            "army.boats.numberOfGuns, " +
            "army.boats.strength, " +
            "army.boats.Jetties_id)\n" +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM army.boats WHERE id=?";

    public BoatDAO() {
    }

    @Override
    public void create(Boat object) throws AttributeNotFoundException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT);
            ps.setString(1, object.getName());
            ps.setDate(2, object.getReleaseDate());
            ps.setInt(3, object.getNumberOfGuns());
            ps.setInt(4, object.getStrength());
            ps.setLong(5, object.getJettiesId());
            ps.executeUpdate();
            object.setId(getObjectId(object));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
    }

    @Override
    public Boat getById(long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                Boat boats = new Boat();
                boats.setId(rs.getInt("id"));
                boats.setName(rs.getString("name"));
                boats.setReleaseDate(rs.getDate("releaseDate"));
                boats.setNumberOfGuns(rs.getInt("numberOfGuns"));
                boats.setStrength(rs.getInt("strength"));
                boats.setJettiesId(rs.getInt("Jetties_id"));
                return boats;
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
    public List<Boat> getAllRows() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);
            List<Boat> boatsList = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Boat boats = new Boat();
                boats.setId(rs.getInt("id"));
                boats.setName(rs.getString("name"));
                boats.setReleaseDate(rs.getDate("releaseDate"));
                boats.setNumberOfGuns(rs.getInt("numberOfGuns"));
                boats.setStrength(rs.getInt("strength"));
                boats.setJettiesId(rs.getInt("Jetties_id"));
                boatsList.add(boats);
            }
            return boatsList;

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
            logger.info("New date (like 2002-07-26) = ");
            try {
                String str = scanner.nextLine();
                java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(str);
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                ps.setDate(2, sqlDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            logger.info("New numberOfGuns = ");
            ps.setInt(3, scanner.nextInt());
            logger.info("New strength = ");
            ps.setInt(4, scanner.nextInt());
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
    public void update(Boat object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);

            ps.setString(1, object.getName());
            ps.setDate(2, object.getReleaseDate());
            ps.setInt(3, object.getNumberOfGuns());
            ps.setInt(4, object.getStrength());
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
    public long getObjectId(Boat object) throws AttributeNotFoundException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Boat boats = new Boat();
                boats.setId(rs.getInt("id"));
                boats.setName(rs.getString("name"));
                boats.setReleaseDate(rs.getDate("releaseDate"));
                boats.setNumberOfGuns(rs.getInt("numberOfGuns"));
                boats.setStrength(rs.getInt("strength"));
                boats.setJettiesId(rs.getInt("Jetties_id"));
                if (boats.equals(object)) {
                    return boats.getId();
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
