package com.solvd.army.dao.jdbc.mysql.hangar;

import com.solvd.army.connection.ConnectionUtil;
import com.solvd.army.dao.IBaseDAO;
import com.solvd.army.models.hangar.Helicopter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HelicopterDAO implements IBaseDAO<Helicopter> {
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

    public HelicopterDAO() {
    }

    @Override
    public void create(Helicopter object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT);
            ps.setString(1, object.getName());
            ps.setDate(2, object.getReleaseDate());
            ps.setInt(3, object.getNumberOfFlights());
            ps.setInt(4, object.getStrength());
            ps.setLong(5, object.getHangars_id());
            ps.executeQuery();

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

            while(rs.next()) {
                Helicopter helicopter = new Helicopter();
                helicopter.setId(rs.getInt("id"));
                helicopter.setName(rs.getString("name"));
                helicopter.setReleaseDate(rs.getDate("releaseDate"));
                helicopter.setNumberOfFlights(rs.getInt("numberOfFlights"));
                helicopter.setStrength(rs.getInt("strength"));
                helicopter.setHangars_id(rs.getInt("Hangars_id"));
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
                helicopter.setHangars_id(rs.getInt("Hangars_id"));
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
            Helicopter helicopter = getById(id);
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);

            ps.setString(1, helicopter.getName());
            ps.setDate(2, helicopter.getReleaseDate());
            ps.setInt(3, helicopter.getNumberOfFlights());
            ps.setInt(4, helicopter.getStrength());
            ps.setInt(5, helicopter.getId());
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
