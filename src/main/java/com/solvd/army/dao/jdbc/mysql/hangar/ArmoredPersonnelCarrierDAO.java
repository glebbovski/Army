package com.solvd.army.dao.jdbc.mysql.hangar;

import com.solvd.army.connection.ConnectionUtil;
import com.solvd.army.dao.IArmoredPersonnelCarrierDAO;
import com.solvd.army.models.hangar.ArmoredPersonnelCarrier;
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

public class ArmoredPersonnelCarrierDAO implements IArmoredPersonnelCarrierDAO {
    private static final Logger logger = LogManager.getLogger(AircraftDAO.class);
    private static final Scanner scanner = new Scanner(System.in);

    private static final String GET = "SELECT * FROM army.armoredpersonnelcarriers WHERE id=?";
    private static final String GET_ALL = "SELECT * FROM army.armoredpersonnelcarriers";
    private static final String UPDATE = "UPDATE army.armoredpersonnelcarriers SET " +
            "army.armoredpersonnelcarriers.name=?, " +
            "army.armoredpersonnelcarriers.releaseDate=?, " +
            "army.armoredpersonnelcarriers.numberOfGuns=?, army.armoredpersonnelcarriers.strength=? WHERE " +
            "army.armoredpersonnelcarriers.id=?";
    private static final String INSERT = "INSERT INTO army.armoredpersonnelcarriers " +
            "(army.armoredpersonnelcarriers.name, " +
            "army.armoredpersonnelcarriers.releaseDate, " +
            "army.armoredpersonnelcarriers.numberOfGuns, army.armoredpersonnelcarriers.strength, " +
            "army.armoredpersonnelcarriers.Hangars_id)\n" +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM army.armoredpersonnelcarriers WHERE id=?";
    private static final String GET_ALL_BY_HANGARS_ID = "SELECT * FROM army.armoredpersonnelcarriers " +
            "WHERE Hangars_id=?";

    public ArmoredPersonnelCarrierDAO() {
    }

    @Override
    public void create(ArmoredPersonnelCarrier object) throws AttributeNotFoundException {
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

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
    }

    @Override
    public ArmoredPersonnelCarrier getById(long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                ArmoredPersonnelCarrier armoredPersonnelCarriers = new ArmoredPersonnelCarrier();
                armoredPersonnelCarriers.setId(rs.getInt("id"));
                armoredPersonnelCarriers.setName(rs.getString("name"));
                armoredPersonnelCarriers.setReleaseDate(rs.getDate("releaseDate"));
                armoredPersonnelCarriers.setNumberOfGuns(rs.getInt("numberOfGuns"));
                armoredPersonnelCarriers.setStrength(rs.getInt("strength"));
                armoredPersonnelCarriers.setHangarsId(rs.getInt("Hangars_id"));
                return armoredPersonnelCarriers;
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
    public List<ArmoredPersonnelCarrier> getAllRows() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);
            List<ArmoredPersonnelCarrier> armoredPersonnelCarriersList = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                ArmoredPersonnelCarrier armoredPersonnelCarriers = new ArmoredPersonnelCarrier();
                armoredPersonnelCarriers.setId(rs.getInt("id"));
                armoredPersonnelCarriers.setName(rs.getString("name"));
                armoredPersonnelCarriers.setReleaseDate(rs.getDate("releaseDate"));
                armoredPersonnelCarriers.setNumberOfGuns(rs.getInt("numberOfGuns"));
                armoredPersonnelCarriers.setStrength(rs.getInt("strength"));
                armoredPersonnelCarriers.setHangarsId(rs.getInt("Hangars_id"));
                armoredPersonnelCarriersList.add(armoredPersonnelCarriers);
            }
            return armoredPersonnelCarriersList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
    }

    @Override
    public List<ArmoredPersonnelCarrier> getAllByHangarId(long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL_BY_HANGARS_ID);
            List<ArmoredPersonnelCarrier> personnelCarriers = new ArrayList<>();
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                ArmoredPersonnelCarrier armoredPersonnelCarriers = new ArmoredPersonnelCarrier();
                armoredPersonnelCarriers.setId(rs.getInt("id"));
                armoredPersonnelCarriers.setName(rs.getString("name"));
                armoredPersonnelCarriers.setReleaseDate(rs.getDate("releaseDate"));
                armoredPersonnelCarriers.setNumberOfGuns(rs.getInt("numberOfGuns"));
                armoredPersonnelCarriers.setStrength(rs.getInt("strength"));
                armoredPersonnelCarriers.setHangarsId(rs.getInt("Hangars_id"));
                personnelCarriers.add(armoredPersonnelCarriers);
            }
            return personnelCarriers;

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
    public void update(ArmoredPersonnelCarrier object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);

            ps.setString(1, object.getName());
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
    public long getObjectId(ArmoredPersonnelCarrier object) throws AttributeNotFoundException {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                ArmoredPersonnelCarrier armoredPersonnelCarriers = new ArmoredPersonnelCarrier();
                armoredPersonnelCarriers.setId(rs.getInt("id"));
                armoredPersonnelCarriers.setName(rs.getString("name"));
                armoredPersonnelCarriers.setReleaseDate(rs.getDate("releaseDate"));
                armoredPersonnelCarriers.setNumberOfGuns(rs.getInt("numberOfGuns"));
                armoredPersonnelCarriers.setStrength(rs.getInt("strength"));
                armoredPersonnelCarriers.setHangarsId(rs.getInt("Hangars_id"));

                if (armoredPersonnelCarriers.equals(object)) {
                    return armoredPersonnelCarriers.getId();
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
