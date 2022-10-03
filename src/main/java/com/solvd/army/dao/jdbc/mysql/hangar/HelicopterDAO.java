package com.solvd.army.dao.jdbc.mysql.hangar;

import com.solvd.army.connection.ConnectionUtil;
import com.solvd.army.dao.IHelicopterDAO;
import com.solvd.army.models.hangar.Helicopter;
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

public class HelicopterDAO implements IHelicopterDAO {
    private static final Logger logger = LogManager.getLogger(AircraftDAO.class);
    private static final Scanner scanner = new Scanner(System.in);

    private static final String GET = "SELECT * FROM army.helicopters WHERE id=?";
    private static final String GET_ALL = "SELECT * FROM army.helicopters";
    private static final String UPDATE = "UPDATE army.helicopters SET army.helicopters.name=?, " +
            "army.helicopters.releaseDate=?, " +
            "army.helicopters.numberOfFlights=?, army.helicopters.strength=? WHERE army.helicopters.id=?";
    private static final String INSERT = "INSERT INTO army.helicopters (army.helicopters.name, " +
            "army.helicopters.releaseDate, " +
            "army.helicopters.numberOfFlights, army.helicopters.strength, army.helicopters.Hangars_id)\n" +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM army.helicopters WHERE id=?";
    private static final String GET_ALL_BY_HANGARS_ID = "SELECT * FROM army.helicopters WHERE Hangars_id=?";

    public HelicopterDAO() {
    }

    @Override
    public void create(Helicopter object) throws AttributeNotFoundException {
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
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
    }

    @Override
    public Helicopter getById(long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                Helicopter helicopter = new Helicopter();
                helicopter.setId(rs.getInt("id"));
                helicopter.setName(rs.getString("name"));
                helicopter.setReleaseDate(rs.getDate("releaseDate"));
                helicopter.setNumberOfFlights(rs.getInt("numberOfFlights"));
                helicopter.setStrength(rs.getInt("strength"));
                helicopter.setHangarsId(rs.getInt("Hangars_id"));
                return helicopter;
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
    public List<Helicopter> getAllRows() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);
            List<Helicopter> helicopters = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Helicopter helicopter = new Helicopter();
                helicopter.setId(rs.getInt("id"));
                helicopter.setName(rs.getString("name"));
                helicopter.setReleaseDate(rs.getDate("releaseDate"));
                helicopter.setNumberOfFlights(rs.getInt("numberOfFlights"));
                helicopter.setStrength(rs.getInt("strength"));
                helicopter.setHangarsId(rs.getInt("Hangars_id"));
                helicopters.add(helicopter);
            }
            return helicopters;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
    }

    @Override
    public List<Helicopter> getAllByHangarId(long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL_BY_HANGARS_ID);
            List<Helicopter> helicopters = new ArrayList<>();
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Helicopter helicopter = new Helicopter();
                helicopter.setId(rs.getInt("id"));
                helicopter.setName(rs.getString("name"));
                helicopter.setReleaseDate(rs.getDate("releaseDate"));
                helicopter.setNumberOfFlights(rs.getInt("numberOfFlights"));
                helicopter.setStrength(rs.getInt("strength"));
                helicopter.setHangarsId(rs.getInt("Hangars_id"));
                helicopters.add(helicopter);
            }
            return helicopters;

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
    public void update(Helicopter object) {
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
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
    }

    @Override
    public long getObjectId(Helicopter object) throws AttributeNotFoundException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Helicopter helicopter = new Helicopter();
                helicopter.setId(rs.getInt("id"));
                helicopter.setName(rs.getString("name"));
                helicopter.setReleaseDate(rs.getDate("releaseDate"));
                helicopter.setNumberOfFlights(rs.getInt("numberOfFlights"));
                helicopter.setStrength(rs.getInt("strength"));
                helicopter.setHangarsId(rs.getInt("Hangars_id"));
                if (helicopter.equals(object)) {
                    return helicopter.getId();
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
