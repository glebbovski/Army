package com.solvd.army.dao.jdbc.mysql.barrack;

import com.solvd.army.dao.IBaseDAO;
import com.solvd.army.models.barrack.Soldier;

import java.sql.*;

public class SoldierDAO implements IBaseDAO<Soldier> {
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/ca";
    private static final String ID = "root";
    private static final String PASS = "";

    private static final String GET = "SELECT * FROM army.soldiers WHERE id=?";
    private static final String UPDATE = "UPDATE army.soldiers SET army.soldiers.name=?, army.soldiers.surname=?, " +
            "army.soldiers.rank=? WHERE army.soldiers.id=?";
    private static final String INSERT = "INSERT INTO army.soldiers (army.soldiers.name, army.soldiers.surname, " +
            "army.soldiers.rank, army.soldiers.strength, army.soldiers.Barracks_id)\n" +
            "VALUES (?, ?, ?, 2, ?)";
    private static final String DELETE = "DELETE FROM army.soldiers WHERE id=?";


    public SoldierDAO() {
    }

    @Override
    public void create(Soldier soldier) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(INSERT);
            ps.setString(1, soldier.getName());
            ps.setString(2, soldier.getSurname());
            ps.setString(3, soldier.getRank());
            ps.setInt(4, soldier.getBarracks_id());
            ps.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(ps);
            close(connection);
        }
    }

    @Override
    public Soldier getById(long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(GET);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Soldier soldier = new Soldier();
                soldier.setId(rs.getInt("id"));
                soldier.setName(rs.getString("name"));
                soldier.setSurname(rs.getString("surname"));
                soldier.setRank(rs.getString("rank"));
                soldier.setBarracks_id(rs.getInt("Barracks_id"));
                return soldier;
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
            Soldier soldier = getById(id);
            connection = getConnection();
            ps = connection.prepareStatement(UPDATE);

            ps.setString(1, soldier.getName());
            ps.setString(2, soldier.getSurname());
            ps.setString(3, soldier.getRank());
            ps.setInt(4, soldier.getId());
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
            Soldier soldier = getById(id);
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



    private Connection getConnection() {
        try {
            Class.forName(DRIVER_NAME);
            return DriverManager.getConnection(DB_URL, ID, PASS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
