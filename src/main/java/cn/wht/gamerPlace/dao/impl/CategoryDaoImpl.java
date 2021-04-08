package cn.wht.gamerPlace.dao.impl;

import cn.wht.gamerPlace.dao.CategoryDao;
import cn.wht.gamerPlace.domain.Category;
import cn.wht.gamerPlace.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 查询所有种类
     * @return
     */
    @Override
    public List<Category> findAll() {
        //定义sql
        String sql = "select * from tab_category";
        //执行sql
        return template.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
    }
}
