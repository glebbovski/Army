package com.solvd.army.dao.jdbc.mysql.barrack;

import com.solvd.army.connection.ConnectionUtil;
import com.solvd.army.dao.IBarrackDAO;
import com.solvd.army.dao.jdbc.mysql.hangar.AircraftDAO;
import com.solvd.army.models.barrack.Barrack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.management.AttributeNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BarrackDAO implements IBarrackDAO {
    private static final Logger logger = LogManager.getLogger(AircraftDAO.class);
    private static final Scanner scanner = new Scanner(System.in);

    private static final String GET = "SELECT * FROM army.barracks WHERE id=?";
    private static final String GET_ALL = "SELECT * FROM army.barracks";
    private static final String UPDATE = "UPDATE army.barracks SET army.barracks.numberOfBeds=?, " +
            "army.barracks.numberOfFloors=? " +
            "WHERE army.barracks.id=?";
    private static final String INSERT = "INSERT INTO army.barracks " +
            "(army.barracks.numberOfBeds, army.barracks.numberOfFloors, army.barracks.Army_id)" +
            "\n" +
            "VALUES (?, ?, ?)";
    private static final String DELETE = "DELETE FROM army.barracks WHERE id=?";
    private static final String GET_ALL_BY_ARMY_ID = "SELECT * FROM army.barracks WHERE Army_id=?";

    public BarrackDAO() {
    }

    @Override
    public void create(Barrack object) throws AttributeNotFoundException{
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT);
            ps.setInt(1, object.getNumberOfBeds());
            ps.setInt(2, object.getNumberOfFloors());
            ps.setLong(3, object.getArmyId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
    }

    @Override
    public Barrack getById(long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                Barrack barrack = new Barrack();
                barrack.setId(rs.getInt("id"));
                barrack.setNumberOfBeds(rs.getInt("numberOfBeds"));
                barrack.setNumberOfFloors(rs.getInt("numberOfFloors"));
                barrack.setArmyId(rs.getInt("Army_id"));
                return barrack;
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
    public List<Barrack> getAllRows() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);

            ResultSet rs = ps.executeQuery();
            List<Barrack> barracks = new ArrayList<>();

            while(rs.next()) {
                Barrack barrack = new Barrack();
                barrack.setId(rs.getInt("id"));
                barrack.setNumberOfBeds(rs.getInt("numberOfBeds"));
                barrack.setNumberOfFloors(rs.getInt("numberOfFloors"));
                barrack.setArmyId(rs.getInt("Army_id"));
                barracks.add(barrack);
            }
            return barracks;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }

    }

    @Override
    public List<Barrack> getAllBarracksByArmyId(long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL_BY_ARMY_ID);
            ps.setLong(1, id);
            List<Barrack> barracks = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Barrack barrack = new Barrack();
                barrack.setId(rs.getInt("id"));
                barrack.setNumberOfBeds(rs.getInt("numberOfBeds"));
                barrack.setNumberOfFloors(rs.getInt("numberOfFloors"));
                barrack.setArmyId(rs.getInt("Army_id"));
                barracks.add(barrack);
            }
            return barracks;

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

            logger.info("New numberOfBeds = ");
            ps.setInt(1, scanner.nextInt());
            logger.info("New numberOfFloors = ");
            ps.setInt(2, scanner.nextInt());
            ps.setLong(3, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
    }

    @Override
    public void update(Barrack object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);

            ps.setInt(1, object.getNumberOfBeds());
            ps.setInt(2, object.getNumberOfFloors());
            ps.setLong(3, object.getId());
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
    public long getObjectId(Barrack object) throws AttributeNotFoundException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);

            ResultSet rs = ps.executeQuery();
            List<Barrack> barracks = new ArrayList<>();

            while(rs.next()) {
                Barrack barrack = new Barrack();
                barrack.setId(rs.getInt("id"));
                barrack.setNumberOfBeds(rs.getInt("numberOfBeds"));
                barrack.setNumberOfFloors(rs.getInt("numberOfFloors"));
                barrack.setArmyId(rs.getInt("Army_id"));
                barracks.add(barrack);
                if(barrack.equals(object)) {
                    return barrack.getId();
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
