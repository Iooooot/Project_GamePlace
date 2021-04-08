package cn.wht.gamerPlace.dao;

import cn.wht.gamerPlace.domain.User;

public interface UserDao {
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 保存用户信息
     * @param user
     */
    void save(User user);

    /**
     * 根据激活码查找用户
     * @param code
     * @return
     */
    User findByCode(String code);

    /**
     * 更新状态
     * @param user
     */
    void updateStatus(User user);

    /**
     * 根据用户名和密码查询用户
     * @param username
     * @param password
     * @return
     */
    User findByUsernameAndPassword(String username, String password);
}
