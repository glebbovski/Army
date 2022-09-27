package com.solvd.army.dao.mybatis;

import com.solvd.army.connection.MyBatisConnectionUtil;
import com.solvd.army.dao.ISubmarineDAO;
import com.solvd.army.models.jettie.Submarine;
import org.apache.ibatis.session.SqlSession;

import javax.management.AttributeNotFoundException;
import java.util.List;

public class SubmarineMapperDAO implements ISubmarineDAO {
    @Override
    public void create(Submarine object) throws AttributeNotFoundException {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        session.insert("src.main.resources.mappers.SubmarineMapper.create", object);
        session.commit();
        session.close();
    }

    @Override
    public Submarine getById(long id) {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        Submarine submarine = session.selectOne("src.main.resources.mappers.SubmarineMapper.getById", id);
        session.close();
        return submarine;
    }

    @Override
    public List<Submarine> getAllRows() {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        List<Submarine> result = session.selectList("src.main.resources.mappers.SubmarineMapper.getAllRows");
        session.close();
        return result;
    }

    @Override
    public void update(long id) {

    }

    @Override
    public void update(Submarine object) {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        session.update("src.main.resources.mappers.SubmarineMapper.update", object);
        session.commit();
        session.close();
    }

    @Override
    public void remove(long id) {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        session.delete("src.main.resources.mappers.SubmarineMapper.remove", id);
        session.commit();
        session.close();
    }

    @Override
    public long getObjectId(Submarine object) throws AttributeNotFoundException {
        return 0;
    }

    @Override
    public List<Submarine> getAllByJettieId(long id) {
        return null;
    }
}
