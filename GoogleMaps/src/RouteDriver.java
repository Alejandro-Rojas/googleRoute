/*
 * CS 250 - Computer Science II - Spring 2020
 * Instructor: Thyago Mota
 * Description: Prg 01 - RouteDriver class
 * Your name(s): Alejandro Rojas and Vicram Mehta
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class RouteDriver extends JFrame implements MouseListener {

    private static final String ROUTE_DATA_FILE      = "route.csv";
    private static final String GOOGLE_MAPS_BASE_URL = "https://maps.googleapis.com/maps/api/staticmap";
    private static final String GOOGLE_MAPS_API_KEY  = "";
    private static final int    DEFAULT_ZOOM         = 13;
    private static final int    ZOOM_OUT_MIN         = 0;
    private static final int    ZOOM_IN_MAX          = 22;
    private static final char[] ALPHABET             = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static final int    WIDTH                = 800;
    private static final int    HEIGHT               = 600;
    private static final String TITLE                = "CS 250 - Prg 01 - Google Maps";

    private int     zoom;      // the zoom level for the map
    private JLabel  labelMap;  // the map as a label
    private Route   route;

    public RouteDriver() throws FileNotFoundException {
        // TODO: instantiate a route object (must use "route" as reference variable)
        route = new Route();



        // TODO: read the waypoints from the data file and add them into the route
        File file = new File("src/route.csv");
        Scanner sc = new Scanner(file);

        String[] row;
        while(sc.hasNextLine()){
            String input = sc.nextLine();
            row = input.split(",");
            double lat = Double.parseDouble(row[0]);
            double longi = Double.parseDouble(row[1]);
            route.add(new Waypoint(lat, longi));
            System.out.println(row[0]+""+ row[1]+""+ row[2]);
        }



        // GUI part of the frame
        setTitle(TITLE);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        zoom = DEFAULT_ZOOM;
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        JPanel pnlWest = new JPanel(new GridLayout(route.size(), 1));
        for (int i = 0; i < route.size(); i++) {
            Waypoint waypoint = route.get(i);
            JButton btn = new JButton(ALPHABET[i] + ": " + waypoint.toString());
            btn.setEnabled(false);
            pnlWest.add(btn);
        }
        contentPane.add(BorderLayout.WEST, pnlWest);
        labelMap = new JLabel(getImageIcon()); // calls getImageIcon to generate the map from Google Maps Static API
        contentPane.add(BorderLayout.CENTER, labelMap);
        addMouseListener(this);
        setVisible(true);
    }

    // generate an ImageIcon from Google Maps Static API
    private ImageIcon getImageIcon() {
        try {
            String urlString = GOOGLE_MAPS_BASE_URL + "?key=" + GOOGLE_MAPS_API_KEY + "&zoom=" + zoom + "&scale=1&size=" + WIDTH + "x" + HEIGHT;
            for (int i = 0; i < route.size(); i++) {
                Waypoint waypoint = route.get(i);
                urlString += "&markers=label:" + ALPHABET[i] + "|" + waypoint.getLatitude() + "," + waypoint.getLongitude();
            }
//            System.out.println(urlString);
            URL url = new URL(urlString);
            return new ImageIcon(url);
        }
        catch (MalformedURLException ex) {
            JOptionPane.showMessageDialog(null, "Sorry but there is a problem with your URL!", TITLE, JOptionPane.INFORMATION_MESSAGE);
        }
        return null;
    }

    // change the zoom of the map based on mouse click
    @Override
    public void mouseClicked(MouseEvent ev) {
        boolean zoomChanged = false;
        // hold CTRL key when zooming out
        if (ev.isControlDown()) {
            zoom--;
            if (zoom < ZOOM_OUT_MIN)
                zoom = ZOOM_OUT_MIN;
            else
                zoomChanged = true;
        }
        else {
            zoom++;
            if (zoom > ZOOM_IN_MAX)
                zoom = ZOOM_IN_MAX;
            else
                zoomChanged = true;
        }
        if (zoomChanged) {
            getContentPane().remove(labelMap);
            labelMap = new JLabel(getImageIcon());
            getContentPane().add(BorderLayout.CENTER, labelMap);

            // redraw the frame
            revalidate(); repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public static void main(String[] args) throws FileNotFoundException {
        new RouteDriver();
    }
}