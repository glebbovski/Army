package com.solvd.army.dao.mybatis;

import com.solvd.army.connection.MyBatisConnectionUtil;
import com.solvd.army.dao.IAircraftDAO;
import com.solvd.army.models.hangar.Aircraft;
import org.apache.ibatis.session.SqlSession;

import javax.management.AttributeNotFoundException;
import java.util.List;

public class AircraftMapperDAO implements IAircraftDAO {
    @Override
    public void create(Aircraft object) throws AttributeNotFoundException {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        session.insert("src.main.resources.mappers.AircraftMapper.create", object);
        session.commit();
        session.close();
    }

    @Override
    public Aircraft getById(long id) {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        Aircraft aircraft = session.selectOne("src.main.resources.mappers.AircraftMapper.getById", id);
        session.close();
        return aircraft;
    }

    @Override
    public List<Aircraft> getAllRows() {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        List<Aircraft> result = session.selectList("src.main.resources.mappers.AircraftMapper.getAllRows");
        session.close();
        return result;
    }

    @Override
    public void update(long id) {
    }

    @Override
    public void update(Aircraft object) {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        session.update("src.main.resources.mappers.AircraftMapper.update", object);
        session.commit();
        session.close();
    }

    @Override
    public void remove(long id) {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        session.delete("src.main.resources.mappers.AircraftMapper.remove", id);
        session.commit();
        session.close();
    }

    @Override
    public long getObjectId(Aircraft object) throws AttributeNotFoundException {
        return 0;
    }

    @Override
    public List<Aircraft> getAllByHangarId(long id) {
        return null;
    }
}
