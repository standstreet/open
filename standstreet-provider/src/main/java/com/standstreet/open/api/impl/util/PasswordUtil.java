package com.standstreet.open.api.impl.util;

import com.standstreet.open.entity.User;
import com.standstreet.open.exception.CoreException;
import com.standstreet.open.exception.ECodeUtil;
import com.standstreet.open.exception.ErrorConstant;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;

/**
 * Created by xiaolong.zhu@changhong.com on 2015/11/4.
 */
public class PasswordUtil {

    public static final int HASH_INTERATIONS = 1024;
    private static final int SALT_SIZE = 8;

    /**
     * 验证密码
     */
    public static String encryptPassword(String password, User user) throws CoreException {
        if (user.getSalt() != null) {
            byte[] salt = Encodes.decodeHex(user.getSalt());
            byte[] hashPassword = Digests.sha1(password.getBytes(), salt, HASH_INTERATIONS);
            return Encodes.encodeHex(hashPassword);
        } else {
            com.standstreet.open.exception.Error error = ECodeUtil.getCommError(ErrorConstant.COMMON_PARAM_IS_NULL);
            throw new CoreException(error);
        }

    }

    /**
     * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
     */
    public static void encryptPassword(User user) {
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        user.setSalt(Encodes.encodeHex(salt));

        byte[] hashPassword = Digests.sha1(user.getPassword().getBytes(), salt, HASH_INTERATIONS);
        user.setEncodePassword(Encodes.encodeHex(hashPassword));
    }
}
