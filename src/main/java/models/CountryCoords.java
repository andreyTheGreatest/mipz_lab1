package models;

import java.util.List;

public class CountryCoords {
    private int xl ;
    private int yl ;
    private int xh ;
    private int yh ;

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

    public void setXl(int xl) {
        this.xl = xl;
    }

    public int getYl() {
        return yl;
    }

    public void setYl(int yl) {
        this.yl = yl;
    }

    public int getXh() {
        return xh;
    }

    public void setXh(int xh) {
        this.xh = xh;
    }

    public int getYh() {
        return yh;
    }

    public void setYh(int yh) {
        this.yh = yh;
    }
}
