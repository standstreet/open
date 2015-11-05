package com.standstreet.open.exception;


import java.util.concurrent.ConcurrentHashMap;

/**
 * 异常码类
 *
 * Created By xiaolong.zhu@changhong.com on 2015-11-05
 */
public class ECode {
    /**
     * 异常列表
     */
    private ConcurrentHashMap<String, Error>
            errorMap = new ConcurrentHashMap<String, Error>();

    public ConcurrentHashMap<String, Error> getErrorMap() {
        return this.errorMap;
    }

    public void setErrorMap(ConcurrentHashMap<String, Error> errorMap) {
        this.errorMap = errorMap;
    }

    /**
     * 获取异常信息
     *
     * @param key
     * @return
     */
    public Error getError(String key) {
        return this.errorMap.get(key);
    }
}
