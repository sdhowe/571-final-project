/*******************************************************************************
 * Copyright 2013 Lars Behnke
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

public class ClusterPair implements Comparable<ClusterPair> {

    private static long globalIndex = 0;

    private HCCluster lCluster;
    private HCCluster rCluster;
	private Double linkageDistance;

    public ClusterPair(){
    }

	public ClusterPair(HCCluster left, HCCluster right, Double distance) {
        lCluster=left;
        rCluster=right;
        linkageDistance=distance;
    }

  public HCCluster getOtherCluster(HCCluster c) {
    return lCluster == c ? rCluster : lCluster;
  }

    public HCCluster getlCluster() {
        return lCluster;
    }

    public void setlCluster(HCCluster lCluster) {
        this.lCluster = lCluster;
    }

    public HCCluster getrCluster() {
        return rCluster;
    }

    public void setrCluster(HCCluster rCluster) {
        this.rCluster = rCluster;
    }

	public Double getLinkageDistance() {
        return linkageDistance;
    }

	public void setLinkageDistance(Double distance) {
        this.linkageDistance = distance;
    }

    /**
     * @return a new ClusterPair with the two left/right inverted
     */
    public ClusterPair reverse() {
        return new ClusterPair(getrCluster(), getlCluster(), getLinkageDistance());
    }



    @Override
    public int compareTo(ClusterPair o) {
        int result;
        if (o == null || o.getLinkageDistance() == null) {
            result = -1;
        } else if (getLinkageDistance() == null) {
            result = 1;
        } else {
            result = getLinkageDistance().compareTo(o.getLinkageDistance());
        }

        return result;
    }


    public HCCluster agglomerate(String name) {
        if (name == null) {
            name = "clstr#" + (++globalIndex);

            /*
            StringBuilder sb = new StringBuilder();
            if (lCluster != null) {
                sb.append(lCluster.getName());
            }
            if (rCluster != null) {
                if (sb.length() > 0) {
                    sb.append("&");
                }
                sb.append(rCluster.getName());
            }
            name = sb.toString();
            */
        }
        HCCluster cluster = new HCCluster(name);
        cluster.setDistance(new Distance(getLinkageDistance()));
        cluster.addChild(lCluster);
        cluster.addChild(rCluster);
        lCluster.setParent(cluster);
        rCluster.setParent(cluster);

        Double lWeight = lCluster.getWeightValue();
        Double rWeight = rCluster.getWeightValue();
		double weight = lWeight + rWeight;
        cluster.getDistance().setWeight(weight);

        return cluster;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (lCluster != null) {
            sb.append(lCluster.getName());
        }
        if (rCluster != null) {
            if (sb.length() > 0) {
                sb.append(" + ");
            }
            sb.append(rCluster.getName());
        }
        sb.append(" : ").append(linkageDistance);
        return sb.toString();
    }

}
