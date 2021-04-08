package cn.wht.gamerPlace.service;

import cn.wht.gamerPlace.domain.Game;
import cn.wht.gamerPlace.domain.PageBean;

import java.util.List;

public interface GameService {
    /**
     * 按照类型分页查询
     * @param currentPage
     * @param pageSize
     * @param cid
     * @return
     */
    PageBean<Game> pageQuery(int currentPage, int pageSize, int cid, String gname);

    /**
     * 根据id查询单个Game对象
     * @param gid
     * @return
     */
    Game findOne(String gid);

    /**
     * 展示排行榜
     * @param currentPage
     * @param pageSize
     * @param gname
     * @param startPrice
     * @param endPrice
     * @return
     */
    PageBean<Game> showFavoriteRank(int currentPage, int pageSize, String gname, int startPrice, int endPrice);

    /**
     * 根据cid查询热门推荐
     * @param cid
     * @return
     */
    List<Game> findHot(int cid);
}
