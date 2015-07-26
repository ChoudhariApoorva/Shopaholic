package com.example.ssadalka.shopaholic;

/**
 * Created by ssadalka on 26-Jul-15.
 */
public class ObjectDrawerItem {

    public String name;
    public double distance;
    private double curlat = 12.913916;
    private double curlong =77.651839;
    // Constructor.
    public ObjectDrawerItem(String name) {


        this.name = name;
        this.distance = distance;
    }
    private double rad(double x)
    {
        return x*Math.PI/180;
    }
    public double calculateDistance(double a,double b)
    {
        double R = 6378137;
        a = rad(a-curlat);
        b = rad(b-curlong);
        double t1 = Math.sin(a/2)*Math.sin(a/2) + Math.cos(rad(a))*Math.cos(rad(a)) *Math.sin(b/2)* Math.sin(b/2);
        double t2 = 2*Math.atan2(Math.sqrt(t1) ,Math.sqrt(1-t1));
        return ((R*t2)/1000);
    }

}
