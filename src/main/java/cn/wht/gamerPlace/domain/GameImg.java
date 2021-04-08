package cn.wht.gamerPlace.domain;

import java.io.Serializable;

/**
 * 游戏图片实体类
 */
public class GameImg implements Serializable {
    private int imgid;//图片id
    private int gid;//游戏id
    private String bigPic;//详情商品大图
    private String smallPic;//详情商品小图

    /**
     * 无参构造方法
     */
    public GameImg() {
    }

    /**
     * 有参构造方法
     * @param imgid
     * @param gid
     * @param bigPic
     * @param smallPic
     */
    public GameImg(int imgid, int gid, String bigPic, String smallPic) {
        this.imgid = imgid;
        this.gid = gid;
        this.bigPic = bigPic;
        this.smallPic = smallPic;
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getBigPic() {
        return bigPic;
    }

    public void setBigPic(String bigPic) {
        this.bigPic = bigPic;
    }

    public String getSmallPic() {
        return smallPic;
    }

    public void setSmallPic(String smallPic) {
        this.smallPic = smallPic;
    }
}
