package com.example.testfor_ktelabs.soap.utils;

import javax.xml.bind.annotation.XmlElement;

class MapElements {
    @XmlElement
    public String key;
    @XmlElement
    public Integer value;

    private MapElements() {
    }

    public MapElements(String key, Integer value) {
        this.key = key;
        this.value = value;
    }
}