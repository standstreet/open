package com.standstreet.open.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.standstreet.open.api.UserService;
import com.standstreet.open.dao.UserMyBatisDao;
import com.standstreet.open.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by xiaolong.zhu@changhong.com on 2015/11/2.
 */
@SuppressWarnings("ALL")
@Transactional
public class UserServiceImpl implements UserService{

    private static final Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserMyBatisDao userMyBatisDao;

    @Override
    public JSONObject login(JSONObject login) throws Exception {
        if(login.containsKey("loginName")&&login.containsKey("password")){
            String loginName=login.getString("loginName");
            User user= userMyBatisDao.findUserByLoginName(loginName);
            if(user!=null){
                if(user.getPassword().equals(login.getString("password"))){
                    JSONObject result=new JSONObject();
                    result.put("msg","登陆成功");
                    return result;
                }else{
                    throw new Exception("密码错误");
                }
            }else{
                throw new Exception("用户不存在");
            }
        }else{
            throw new Exception("参数错误");
        }
    }
}
