

public class Route {

    // TODO: implement instance/class variables
    public Node start;
    private static final int MAX_JUMP = 5;


    // TODO: implement the parameter-less constructor
    public Route() {
        start = null;

    }

    // TODO: return the number of waypoints in the route
    public int size() {
        int count = 0;
        Node current = this.start;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    // TODO: a route should not accept jumps from one waypoint to another greater than MAX_KUMP; return true/false depending whether the waypoint was successfully added or not
    public boolean add(final Waypoint waypoint) {
        Node current = start;

        if(current == null){
            this.start = new Node(waypoint);
            return true;
        }

        Node previous = null;
        while(current != null){
            previous = current;
            current = current.getNext();
        }

        if(previous.getWaypoint().distance(waypoint) < MAX_JUMP){
            Node newNode = new Node(waypoint);
            previous.setNext(newNode);
            return true;
        }
        return false;


    }

    // TODO: return the waypoint at the specified index location
    public Waypoint get(int index) {
        if (index < 0 || index >= size())
            return null;
        int i = 0;
        Node current = start;
        while (i < index){
            i++;
            current = current.getNext();
        }
        return current.getWaypoint();
    }
    // TODO: return a string representation of a route from its start to its end
    @Override
    public String toString() {
        String out = "(start)";
        Node current = start;
        while (current != null) {
            out += " " + current.toString();
            current = current.getNext();
        }
        out += " (end)";
        return out;
    }
}




