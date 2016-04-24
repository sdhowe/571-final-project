/**
 * Created by Alexander Dang on 4/24/2016.
 */

import java.util.ArrayList;
import java.util.List;

public class CustomAlgorithm {
    private int kValue;

    private List points;
    private List clusters;

    public CustomAlgorithm(int k) {
        this.points = new ArrayList();
        this.clusters = new ArrayList();
        kValue = k;
    }

    public void initCustom (int k, int dataset) {
        // Parse points from dataset
        points = Point.parseData(dataset);

        // Each point starts off as its own cluster
        for (int i = 0; i < points.size(); i++) {
            int temp = i;
            String name = Integer.toString(i);
            HCCluster cluster = new HCCluster(name);
            clusters.add(cluster);
        }

        // Print initial state
        plotClusters(k);
    }

    // Call this until there are k clusters
    public void agglomerateClusters () {
        double[][] distanceMatrix = { };
        DistanceMap links = createLinkages(distanceMatrix, clusters);
    }

    private DistanceMap createLinkages(double[][] distances,
                                       List<HCCluster> clusters) {
        DistanceMap linkages = new DistanceMap();
        for (int col = 0; col < clusters.size(); col++) {
            for (int row = col + 1; row < clusters.size(); row++) {
                ClusterPair link = new ClusterPair();
                HCCluster lCluster = clusters.get(col);
                HCCluster rCluster = clusters.get(row);
                link.setLinkageDistance(distances[col][row]);
                link.setlCluster(lCluster);
                link.setrCluster(rCluster);
                linkages.add(link);
            }
        }
        return linkages;
    }

    private void plotClusters(int k) {
        for (int i = 0; i < k; i++) {
            Cluster c = (Cluster) clusters.get(i);
            c.plotCluster();
        }
    }

    // Gets centroid of each cluster
    public void getCentroid(List<Cluster> cluster) {
        // For each cluster
        for (int i = 0; i < cluster.size(); i++) {
            // Initialize temp variables for x & y values
            double totalX = 0;
            double totalY = 0;
            double averageX = 0;
            double averageY = 0;

            List<Point> clusterPoints = cluster.get(i).getPoints();

            // Get total x & y values for all points in a cluster
            for (int k = 0; k < clusterPoints.size(); k++) {
                double x = clusterPoints.get(k).getX();
                totalX = totalX + x;
                double y = clusterPoints.get(k).getY();
                totalY = totalY + y;
            }

            // Get average x & y values for a cluster to get the centroid
            averageX = totalX / clusterPoints.size();
            averageY = totalY / clusterPoints.size();

            Point newCentroid = new Point(averageX, averageY);

            // Set centroid
            cluster.get(i).setCentroid(newCentroid);
        }
    }

    public void calculateCustom(int k) {
        boolean finish = false;
        int iteration = 0;

        while (!finish) {
            clearClusters();

            List lastCentroids = getCentroids(k);

            // Assign points to the closer cluster
            assignCluster(k);

            // Calculate new centroids
            calculateCentroids();

            iteration++;

            List currentCentroids = getCentroids(k);

            // Calculates total distance between new and old centroids
            double distance = 0;
            for(int i = 0; i < lastCentroids.size(); i++) {
                distance += Point.distance(lastCentroids.get(i),currentCentroids.get(i));
            }
            System.out.println("#################");
            System.out.println("Iteration: " + iteration);
            System.out.println("Centroid distances: " + distance);
            plotClusters(k);

            if(distance == 0) {
                finish = true;
            }
        }
    }

    private List getCentroids(int k) {
        List centroids = new ArrayList(k);
        for(Object cluster : clusters) {
            Point aux = ((Cluster) cluster).getCentroid();
            Point point = new Point(aux.getX(),aux.getY());
            centroids.add(point);
        }
        return centroids;
    }

    private void assignCluster(int k) {
        double max = Double.MAX_VALUE;
        double min = max;
        int cluster = 0;
        double distance = 0.0;

        for(Object point : points) {
            min = max;
            for(int i = 0; i < k; i++) {
                Cluster c = (Cluster) clusters.get(i);
                distance = Point.distance(point, c.getCentroid());
                if(distance < min){
                    min = distance;
                    cluster = i;
                }
            }
            ((Point) point).setCluster(cluster);
            ((Cluster) clusters.get(cluster)).addPoint((Point) point);
        }
    }

    private void calculateCentroids() {
        for(Object cluster : clusters) {
            double sumX = 0;
            double sumY = 0;
            List list = ((Cluster) cluster).getPoints();
            int n_points = list.size();

            for(Object point : list) {
                sumX += ((Point) point).getX();
                sumY += ((Point) point).getY();
            }

            Point centroid = ((Cluster) cluster).getCentroid();
            if(n_points > 0) {
                double newX = sumX / n_points;
                double newY = sumY / n_points;
                centroid.setX(newX);
                centroid.setY(newY);
            }
        }
    }

    private void clearClusters() {
        for(Object cluster : clusters) {
            ((Cluster) cluster).clear();
        }
    }
}
