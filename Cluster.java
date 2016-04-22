/**
 * Created by Alexander Dang on 4/6/2016.
 * AUTHOR CREDIT: http://www.dataonfocus.com/k-means-clustering-java-code/
 */
// package com.dataonfocus.clustering;

import java.util.ArrayList;
import java.util.List;

public class Cluster {

    public List points;
    public Point centroid;
    public int id;

    //Creates a new Cluster
    public Cluster(int id) {
        this.id = id;
        this.points = new ArrayList();
        this.centroid = null;
    }

    public List getPoints() {
        return points;
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public void setPoints(List points) {
        this.points = points;
    }

    public Point getCentroid() {
        return centroid;
    }

    public void setCentroid(Point centroid) {
        this.centroid = centroid;
    }

    public int getId() {
        return id;
    }

    public void clear() {
        points.clear();
    }

    public void plotCluster() {
        System.out.println("[Cluster: " + (id+1)+"]");
        System.out.println("[Centroid: " + centroid + "]");
        System.out.println("[Points: \n");
        //for(Point p : points) {
        for (int i = 0; i < points.size(); i++) {
            System.out.println(points.get(i));
        }
        System.out.println("]");
    }

}
