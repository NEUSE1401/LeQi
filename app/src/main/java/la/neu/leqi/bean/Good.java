package la.neu.leqi.bean;


import java.io.Serializable;
import java.util.ArrayList;

public class Good implements Serializable {
    private int id;
    private String name;
    private String brand;
    private double original_price;
    private double current_price;
    private String description;
    private boolean is_rent;
    private String type;
    private int hit;
    private String onsale_time;
    private ArrayList<String> pic_list;
    private ArrayList<String> paremeters;

    public Good() {
    }

    public Good(int id, String name, String brand, double original_price, double current_price, String description, boolean is_rent, String type, int hit, String onsale_time, ArrayList<String> pic_list) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.original_price = original_price;
        this.current_price = current_price;
        this.description = description;
        this.is_rent = is_rent;
        this.type = type;
        this.hit = hit;
        this.onsale_time = onsale_time;
        this.pic_list = pic_list;
        paremeters=new ArrayList<>();
    }

    public Good(int id, String name, double original_price, double current_price, ArrayList<String> pic_list) {
        this.id = id;
        this.name = name;
        this.original_price = original_price;
        this.current_price = current_price;
        this.pic_list = pic_list;
        paremeters=new ArrayList<>();
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public String getOnsale_time() {
        return onsale_time;
    }

    public void setOnsale_time(String onsale_time) {
        this.onsale_time = onsale_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(double original_price) {
        this.original_price = original_price;
    }

    public double getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(double current_price) {
        this.current_price = current_price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean is_rent() {
        return is_rent;
    }

    public void setIs_rent(boolean is_rent) {
        this.is_rent = is_rent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<String> getPic_list() {
        return pic_list;
    }

    public void setPic_list(ArrayList<String> pic_list) {
        this.pic_list = pic_list;
    }

    public ArrayList<String> getParemeters() {
        return paremeters;
    }

    public void setParemeters(ArrayList<String> paremeters) {
        this.paremeters = paremeters;
    }
}
