package cn.wht.gamerPlace.service.impl;

import cn.wht.gamerPlace.dao.FavoriteDao;
import cn.wht.gamerPlace.dao.GameDao;
import cn.wht.gamerPlace.dao.impl.FavoriteDaoImpl;
import cn.wht.gamerPlace.dao.impl.GameDaoImpl;
import cn.wht.gamerPlace.domain.Favorite;
import cn.wht.gamerPlace.domain.Game;
import cn.wht.gamerPlace.domain.PageBean;
import cn.wht.gamerPlace.service.FavoriteService;

import java.util.List;

public class FavoriteServiceImpl implements FavoriteService {
    private GameDao dao = new GameDaoImpl();
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();
    /**
     * 查询用户是否收藏该游戏信息
     * @param gid
     * @param uid
     * @return
     */
    @Override
    public boolean isFavorite(int gid, int uid) {
        Favorite favorite = favoriteDao.findByUidAndRid(gid, uid);
        if(favorite==null){
            //未查到
            return false;
        }else{
            return true;
        }
    }

    /**
     * 添加收藏
     * @param gid
     * @param uid
     */
    @Override
    public void add(int gid, int uid) {
        favoriteDao.add(gid,uid);
    }

    /**
     * 查询自己的收藏
     * @param currentPage
     * @param pageSize
     * @param uid
     * @return
     */
    @Override
    public PageBean<Game> showFavorite(int currentPage, int pageSize, int uid) {
        PageBean<Game> pb = new PageBean<>();
        //完善对象数据
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);
        //用dao查询总记录数
        int totalCount = favoriteDao.findTotalCountByUid(uid);
        pb.setTotalCount(totalCount);
        //计算出总页数
        int totalPage= totalCount%pageSize==0 ? (totalCount/pageSize) : (totalCount/pageSize+1);
        pb.setTotalPage(totalPage);
        //用dao查询route对象集合
        int start =(currentPage-1)*pageSize;
        List<Game> list = dao.findPageByUid(uid,start,pageSize);
        pb.setList(list);
        return pb;
    }
}
