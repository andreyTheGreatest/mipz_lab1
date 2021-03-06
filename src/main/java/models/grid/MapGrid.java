package models.grid;

import models.City;
import models.Country;

import java.util.*;

public class MapGrid {
    private List<Country> countries;
    private Grid countriesGrid = new Grid();

    private int minX;
    private int minY;
    private int maxX;
    private int maxY;

    public MapGrid(List<Country> countries) {
        this.countries = countries;
        maxX = 0;
        maxY = 0;
        minX = 0;
        minY = 0;

        countries.forEach(c -> {
            minX = Math.min(minX, c.getCoords().xl);
            minY = Math.min(minY, c.getCoords().yl);
            maxX = Math.max(maxX, c.getCoords().xh);
            maxY = Math.max(maxY, c.getCoords().yh);
        });
        addCitiesToCountries();
        addNeighboursToCities();
    }

    public boolean isCompleted() {
        return countries.stream().allMatch(Country::isCompleted);
    }

    public void addNeighboursToCities() {
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                City city = countriesGrid.get(new Grid.Coords(x, y));
                if (city == null) continue;
                List<City> neighbours = new ArrayList<>();
                if (x < maxX) newNeighbour(x + 1, y, neighbours);
                if (x > minX) newNeighbour(x - 1, y, neighbours);
                if (y < maxY) newNeighbour(x, y + 1, neighbours);
                if (y > minY) newNeighbour(x, y - 1, neighbours);

                if (countries.size() > 1 && neighbours.size() == 0) {
                    throw new Error("City in " + city.getCountryName() + " has no neighbors");
                }

                city.setNeighbours(neighbours);
            }
        }
    }

    private void newNeighbour(int x, int y, List<City> neighbours) {
        City neighbour = countriesGrid.get(new Grid.Coords(x, y));
        if (neighbour != null)
            neighbours.add(neighbour);
    }

    public Map<String, Integer> startDiffusion() {
        countriesGrid = new Grid();
        Map<String, Integer> res = new TreeMap<>();
        int currDay = 0;

        do {
            int finalCurrDay = currDay;
            countries.forEach(country -> {
                country.getCities().forEach(City::transportCoinsToNeighbours);
                if (country.isCompleted() && !res.containsKey(country.getName())) {
                    res.put(country.getName(), finalCurrDay);
                }
            });
            countries.forEach(country -> country.getCities().forEach(City::updateCoins));
            currDay++;

        } while(!isCompleted());

        int finalCurrDay1 = currDay;
        countries.forEach(country -> {
            if (!res.containsKey(country.getName())) {
                res.put(country.getName(), finalCurrDay1);
            }
        });

        return res;
    }

    private static List<Map.Entry<String, Integer>> sortByValue(Map<String, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list = new LinkedList<>(hm.entrySet());

        // Sort the list
        list.sort(Map.Entry.comparingByValue());

        return list;
    }

    public static String diffusionToString(Map<String, Integer> diffusion) {
        List<Map.Entry<String, Integer>> listMap = sortByValue(diffusion);
        List<String> list = listMap.stream().map(val -> val.getKey() + " " + val.getValue()).toList();
        return String.join("\n", list);
    }

    public void addCitiesToCountries() {
        List<String> coinTypes = countries.stream().map(Country::getName).toList();
        for (Country country : countries) {
            for (int x = country.getCoords().xl; x <= country.getCoords().xh; x++) {
                for (int y = country.getCoords().yl; y <= country.getCoords().yh; y++) {
                    City city = new City(coinTypes, country.getName());
                    City exists;
                    if ((exists = countriesGrid.get(new Grid.Coords(x, y))) != null) {
                        throw new Error("Countries " + country.getName() + " and " + exists.getCountryName() + " overlap! Aborting...");
                    }
                    countriesGrid.set(new Grid.Coords(x, y), city);
                    country.addCity(city);
                }
            }
        }
    }
}