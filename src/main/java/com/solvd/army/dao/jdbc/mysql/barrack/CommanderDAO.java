package com.solvd.army.dao.jdbc.mysql.barrack;

import com.solvd.army.connection.ConnectionUtil;
import com.solvd.army.dao.IBaseDAO;
import com.solvd.army.dao.ICommanderDAO;
import com.solvd.army.dao.jdbc.mysql.hangar.AircraftDAO;
import com.solvd.army.models.barrack.Commander;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.management.AttributeNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommanderDAO implements ICommanderDAO {
    private static final Logger logger = LogManager.getLogger(AircraftDAO.class);
    private static final Scanner scanner = new Scanner(System.in);

    private static final String GET = "SELECT * FROM army.commanders WHERE id=?";
    private static final String GET_ALL = "SELECT * FROM army.commanders";
    private static final String UPDATE = "UPDATE army.commanders SET army.commanders.name=?, army.commanders.surname=?, " +
            "army.commanders.rank=? WHERE army.commanders.id=?";
    private static final String INSERT = "INSERT INTO army.commanders (army.commanders.name, army.commanders.surname, " +
            "army.commanders.rank, army.commanders.strength, army.commanders.Barracks_id)\n" +
            "VALUES (?, ?, ?, 3, ?)";
    private static final String DELETE = "DELETE FROM army.commanders WHERE id=?";

    public CommanderDAO() {
    }

    @Override
    public void create(Commander commander) throws AttributeNotFoundException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT);
            ps.setString(1, commander.getName());
            ps.setString(2, commander.getSurname());
            ps.setString(3, commander.getRank());
            ps.setLong(4, commander.getBarracksId());
            ps.executeUpdate();
            commander.setId(getObjectId(commander));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
    }

    @Override
    public Commander getById(long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                Commander commander = new Commander();
                commander.setId(rs.getInt("id"));
                commander.setName(rs.getString("name"));
                commander.setSurname(rs.getString("surname"));
                commander.setRank(rs.getString("rank"));
                commander.setBarracksId(rs.getInt("Barracks_id"));
                return commander;
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
    public List<Commander> getAllRows() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);
            List<Commander> commanders = new ArrayList<>();
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Commander commander = new Commander();
                commander.setId(rs.getInt("id"));
                commander.setName(rs.getString("name"));
                commander.setSurname(rs.getString("surname"));
                commander.setRank(rs.getString("rank"));
                commander.setBarracksId(rs.getInt("Barracks_id"));
                commanders.add(commander);
            }
            return commanders;

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
    public long getObjectId(Commander object) throws AttributeNotFoundException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Commander commander = new Commander();
                commander.setId(rs.getInt("id"));
                commander.setName(rs.getString("name"));
                commander.setSurname(rs.getString("surname"));
                commander.setRank(rs.getString("rank"));
                commander.setBarracksId(rs.getInt("Barracks_id"));
                if (commander.equals(object)) {
                    return commander.getId();
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
