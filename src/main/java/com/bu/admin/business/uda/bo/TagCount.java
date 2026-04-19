package com.bu.admin.business.uda.bo;

import lombok.Data;

@Data
public class TagCount {
    private String tagName;
    private Long count;

    public TagCount(String tagName, Long count) {
        this.tagName = tagName;
        this.count = count;
    }

}
