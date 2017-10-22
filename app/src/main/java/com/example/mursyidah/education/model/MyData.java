package com.example.mursyidah.education.model;

/**
 * Created by mursyidah on 11/10/2017.
 */

public class MyData
{

    private String name, path;

    public MyData(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
