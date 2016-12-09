package la.neu.leqi.bean;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by lenovo on 2016/11/3.
 */

public class Clinet {
    private int userId;
    private String nick_name;
    private String gender;
    private Calendar birthday;
    private String faceImg;
    private String contactWay;
    private String signature;
    private String province;
    private String city;
    private String distract;
    private String detial;

    public Clinet(String nick_name,Calendar birthday, String gender, String contactWay, String signature, String province, String city, String distract, String detial) {
        this.nick_name = nick_name;
        this.birthday=birthday;
        this.gender = gender;
        this.contactWay = contactWay;
        this.signature = signature;
        this.province = province;
        this.city = city;
        this.distract = distract;
        this.detial = detial;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday.get(Calendar.YEAR)+"-"+birthday.get(Calendar.MONTH)+"-"+birthday.get(Calendar.DAY_OF_MONTH);
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public String getFaceImg() {
        return faceImg;
    }

    public void setFaceImg(String faceImg) {
        this.faceImg = faceImg;
    }

    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
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

    public String getDistract() {
        return distract;
    }

    public void setDistract(String distract) {
        this.distract = distract;
    }

    public String getDetial() {
        return detial;
    }

    public void setDetial(String detial) {
        this.detial = detial;
    }

    public String getAddress(){
        return province+"-"+city+"-"+distract;
    }

}
