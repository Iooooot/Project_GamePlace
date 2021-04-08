package cn.wht.gamerPlace.service.impl;

import cn.wht.gamerPlace.dao.UserDao;
import cn.wht.gamerPlace.dao.impl.UserDaoImpl;
import cn.wht.gamerPlace.domain.User;
import cn.wht.gamerPlace.service.UserService;
import cn.wht.gamerPlace.util.MailUtils;
import cn.wht.gamerPlace.util.UuidUtil;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public boolean register(User user) {
        //1.根据用户名查询用户对象
        User u = userDao.findByUsername(user.getUsername());
        //2.若不存在保存用户信息
        if(u!=null){
            //用户名存在
            return false;
        }else {
            //用户名不存在
            //设置uuid和状态
            user.setCode(UuidUtil.getUuid());
            user.setStatus("N");
            userDao.save(user);
            //3.激活邮件发送
            String content = "<a href='http://localhost:8080/user/activeUser?code="+user.getCode()+"'>点击激活《游民小窝》账号</a>";
            MailUtils.sendMail(user.getEmail(),content,"激活邮件");
            return true;
        }

    }
    /**
     * 激活用户账号
     * @param code
     * @return
     */
    @Override
    public boolean active(String code) {
        //根据code来查找用户
        User user=userDao.findByCode(code);
        if(user!=null){
            //查找到了修改状态
            userDao.updateStatus(user);
            return true;
        }else{
            //没找到
            return false;
        }
    }
    /**
     * 用户登录功能
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        return userDao.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }
}
