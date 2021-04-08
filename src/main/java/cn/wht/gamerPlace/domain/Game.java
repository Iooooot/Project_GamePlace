package cn.wht.gamerPlace.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 旅游游戏商品实体类
 */
public class Game implements Serializable {

    private int gid;//游戏id，必输
    private String gname;//游戏名称，必输
    private double price;//价格，必输
    private String gameIntroduce;//游戏介绍
    private String gflag;   //是否热门推荐，必输，0代表不热门，1代表热门推荐
    private int count;//收藏数量
    private int cid;//所属分类，必输
    private String gimage;//缩略图


    private Category category;//所属分类
    private List<GameImg> gameImgList;//游戏详情图片列表



    /**
     * 无参构造方法
     */
    public Game(){}

    /**
     * 有参构造方法
     * @param gid
     * @param gname
     * @param price
     * @param gameIntroduce
     * @param gflag
     * @param count
     * @param cid
     * @param gimage
     */
    public Game(int gid, String gname, double price, String gameIntroduce, String gflag,  int count, int cid, String gimage) {
        this.gid = gid;
        this.gname = gname;
        this.price = price;
        this.gameIntroduce = gameIntroduce;
        this.gflag = gflag;
        this.count = count;
        this.cid = cid;
        this.gimage = gimage;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getGameIntroduce() {
        return gameIntroduce;
    }

    public void setGameIntroduce(String gameIntroduce) {
        this.gameIntroduce = gameIntroduce;
    }

    public String getGflag() {
        return gflag;
    }

    public void setGflag(String gflag) {
        this.gflag = gflag;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getGimage() {
        return gimage;
    }

    public void setGimage(String gimage) {
        this.gimage = gimage;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<GameImg> getGameImgList() {
        return gameImgList;
    }

    public void setGameImgList(List<GameImg> gameImgList) {
        this.gameImgList = gameImgList;
    }
}
