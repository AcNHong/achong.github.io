package com.itheima.service;

import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

public class UserService {
    private SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public User Select(String name, String password) {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.select(name, password);
        sqlSession.close();
        return user;
    }

    public Boolean add(User user) {
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User isUser = mapper.selectByUsername(user.getUsername());
        Boolean isEmpty = (isUser == null ? true : false);

        if (isEmpty) {
            sqlSession.commit();
        }

        sqlSession.close();
        return isEmpty;
    }

}
