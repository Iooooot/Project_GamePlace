package cn.wht.gamerPlace.service.impl;

import cn.wht.gamerPlace.dao.CategoryDao;
import cn.wht.gamerPlace.dao.impl.CategoryDaoImpl;
import cn.wht.gamerPlace.domain.Category;
import cn.wht.gamerPlace.service.CategoryService;
import cn.wht.gamerPlace.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<Category> findAll() {
        //1.1获取jedis客户端
        Jedis jedis = JedisUtil.getJedis();
        //1.2使用sortedset排序查询
        Set<Tuple> tuples = jedis.zrangeWithScores("category", 0, -1);
        List<Category> cs = null;
        //2.判断查询的集合是否为空
        if(tuples ==null || tuples.size() ==0){
            //redis中没有
            //从数据库中查询
            cs=categoryDao.findAll();
            //存入redis
            for (int i = 0; i <cs.size(); i++) {
                jedis.zadd("category",cs.get(i).getCid(),cs.get(i).getCname());
            }
        }else{
            //4.不为空直接返回集合
            cs=new ArrayList<Category>();
            for (Tuple tuple : tuples) {
                Category category = new Category();
                category.setCid((int) tuple.getScore());
                category.setCname(tuple.getElement());
                cs.add(category);
            }
        }
        return cs;
    }
}
