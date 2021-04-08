package cn.wht.gamerPlace.dao;

import cn.wht.gamerPlace.domain.GameImg;

import java.util.List;

public interface GameImgDao {
    /**
     * 根据游戏id查询图片集
     * @param gid
     * @return
     */
    public List<GameImg> findByGid(int gid);
}
