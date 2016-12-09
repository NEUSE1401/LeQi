package la.neu.leqi.bean;

import java.util.ArrayList;

/**
 * @author HXS
 */

public class MainBean {
    private ArrayList<Good> goods;
    private ArrayList<ActivityBean> activities;
    private ArrayList<Share> shares;
    private ArrayList<BicycleShop> shops;
    private ArrayList<Club> clubs;
    private ArrayList<String> titles;

    public ArrayList<String> getTitles() {
        return titles;
    }

    public void setTitles(ArrayList<String> titles) {
        this.titles = titles;
    }

    public ArrayList<BicycleShop> getShops() {
        return shops;
    }

    public void setShops(ArrayList<BicycleShop> shops) {
        this.shops = shops;
    }

    public ArrayList<Club> getClubs() {
        return clubs;
    }

    public void setClubs(ArrayList<Club> clubs) {
        this.clubs = clubs;
    }

    public ArrayList<Good> getGoods() {
        return goods;
    }

    public void setGoods(ArrayList<Good> goods) {
        this.goods = goods;
    }

    public ArrayList<ActivityBean> getActivities() {
        return activities;
    }

    public void setActivities(ArrayList<ActivityBean> activities) {
        this.activities = activities;
    }

    public ArrayList<Share> getShares() {
        return shares;
    }

    public void setShares(ArrayList<Share> shares) {
        this.shares = shares;
    }
}
