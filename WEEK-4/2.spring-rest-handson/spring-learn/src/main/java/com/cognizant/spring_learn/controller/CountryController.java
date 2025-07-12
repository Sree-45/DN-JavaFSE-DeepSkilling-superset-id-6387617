package com.cognizant.spring_learn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cognizant.spring_learn.Country;
import com.cognizant.spring_learn.service.CountryService;
import com.cognizant.spring_learn.service.exception.CountryNotFoundException;
import java.util.List;

@RestController
public class CountryController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);
    
    @Autowired
    private CountryService countryService;
    
    @RequestMapping("/country")
    public Country getCountryIndia() {
        LOGGER.info("START");
        
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        
        Country india = context.getBean("in", Country.class);
        
        LOGGER.debug("Retrieved India country: {}", india.toString());
        LOGGER.info("END");
        
        return india;
    }
    
    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        LOGGER.info("START");
        
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        
        List<Country> countries = context.getBean("countryList", List.class);
        
        LOGGER.debug("Retrieved all countries: {}", countries.toString());
        LOGGER.info("END");
        
        return countries;
    }
    
    @GetMapping("/countries/{code}")
    public Country getCountry(@PathVariable String code) throws CountryNotFoundException {
        LOGGER.info("START");
        LOGGER.debug("Requested country code: {}", code);
        
        Country country = countryService.getCountry(code);
        
        LOGGER.debug("Retrieved country: {}", country.toString());
        LOGGER.info("END");
        
        return country;
    }
} 