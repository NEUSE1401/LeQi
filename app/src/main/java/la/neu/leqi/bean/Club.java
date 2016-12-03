package la.neu.leqi.bean;

import java.io.Serializable;
import java.util.ArrayList;

import la.neu.leqi.ShopActivity;

/**
 * Created by lenovo on 2016/11/27.
 */

public class Club implements Serializable{
    private String title;
    private String owner;
    private String description;
    private int level;
    private String contectWay;
    private String province;
    private String city;
    private String district;
    private String addressDetial;
    private ArrayList<String> pics;
    private int participateCount;

    public Club(String title, String owner, String description, int level, String contectWay, String province, String city, String district, String addressDetial, ArrayList<String> pics, int participateCount) {
        this.title = title;
        this.owner = owner;
        this.description = description;
        this.level = level;
        this.contectWay = contectWay;
        this.province = province;
        this.city = city;
        this.district = district;
        this.addressDetial = addressDetial;
        this.pics = pics;
        this.participateCount = participateCount;
    }

    public Club() {
        pics=new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getContectWay() {
        return contectWay;
    }

    public void setContectWay(String contectWay) {
        this.contectWay = contectWay;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddressDetial() {
        return addressDetial;
    }

    public void setAddressDetial(String addressDetial) {
        this.addressDetial = addressDetial;
    }

    public ArrayList<String> getPics() {
        return pics;
    }

    public void setPics(ArrayList<String> pics) {
        this.pics = pics;
    }

    public int getParticipateCount() {
        return participateCount;
    }

    public void setParticipateCount(int participateCount) {
        this.participateCount = participateCount;
    }

    public String getAddress(){
        return province+"-"+city+"-"+district+"-"+addressDetial;
    }
}
