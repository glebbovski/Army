package com.solvd.army.dao.jdbc.mysql;

import com.solvd.army.connection.ConnectionUtil;
import com.solvd.army.dao.IBaseDAO;
import com.solvd.army.dao.IMainArmyDAO;
import com.solvd.army.dao.jdbc.mysql.hangar.AircraftDAO;
import com.solvd.army.models.MainArmy;
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

public class MainArmyDAO implements IMainArmyDAO {
    private static final Logger logger = LogManager.getLogger(AircraftDAO.class);
    private static final Scanner scanner = new Scanner(System.in);

    private static final String GET = "SELECT * FROM army.mainArmy WHERE id=?";
    private static final String GET_ALL = "SELECT * FROM army.mainArmy";
    private static final String UPDATE = "UPDATE army.mainArmy SET " +
            "army.mainArmy.name=?, " +
            "army.mainArmy.rating=? " +
            "WHERE army.mainArmy.id=?";
    private static final String INSERT = "INSERT INTO army.mainArmy " +
            "(army.mainArmy.name, army.mainArmy.rating)" +
            "\n" +
            "VALUES (?, ?)";
    private static final String DELETE = "DELETE FROM army.mainArmy WHERE id=?";
    private static final String COUNT = "SELECT COUNT(*) FROM army.mainarmy";
    private static final String NAMES = "SELECT name FROM army.mainarmy";

    public MainArmyDAO() {
    }

    @Override
    public void create(MainArmy object) throws AttributeNotFoundException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT);
            ps.setString(1, object.getName());
            ps.setInt(2, object.getRating());
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
    public MainArmy getById(long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                MainArmy mainArmy = new MainArmy();
                mainArmy.setId(rs.getInt("id"));
                mainArmy.setName(rs.getString("name"));
                mainArmy.setRating(rs.getInt("rating"));
                return mainArmy;
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
    public List<MainArmy> getAllRows() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);
            List<MainArmy> mainArmies = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                MainArmy mainArmy = new MainArmy();
                mainArmy.setId(rs.getInt("id"));
                mainArmy.setName(rs.getString("name"));
                mainArmy.setRating(rs.getInt("rating"));
                mainArmies.add(mainArmy);
            }
            
            return mainArmies;

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
            logger.info("New rating = ");
            ps.setInt(2, scanner.nextInt());
            ps.setLong(3, id);
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
    public void update(MainArmy object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);

            ps.setString(1, object.getName());
            ps.setInt(2, object.getRating());
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
    public long getObjectId(MainArmy object) throws AttributeNotFoundException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                MainArmy mainArmy = new MainArmy();
                mainArmy.setId(rs.getInt("id"));
                mainArmy.setName(rs.getString("name"));
                mainArmy.setRating(rs.getInt("rating"));
                if (mainArmy.equals(object)) {
                    return mainArmy.getId();
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

    @Override
    public int getRowsCount() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(COUNT);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return rs.getInt("COUNT(*)");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
        return 0;
    }

    @Override
    public List<String> getArmiesNames() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(NAMES);
            List<String> lst = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lst.add(rs.getString("name"));
            }
            return lst;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
    }
}
