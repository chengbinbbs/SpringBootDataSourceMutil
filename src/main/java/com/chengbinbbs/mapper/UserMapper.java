package com.chengbinbbs.mapper;


import com.chengbinbbs.model.User;

/**
 * @author zhangcb
 * @created on 2017/5/15.
 */
public interface UserMapper {

    User findByUserName(String name);

    public int insert(User user);
}
