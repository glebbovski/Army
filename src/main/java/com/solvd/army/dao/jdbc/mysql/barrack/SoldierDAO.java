package com.solvd.army.dao.jdbc.mysql.barrack;

import com.solvd.army.connection.ConnectionUtil;
import com.solvd.army.dao.IBaseDAO;
import com.solvd.army.models.barrack.Soldier;


import java.sql.*;

public class SoldierDAO implements IBaseDAO<Soldier> {
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
            connection = ConnectionUtil.getConnection();
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
            connection = ConnectionUtil.getConnection();
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
            connection = ConnectionUtil.getConnection();
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
