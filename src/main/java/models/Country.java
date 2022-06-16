package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Country {
    private final List<City> cities;
    private final String name;
    private final CountryCoords coords;
    public static int MIN_COORD = 1;
    public static int MAX_COORD = 10;
    public static int NAME_MAX_LENGTH = 25;

    public Country(String name, CountryCoords coords) {
        if (!areCoordsValid(coords)) {
            throw new IllegalArgumentException("Coordinates are invalid! " + coords.toString());
        }
        if (name.length() > NAME_MAX_LENGTH) {
            throw new IllegalArgumentException("Name must be less than " + NAME_MAX_LENGTH + " !");
        }
        this.cities = new ArrayList<>();
        this.name = name;
        this.coords = coords;
    }

    // refactoring function name according to naming convention
    public static boolean areCoordsValid(CountryCoords coords) {
        Predicate<Integer> inBounds = coord -> (coord >= MIN_COORD) && (coord <= MAX_COORD);
        return Stream.of(coords.getXl(), coords.getXh(), coords.getYh(), coords.getYl()).allMatch(inBounds)
                && coords.getXl() <= coords.getXh()
                && coords.getYl() <= coords.getYh();
    }

    public void addCity(City city) {
        cities.add(city);
    }

    public boolean isCompleted() {
        return cities.stream().allMatch(City::isCompleted);
    }

    public static Country parseCountry(String country) {
        List<String> args = new ArrayList<>(Arrays.asList(country.split(" ")));
        String name = args.remove(0);
        CountryCoords coords = new CountryCoords(args.stream().map(Integer::parseInt).collect(Collectors.toList()));
        return new Country(name, coords);
    }

    public CountryCoords getCoords() {
        return coords;
    }

    public String getName() {
        return name;
    }

    public List<City> getCities() {
        return cities;
    }
}
