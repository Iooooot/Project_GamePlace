package cn.wht.gamerPlace.web.servlet;

import cn.wht.gamerPlace.domain.ResultInfo;
import cn.wht.gamerPlace.domain.User;
import cn.wht.gamerPlace.service.UserService;
import cn.wht.gamerPlace.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @Description TODO
 * @ClassName ${NAME}
 * @Author qgq
 * @Date 2020/12/30 18:25
 * @Version V1.0
 */
@WebServlet(value = "/user/*")
public class UserServlet extends BaseServlet {
    private UserService service = new UserServiceImpl();

    /**
     * 用户注册
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取验证码
        String check = request.getParameter("check");
        //从session中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String)session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if(!check.equalsIgnoreCase(checkcode_server)){
            //验证码错误
            ResultInfo info = new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            writeValue(info,response);
        }else {
            //获取数据
            Map<String, String[]> map = request.getParameterMap();
            //封装对象
            User user = new User();
            try {
                BeanUtils.populate(user,map);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            //调用service完成注册
            boolean flag = service.register(user);
            //响应结果
            ResultInfo info = new ResultInfo();
            if(flag){
                //注册成功
                info.setFlag(true);
            }else{
                //注册失败
                info.setFlag(false);
                info.setErrorMsg("注册失败！");
            }
            writeValue(info,response);
        }
    }
    /**
     * 用户登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取验证码
        String check = request.getParameter("check");
        //从session中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if (!check.equalsIgnoreCase(checkcode_server)) {
            //验证码错误
            ResultInfo info = new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            writeValue(info, response);
        } else {
            //获取数据
            Map<String, String[]> map = request.getParameterMap();
            //封装对象
            User user = new User();
            try {
                BeanUtils.populate(user, map);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            //调用service完成登录
            User u = service.login(user);
            //响应结果
            ResultInfo info = new ResultInfo();
            if(u==null){
                //登录失败
                info.setFlag(false);
                info.setErrorMsg("用户名或密码错误！");
            }
            if(u!=null&&!u.getStatus().equals("Y")){
                //登录成功但未激活
                info.setFlag(false);
                info.setErrorMsg("账号未激活");
            }
            if(u!=null&&"Y".equals(u.getStatus())){
                //登录成功
                info.setFlag(true);
                session.setAttribute("user",u);
            }
            writeValue(info,response);
        }
    }
    /**
     * 退出登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.销毁session
        HttpSession session = request.getSession();
        session.invalidate();
        //2.跳转页面
        response.sendRedirect(request.getContextPath()+"/login.html");
    }
    /**
     * 查询单个用户
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session中获取用户
        User user = (User)request.getSession().getAttribute("user");
        //回写数据
        writeValue(user,response);
    }
    /**
     * 激活邮件
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void activeUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取激活码
        String code = request.getParameter("code");
        if(code!=null){
            //2.调用service完成激活操作
            boolean flag = service.active(code);
            //3.判断标记
            String msg=null;
            if(flag){
                //激活成功
                msg="激活成功,请<a href='login.html'>登录</a>";
            }else{
                //激活失败
                msg="激活失败，请联系管理员！";
                writeValue(msg,response);
            }
        }
    }

}
