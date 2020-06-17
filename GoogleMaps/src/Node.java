public class Node {
    private static final Object Waypoint = null;
    private Waypoint waypoint;
    private Node next;

    public Node() {
        waypoint = null;
        next = null;
    }

    public Node(Waypoint waypoint) {
        this.waypoint = waypoint;
        this.next = null;
    }

    public Waypoint getWaypoint() {
        return waypoint;
    }

    public Node getNext() {
        return next;
    }

    public void setData(Waypoint waypoint) {
        this.waypoint = waypoint;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return waypoint.toString();
    }
}
