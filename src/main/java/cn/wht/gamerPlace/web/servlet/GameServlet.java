package cn.wht.gamerPlace.web.servlet;

import cn.wht.gamerPlace.domain.Game;
import cn.wht.gamerPlace.domain.PageBean;
import cn.wht.gamerPlace.domain.User;
import cn.wht.gamerPlace.service.FavoriteService;
import cn.wht.gamerPlace.service.GameService;
import cn.wht.gamerPlace.service.impl.FavoriteServiceImpl;
import cn.wht.gamerPlace.service.impl.GameServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Description TODO
 * @ClassName ${NAME}
 * @Author qgq
 * @Date 2020/12/30 20:18
 * @Version V1.0
 */
@WebServlet(value = "/game/*")
public class GameServlet extends BaseServlet {
    private GameService service = new GameServiceImpl();
    private FavoriteService favoriteService = new FavoriteServiceImpl();
    /**
     * 按照类型进行分页查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");
        String gname = request.getParameter("gname");
        //处理参数
        if("null".equals(gname)){
            gname=null;
        }
        int cid=0,currentPage=0,pageSize=0;
        if(cidStr!=null&&cidStr.length()>0&&!"null".equals(cidStr)){
            cid=Integer.parseInt(cidStr);
        }
        if(currentPageStr!=null&&currentPageStr.length()>0){
            currentPage=Integer.parseInt(currentPageStr);
        }else{
            currentPage=1;
        }
        if(pageSizeStr!=null&&pageSizeStr.length()>0){
            pageSize=Integer.parseInt(pageSizeStr);
        }else{
            pageSize=5;
        }
        //调用service进行路线的查询pageBean对象
        PageBean<Game> pb = service.pageQuery(currentPage,pageSize,cid,gname);
        //将pageBean写回
        writeValue(pb,response);
    }

    /**
     * 根据分类cid查询热门推荐游戏
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void hotShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受参数
        String cidStr = request.getParameter("cid");
        //处理参数
        int cid=0;
        if(cidStr!=null&&cidStr.length()>0&&!"null".equals(cidStr)){
            cid=Integer.parseInt(cidStr);
        }
        //调用service进行路线的查询game集合
        List<Game> list = service.findHot(cid);
        //将List<Game>写回
        writeValue(list,response);
    }

    /**
     * 根据id查询游戏的详细信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收id
        String gid = request.getParameter("gid");
        //2.调用service查询route对象
        Game game =service.findOne(gid);
        //3.转为json写回客户端
        writeValue(game,response);
    }
    /**
     * 判断当前用户是否收藏过该线路
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收gid
        String gid = request.getParameter("gid");
        //2.获取user信息
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if(user==null){
            //未登录
            uid=0;
        }else {
            uid=user.getUid();
        }
        //2.调用service查询是否收藏
        boolean flag = favoriteService.isFavorite(Integer.parseInt(gid), uid);
        //3.转为json写回客户端
        writeValue(flag,response);
    }
    /**
     * 收藏该线路
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收gid
        String gid = request.getParameter("gid");
        //2.获取user信息
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if(user==null){
            //未登录
            return;
        }else {
            uid=user.getUid();
        }
        //2.调用service添加收藏
        favoriteService.add(Integer.parseInt(gid), uid);
    }
    /**
     * 查询自己的收藏
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void showFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        //1.获取user信息
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if(user==null){
            //未登录
            return;
        }else {
            uid=user.getUid();
        }
        int currentPage=0,pageSize=0;
        if(currentPageStr!=null&&currentPageStr.length()>0){
            currentPage=Integer.parseInt(currentPageStr);
        }else{
            currentPage=1;
        }
        if(pageSizeStr!=null&&pageSizeStr.length()>0){
            pageSize=Integer.parseInt(pageSizeStr);
        }else{
            pageSize=12;
        }
        //2.调用service查询收藏返回分页对象
        PageBean<Game> pb = favoriteService.showFavorite(currentPage,pageSize,uid);
        //3.回写pb对象
        writeValue(pb,response);
    }
    /**
     * 查询收藏排行榜
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void showFavoriteRank(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String gname = request.getParameter("gname");
        String startPriceStr = request.getParameter("startPrice");
        String endPriceStr = request.getParameter("endPrice");
        //处理数据
        if("null".equals(gname)){
            gname=null;
        }
        int currentPage=0,pageSize=0,startPrice,endPrice;
        if(currentPageStr!=null&&currentPageStr.length()>0&&!"null".equals(currentPageStr)){
            currentPage=Integer.parseInt(currentPageStr);
        }else{
            currentPage=1;
        }
        if(pageSizeStr!=null&&pageSizeStr.length()>0){
            pageSize=Integer.parseInt(pageSizeStr);
        }else{
            pageSize=8;
        }
        if(startPriceStr!=null&&startPriceStr.length()>0&&!"null".equals(startPriceStr)){
            startPrice=Integer.parseInt(startPriceStr);
        }else{
            startPrice=-1;
        }
        if(endPriceStr!=null&&endPriceStr.length()>0&&!"null".equals(endPriceStr)){
            endPrice=Integer.parseInt(endPriceStr);
        }else{
            endPrice=-1;
        }
        //2.调用service查询收藏返回分页对象
        PageBean<Game> pb = service.showFavoriteRank(currentPage,pageSize,gname,startPrice,endPrice);
        //3.回写pb对象
        writeValue(pb,response);
    }
}
