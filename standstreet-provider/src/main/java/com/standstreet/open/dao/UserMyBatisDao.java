package com.standstreet.open.dao;

import com.standstreet.open.dao.common.MyBatisRepository;
import com.standstreet.open.entity.User;

/**
 * Created by xiaolong.zhu@changhong.com on 2015/11/2.
 */
@MyBatisRepository
public interface UserMyBatisDao {

    User findUserByLoginName(String loginName);

    User findUserByUuid(String uuid);

    User findByEmail(String email);

    int register(User user);
}
