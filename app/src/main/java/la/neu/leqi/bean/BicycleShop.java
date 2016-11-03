package la.neu.leqi.bean;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/11/3.
 */

public class BicycleShop {
    private int shopId;
    private String shopName;
    private String ownerName;
    private String deccription;
    private int level;
    private String tele;
    private int collectCount;
    private String address;

    private ArrayList<ShopActivityBean> shopActivities;
    private ArrayList<Clinet> clientCollect;
    private ArrayList<String> shopPics;
    private ArrayList<Advertisement> advertisements;
    private ArrayList<Good> goods;

    public BicycleShop() {
    }

    public BicycleShop(String shopName, String ownerName, String deccription, int level, String tele, int collectCount, String address) {
        this.shopName = shopName;
        this.ownerName = ownerName;
        this.deccription = deccription;
        this.level = level;
        this.tele = tele;
        this.collectCount = collectCount;
        this.address = address;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getDeccription() {
        return deccription;
    }

    public void setDeccription(String deccription) {
        this.deccription = deccription;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(int collectCount) {
        this.collectCount = collectCount;
    }

    public ArrayList<ShopActivityBean> getShopActivities() {
        return shopActivities;
    }

    public void setShopActivities(ArrayList<ShopActivityBean> shopActivities) {
        this.shopActivities = shopActivities;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Clinet> getClientCollect() {
        return clientCollect;
    }

    public void setClientCollect(ArrayList<Clinet> clientCollect) {
        this.clientCollect = clientCollect;
    }

    public ArrayList<String> getShopPics() {
        return shopPics;
    }

    public void setShopPics(ArrayList<String> shopPics) {
        this.shopPics = shopPics;
    }

    public ArrayList<Good> getGoods() {
        return goods;
    }

    public void setGoods(ArrayList<Good> goods) {
        this.goods = goods;
    }

    public ArrayList<Advertisement> getAdvertisements() {
        return advertisements;
    }

    public void setAdvertisements(ArrayList<Advertisement> advertisements) {
        this.advertisements = advertisements;
    }
}
