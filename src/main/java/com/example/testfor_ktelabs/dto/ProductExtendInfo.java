package com.example.testfor_ktelabs.dto;

import com.example.testfor_ktelabs.soap.utils.MapAdapter;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.HashMap;

@XmlRootElement
public class ProductExtendInfo {
    private String description;
    private String avgRating;
    private HashMap<String, Integer> grades = new HashMap<>();
    private int userRating;

    public ProductExtendInfo() {
    }

    public String getDescription() {
        return description;
    }

    public ProductExtendInfo setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getAvgRating() {
        return avgRating;
    }

    public ProductExtendInfo setAvgRating(String avgRating) {
        this.avgRating = avgRating;
        return this;
    }

    @XmlJavaTypeAdapter(MapAdapter.class)
    public HashMap<String, Integer> getGrades() {
        return grades;
    }

    public ProductExtendInfo setGrades(HashMap<String, Integer> grades) {
        this.grades = grades;
        return this;
    }

    public int getUserRating() {
        return userRating;
    }

    public ProductExtendInfo setUserRating(int userRating) {
        this.userRating = userRating;
        return this;
    }
}

