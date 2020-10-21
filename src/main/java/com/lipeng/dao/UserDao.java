package com.lipeng.dao;

import com.lipeng.domain.QueryUser;
import com.lipeng.domain.User;
import java.util.List;

/**
 * @Author: lipeng 910138
 * @Date: 2020/6/5 16:06
 */
public interface UserDao {

    List<User> listUser();

    List<User> userPage(QueryUser queryUser);

    List<User> userPageAnno(QueryUser queryUser);

}