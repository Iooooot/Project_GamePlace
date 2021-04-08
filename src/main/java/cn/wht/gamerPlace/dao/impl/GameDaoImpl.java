package cn.wht.gamerPlace.dao.impl;

import cn.wht.gamerPlace.dao.GameDao;
import cn.wht.gamerPlace.domain.Game;
import cn.wht.gamerPlace.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class GameDaoImpl implements GameDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 根据cid和gname查询总记录数
     * @param cid
     * @param gname
     * @return
     */
    @Override
    public int findTotalCount(int cid,String gname) {
        //定义sql模板
        String sql="select count(*) from tab_game where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        //判断参数
        List params = new ArrayList();
        if(cid!=0){
            sb.append("and cid = ? ");
            params.add(cid);
        }
        if(gname!=null&&gname.length()>0){
            sb.append(" and gname like ?");
            params.add("%"+gname+"%");
        }
        //执行sql
        sql=sb.toString();
        return template.queryForObject(sql,Integer.class,params.toArray());
    }

    /**
     * 根据cid,start,pageSize,gname查询当前页面的数据集合
     * @param cid
     * @param start
     * @param pageSize
     * @param gname
     * @return
     */
    @Override
    public List<Game> findByPage(int cid, int start, int pageSize, String gname) {
        //定义sql模板
        String sql="select * from tab_game where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        //判断参数
        List params = new ArrayList();
        if(cid!=0){
            sb.append("and cid = ? ");
            params.add(cid);
        }
        if(gname!=null&&gname.length()>0){
            sb.append(" and gname like ?");
            params.add("%"+gname+"%");
        }
        sb.append(" limit ? , ? ");
        //执行sql
        sql=sb.toString();
        params.add(start);
        params.add(pageSize);
        //执行sql
        return template.query(sql,new BeanPropertyRowMapper<Game>(Game.class),params.toArray());
    }

    /**
     * 根据id查询单个对象
     * @param gid
     * @return
     */
    @Override
    public Game findOne(int gid) {
        //定义sql
        String sql="select * from tab_game where gid = ?";
        //执行sql
        return template.queryForObject(sql,new BeanPropertyRowMapper<Game>(Game.class),gid);
    }

    /**
     * 根据uid连表查询出该用户收藏的游戏
     * @param uid
     * @param start
     * @param pageSize
     * @return
     */
    @Override
    public List<Game> findPageByUid(int uid, int start, int pageSize) {
        //1.定义sql
        String sql ="select * from tab_game where gid in (select gid from tab_favorite where uid = ?) limit ? , ?";
        return template.query(sql,new BeanPropertyRowMapper<Game>(Game.class),uid,start,pageSize);
    }

    /**
     * 查询所有游戏
     * @return
     */
    @Override
    public int findAll() {
        //定义sql
        String sql = "select count(*) from tab_game";
        return template.queryForObject(sql,Integer.class);
    }
    /**
     * 根据分页条件和查询条件并且以收藏数量排序
     * @param start
     * @param pageSize
     * @param gname
     * @param startPrice
     * @param endPrice
     * @return
     */
    @Override
    public List<Game> findRankBySearch(int start, int pageSize, String gname, int startPrice, int endPrice) {
        String sql = "select * from tab_game where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        //判断参数
        List params = new ArrayList();
        //查询条件
        if(gname!=null&&gname.length()>0){
            sb.append(" and gname like ? ");
            params.add("%"+gname+"%");
        }
        //价格区间条件
        if(startPrice!=-1&&endPrice!=-1){
            sb.append(" and price >= ? and price <= ? ");
            params.add(startPrice);
            params.add(endPrice);
        }else if(startPrice!=-1){
            sb.append(" and price >= ?");
            params.add(startPrice);
        }else if(endPrice!=-1){
            sb.append(" and price <= ?");
            params.add(endPrice);
        }
        //排序条件
        sb.append(" order by count desc ");
        //分页条件
        sb.append(" limit ? , ? ");
        sql=sb.toString();
        params.add(start);
        params.add(pageSize);
        return template.query(sql,new BeanPropertyRowMapper<Game>(Game.class),params.toArray());
    }

    /**
     * 根据cid查询热门推荐
     * @param cid
     * @return
     */
    @Override
    public List<Game> findHot(int cid) {
        //定义sql
        String sql = "select * from tab_game where cid = ? and gflag = 1 limit 5";
        return template.query(sql,new BeanPropertyRowMapper<Game>(Game.class),cid);
    }
}
