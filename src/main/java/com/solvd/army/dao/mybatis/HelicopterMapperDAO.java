package com.solvd.army.dao.mybatis;

import com.solvd.army.connection.MyBatisConnectionUtil;
import com.solvd.army.dao.IHelicopterDAO;
import com.solvd.army.models.hangar.Helicopter;
import org.apache.ibatis.session.SqlSession;

import javax.management.AttributeNotFoundException;
import java.util.List;

public class HelicopterMapperDAO implements IHelicopterDAO {
    @Override
    public void create(Helicopter object) throws AttributeNotFoundException {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        session.insert("src.main.resources.mappers.HelicopterMapper.create", object);
        session.commit();
        session.close();
    }

    @Override
    public Helicopter getById(long id) {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        Helicopter helicopter = session.selectOne("src.main.resources.mappers.HelicopterMapper.getById", id);
        session.close();
        return helicopter;
    }

    @Override
    public List<Helicopter> getAllRows() {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        List<Helicopter> result = session.selectList("src.main.resources.mappers.HelicopterMapper.getAllRows");
        session.close();
        return result;
    }

    @Override
    public void update(long id) {

    }

    @Override
    public void update(Helicopter object) {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        session.update("src.main.resources.mappers.HelicopterMapper.update", object);
        session.commit();
        session.close();
    }

    @Override
    public void remove(long id) {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        session.delete("src.main.resources.mappers.HelicopterMapper.remove", id);
        session.commit();
        session.close();
    }

    @Override
    public long getObjectId(Helicopter object) throws AttributeNotFoundException {
        return 0;
    }

    @Override
    public List<Helicopter> getAllByHangarId(long id) {
        return null;
    }
}
