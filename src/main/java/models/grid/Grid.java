package models.grid;

import models.City;

import java.util.HashMap;

class Grid {
    private HashMap<String, City> map = new HashMap<>();

    private String key(Coords coords) {
        return coords.x + "-" + coords.y;
    }

    public void set(Coords coords, City city) {
        map.put(key(coords), city);
    }

    public City get(Coords coords) {
        return map.get(key(coords));
    }

    static class Coords {
        int x;
        int y;

        public Coords(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
