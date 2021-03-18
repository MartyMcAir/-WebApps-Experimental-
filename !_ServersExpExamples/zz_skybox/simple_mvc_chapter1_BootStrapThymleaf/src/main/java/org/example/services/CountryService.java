package org.example.services;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.example.web.model.Country;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CountryService {

    public static void main(String[] args) throws IOException {
        List<Country> list = new ArrayList<>();
        Optional<List<Country>> listOfCountries = getListOfCountries();

        // https://www.baeldung.com/java-optional
        if (listOfCountries.isPresent()) list = listOfCountries.get();

        list.forEach(System.out::println);
    }

    public static Optional<List<Country>> getListOfCountries() throws IOException {
        List<Country> countries;

        try (InputStream input = CountryService.class.getClassLoader().getResourceAsStream("countries.csv")) {
            if (input == null) return Optional.empty();

            HeaderColumnNameMappingStrategy<Country> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(Country.class);

            try (BufferedReader br = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))) {
                CsvToBean<Country> csvToBean = new CsvToBeanBuilder<Country>(br)
                        .withType(Country.class)
                        .withMappingStrategy(strategy)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                countries = csvToBean.parse();
            }
        }

        return Optional.of(countries);
    }
}