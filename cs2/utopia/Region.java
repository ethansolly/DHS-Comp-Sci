package com.lutalica.utopia;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Lutalica on 3/22/2017.
 */
public class Region {

    private ArrayList<Point> coords = new ArrayList<Point>();

    public Region(ArrayList<Point> pcoords) {
        for (Point p : pcoords)
            coords.add(p);
    }

    public Region(ArrayList<Integer> xcoords, ArrayList<Integer> yCoords) {
        for (int i = 0; i < xcoords.size(); i++)
            coords.add(new Point(xcoords.get(i), yCoords.get(i)));
    }

    public ArrayList<Point> getContinuousRegion(Point p) {
        ArrayList<Point> ret = new ArrayList<Point>();
        Point temp;

        ret.add(p);

        if (coords.contains(temp = new Point(p.x+1, p.y)) && !ret.contains(temp))
            add(ret, getContinuousRegion(temp));
        if (coords.contains(temp = new Point(p.x, p.y+1)) && !ret.contains(temp))
            add(ret, getContinuousRegion(temp));
        if (coords.contains(temp = new Point(p.x-1, p.y)) && !ret.contains(temp))
            add(ret, getContinuousRegion(temp));
        if (coords.contains(temp = new Point(p.x, p.y-1)) && !ret.contains(temp))
            add(ret, getContinuousRegion(temp));

        return ret;
    }

    public ArrayList<Point> getContinuousRegion(int x, int y) {
        Point ref = new Point(0, 0);
        for (Point p : coords)
            if (p.x == x && p.y == y)
                ref = p;
        return getContinuousRegion(ref);
    }

    private void add(ArrayList<Point> front, ArrayList<Point> end) {
        for (Point p : end) front.add(p);
    }

}
