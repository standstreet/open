package com.standstreet.open.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.standstreet.open.api.UserService;
import com.standstreet.open.api.impl.util.AccountUtil;
import com.standstreet.open.api.impl.util.PasswordUtil;
import com.standstreet.open.dao.UserMyBatisDao;
import com.standstreet.open.dto.UserDto;
import com.standstreet.open.entity.User;
import com.standstreet.open.exception.CoreException;
import com.standstreet.open.exception.ECodeUtil;
import com.standstreet.open.exception.ErrorConstant;
import com.standstreet.open.utils.PatternUtil;
import com.standstreet.open.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.mapper.BeanMapper;

import java.util.Date;
import java.util.regex.Matcher;

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
    public void login(String ip, String longName, String password) throws CoreException {
        logger.info("用户登陆：IP:[{}],账号:[{}],密码:[{}]",ip,longName,password);
        if(AccountUtil.judgeAccountType(longName)==1){
            User user=userMyBatisDao.findUserByLoginName(longName);
            if(user==null){
                logger.info("登陆失败,无此账号");
                throw new CoreException(ECodeUtil.getCommError(ErrorConstant.USER_NOT_EXISTED));
            }
            if(!user.getEncodePassword().equals(PasswordUtil.encryptPassword(password,user))){
                logger.info("登陆失败,密码错误");
                throw new CoreException(ECodeUtil.getCommError(ErrorConstant.USER_LOGIN_FAILED));
            }
        }
    }

    @Override
    public void register(String ip, UserDto userDto) throws CoreException {
        logger.info("IP:[{}],用户注册:[{}]",ip,JSONObject.toJSONString(userDto));
        //验证邮箱格式
        Matcher emailMatcher= PatternUtil.emailPattern.matcher(userDto.getEmail());
        if (!emailMatcher.matches()) {
            logger.info("邮箱格式不合法[{}]", userDto.getEmail());
            throw new CoreException(ECodeUtil.getCommError(ErrorConstant.COMMON_EMAIL_IS_ERROR));
        }
        //判断用户名全是数字(用户名不能全为数字)
        Boolean isNum = userDto.getLoginName().matches("[0-9]+");
        if (isNum) {
            logger.info("用户名全为数字[{}]", userDto.getLoginName());
            throw new CoreException(ECodeUtil.getCommError(ErrorConstant.COMMON_USER_LOGINNAME_ERROR));
        }
        //邮箱是否已经被注册
        if (isExistedEmail(userDto.getEmail())) {
            logger.info("邮箱已注册[{}]", userDto.getEmail());
            throw new CoreException(ECodeUtil.getCommError(ErrorConstant.USER_EMAIL_IS_EXISTED));
        }
        //登陆名是否已被注册
        if(isExisted(userDto.getLoginName())){
            logger.info("登录名已存在[{}]", userDto.getLoginName());
            throw new CoreException(ECodeUtil.getCommError(ErrorConstant.USER_LOGIN_NAME_HAS_EXISTED));
        }
        User user= BeanMapper.map(userDto,User.class);
        Date date=new Date();
        user.setCreateTime(date);
        user.setUpdateTime(date);
        user.setUuid(StringUtils.generateUuidString());
        PasswordUtil.encryptPassword(user);
        user.setOnline(false);
        int result=userMyBatisDao.register(user);
        if(result!=1){
            logger.info("注册失败");
            throw new CoreException(ECodeUtil.getCommError(ErrorConstant.USER_REGISTER_FAILED));
        }
    }

    public boolean isExistedEmail(String email) throws CoreException {
        User user = userMyBatisDao.findByEmail(email);
        if (user == null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isExisted(String loginName) throws CoreException {
        if (StringUtils.isNoneBlank(loginName)) {
            User user = userMyBatisDao.findUserByLoginName(loginName);
            if (user != null) {
                return true;
            } else {
                return false;
            }
        } else {
            throw new CoreException(ECodeUtil.getCommError(ErrorConstant.COMMON_PARAM_IS_NULL));
        }
    }
}
