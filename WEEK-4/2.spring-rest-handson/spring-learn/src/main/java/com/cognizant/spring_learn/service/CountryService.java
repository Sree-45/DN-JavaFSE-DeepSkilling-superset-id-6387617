package com.cognizant.spring_learn.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import com.cognizant.spring_learn.Country;
import com.cognizant.spring_learn.service.exception.CountryNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);
    
    public Country getCountry(String code) throws CountryNotFoundException {
        LOGGER.info("START");
        
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        
        List<Country> countries = context.getBean("countryList", List.class);
        
        LOGGER.debug("Searching for country with code: {}", code);
        
        Optional<Country> foundCountry = countries.stream()
            .filter(country -> country.getCode().equalsIgnoreCase(code))
            .findFirst();
        
        if (foundCountry.isPresent()) {
            Country country = foundCountry.get();
            LOGGER.debug("Found country: {}", country.toString());
            LOGGER.info("END");
            return country;
        } else {
            LOGGER.debug("Country with code '{}' not found", code);
            LOGGER.info("END");
            throw new CountryNotFoundException("Country not found with code: " + code);
        }
    }
} 