package cn.wht.gamerPlace.service;

import cn.wht.gamerPlace.domain.User;

public interface UserService {
    /**
     * 注册用户
     * @param user
     * @return
     */
    boolean register(User user);

    /**
     * 激活用户账号
     * @param code
     * @return
     */
    boolean active(String code);

    /**
     * 用户登录功能
     * @param user
     * @return
     */
    User login(User user);
}
