package com.bu.admin.feign.bo.content;

import java.io.Serializable;

/**
 * Created by ZHangKaiLong on 2024/5/14.
 */
public class Test implements Serializable {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
