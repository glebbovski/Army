package com.solvd.army.dao.jdbc.mysql.hangar;

import com.solvd.army.connection.ConnectionUtil;
import com.solvd.army.dao.IBaseDAO;
import com.solvd.army.models.hangar.Aircraft;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AircraftDAO implements IBaseDAO<Aircraft> {
    private static final String GET = "SELECT * FROM army.aircrafts WHERE id=?";
    private static final String UPDATE = "UPDATE army.aircrafts SET army.aircrafts.name=?, " +
            "army.aircrafts.releaseDate=?, " +
            "army.aircrafts.numberOfFlights=?, army.aircrafts.strength=? WHERE army.aircrafts.id=?";
    private static final String INSERT = "INSERT INTO army.aircrafts (army.aircrafts.name, " +
            "army.aircrafts.releaseDate, " +
            "army.aircrafts.numberOfFlights, army.aircrafts.strength, army.aircrafts.Hangars_id)\n" +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM army.aircrafts WHERE id=?";

    public AircraftDAO() {
    }

    @Override
    public void create(Aircraft object) {
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
            close(ps);
            close(connection);
        }
    }

    @Override
    public Aircraft getById(long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Aircraft aircraft = new Aircraft();
                aircraft.setId(rs.getInt("id"));
                aircraft.setName(rs.getString("name"));
                aircraft.setReleaseDate(rs.getDate("releaseDate"));
                aircraft.setNumberOfFlights(rs.getInt("numberOfFlights"));
                aircraft.setStrength(rs.getInt("strength"));
                aircraft.setHangars_id(rs.getInt("Hangars_id"));
                return aircraft;
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
            Aircraft aircraft = getById(id);
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);

            ps.setString(1, aircraft.getName());
            ps.setDate(2, aircraft.getReleaseDate());
            ps.setInt(3, aircraft.getNumberOfFlights());
            ps.setInt(4, aircraft.getStrength());
            ps.setInt(5, aircraft.getId());
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
            connection = ConnectionUtil.getConnection();
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
