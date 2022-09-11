package com.solvd.army.dao.jdbc.mysql.hangar;

import com.solvd.army.dao.IBaseDAO;
import com.solvd.army.models.hangar.UAV;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UAVDAO implements IBaseDAO<UAV> {
    private static final String GET = "SELECT * FROM army.uavs WHERE id=?";
    private static final String UPDATE = "UPDATE army.uavs SET army.uavs.name=?, " +
            "army.uavs.releaseDate=?, " +
            "army.uavs.numberOfBombs=?, army.uavs.strength=? WHERE army.uavs.id=?";
    private static final String INSERT = "INSERT INTO army.uavs (army.uavs.name, " +
            "army.uavs.releaseDate, " +
            "army.uavs.numberOfBombs, army.uavs.strength, army.uavs.Hangars_id)\n" +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM army.uavs WHERE id=?";

    public UAVDAO() {
    }

    @Override
    public void create(UAV object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(INSERT);
            ps.setString(1, object.getName());
            ps.setDate(2, object.getReleaseDate());
            ps.setInt(3, object.getNumberOfBombs());
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
    public UAV getById(long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(GET);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                UAV uavs = new UAV();
                uavs.setId(rs.getInt("id"));
                uavs.setName(rs.getString("name"));
                uavs.setReleaseDate(rs.getDate("releaseDate"));
                uavs.setNumberOfBombs(rs.getInt("numberOfBombs"));
                uavs.setStrength(rs.getInt("strength"));
                uavs.setHangars_id(rs.getInt("Hangars_id"));
                return uavs;
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
            UAV uav = getById(id);
            connection = getConnection();
            ps = connection.prepareStatement(UPDATE);

            ps.setString(1, uav.getName());
            ps.setDate(2, uav.getReleaseDate());
            ps.setInt(3, uav.getNumberOfBombs());
            ps.setInt(4, uav.getStrength());
            ps.setInt(5, uav.getId());
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
