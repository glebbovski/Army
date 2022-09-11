package com.solvd.army.dao.jdbc.mysql.jettie;

import com.solvd.army.dao.IBaseDAO;
import com.solvd.army.models.jettie.Jettie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JettieDAO implements IBaseDAO<Jettie> {
    private static final String GET = "SELECT * FROM army.jetties WHERE id=?";
    private static final String UPDATE = "UPDATE army.jetties SET army.jetties.numberOfShips=? " +
            "WHERE army.jetties.id=?";
    private static final String INSERT = "INSERT INTO army.jetties " +
            "(army.jetties.numberOfShips, army.jetties.Army_id)" +
            "\n" +
            "VALUES (?, ?)";
    private static final String DELETE = "DELETE FROM army.jetties WHERE id=?";

    public JettieDAO() {
    }

    @Override
    public void create(Jettie object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(INSERT);
            ps.setInt(1, object.getNumberOfShips());
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
    public Jettie getById(long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(GET);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Jettie jettie = new Jettie();
                jettie.setId(rs.getInt("id"));
                jettie.setNumberOfShips(rs.getInt("numberOfShips"));
                jettie.setArmy_id(rs.getInt("Army_id"));
                return jettie;
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
            Jettie jettie = getById(id);
            connection = getConnection();
            ps = connection.prepareStatement(UPDATE);

            ps.setInt(1, jettie.getNumberOfShips());
            ps.setInt(2, jettie.getId());
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
