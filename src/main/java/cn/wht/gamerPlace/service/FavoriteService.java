package cn.wht.gamerPlace.service;

import cn.wht.gamerPlace.domain.Game;
import cn.wht.gamerPlace.domain.PageBean;

public interface FavoriteService {
    /**
     * 查询用户是否收藏该游戏信息
     * @param gid
     * @param uid
     * @return
     */
    public boolean isFavorite(int gid, int uid);

    /**
     * 添加收藏
     * @param gid
     * @param uid
     */
    void add(int gid, int uid);

    /**
     * 查询自己的收藏
     * @param currentPage
     * @param pageSize
     * @param uid
     * @return
     */
    PageBean<Game> showFavorite(int currentPage, int pageSize, int uid);
}
