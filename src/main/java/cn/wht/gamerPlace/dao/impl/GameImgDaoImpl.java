package cn.wht.gamerPlace.dao.impl;

import cn.wht.gamerPlace.dao.GameImgDao;
import cn.wht.gamerPlace.domain.GameImg;
import cn.wht.gamerPlace.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class GameImgDaoImpl implements GameImgDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    /**
     * 根据游戏id查询图片集
     * @param gid
     * @return
     */
    @Override
    public List<GameImg> findByGid(int gid) {
        String sql="select * from tab_game_img where gid = ?";
        return template.query(sql,new BeanPropertyRowMapper<GameImg>(GameImg.class),gid);
    }
}
