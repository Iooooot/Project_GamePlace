package cn.wht.gamerPlace.service;

import cn.wht.gamerPlace.domain.Category;

import java.util.List;

public interface CategoryService {
    /**
     * 查询所有种类
     * @return
     */
    public List<Category> findAll();
}
