package com.solvd.army.dao.mybatis;

import com.solvd.army.connection.MyBatisConnectionUtil;
import com.solvd.army.dao.IWarshipDAO;
import com.solvd.army.models.jettie.Warship;
import org.apache.ibatis.session.SqlSession;

import javax.management.AttributeNotFoundException;
import java.util.List;

public class WarshipMapperDAO implements IWarshipDAO {
    @Override
    public void create(Warship object) throws AttributeNotFoundException {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        session.insert("src.main.resources.mappers.WarshipMapper.create", object);
        session.commit();
        session.close();
    }

    @Override
    public Warship getById(long id) {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        Warship warship = session.selectOne("src.main.resources.mappers.WarshipMapper.getById", id);
        session.close();
        return warship;
    }

    @Override
    public List<Warship> getAllRows() {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        List<Warship> result = session.selectList("src.main.resources.mappers.WarshipMapper.getAllRows");
        session.close();
        return result;
    }

    @Override
    public void update(long id) {

    }

    @Override
    public void update(Warship object) {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        session.update("src.main.resources.mappers.WarshipMapper.update", object);
        session.commit();
        session.close();
    }

    @Override
    public void remove(long id) {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        session.delete("src.main.resources.mappers.WarshipMapper.remove", id);
        session.commit();
        session.close();
    }

    @Override
    public long getObjectId(Warship object) throws AttributeNotFoundException {
        return 0;
    }

    @Override
    public List<Warship> getAllByJettieId(long id) {
        return null;
    }
}
