package com.solvd.army.dao.jdbc.mysql.jettie;

import com.solvd.army.connection.ConnectionUtil;
import com.solvd.army.dao.IBaseDAO;
import com.solvd.army.dao.IJettieDAO;
import com.solvd.army.dao.jdbc.mysql.hangar.AircraftDAO;
import com.solvd.army.models.jettie.Jettie;
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

public class JettieDAO implements IJettieDAO {
    private static final Logger logger = LogManager.getLogger(AircraftDAO.class);
    private static final Scanner scanner = new Scanner(System.in);

    private static final String GET = "SELECT * FROM army.jetties WHERE id=?";
    private static final String GET_ALL = "SELECT * FROM army.jetties";
    private static final String UPDATE = "UPDATE army.jetties SET army.jetties.numberOfShips=? " +
            "WHERE army.jetties.id=?";
    private static final String INSERT = "INSERT INTO army.jetties " +
            "(army.jetties.numberOfShips, army.jetties.Army_id)" +
            "\n" +
            "VALUES (?, ?)";
    private static final String DELETE = "DELETE FROM army.jetties WHERE id=?";

    public JettieDAO() {
    }

    @Override
    public void create(Jettie object) throws AttributeNotFoundException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT);
            ps.setInt(1, object.getNumberOfShips());
            ps.setLong(2, object.getArmyId());
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
    public Jettie getById(long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                Jettie jettie = new Jettie();
                jettie.setId(rs.getInt("id"));
                jettie.setNumberOfShips(rs.getInt("numberOfShips"));
                jettie.setArmyId(rs.getInt("Army_id"));
                return jettie;
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
    public List<Jettie> getAllRows() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);
            List<Jettie> jetties = new ArrayList<>();
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Jettie jettie = new Jettie();
                jettie.setId(rs.getInt("id"));
                jettie.setNumberOfShips(rs.getInt("numberOfShips"));
                jettie.setArmyId(rs.getInt("Army_id"));
                jetties.add(jettie);
            }
            return jetties;

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

            logger.info("New numberOfShips = ");
            ps.setInt(1, scanner.nextInt());
            ps.setLong(2, id);
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
    public void update(Jettie object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);

            ps.setInt(1, object.getNumberOfShips());
            ps.setLong(2, object.getId());
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
    public long getObjectId(Jettie object) throws AttributeNotFoundException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Jettie jettie = new Jettie();
                jettie.setId(rs.getInt("id"));
                jettie.setNumberOfShips(rs.getInt("numberOfShips"));
                jettie.setArmyId(rs.getInt("Army_id"));
                if(jettie.equals(object)) {
                    return jettie.getId();
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
