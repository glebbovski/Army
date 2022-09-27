package com.solvd.army.dao.mybatis;

import com.solvd.army.connection.MyBatisConnectionUtil;
import com.solvd.army.dao.ISoldierDAO;
import com.solvd.army.models.barrack.Soldier;
import org.apache.ibatis.session.SqlSession;

import javax.management.AttributeNotFoundException;
import java.util.List;

public class SoldierMapperDAO implements ISoldierDAO {
    @Override
    public void create(Soldier object) throws AttributeNotFoundException {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        session.insert("src.main.resources.mappers.SoldierMapper.create", object);
        session.commit();
        session.close();
    }

    @Override
    public Soldier getById(long id) {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        Soldier soldier = session.selectOne("src.main.resources.mappers.SoldierMapper.getById", id);
        session.close();
        return soldier;
    }

    @Override
    public List<Soldier> getAllRows() {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        List<Soldier> result = session.selectList("src.main.resources.mappers.SoldierMapper.getAllRows");
        session.close();
        return result;
    }

    @Override
    public void update(long id) {

    }

    @Override
    public void update(Soldier object) {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        session.update("src.main.resources.mappers.SoldierMapper.update", object);
        session.commit();
        session.close();
    }

    @Override
    public void remove(long id) {
        SqlSession session = MyBatisConnectionUtil.getSqlSessionFactory().openSession();
        session.delete("src.main.resources.mappers.SoldierMapper.remove", id);
        session.commit();
        session.close();
    }

    @Override
    public long getObjectId(Soldier object) throws AttributeNotFoundException {
        return 0;
    }

    @Override
    public List<Soldier> getAllByBarrackId(long id) {
        return null;
    }
}
