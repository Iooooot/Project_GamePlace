package cn.wht.gamerPlace.service.impl;

import cn.wht.gamerPlace.dao.FavoriteDao;
import cn.wht.gamerPlace.dao.GameDao;
import cn.wht.gamerPlace.dao.GameImgDao;
import cn.wht.gamerPlace.dao.impl.FavoriteDaoImpl;
import cn.wht.gamerPlace.dao.impl.GameDaoImpl;
import cn.wht.gamerPlace.dao.impl.GameImgDaoImpl;
import cn.wht.gamerPlace.domain.Game;
import cn.wht.gamerPlace.domain.PageBean;
import cn.wht.gamerPlace.service.GameService;

import java.util.List;

public class GameServiceImpl implements GameService {
    private GameDao dao = new GameDaoImpl();
    private GameImgDao gameImgDao = new GameImgDaoImpl();
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();
    /**
     * 按照类型分页查询
     * @param currentPage
     * @param pageSize
     * @param cid
     * @return
     */
    @Override
    public PageBean<Game> pageQuery(int currentPage, int pageSize, int cid, String gname) {
        PageBean<Game> pb = new PageBean<Game>();
        //组装pageBean对象
        //从数据库中查询总记录数
        int totalCount=dao.findTotalCount(cid,gname);
        //查询包含route对象的集合
        int start =(currentPage-1)*pageSize;
        //设置数据集合
        pb.setList(dao.findByPage(cid,start,pageSize,gname));
        //设置总记录数
        pb.setTotalCount(totalCount);
        //设置当前页面
        pb.setCurrentPage(currentPage);
        //设置每页显示个数
        pb.setPageSize(pageSize);
        //设置总页数 总记录数/每页显示个数
        int totalPage =totalCount%pageSize==0 ? totalCount/pageSize : (totalCount/pageSize)+1;
        pb.setTotalPage(totalPage);
        return pb;
    }
    /**
     * 根据id查询单个Game对象
     * @param gid
     * @return
     */
    @Override
    public Game findOne(String gid) {
        //根据id查询出game对象
        Game game = dao.findOne(Integer.parseInt(gid));
        //设置收藏数量
        game.setCount(game.getCount());
        //根据gid查询game对象的图片集信息
        game.setGameImgList(gameImgDao.findByGid(game.getGid()));
        return game;
    }

    /**
     * 展示排行榜
     * @param currentPage
     * @param pageSize
     * @param gname
     * @param startPrice
     * @param endPrice
     * @return
     */
    @Override
    public PageBean<Game> showFavoriteRank(int currentPage, int pageSize, String gname, int startPrice, int endPrice) {
        PageBean<Game> pb = new PageBean<>();
        //1.查询总数据
        int totalCount = dao.findAll();
        pb.setTotalCount(totalCount);
        int totalPage = totalCount%pageSize == 0 ? (totalCount/pageSize) : (totalCount/pageSize+1);
        pb.setTotalPage(totalPage);
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);
        //2.查询route集合
        int start=(currentPage-1)*pageSize;
        List<Game> list=dao.findRankBySearch(start,pageSize,gname,startPrice,endPrice);
        pb.setList(list);
        return pb;
    }
    /**
     * 根据cid查询热门推荐
     * @param cid
     * @return
     */
    @Override
    public List<Game> findHot(int cid) {
        return dao.findHot(cid);
    }
}
