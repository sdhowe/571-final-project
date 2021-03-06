/**
 * Created by Alexander Dang on 4/6/2016.
 * AUTHOR CREDIT: http://www.dataonfocus.com/k-means-clustering-java-code/
 */
// package com.dataonfocus.clustering;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Point {

    private double x = 0;
    private double y = 0;
    private int cluster_number = 0;

    public Point(double x, double y)
    {
        this.setX(x);
        this.setY(y);
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getX()  {
        return this.x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return this.y;
    }

    public void setCluster(int n) {
        this.cluster_number = n;
    }

    public int getCluster() {
        return this.cluster_number;
    }

    //Calculates the distance between two points.
    protected static double distance(Object object, Object object2) {
        return Math.sqrt(Math.pow((((Point) object2).getY() - ((Point) object).getY()), 2) + Math.pow((((Point) object2).getX() - ((Point) object).getX()), 2));
    }

    //Creates random point
    protected static Point createRandomPoint(int min, int max) {
        Random r = new Random();
        double x = min + (max - min) * r.nextDouble();
        double y = min + (max - min) * r.nextDouble();
        return new Point(x,y);
    }

    protected static List createRandomPoints(int min, int max, int number) {
        List points = new ArrayList(number);
        for(int i = 0; i < number; i++) {
            points.add(createRandomPoint(min,max));
        }
        return points;
    }

    public String toString() {
        return "("+x+","+y+")";
    }

    protected static List parseData(int d){
        List points = new ArrayList(d);
        for(int i = 0; i < d; i++){
            Point point = new Point(Dataset.dataset[2*i], Dataset.dataset[(2*i)+1]);
            points.add(point);
        }
        return points;
    }

}