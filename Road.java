/**
 * Represents a Road connecting two endpoints with a specified length and ID.
 * This class provides methods to retrieve the road's attributes and 
 * to compare Road objects based on their length and ID.
 * The toString method allows for an optional reversed print format for endpoints.
 * Implements {@code Comparable<Road>} to allow for natural ordering.
 * 
 * @see java.lang.Comparable
 */
public class Road implements Comparable<Road>{
    private final String endPoint1;
    private final String endPoint2;
    private final int length;
    private final int roadID;
    private final boolean reversePrint;
    
    /**
     * Constructs a Road with the specified endpoints, length, ID, and print format.
     *
     * @param endPoint1 the first endpoint of the road
     * @param endPoint2 the second endpoint of the road
     * @param length the length of the road
     * @param roadID the unique identifier for the road
     * @param reversePrint whether the endpoints should be printed in reverse order
     */    
    public Road(String endPoint1, String endPoint2, int length, int roadID, boolean reversePrint) {
        this.endPoint1 = endPoint1;
        this.endPoint2 = endPoint2;
        this.length = length;
        this.roadID = roadID;
        this.reversePrint = reversePrint;
    }
    
    /**
     * Returns the first endpoint of the road.
     *
     * @return the first endpoint
     */
    public String getEndPoint1() {
        return endPoint1;
    }
    
    /**
     * Returns the second endpoint of the road.
     *
     * @return the second endpoint
     */
    public String getEndPoint2() {
        return endPoint2;
    }

    /**
     * Returns both endpoints of the road as an array.
     *
     * @return an array containing the two endpoints
     */
    public String[] getEndPoints() {
        return new String[] {endPoint1, endPoint2};
    }

    /**
     * Returns the length of the road.
     *
     * @return the length of the road
     */
    public int getLength() {
        return length;
    }

    /**
     * Returns the unique identifier of the road.
     *
     * @return the road ID
     */
    public int getRoadID() {
        return roadID;
    }

    /**
     * Returns a string representation of the road.
     * The format can be reversed based on the reversePrint flag.
     * When reversePrint is true, the first and second endpoints are swapped.
     *
     * @return a string representing the road
     */
    @Override
    public String toString() {
        if(reversePrint) {
            return endPoint2 + "\t" + endPoint1 + "\t" + length + "\t" + roadID;
        }
        return endPoint1 + "\t" + endPoint2 + "\t" + length + "\t" + roadID;
    }
    
    /**
     * Compares this road to another based on length, and then by road ID if lengths are equal.
     *
     * @param road the road to compare with
     * @return a negative integer, zero, or a positive integer as this road
     *         is less than, equal to, or greater than the specified road
     */
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