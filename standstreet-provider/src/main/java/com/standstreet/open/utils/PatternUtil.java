package com.standstreet.open.utils;

import java.util.regex.Pattern;

/**
 * Created by xiaolong.zhu@changhong.com on 2015/11/6.
 */
public class PatternUtil {
    //用正则表达式判断邮箱格式
    public static final Pattern emailPattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
}
