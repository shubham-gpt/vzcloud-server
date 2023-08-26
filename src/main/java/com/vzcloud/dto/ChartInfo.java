package com.vzcloud.dto;

import com.vzcloud.entity.BaseEntity;

/**
 * @version v2.3
 * @ClassName:ChartInfo.java
 * @author: http://www.wgstart.com
 * @date: November 16, 2019
 * @Description: Chart DTO Information
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
public class ChartInfo extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = -2913111613773445949L;


    /**
     * name
     */
    private String item;

    private Integer count;

    private Double percent;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }
}