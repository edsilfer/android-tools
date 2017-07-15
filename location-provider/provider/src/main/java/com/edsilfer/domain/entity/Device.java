package com.edsilfer.domain.entity;

import java.io.Serializable;

/**
 * Created by ferna on 6/3/2017.
 */
public class Device implements Serializable {

    private String name;

    public Device(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
