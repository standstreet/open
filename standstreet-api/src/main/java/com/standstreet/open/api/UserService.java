package com.standstreet.open.api;


import com.alibaba.fastjson.JSONObject;
import com.standstreet.open.dto.UserDto;
import com.standstreet.open.exception.CoreException;

/**
 * Created by xiaolong.zhu@changhong.com on 2015/11/2.
 */
public interface UserService {
    /**                         用户登陆
     * @param ip                用户登陆ip地址
     * @param longName          用户名:可以是登录名/邮箱/手机号码/qq号
     * @param password          密码
     * @return
     * @throws com.standstreet.open.exception.CoreException
     */
    public void login(String ip,String longName,String password) throws CoreException;

    /**                         用户注册
     * @param ip                注册所在的ip地址
     * @param userDto           用户信息实体
     * @throws com.standstreet.open.exception.CoreException
     */
    public void register(String ip,UserDto userDto) throws CoreException;
}
