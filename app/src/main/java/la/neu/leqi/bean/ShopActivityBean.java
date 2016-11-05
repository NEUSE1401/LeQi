package la.neu.leqi.bean;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/11/3.
 */

public class ShopActivityBean {
    private int activityId;
    private String title;
    private String description;
    private String startTime;
    private String endTime;
    private String releaseTime;
    private ArrayList<String> pic_listp;
    private int count;


    public ShopActivityBean(String title, String description, String startTime, String endTime, String releaseTime, ArrayList<String> pic_listp, int activityId) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.releaseTime = releaseTime;
        this.pic_listp = pic_listp;
        this.activityId = activityId;
    }

    public ShopActivityBean( int activityId,String title, String startTime, String endTime, ArrayList<String> pic_list,int count) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.pic_listp = pic_list;
        this.activityId = activityId;
        this.count =count;
    }

    public ShopActivityBean() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public ArrayList<String> getPic_listp() {
        return pic_listp;
    }

    public void setPic_listp(ArrayList<String> pic_listp) {
        this.pic_listp = pic_listp;
    }
}
