import models.Country;
import models.Parser;
import models.grid.MapGrid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String... args) throws IOException {
        List<List<String>> countryStrings = Parser.parseFile("C:\\Users\\shevdid\\Desktop\\KPI\\mipz\\lab1\\src\\main\\resources\\inputFile");
        for (int i = 0; i < countryStrings.size(); i++) {
            System.out.println("Case Number " + (i + 1));
            processCase(countryStrings.get(i));
        }
    }

    private static void processCase(List<String> countriesStrings) {
        try {
            List<Country> countries = new ArrayList<>();
            countriesStrings.forEach((countryString) -> {
                countries.add(Country.parseCountry(countryString));
            });

            Map<String, Integer> result = new MapGrid(countries).startDiffusion();
            System.out.println(MapGrid.diffusionToString(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ;
}
