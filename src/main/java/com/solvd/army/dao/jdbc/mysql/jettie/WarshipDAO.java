package com.solvd.army.dao.jdbc.mysql.jettie;

import com.solvd.army.connection.ConnectionUtil;
import com.solvd.army.dao.IBaseDAO;
import com.solvd.army.dao.IWarshipDAO;
import com.solvd.army.dao.jdbc.mysql.hangar.AircraftDAO;
import com.solvd.army.models.jettie.Warship;
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

public class WarshipDAO implements IWarshipDAO {
    private static final Logger logger = LogManager.getLogger(AircraftDAO.class);
    private static final Scanner scanner = new Scanner(System.in);

    private static final String GET = "SELECT * FROM army.warships WHERE id=?";
    private static final String GET_ALL = "SELECT * FROM army.warships";
    private static final String UPDATE = "UPDATE army.warships SET " +
            "army.warships.name=?, " +
            "army.warships.releaseDate=?, " +
            "army.warships.numberOfGuns=?, " +
            "army.warships.numberOfBombs=?, " +
            "army.warships.strength=? " +
            "WHERE army.warships.id=?";
    private static final String INSERT = "INSERT INTO army.warships " +
            "(army.warships.name, " +
            "army.warships.releaseDate, " +
            "army.warships.numberOfGuns, " +
            "army.warships.numberOfBombs, " +
            "army.warships.strength, " +
            "army.warships.Jetties_id)\n" +
            "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM army.warships WHERE id=?";

    public WarshipDAO() {
    }

    @Override
    public void create(Warship object) throws AttributeNotFoundException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT);
            ps.setString(1, object.getName());
            ps.setDate(2, object.getReleaseDate());
            ps.setInt(3, object.getNumberOfGuns());
            ps.setInt(4, object.getNumberOfBombs());
            ps.setInt(5, object.getStrength());
            ps.setLong(6, object.getJettiesId());
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
    public Warship getById(long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                Warship warship = new Warship();
                warship.setId(rs.getInt("id"));
                warship.setName(rs.getString("name"));
                warship.setReleaseDate(rs.getDate("releaseDate"));
                warship.setNumberOfGuns(rs.getInt("numberOfGuns"));
                warship.setNumberOfBombs(rs.getInt("numberOfBombs"));
                warship.setStrength(rs.getInt("strength"));
                warship.setJettiesId(rs.getInt("Jetties_id"));
                return warship;
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
    public List<Warship> getAllRows() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);
            List<Warship> warships = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Warship warship = new Warship();
                warship.setId(rs.getInt("id"));
                warship.setName(rs.getString("name"));
                warship.setReleaseDate(rs.getDate("releaseDate"));
                warship.setNumberOfGuns(rs.getInt("numberOfGuns"));
                warship.setNumberOfBombs(rs.getInt("numberOfBombs"));
                warship.setStrength(rs.getInt("strength"));
                warship.setJettiesId(rs.getInt("Jetties_id"));
                warships.add(warship);
            }
            return warships;

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
            logger.info("New numberrOfGuns = ");
            ps.setInt(3, scanner.nextInt());
            logger.info("New numberOfBombs = ");
            ps.setInt(4, scanner.nextInt());
            logger.info("New strength = ");
            ps.setInt(5, scanner.nextInt());
            ps.setLong(6, id);
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
    public void update(Warship object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);

            ps.setString(1, object.getName());
            ps.setDate(2, object.getReleaseDate());
            ps.setInt(3, object.getNumberOfGuns());
            ps.setInt(4, object.getNumberOfBombs());
            ps.setInt(5, object.getStrength());
            ps.setLong(6, object.getId());
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
    public long getObjectId(Warship object) throws AttributeNotFoundException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Warship warship = new Warship();
                warship.setId(rs.getInt("id"));
                warship.setName(rs.getString("name"));
                warship.setReleaseDate(rs.getDate("releaseDate"));
                warship.setNumberOfGuns(rs.getInt("numberOfGuns"));
                warship.setNumberOfBombs(rs.getInt("numberOfBombs"));
                warship.setStrength(rs.getInt("strength"));
                warship.setJettiesId(rs.getInt("Jetties_id"));
                if (warship.equals(object)) {
                    return warship.getId();
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
