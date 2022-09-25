package com.solvd.army.dao.jdbc.mysql.hangar;

import com.solvd.army.connection.ConnectionUtil;
import com.solvd.army.dao.IBaseDAO;
import com.solvd.army.dao.IInfantryFightingVehicleDAO;
import com.solvd.army.models.hangar.InfantryFightingVehicle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.management.AttributeNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InfantryFightingVehicleDAO implements IInfantryFightingVehicleDAO {
    private static final Logger logger = LogManager.getLogger(AircraftDAO.class);
    private static final Scanner scanner = new Scanner(System.in);

    private static final String GET = "SELECT * FROM army.infantryfightingvehicles WHERE id=?";
    private static final String GET_ALL = "SELECT * FROM army.infantryfightingvehicles";
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
    public void create(InfantryFightingVehicle object) throws AttributeNotFoundException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT);
            ps.setString(1, object.getName());
            ps.setDate(2, object.getReleaseDate());
            ps.setInt(3, object.getNumberOfGuns());
            ps.setInt(4, object.getStrength());
            ps.setLong(5, object.getHangarsId());
            ps.executeUpdate();
            object.setId(getObjectId(object));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
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

            if(rs.next()) {
                InfantryFightingVehicle infantryFightingVehicles = new InfantryFightingVehicle();
                infantryFightingVehicles.setId(rs.getInt("id"));
                infantryFightingVehicles.setName(rs.getString("name"));
                infantryFightingVehicles.setReleaseDate(rs.getDate("releaseDate"));
                infantryFightingVehicles.setNumberOfGuns(rs.getInt("numberOfGuns"));
                infantryFightingVehicles.setStrength(rs.getInt("strength"));
                infantryFightingVehicles.setHangarsId(rs.getInt("Hangars_id"));
                return infantryFightingVehicles;
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
    public List<InfantryFightingVehicle> getAllRows() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);
            List<InfantryFightingVehicle> infantryFightingVehicleList = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                InfantryFightingVehicle infantryFightingVehicles = new InfantryFightingVehicle();
                infantryFightingVehicles.setId(rs.getInt("id"));
                infantryFightingVehicles.setName(rs.getString("name"));
                infantryFightingVehicles.setReleaseDate(rs.getDate("releaseDate"));
                infantryFightingVehicles.setNumberOfGuns(rs.getInt("numberOfGuns"));
                infantryFightingVehicles.setStrength(rs.getInt("strength"));
                infantryFightingVehicles.setHangarsId(rs.getInt("Hangars_id"));
                infantryFightingVehicleList.add(infantryFightingVehicles);
            }
            return infantryFightingVehicleList;

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
            logger.info("New date (like 2002-07-26) = ");
            try {
                String str = scanner.nextLine();
                java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(str);
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                ps.setDate(2, sqlDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            logger.info("New numberOfGuns = ");
            ps.setInt(3, scanner.nextInt());
            logger.info("New strength = ");
            ps.setInt(4, scanner.nextInt());
            ps.setLong(5, id);
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
    public void update(InfantryFightingVehicle object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);

            ps.setString(1, object.getName());
            logger.info("New date (like 2002-07-26) = ");
            ps.setDate(2, object.getReleaseDate());
            ps.setInt(3, object.getNumberOfGuns());
            ps.setInt(4, object.getStrength());
            ps.setLong(5, object.getId());
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
    public long getObjectId(InfantryFightingVehicle object) throws AttributeNotFoundException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                InfantryFightingVehicle infantryFightingVehicles = new InfantryFightingVehicle();
                infantryFightingVehicles.setId(rs.getInt("id"));
                infantryFightingVehicles.setName(rs.getString("name"));
                infantryFightingVehicles.setReleaseDate(rs.getDate("releaseDate"));
                infantryFightingVehicles.setNumberOfGuns(rs.getInt("numberOfGuns"));
                infantryFightingVehicles.setStrength(rs.getInt("strength"));
                infantryFightingVehicles.setHangarsId(rs.getInt("Hangars_id"));
                if (infantryFightingVehicles.equals(object)) {
                    return infantryFightingVehicles.getId();
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
