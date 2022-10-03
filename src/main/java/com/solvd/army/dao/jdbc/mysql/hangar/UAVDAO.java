package com.solvd.army.dao.jdbc.mysql.hangar;

import com.solvd.army.connection.ConnectionUtil;
import com.solvd.army.dao.IUAVDAO;
import com.solvd.army.models.hangar.UAV;
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

public class UAVDAO implements IUAVDAO {
    private static final Logger logger = LogManager.getLogger(AircraftDAO.class);
    private static final Scanner scanner = new Scanner(System.in);

    private static final String GET = "SELECT * FROM army.uavs WHERE id=?";
    private static final String GET_ALL = "SELECT * FROM army.uavs";
    private static final String UPDATE = "UPDATE army.uavs SET army.uavs.name=?, " +
            "army.uavs.releaseDate=?, " +
            "army.uavs.numberOfBombs=?, army.uavs.strength=? WHERE army.uavs.id=?";
    private static final String INSERT = "INSERT INTO army.uavs (army.uavs.name, " +
            "army.uavs.releaseDate, " +
            "army.uavs.numberOfBombs, army.uavs.strength, army.uavs.Hangars_id)\n" +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM army.uavs WHERE id=?";
    private static final String GET_ALL_BY_HANGARS_ID = "SELECT * FROM army.uavs WHERE Hangars_id=?";

    public UAVDAO() {
    }

    @Override
    public void create(UAV object) throws AttributeNotFoundException{
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT);
            ps.setString(1, object.getName());
            ps.setDate(2, object.getReleaseDate());
            ps.setInt(3, object.getNumberOfBombs());
            ps.setInt(4, object.getStrength());
            ps.setLong(5, object.getHangarsId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
    }

    @Override
    public UAV getById(long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                UAV uavs = new UAV();
                uavs.setId(rs.getInt("id"));
                uavs.setName(rs.getString("name"));
                uavs.setReleaseDate(rs.getDate("releaseDate"));
                uavs.setNumberOfBombs(rs.getInt("numberOfBombs"));
                uavs.setStrength(rs.getInt("strength"));
                uavs.setHangarsId(rs.getInt("Hangars_id"));
                return uavs;
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
    public List<UAV> getAllRows() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);
            List<UAV> uavList = new ArrayList<>();
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                UAV uavs = new UAV();
                uavs.setId(rs.getInt("id"));
                uavs.setName(rs.getString("name"));
                uavs.setReleaseDate(rs.getDate("releaseDate"));
                uavs.setNumberOfBombs(rs.getInt("numberOfBombs"));
                uavs.setStrength(rs.getInt("strength"));
                uavs.setHangarsId(rs.getInt("Hangars_id"));
                uavList.add(uavs);
            }
            
            return uavList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
    }

    @Override
    public List<UAV> getAllByHangarId(long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL_BY_HANGARS_ID);
            List<UAV> uavsList = new ArrayList<>();
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UAV uavs = new UAV();
                uavs.setId(rs.getInt("id"));
                uavs.setName(rs.getString("name"));
                uavs.setReleaseDate(rs.getDate("releaseDate"));
                uavs.setNumberOfBombs(rs.getInt("numberOfBombs"));
                uavs.setStrength(rs.getInt("strength"));
                uavs.setHangarsId(rs.getInt("Hangars_id"));
                uavsList.add(uavs);
            }
            return uavsList;

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
            logger.info("New numberOfBombs = ");
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
    public void update(UAV object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);

            ps.setString(1, object.getName());
            ps.setDate(2, object.getReleaseDate());
            ps.setInt(3, object.getNumberOfBombs());
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
    public long getObjectId(UAV object) throws AttributeNotFoundException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                UAV uavs = new UAV();
                uavs.setId(rs.getInt("id"));
                uavs.setName(rs.getString("name"));
                uavs.setReleaseDate(rs.getDate("releaseDate"));
                uavs.setNumberOfBombs(rs.getInt("numberOfBombs"));
                uavs.setStrength(rs.getInt("strength"));
                uavs.setHangarsId(rs.getInt("Hangars_id"));
                if (uavs.equals(object)) {
                    return uavs.getId();
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
