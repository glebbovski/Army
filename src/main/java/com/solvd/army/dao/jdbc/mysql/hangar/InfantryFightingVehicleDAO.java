package com.solvd.army.dao.jdbc.mysql.hangar;

import com.solvd.army.connection.ConnectionUtil;
import com.solvd.army.dao.IBaseDAO;
import com.solvd.army.models.hangar.InfantryFightingVehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InfantryFightingVehicleDAO implements IBaseDAO<InfantryFightingVehicle> {
    private static final String GET = "SELECT * FROM army.infantryfightingvehicles WHERE id=?";
    private static final String UPDATE = "UPDATE army.infantryfightingvehicles SET " +
            "army.infantryfightingvehicles.name=?, " +
            "army.infantryfightingvehicles.releaseDate=?, " +
            "army.infantryfightingvehicles.numberOfGuns=?, army.infantryfightingvehicles.strength=? WHERE " +
            "army.infantryfightingvehicles.id=?";
    private static final String INSERT = "INSERT INTO army.infantryfightingvehicles " +
            "(army.infantryfightingvehicles.name, " +
            "army.infantryfightingvehicles.releaseDate, " +
            "army.infantryfightingvehicles.numberOfGuns, army.infantryfightingvehicles.strength, " +
            "army.infantryfightingvehicles.Hangars_id)\n" +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM army.infantryfightingvehicles WHERE id=?";

    public InfantryFightingVehicleDAO() {
    }

    @Override
    public void create(InfantryFightingVehicle object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT);
            ps.setString(1, object.getName());
            ps.setDate(2, object.getReleaseDate());
            ps.setInt(3, object.getNumberOfGuns());
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
    public InfantryFightingVehicle getById(long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                InfantryFightingVehicle infantryFightingVehicles = new InfantryFightingVehicle();
                infantryFightingVehicles.setId(rs.getInt("id"));
                infantryFightingVehicles.setName(rs.getString("name"));
                infantryFightingVehicles.setReleaseDate(rs.getDate("releaseDate"));
                infantryFightingVehicles.setNumberOfGuns(rs.getInt("numberOfGuns"));
                infantryFightingVehicles.setStrength(rs.getInt("strength"));
                infantryFightingVehicles.setHangars_id(rs.getInt("Hangars_id"));
                return infantryFightingVehicles;
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
            InfantryFightingVehicle infantryFightingVehicles = getById(id);
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);

            ps.setString(1, infantryFightingVehicles.getName());
            ps.setDate(2, infantryFightingVehicles.getReleaseDate());
            ps.setInt(3, infantryFightingVehicles.getNumberOfGuns());
            ps.setInt(4, infantryFightingVehicles.getStrength());
            ps.setInt(5, infantryFightingVehicles.getId());
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
