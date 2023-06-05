package org.fasttrackit.countries.country;


import org.springframework.stereotype.Component;

@Component
public class Main {
    private final CountryService countryService;

    public Main(CountryService countryService) {
        this.countryService = countryService;
        //test methods

        System.out.println(countryService.getAllCountries());
        System.out.println(countryService.getCountryName());
        System.out.println(countryService.getCapital("Slovenia"));
        System.out.println(countryService.getByContinent("Asia"));

    }
}
