package com.solvd.army.dao.jdbc.mysql.hangar;

import com.solvd.army.Executor;
import com.solvd.army.connection.ConnectionUtil;
import com.solvd.army.dao.IAircraftDAO;
import com.solvd.army.dao.IBaseDAO;
import com.solvd.army.models.hangar.Aircraft;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.management.AttributeNotFoundException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AircraftDAO implements IAircraftDAO {
    //TODO
    private static final Logger logger = LogManager.getLogger(AircraftDAO.class);
    private static final Scanner scanner = new Scanner(System.in);

    private static final String GET = "SELECT * FROM army.aircrafts WHERE id=?";
    private static final String GET_ALL = "SELECT * FROM army.aircrafts";
    private static final String UPDATE = "UPDATE army.aircrafts SET army.aircrafts.name=?, " +
            "army.aircrafts.releaseDate=?, " +
            "army.aircrafts.numberOfFlights=?, army.aircrafts.strength=? WHERE army.aircrafts.id=?";
    private static final String INSERT = "INSERT INTO army.aircrafts (army.aircrafts.name, " +
            "army.aircrafts.releaseDate, " +
            "army.aircrafts.numberOfFlights, army.aircrafts.strength, army.aircrafts.Hangars_id)\n" +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM army.aircrafts WHERE id=?";

    public AircraftDAO() {
    }

    @Override
    public void create(Aircraft object) throws AttributeNotFoundException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT);
            ps.setString(1, object.getName());
            ps.setDate(2, object.getReleaseDate());
            ps.setInt(3, object.getNumberOfFlights());
            ps.setInt(4, object.getStrength());
            ps.setLong(5, object.getHangarsId());
            // TODO
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
    public Aircraft getById(long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                Aircraft aircraft = new Aircraft();
                aircraft.setId(rs.getInt("id"));
                aircraft.setName(rs.getString("name"));
                aircraft.setReleaseDate(rs.getDate("releaseDate"));
                aircraft.setNumberOfFlights(rs.getInt("numberOfFlights"));
                aircraft.setStrength(rs.getInt("strength"));
                aircraft.setHangarsId(rs.getInt("Hangars_id"));
                return aircraft;
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
    public List<Aircraft> getAllRows() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);
            List<Aircraft> aircrafts = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Aircraft aircraft = new Aircraft();
                aircraft.setId(rs.getInt("id"));
                aircraft.setName(rs.getString("name"));
                aircraft.setReleaseDate(rs.getDate("releaseDate"));
                aircraft.setNumberOfFlights(rs.getInt("numberOfFlights"));
                aircraft.setStrength(rs.getInt("strength"));
                aircraft.setHangarsId(rs.getInt("Hangars_id"));
                aircrafts.add(aircraft);
            }
            return aircrafts;

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
            logger.info("New numberOfFlights = ");
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
    public void update(Aircraft object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);

            ps.setString(1, object.getName());
            ps.setDate(2, object.getReleaseDate());
            ps.setInt(3, object.getNumberOfFlights());
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
            // TODO
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
    }

    public long getObjectId(Aircraft object) throws AttributeNotFoundException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Aircraft aircraft = new Aircraft();
                aircraft.setId(rs.getInt("id"));
                aircraft.setName(rs.getString("name"));
                aircraft.setReleaseDate(rs.getDate("releaseDate"));
                aircraft.setNumberOfFlights(rs.getInt("numberOfFlights"));
                aircraft.setStrength(rs.getInt("strength"));
                aircraft.setHangarsId(rs.getInt("Hangars_id"));
                if(object.equals(aircraft)) {
                    return aircraft.getId(); // TODO equals
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
