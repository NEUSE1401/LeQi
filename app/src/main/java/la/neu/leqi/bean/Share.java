package la.neu.leqi.bean;

import java.util.ArrayList;

/**
 * 分享BEAN
 */

public class Share {
    private int id;
    private String theme;
    private String start_time;
    private String end_time;
    private String description;
    private String start_place;
    private String end_place;
    private ArrayList<String> pic_list;
    private int hit_count;

    public Share(int id, String theme, String start_time, String end_time, String description, String start_place, String end_place, ArrayList<String> pic_list, int hit_count) {
        this.id = id;
        this.theme = theme;
        this.start_time = start_time;
        this.end_time = end_time;
        this.description = description;
        this.start_place = start_place;
        this.end_place = end_place;
        this.pic_list = pic_list;
        this.hit_count = hit_count;
    }


    public Share(int id, String theme, ArrayList<String> pic_list) {
        this.id = id;
        this.theme = theme;
        this.pic_list = pic_list;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getPic_list() {
        return pic_list;
    }

    public void setPic_list(ArrayList<String> pic_list) {
        this.pic_list = pic_list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getStart_place() {
        return start_place;
    }

    public void setStart_place(String start_place) {
        this.start_place = start_place;
    }

    public String getEnd_place() {
        return end_place;
    }

    public void setEnd_place(String end_place) {
        this.end_place = end_place;
    }

    public int getHit_count() {
        return hit_count;
    }

    public void setHit_count(int hit_count) {
        this.hit_count = hit_count;
    }
}
