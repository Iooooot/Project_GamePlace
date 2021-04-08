package cn.wht.gamerPlace.dao.impl;

import cn.wht.gamerPlace.dao.UserDao;
import cn.wht.gamerPlace.domain.User;
import cn.wht.gamerPlace.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    @Override
    public User findByUsername(String username) {
        User user = null;
        try {
            //1.定义sql语句
            String sql ="select * from tab_user where username = ?";
            //2.执行sql语句
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        } catch (Exception e) {
        }
        return user;
    }
    /**
     * 保存用户信息
     * @param user
     */
    @Override
    public void save(User user) {
        //1.定义sql语句
        String sql ="insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) values(?,?,?,?,?,?,?,?,?)";
        //2.执行sql语句
        template.update(sql,user.getUsername(),user.getPassword(),user.getName(),user.getBirthday(),user.getSex(),user.getTelephone(),user.getEmail(),user.getStatus(),user.getCode());
    }

    /**
     * 根据激活码查找用户
     * @param code
     * @return
     */
    @Override
    public User findByCode(String code) {
        User user = null;
        try {
            //1.定义sql语句
            String sql ="select * from tab_user where code = ?";
            //2.执行sql语句
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), code);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 更新用户状态
     * @param user
     */
    @Override
    public void updateStatus(User user) {
        //1.定义sql语句
        String sql ="update tab_user set status = 'Y' where uid = ?";
        //2.执行sql语句
        template.update(sql,user.getUid());
    }

    /**
     * 根据用户名和密码查询用户
     * @param username
     * @param password
     * @return
     */
    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user = null;
        try {
            //1.定义sql语句
            String sql ="select * from tab_user where username = ? and password = ?";
            //2.执行sql语句
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
