package com.solvd.army.dao.jdbc.mysql.barrack;

import com.solvd.army.dao.IBaseDAO;
import com.solvd.army.models.barrack.Beginner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BeginnerDAO implements IBaseDAO<Beginner> {
    private static final String GET = "SELECT * FROM army.beginners WHERE id=?";
    private static final String UPDATE = "UPDATE army.beginners SET army.beginners.name=?, army.beginners.surname=?, " +
            "army.beginners.beginDate=?, army.beginners.endDate=? WHERE army.beginners.id=?";
    private static final String INSERT = "INSERT INTO army.beginners (army.beginners.name, army.beginners.surname, " +
            "army.beginners.beginDate, army.beginners.endDate, army.beginners.strength, army.beginners.Barracks_id)\n" +
            "VALUES (?, ?, ?, ?, 1, ?)";
    private static final String DELETE = "DELETE FROM army.beginners WHERE id=?";

    public BeginnerDAO() {
    }

    @Override
    public void create(Beginner object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(INSERT);
            ps.setString(1, object.getName());
            ps.setString(2, object.getSurname());
            ps.setDate(3, object.getBeginDate());
            ps.setDate(4, object.getEndDate());
            ps.setLong(5, object.getBarracks_id());
            ps.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(ps);
            close(connection);
        }
    }

    @Override
    public Beginner getById(long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(GET);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Beginner beginner = new Beginner();
                beginner.setId(rs.getInt("id"));
                beginner.setName(rs.getString("name"));
                beginner.setSurname(rs.getString("surname"));
                beginner.setBeginDate(rs.getDate("beginDate"));
                beginner.setEndDate(rs.getDate("endDate"));
                beginner.setBarracks_id(rs.getInt("Barracks_id"));
                return beginner;
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
            Beginner beginner = getById(id);
            connection = getConnection();
            ps = connection.prepareStatement(UPDATE);

            ps.setString(1, beginner.getName());
            ps.setString(2, beginner.getSurname());
            ps.setDate(3, beginner.getBeginDate());
            ps.setDate(4, beginner.getEndDate());
            ps.setInt(5, beginner.getId());
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
