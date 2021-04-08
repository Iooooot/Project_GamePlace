package cn.wht.gamerPlace.dao;

import cn.wht.gamerPlace.domain.Category;

import java.util.List;

public interface CategoryDao {
    /**
     * 查询所有种类
     * @return
     */
    public List<Category> findAll();
}
