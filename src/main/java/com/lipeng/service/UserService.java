package com.lipeng.service;

import com.lipeng.dao.UserDao;
import com.lipeng.domain.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: lipeng 910138
 * @Date: 2020/6/5 16:06
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> listUser() {
        return userDao.listUser();
    }

}