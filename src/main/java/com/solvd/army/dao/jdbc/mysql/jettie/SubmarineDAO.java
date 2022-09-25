package com.solvd.army.dao.jdbc.mysql.jettie;

import com.solvd.army.connection.ConnectionUtil;
import com.solvd.army.dao.IBaseDAO;
import com.solvd.army.dao.ISubmarineDAO;
import com.solvd.army.dao.jdbc.mysql.hangar.AircraftDAO;
import com.solvd.army.models.jettie.Submarine;
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

public class SubmarineDAO implements ISubmarineDAO {
    private static final Logger logger = LogManager.getLogger(AircraftDAO.class);
    private static final Scanner scanner = new Scanner(System.in);

    private static final String GET = "SELECT * FROM army.submarines WHERE id=?";
    private static final String GET_ALL = "SELECT * FROM army.submarines";
    private static final String UPDATE = "UPDATE army.submarines SET " +
            "army.submarines.name=?, " +
            "army.submarines.releaseDate=?, " +
            "army.submarines.numberOfBombs=?, " +
            "army.submarines.numberOfEchoSounders=?, " +
            "army.submarines.strength=? " +
            "WHERE army.submarines.id=?";
    private static final String INSERT = "INSERT INTO army.submarines " +
            "(army.submarines.name, " +
            "army.submarines.releaseDate, " +
            "army.submarines.numberOfBombs, " +
            "army.submarines.numberOfEchoSounders, " +
            "army.submarines.strength, " +
            "army.submarines.Jetties_id)\n" +
            "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM army.submarines WHERE id=?";
    private static final String GET_ALL_BY_JETTIES_ID = "SELECT * FROM army.submarines WHERE Jetties_id=?";

    public SubmarineDAO() {
    }

    @Override
    public void create(Submarine object) throws AttributeNotFoundException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT);
            ps.setString(1, object.getName());
            ps.setDate(2, object.getReleaseDate());
            ps.setInt(3, object.getNumberOfBombs());
            ps.setInt(4, object.getNumberOfEchoSounders());
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
    public Submarine getById(long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                Submarine submarine = new Submarine();
                submarine.setId(rs.getInt("id"));
                submarine.setName(rs.getString("name"));
                submarine.setReleaseDate(rs.getDate("releaseDate"));
                submarine.setNumberOfBombs(rs.getInt("numberOfBombs"));
                submarine.setNumberOfEchoSounders(rs.getInt("numberOfEchoSounders"));
                submarine.setStrength(rs.getInt("strength"));
                submarine.setJettiesId(rs.getInt("Jetties_id"));
                return submarine;
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
    public List<Submarine> getAllRows() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);
            List<Submarine> submarineList = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Submarine submarine = new Submarine();
                submarine.setId(rs.getInt("id"));
                submarine.setName(rs.getString("name"));
                submarine.setReleaseDate(rs.getDate("releaseDate"));
                submarine.setNumberOfBombs(rs.getInt("numberOfBombs"));
                submarine.setNumberOfEchoSounders(rs.getInt("numberOfEchoSounders"));
                submarine.setStrength(rs.getInt("strength"));
                submarine.setJettiesId(rs.getInt("Jetties_id"));
                submarineList.add(submarine);
            }
            return submarineList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
    }


    @Override
    public List<Submarine> getAllByJettieId(long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL_BY_JETTIES_ID);
            List<Submarine> submarineList = new ArrayList<>();
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Submarine submarine = new Submarine();
                submarine.setId(rs.getInt("id"));
                submarine.setName(rs.getString("name"));
                submarine.setReleaseDate(rs.getDate("releaseDate"));
                submarine.setNumberOfBombs(rs.getInt("numberOfBombs"));
                submarine.setNumberOfEchoSounders(rs.getInt("numberOfEchoSounders"));
                submarine.setStrength(rs.getInt("strength"));
                submarine.setJettiesId(rs.getInt("Jetties_id"));
                submarineList.add(submarine);
            }
            return submarineList;
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
            logger.info("New numberOfBombs = ");
            ps.setInt(3, scanner.nextInt());
            logger.info("New numberOfEchoSounders = ");
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
    public void update(Submarine object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);

            ps.setString(1, object.getName());
            ps.setDate(2, object.getReleaseDate());
            ps.setInt(3, object.getNumberOfBombs());
            ps.setInt(4, object.getNumberOfEchoSounders());
            ps.setInt(5, object.getStrength());
            ps.setLong(6, object.getId());
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
    public long getObjectId(Submarine object) throws AttributeNotFoundException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Submarine submarine = new Submarine();
                submarine.setId(rs.getInt("id"));
                submarine.setName(rs.getString("name"));
                submarine.setReleaseDate(rs.getDate("releaseDate"));
                submarine.setNumberOfBombs(rs.getInt("numberOfBombs"));
                submarine.setNumberOfEchoSounders(rs.getInt("numberOfEchoSounders"));
                submarine.setStrength(rs.getInt("strength"));
                submarine.setJettiesId(rs.getInt("Jetties_id"));
                if (submarine.equals(object)) {
                    return submarine.getId();
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
