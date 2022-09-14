package com.solvd.army.dao.jdbc.mysql;

import com.solvd.army.connection.ConnectionUtil;
import com.solvd.army.dao.IBaseDAO;
import com.solvd.army.dao.IMainArmyDAO;
import com.solvd.army.models.MainArmy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainArmyDAO implements IMainArmyDAO {
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

    public MainArmyDAO() {
    }

    @Override
    public void create(MainArmy object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT);
            ps.setString(1, object.getName());
            ps.setInt(2, object.getRating());
            ps.executeQuery();

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
            MainArmy mainArmy = getById(id);
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);

            ps.setString(1, mainArmy.getName());
            ps.setInt(2, mainArmy.getRating());
            ps.setInt(3, mainArmy.getId());
            ps.executeQuery();
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
            ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
    }
}
