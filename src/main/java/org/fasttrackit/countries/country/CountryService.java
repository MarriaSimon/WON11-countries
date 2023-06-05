package org.fasttrackit.countries.country;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CountryService {
    private final CountryReader countryReader;
    private final List<Country> countryList = new ArrayList<>();

    @PostConstruct
    public void init() throws FileNotFoundException {
        System.out.println("Post countruct in Country Service");
        countryList.addAll(countryReader.readCountries());
    }

    public List<Country> getAllCountries() {
        return countryList;
    }

    public List<String> getCountryName() {
        return countryList.stream()
                .map(Country::getName)
                .toList();
    }

    public String getCapital(String countryName) {
        return countryList.stream()
                .filter(country -> country.getName().contains(countryName))
                .findFirst()
                .map(Country::getCapital)
                .orElse(null);
    }

    public List<Country> getByContinent(String continent) {
        return countryList.stream()
                .filter(country -> country.getContinent().equals(continent))
                .toList();
    }

    public Country getById(long id) {
        return getAllCountries().stream()
                .filter(country -> country.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Country delete(long id) {
        Country country = getById(id);
        countryList.remove(country);
        return country;
    }

    public Country add(Country country) {
        countryList.add(country);
        return country;
    }

    public Country update(Country country, long id) {
        Country existingCountry = delete(id);
        return add(Country.builder()
                .id(id)
                .name(existingCountry.getName())
                .neighbours(existingCountry.getNeighbours())
                .capital(country.getCapital())
                .population(country.getPopulation())
                .area(country.getArea())
                .continent(existingCountry.getContinent())
                .build());
    }

}
