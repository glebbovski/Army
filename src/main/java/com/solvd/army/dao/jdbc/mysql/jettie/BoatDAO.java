package com.solvd.army.dao.jdbc.mysql.jettie;

import com.solvd.army.dao.IBaseDAO;
import com.solvd.army.models.jettie.Boat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BoatDAO implements IBaseDAO<Boat> {
    private static final String GET = "SELECT * FROM army.boats WHERE id=?";
    private static final String UPDATE = "UPDATE army.boats SET " +
            "army.boats.name=?, " +
            "army.boats.releaseDate=?, " +
            "army.boats.numberOfGuns=?, " +
            "army.boats.strength=? " +
            "WHERE army.boats.id=?";
    private static final String INSERT = "INSERT INTO army.boats " +
            "(army.boats.name, " +
            "army.boats.releaseDate, " +
            "army.boats.numberOfGuns, " +
            "army.boats.strength, " +
            "army.boats.Jetties_id)\n" +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM army.boats WHERE id=?";

    public BoatDAO() {
    }

    @Override
    public void create(Boat object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(INSERT);
            ps.setString(1, object.getName());
            ps.setDate(2, object.getReleaseDate());
            ps.setInt(3, object.getNumberOfGuns());
            ps.setInt(4, object.getStrength());
            ps.setLong(5, object.getJetties_id());
            ps.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(ps);
            close(connection);
        }
    }

    @Override
    public Boat getById(long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(GET);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Boat boats = new Boat();
                boats.setId(rs.getInt("id"));
                boats.setName(rs.getString("name"));
                boats.setReleaseDate(rs.getDate("releaseDate"));
                boats.setNumberOfGuns(rs.getInt("numberOfGuns"));
                boats.setStrength(rs.getInt("strength"));
                boats.setJetties_id(rs.getInt("Jetties_id"));
                return boats;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(ps);
            close(connection);
        }
        return null;
    }

    @Override
    public void update(long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            Boat boats = getById(id);
            connection = getConnection();
            ps = connection.prepareStatement(UPDATE);

            ps.setString(1, boats.getName());
            ps.setDate(2, boats.getReleaseDate());
            ps.setInt(3, boats.getNumberOfGuns());
            ps.setInt(4, boats.getStrength());
            ps.setInt(5, boats.getId());
            ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(ps);
            close(connection);
        }
    }

    @Override
    public void remove(long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(DELETE);
            ps.setLong(1, id);
            ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(ps);
            close(connection);
        }
    }
}
