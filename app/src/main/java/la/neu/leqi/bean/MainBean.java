package la.neu.leqi.bean;

import java.util.ArrayList;

/**
 * @author HXS
 */

public class MainBean {
    private ArrayList<Good> goods;
    private ArrayList<ActivityBean> activities;
    private ArrayList<Share> shares;

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
