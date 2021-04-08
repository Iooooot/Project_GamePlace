package cn.wht.gamerPlace.dao;

import cn.wht.gamerPlace.domain.Game;

import java.util.List;

public interface GameDao {
    /**
     * 根据cid和gname查询总记录数
     * @param cid
     * @param gname
     * @return
     */
    public int findTotalCount(int cid, String gname);

    /**
     * 根据cid,start,pageSize,gname查询当前页面的数据集合
     * @param cid
     * @param start
     * @param pageSize
     * @param gname
     * @return
     */
    public List<Game> findByPage(int cid, int start, int pageSize, String gname);

    /**
     * 根据id查询Game对象
     * @param gid
     * @return
     */
    public Game findOne(int gid);

    /**
     * 分页查询个人收藏数据
     * @param uid
     * @param start
     * @param pageSize
     * @return
     */
    List<Game> findPageByUid(int uid, int start, int pageSize);

    /**
     * 查询所有游戏
     * @return
     */
    int findAll();

    /**
     * 根据分页条件和查询条件并且以收藏数量排序
     * @param start
     * @param pageSize
     * @param gname
     * @param startPrice
     * @param endPrice
     * @return
     */
    List<Game> findRankBySearch(int start, int pageSize, String gname, int startPrice, int endPrice);

    /**
     * 根据cid查询热门推荐
     * @param cid
     * @return
     */
    List<Game> findHot(int cid);
}
