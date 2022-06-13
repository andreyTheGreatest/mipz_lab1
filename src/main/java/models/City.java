package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class City {
    private final String countryName;
    private final List<String> coinTypes;
    private List<City> neighbours;
    private final List<Long> coins;
    private final List<Long> cache;
    private final Long portion;
    private final long INIT_COINS = 1_000_000L;
    private final long REPRESENTATIVE_PORTION_PER_COIN = 1_000L;


    public City(List<String> coinTypes, String countryName) {
        this.coinTypes = coinTypes;
        this.countryName = countryName;
        this.neighbours = List.of();

        this.coins = new ArrayList<>(Collections.nCopies(coinTypes.size(), 0L));
        this.cache = new ArrayList<>(Collections.nCopies(coinTypes.size(), 0L));

        int countryIndex = coinTypes.indexOf(countryName);


        this.coins.set(countryIndex, INIT_COINS);
        this.portion = INIT_COINS / REPRESENTATIVE_PORTION_PER_COIN;
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

    public String getCountryName() {
        return countryName;
    }

    public void setNeighbours(List<City> neighbours) {
        this.neighbours = neighbours;
    }
}
