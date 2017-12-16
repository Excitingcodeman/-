package com.example.hjj.mddemo.md;

import lombok.Data;

/**
 * Created by 黄俊杰 on 2017-12-14.
 */
@Data
public class Fruit {
    private String name;
    private int imageId;

    public Fruit(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }
}
