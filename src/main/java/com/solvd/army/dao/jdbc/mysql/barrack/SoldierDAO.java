package com.solvd.army.dao.jdbc.mysql.barrack;

import com.solvd.army.connection.ConnectionUtil;
import com.solvd.army.dao.IBaseDAO;
import com.solvd.army.dao.ISoldierDAO;
import com.solvd.army.dao.jdbc.mysql.hangar.AircraftDAO;
import com.solvd.army.models.barrack.Soldier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.management.AttributeNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SoldierDAO implements ISoldierDAO {
    private static final Logger logger = LogManager.getLogger(AircraftDAO.class);
    private static final Scanner scanner = new Scanner(System.in);

    private static final String GET = "SELECT * FROM army.soldiers WHERE id=?";
    private static final String GET_ALL = "SELECT * FROM army.soldiers";
    private static final String UPDATE = "UPDATE army.soldiers SET army.soldiers.name=?, army.soldiers.surname=?, " +
            "army.soldiers.rank=? WHERE army.soldiers.id=?";
    private static final String INSERT = "INSERT INTO army.soldiers (army.soldiers.name, army.soldiers.surname, " +
            "army.soldiers.rank, army.soldiers.strength, army.soldiers.Barracks_id)\n" +
            "VALUES (?, ?, ?, 2, ?)";
    private static final String DELETE = "DELETE FROM army.soldiers WHERE id=?";


    public SoldierDAO() {
    }

    @Override
    public void create(Soldier soldier) throws AttributeNotFoundException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT);
            ps.setString(1, soldier.getName());
            ps.setString(2, soldier.getSurname());
            ps.setString(3, soldier.getRank());
            ps.setLong(4, soldier.getBarracksId());
            ps.executeUpdate();
            soldier.setId(getObjectId(soldier));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
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

            if(rs.next()) {
                Soldier soldier = new Soldier();
                soldier.setId(rs.getInt("id"));
                soldier.setName(rs.getString("name"));
                soldier.setSurname(rs.getString("surname"));
                soldier.setRank(rs.getString("rank"));
                soldier.setBarracksId(rs.getInt("Barracks_id"));
                return soldier;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
        return null;
    }

    @Override
    public List<Soldier> getAllRows() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);
            List<Soldier> soldiers = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Soldier soldier = new Soldier();
                soldier.setId(rs.getInt("id"));
                soldier.setName(rs.getString("name"));
                soldier.setSurname(rs.getString("surname"));
                soldier.setRank(rs.getString("rank"));
                soldier.setBarracksId(rs.getInt("Barracks_id"));
                soldiers.add(soldier);
            }
            return soldiers;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
    }

    @Override
    public void update(long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);

            logger.info("New name = ");
            ps.setString(1, scanner.nextLine());
            logger.info("New surname = ");
            ps.setString(2, scanner.nextLine());
            logger.info("New rank = ");
            ps.setString(3, scanner.nextLine());
            ps.setLong(4, id);
            scanner.close();
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
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
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
    }

    @Override
    public long getObjectId(Soldier object) throws AttributeNotFoundException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Soldier soldier = new Soldier();
                soldier.setId(rs.getInt("id"));
                soldier.setName(rs.getString("name"));
                soldier.setSurname(rs.getString("surname"));
                soldier.setRank(rs.getString("rank"));
                soldier.setBarracksId(rs.getInt("Barracks_id"));
                if(soldier.equals(object)) {
                    return soldier.getId();
                }
            }
            throw new AttributeNotFoundException();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
    }
}
