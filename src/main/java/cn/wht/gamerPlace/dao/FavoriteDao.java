package cn.wht.gamerPlace.dao;

import cn.wht.gamerPlace.domain.Favorite;

public interface FavoriteDao {
    /**
     * 根据uid和gid查询出数据
     * @param uid
     * @param gid
     * @return
     */
    public Favorite findByUidAndRid(int gid, int uid);

    /**
     * 添加收藏
     * @param gid
     * @param uid
     */
    void add(int gid, int uid);

    /**
     * 查询总收藏数
     * @param uid
     * @return
     */
    int findTotalCountByUid(int uid);
}
