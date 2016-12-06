package la.neu.leqi.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by lenovo on 2016/11/3.
 */

public class ActivityBean implements Serializable{
    private int activityId;
    private String title;//
    private String description;//
    private String startTime;//
    private String endTime;//
    private String releaseTime;
    private ArrayList<String> pic_listp;
    private int count;
    private String startPlace;//
    private String endPlace;//
    private String activityPlace;
    private String participateWay;//
    private String owner;
    private String requirement;//
    public ActivityBean(String title, String description, String startTime, String endTime, String releaseTime, ArrayList<String> pic_listp, int activityId) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.releaseTime = releaseTime;
        this.pic_listp = pic_listp;
        this.activityId = activityId;
    }

    public ActivityBean(int activityId, String title, String startTime, String endTime, ArrayList<String> pic_list, int count) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.pic_listp = pic_list;
        this.activityId = activityId;
        this.count =count;
    }

    public ActivityBean() {
    }

    public ActivityBean(int activityId, String title, String description, String startTime, String endTime, String releaseTime, ArrayList<String> pic_listp, int count, String startPlace, String endPlace, String activityPlace, String participateWay, String owner, String requirement) {
        this.activityId = activityId;
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.releaseTime = releaseTime;
        this.pic_listp = pic_listp;
        this.count = count;
        this.startPlace = startPlace;
        this.endPlace = endPlace;
        this.activityPlace = activityPlace;
        this.participateWay = participateWay;
        this.owner = owner;
        this.requirement = requirement;
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

    public String getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(String startPlace) {
        this.startPlace = startPlace;
    }

    public String getEndPlace() {
        return endPlace;
    }

    public void setEndPlace(String endPlace) {
        this.endPlace = endPlace;
    }

    public String getActivityPlace() {
        return activityPlace;
    }

    public void setActivityPlace(String activityPlace) {
        this.activityPlace = activityPlace;
    }

    public String getParticipateWay() {
        return participateWay;
    }

    public void setParticipateWay(String participateWay) {
        this.participateWay = participateWay;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }
}
