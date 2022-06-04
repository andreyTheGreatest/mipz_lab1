package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class City {
    private String countryName;
    private List<String> coinTypes;
    private List<City> neighbours;
    private List<Long> coins;
    private List<Long> cache;
    private Long portion;

    public City(List<String> coinTypes, String countryName) {
        this.coinTypes = coinTypes;
        this.countryName = countryName;
        this.neighbours = List.of();

        this.coins = new ArrayList<>(Collections.nCopies(coinTypes.size(), 0L));
        this.cache = new ArrayList<>(Collections.nCopies(coinTypes.size(), 0L));

        int countryIndex = coinTypes.indexOf(countryName);

        long INIT_COINS = 1_000_000L;
        this.coins.set(countryIndex, INIT_COINS);
        this.portion = INIT_COINS / 1000;
    }

    public boolean isCompleted() {
        return coins.stream().allMatch(c -> c > 0);
    }

    public void transportCoinsToNeighbours() {
        for (int i = 0; i < coins.size(); i++) {
            long share = Math.floorDiv(coins.get(i), portion);
            int finalI = i;
            neighbours.forEach(c -> {
                c.cache.set(finalI, c.cache.get(finalI) + share);
                coins.set(finalI, coins.get(finalI) - share);
            });
        }
    }

    public void updateCoins() {
        for (int i = 0; i < coinTypes.size(); i++) {
            coins.set(i, coins.get(i) + cache.get(i));
            cache.set(i, 0L);
        }
    }

    public List<City> getNeighbours() {
        return neighbours;
    }

    public Long getPortion() {
        return portion;
    }

    public List<Long> getCache() {
        return cache;
    }

    public List<Long> getCoins() {
        return coins;
    }

    public String getCountryName() {
        return countryName;
    }

    public List<String> getCoinTypes() {
        return coinTypes;
    }

    public void setCache(List<Long> cache) {
        this.cache = cache;
    }

    public void setCoins(List<Long> coins) {
        this.coins = coins;
    }

    public void setCoinTypes(List<String> coinTypes) {
        this.coinTypes = coinTypes;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setNeighbours(List<City> neighbours) {
        this.neighbours = neighbours;
    }

    public void setPortion(Long portion) {
        this.portion = portion;
    }
}
