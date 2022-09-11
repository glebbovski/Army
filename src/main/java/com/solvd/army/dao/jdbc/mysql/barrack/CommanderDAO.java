package com.solvd.army.dao.jdbc.mysql.barrack;

import com.solvd.army.dao.IBaseDAO;
import com.solvd.army.models.barrack.Commander;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommanderDAO implements IBaseDAO<Commander> {
    private static final String GET = "SELECT * FROM army.commanders WHERE id=?";
    private static final String UPDATE = "UPDATE army.commanders SET army.commanders.name=?, army.commanders.surname=?, " +
            "army.commanders.rank=? WHERE army.commanders.id=?";
    private static final String INSERT = "INSERT INTO army.commanders (army.commanders.name, army.commanders.surname, " +
            "army.commanders.rank, army.commanders.strength, army.commanders.Barracks_id)\n" +
            "VALUES (?, ?, ?, 3, ?)";
    private static final String DELETE = "DELETE FROM army.commanders WHERE id=?";

    public CommanderDAO() {
    }

    @Override
    public void create(Commander commander) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(INSERT);
            ps.setString(1, commander.getName());
            ps.setString(2, commander.getSurname());
            ps.setString(3, commander.getRank());
            ps.setInt(4, commander.getBarracks_id());
            ps.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(ps);
            close(connection);
        }
    }

    @Override
    public Commander getById(long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(GET);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Commander commander = new Commander();
                commander.setId(rs.getInt("id"));
                commander.setName(rs.getString("name"));
                commander.setSurname(rs.getString("surname"));
                commander.setRank(rs.getString("rank"));
                commander.setBarracks_id(rs.getInt("Barracks_id"));
                return commander;
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
            Commander commander = getById(id);
            connection = getConnection();
            ps = connection.prepareStatement(UPDATE);

            ps.setString(1, commander.getName());
            ps.setString(2, commander.getSurname());
            ps.setString(3, commander.getRank());
            ps.setInt(4, commander.getId());
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