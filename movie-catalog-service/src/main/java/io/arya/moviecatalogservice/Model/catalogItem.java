package io.arya.moviecatalogservice.Model;

import org.springframework.stereotype.Component;

@Component
public class catalogItem {
    private String name;
    private String desc;
    private int rating;

    public catalogItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public catalogItem(String name, String desc, int rating) {
        this.name = name;
        this.desc = desc;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "catalogItem{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", rating=" + rating +
                '}';
    }
}
