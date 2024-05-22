public class Road implements Comparable<Road>{
    private final String endPoint1;
    private final String endPoint2;
    private final int length;
    private final int roadID;
    private final boolean reversePrint;
    
    public Road(String endPoint1, String endPoint2, int length, int roadID, boolean reversePrint) {
        this.endPoint1 = endPoint1;
        this.endPoint2 = endPoint2;
        this.length = length;
        this.roadID = roadID;
        this.reversePrint = reversePrint;
    }

    public String getEndPoint1() {
        return endPoint1;
    }

    public String getEndPoint2() {
        return endPoint2;
    }

    public String[] getEndPoints() {
        return new String[] {endPoint1, endPoint2};
    }

    public int getLength() {
        return length;
    }

    public int getRoadID() {
        return roadID;
    }

    @Override
    public String toString() {
        if(reversePrint) {
            return endPoint2 + "\t" + endPoint1 + "\t" + length + "\t" + roadID;
        }
        return endPoint1 + "\t" + endPoint2 + "\t" + length + "\t" + roadID;
    }

    @Override
    public int compareTo(Road road) {
        if(this.length < road.length) {
            return -1;
        } else if(this.length > road.length) {
            return 1;
        } else {
            if (this.roadID < road.roadID) {
                return -1;
            } else if (this.roadID > road.roadID) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}