package com.solvd.army.dao.jdbc.mysql.hangar;

import com.solvd.army.connection.ConnectionUtil;
import com.solvd.army.dao.IBaseDAO;
import com.solvd.army.models.hangar.ArmoredPersonnelCarrier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArmoredPersonnelCarrierDAO implements IBaseDAO<ArmoredPersonnelCarrier> {
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

    public ArmoredPersonnelCarrierDAO() {
    }

    @Override
    public void create(ArmoredPersonnelCarrier object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT);
            ps.setString(1, object.getName());
            ps.setDate(2, object.getReleaseDate());
            ps.setInt(3, object.getNumberOfGuns());
            ps.setInt(4, object.getStrength());
            ps.setLong(5, object.getHangars_id());
            ps.executeQuery();

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

            while(rs.next()) {
                ArmoredPersonnelCarrier armoredPersonnelCarriers = new ArmoredPersonnelCarrier();
                armoredPersonnelCarriers.setId(rs.getInt("id"));
                armoredPersonnelCarriers.setName(rs.getString("name"));
                armoredPersonnelCarriers.setReleaseDate(rs.getDate("releaseDate"));
                armoredPersonnelCarriers.setNumberOfGuns(rs.getInt("numberOfGuns"));
                armoredPersonnelCarriers.setStrength(rs.getInt("strength"));
                armoredPersonnelCarriers.setHangars_id(rs.getInt("Hangars_id"));
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
                armoredPersonnelCarriers.setHangars_id(rs.getInt("Hangars_id"));
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
    public void update(long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            ArmoredPersonnelCarrier armoredPersonnelCarriers = getById(id);
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);

            ps.setString(1, armoredPersonnelCarriers.getName());
            ps.setDate(2, armoredPersonnelCarriers.getReleaseDate());
            ps.setInt(3, armoredPersonnelCarriers.getNumberOfGuns());
            ps.setInt(4, armoredPersonnelCarriers.getStrength());
            ps.setInt(5, armoredPersonnelCarriers.getId());
            ps.executeQuery();
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
            ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
    }
}
