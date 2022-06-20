package models;

import java.util.List;

public class CountryCoords {
    public final int xl ;
    public final int yl ;
    public final int xh ;
    public final int yh ;

    public CountryCoords(List<Integer> coords) {
        this.xl = coords.get(0);
        this.yl = coords.get(1);
        this.xh = coords.get(2);
        this.yh = coords.get(3);
    }

    public String toString() {
        return "" + xh + ", " + xl + ", " + yh + ", " + yl;
    }
}
