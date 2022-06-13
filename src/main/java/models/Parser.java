package models;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Parser {
    public static List<List<String>> parseFile(String fileName) throws NumberFormatException, IOException {
        Objects.requireNonNull(fileName);
        List<List<String>> countryStrings = new ArrayList<>();
        try (
                Stream<String> streamLines = Files.lines(Paths.get(fileName));
        ) {
            List<String> lines = streamLines.toList();
            int lineIndex = 0;
            while (lineIndex < lines.size() - 2) {
                String currLine = lines.get(lineIndex);
                int countryNum = Integer.parseInt(currLine);
                // countryNum check
                if (countryNum < 1 || countryNum > 20)
                    throw new Error("Invalid number of countries! Need to be 1 <= c <= 20");
                lineIndex++;
                List<String> countries = new ArrayList<>();
                for (int countryLineIndex = lineIndex; countryLineIndex < countryNum + lineIndex; countryLineIndex++) {
                    countries.add(lines.get(countryLineIndex));
                }
                lineIndex += countryNum;
                countryStrings.add(countries);
            }
            if (!Objects.equals(lines.get(lines.size() - 1), "0")) {
                throw new Error("Input file must end with '0' line");
            }
        }

        return countryStrings;
    }
}
