package com.solvd.army.dao.jdbc.mysql.jettie;

import com.solvd.army.connection.ConnectionUtil;
import com.solvd.army.dao.IBaseDAO;
import com.solvd.army.dao.ISubmarineDAO;
import com.solvd.army.models.jettie.Submarine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubmarineDAO implements ISubmarineDAO {
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

    public SubmarineDAO() {
    }

    @Override
    public void create(Submarine object) {
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
            ps.executeQuery();

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
    public void update(long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            Submarine submarine = getById(id);
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);

            ps.setString(1, submarine.getName());
            ps.setDate(2, submarine.getReleaseDate());
            ps.setInt(3, submarine.getNumberOfBombs());
            ps.setInt(4, submarine.getNumberOfEchoSounders());
            ps.setInt(5, submarine.getStrength());
            ps.setInt(6, submarine.getId());
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
