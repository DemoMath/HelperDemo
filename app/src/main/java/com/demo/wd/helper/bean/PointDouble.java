package com.demo.wd.helper.bean;

/**
 * Created by Administrator on 2016/5/9.
 */
public class PointDouble {
    public double x, y;

    public PointDouble(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "x=" + x + ", y=" + y;
    }
}
