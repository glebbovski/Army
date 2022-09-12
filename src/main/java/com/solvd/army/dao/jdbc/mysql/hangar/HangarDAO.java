package com.solvd.army.dao.jdbc.mysql.hangar;

import com.solvd.army.connection.ConnectionUtil;
import com.solvd.army.dao.IBaseDAO;
import com.solvd.army.models.hangar.Hangar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HangarDAO implements IBaseDAO<Hangar> {
    private static final String GET = "SELECT * FROM army.hangars WHERE id=?";
    private static final String UPDATE = "UPDATE army.hangars SET army.hangars.numberOfMilitaryCraft=? " +
            "WHERE army.hangars.id=?";
    private static final String INSERT = "INSERT INTO army.hangars " +
            "(army.hangars.numberOfMilitaryCraft, army.hangars.Army_id)" +
            "\n" +
            "VALUES (?, ?)";
    private static final String DELETE = "DELETE FROM army.hangars WHERE id=?";

    public HangarDAO() {
    }

    @Override
    public void create(Hangar object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT);
            ps.setInt(1, object.getNumberOfMilitaryCraft());
            ps.setLong(2, object.getArmy_id());
            ps.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(ps);
            close(connection);
        }
    }

    @Override
    public Hangar getById(long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Hangar hangar = new Hangar();
                hangar.setId(rs.getInt("id"));
                hangar.setNumberOfMilitaryCraft(rs.getInt("numberOfMilitaryCraft"));
                hangar.setArmy_id(rs.getInt("Army_id"));
                return hangar;
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
            Hangar hangar = getById(id);
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);

            ps.setInt(1, hangar.getNumberOfMilitaryCraft());
            ps.setInt(2, hangar.getId());
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
