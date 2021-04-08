package cn.wht.gamerPlace.domain;

import java.io.Serializable;

/**
 * 收藏实体类
 */
public class Favorite implements Serializable {
    private Game game;//旅游线路对象
    private String date;//收藏时间
    private User user;//所属用户

    /**
     * 无参构造方法
     */
    public Favorite() {
    }

    /**
     * 有参构造方法
     * @param game
     * @param date
     * @param user
     */
    public Favorite(Game game, String date, User user) {
            this.game = game;
            this.date = date;
            this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
