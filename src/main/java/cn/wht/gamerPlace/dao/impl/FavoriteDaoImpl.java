package cn.wht.gamerPlace.dao.impl;

import cn.wht.gamerPlace.dao.FavoriteDao;
import cn.wht.gamerPlace.domain.Favorite;
import cn.wht.gamerPlace.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Date;

public class FavoriteDaoImpl implements FavoriteDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 根据uid和gid查询出数据
     * @param uid
     * @param gid
     * @return
     */
    @Override
    public Favorite findByUidAndRid(int gid, int uid) {
        //定义sql
        Favorite favorite = null;
        try {
            String sql = "select * from tab_favorite where gid = ? and uid = ?";
            favorite = template.queryForObject(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class), gid, uid);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return favorite;
    }


    /**
     * 添加收藏
     * @param gid
     * @param uid
     */
    @Override
    public void add(int gid, int uid) {
        Date date = new Date(System.currentTimeMillis());
        String sql = "insert into tab_favorite values(?,?,?)";
        template.update(sql,gid,date,uid);
        String sql2 = "update tab_route set count = (select count(*) from tab_favorite where gid = ?) where gid = ?";
        template.update(sql2,gid,gid);
    }

    @Override
    public int findTotalCountByUid(int uid) {
        String sql = "select count(*) from tab_favorite where uid = ?";
        return template.queryForObject(sql,Integer.class,uid);
    }
}
