package com.solvd.army.dao.jdbc.mysql.jettie;

import com.solvd.army.dao.IBaseDAO;
import com.solvd.army.models.jettie.Warship;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WarshipDAO implements IBaseDAO<Warship> {
    private static final String GET = "SELECT * FROM army.warships WHERE id=?";
    private static final String UPDATE = "UPDATE army.warships SET " +
            "army.warships.name=?, " +
            "army.warships.releaseDate=?, " +
            "army.warships.numberOfGuns=?, " +
            "army.warships.numberOfBombs=?, " +
            "army.warships.strength=? " +
            "WHERE army.warships.id=?";
    private static final String INSERT = "INSERT INTO army.warships " +
            "(army.warships.name, " +
            "army.warships.releaseDate, " +
            "army.warships.numberOfGuns, " +
            "army.warships.numberOfBombs, " +
            "army.warships.strength, " +
            "army.warships.Jetties_id)\n" +
            "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM army.warships WHERE id=?";

    public WarshipDAO() {
    }

    @Override
    public void create(Warship object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(INSERT);
            ps.setString(1, object.getName());
            ps.setDate(2, object.getReleaseDate());
            ps.setInt(3, object.getNumberOfGuns());
            ps.setInt(4, object.getNumberOfBombs());
            ps.setInt(5, object.getStrength());
            ps.setLong(6, object.getJetties_id());
            ps.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(ps);
            close(connection);
        }
    }

    @Override
    public Warship getById(long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(GET);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Warship warship = new Warship();
                warship.setId(rs.getInt("id"));
                warship.setName(rs.getString("name"));
                warship.setReleaseDate(rs.getDate("releaseDate"));
                warship.setNumberOfGuns(rs.getInt("numberOfGuns"));
                warship.setNumberOfBombs(rs.getInt("numberOfBombs"));
                warship.setStrength(rs.getInt("strength"));
                warship.setJetties_id(rs.getInt("Jetties_id"));
                return warship;
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
            Warship warship = getById(id);
            connection = getConnection();
            ps = connection.prepareStatement(UPDATE);

            ps.setString(1, warship.getName());
            ps.setDate(2, warship.getReleaseDate());
            ps.setInt(3, warship.getNumberOfGuns());
            ps.setInt(4, warship.getNumberOfBombs());
            ps.setInt(5, warship.getStrength());
            ps.setInt(6, warship.getId());
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