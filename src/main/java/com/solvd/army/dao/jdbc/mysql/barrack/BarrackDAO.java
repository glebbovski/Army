package com.solvd.army.dao.jdbc.mysql.barrack;

import com.solvd.army.connection.ConnectionUtil;
import com.solvd.army.dao.IBaseDAO;
import com.solvd.army.models.barrack.Barrack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BarrackDAO implements IBaseDAO<Barrack> {
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

    public BarrackDAO() {
    }

    @Override
    public void create(Barrack object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT);
            ps.setInt(1, object.getNumberOfBeds());
            ps.setInt(2, object.getNumberOfFloors());
            ps.setLong(3, object.getArmy_id());
            ps.executeQuery();

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

            while(rs.next()) {
                Barrack barrack = new Barrack();
                barrack.setId(rs.getInt("id"));
                barrack.setNumberOfBeds(rs.getInt("numberOfBeds"));
                barrack.setNumberOfFloors(rs.getInt("numberOfFloors"));
                barrack.setArmy_id(rs.getInt("Army_id"));
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
                barrack.setArmy_id(rs.getInt("Army_id"));
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
            Barrack barrack = getById(id);
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);

            ps.setInt(1, barrack.getNumberOfBeds());
            ps.setInt(2, barrack.getNumberOfFloors());
            ps.setInt(3, barrack.getId());
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
