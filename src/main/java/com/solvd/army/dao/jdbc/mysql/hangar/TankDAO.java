package com.solvd.army.dao.jdbc.mysql.hangar;

import com.solvd.army.connection.ConnectionUtil;
import com.solvd.army.dao.IBaseDAO;
import com.solvd.army.models.hangar.Tank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TankDAO implements IBaseDAO<Tank> {
    private static final String GET = "SELECT * FROM army.tanks WHERE id=?";
    private static final String GET_ALL = "SELECT * FROM army.tanks";
    private static final String UPDATE = "UPDATE army.tanks SET " +
            "army.tanks.name=?, " +
            "army.tanks.releaseDate=?, " +
            "army.tanks.numberOfGuns=?, army.tanks.centimetersOfArmor=?, " +
            "army.tanks.strength=? WHERE " +
            "army.tanks.id=?";
    private static final String INSERT = "INSERT INTO army.tanks " +
            "(army.tanks.name, " +
            "army.tanks.releaseDate, " +
            "army.tanks.numberOfGuns, army.tanks.centimetersOfArmor, army.tanks.strength, " +
            "army.tanks.Hangars_id)\n" +
            "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM army.tanks WHERE id=?";

    public TankDAO() {
    }

    @Override
    public void create(Tank object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT);
            ps.setString(1, object.getName());
            ps.setDate(2, object.getReleaseDate());
            ps.setInt(3, object.getNumberOfGuns());
            ps.setInt(4, object.getCentimetersOfArmor());
            ps.setInt(5, object.getStrength());
            ps.setLong(6, object.getHangars_id());
            ps.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
    }

    @Override
    public Tank getById(long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Tank tank = new Tank();
                tank.setId(rs.getInt("id"));
                tank.setName(rs.getString("name"));
                tank.setReleaseDate(rs.getDate("releaseDate"));
                tank.setNumberOfGuns(rs.getInt("numberOfGuns"));
                tank.setCentimetersOfArmor(rs.getInt("centimetersOfArmor"));
                tank.setStrength(rs.getInt("strength"));
                tank.setHangars_id(rs.getInt("Hangars_id"));
                return tank;
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
    public List<Tank> getAllRows() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);
            List<Tank> tanks = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Tank tank = new Tank();
                tank.setId(rs.getInt("id"));
                tank.setName(rs.getString("name"));
                tank.setReleaseDate(rs.getDate("releaseDate"));
                tank.setNumberOfGuns(rs.getInt("numberOfGuns"));
                tank.setCentimetersOfArmor(rs.getInt("centimetersOfArmor"));
                tank.setStrength(rs.getInt("strength"));
                tank.setHangars_id(rs.getInt("Hangars_id"));
                tanks.add(tank);
            }
            return tanks;

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
            Tank tank = getById(id);
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);

            ps.setString(1, tank.getName());
            ps.setDate(2, tank.getReleaseDate());
            ps.setInt(3, tank.getNumberOfGuns());
            ps.setInt(4, tank.getCentimetersOfArmor());
            ps.setInt(5, tank.getStrength());
            ps.setInt(6, tank.getId());
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
