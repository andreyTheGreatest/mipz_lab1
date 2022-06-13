package models;

import java.util.List;

public class CountryCoords {
    private final int xl ;
    private final int yl ;
    private final int xh ;
    private final int yh ;

    public CountryCoords(List<Integer> coords) {
        this.xl = coords.get(0);
        this.yl = coords.get(1);
        this.xh = coords.get(2);
        this.yh = coords.get(3);
    }

    public String toString() {
        return "" + xh + ", " + xl + ", " + yh + ", " + yl;
    }

    public int getXl() {
        return xl;
    }

    public int getYl() {
        return yl;
    }

    public int getXh() {
        return xh;
    }

    public int getYh() {
        return yh;
    }
}
