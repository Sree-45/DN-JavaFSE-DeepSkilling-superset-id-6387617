package com.cognizant.spring_learn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Starting Spring Application...");

        SpringApplication.run(SpringLearnApplication.class, args);

        LOGGER.info("Spring Application started successfully.");
        displayDate();
        displayCountry();
        displayCountries();
    }
    
    public static void displayDate() {
        LOGGER.info("START");
        ApplicationContext context = new ClassPathXmlApplicationContext("date-format.xml");
        SimpleDateFormat format = context.getBean("dateFormat", SimpleDateFormat.class);
        try {
            Date date = format.parse("31/12/2018");
            LOGGER.debug(date.toString());
        } catch (Exception e) {
            LOGGER.error("Error parsing date: " + e.getMessage(), e);
        }
        LOGGER.info("END");
    }
    
    public static void displayCountry() {
        LOGGER.info("START");
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        Country country = context.getBean("in", Country.class);
        Country anotherCountry = context.getBean("in", Country.class);
        LOGGER.debug("Country : {}", country.toString());
        LOGGER.debug("Another Country : {}", anotherCountry.toString());
        LOGGER.debug("Are both references same? : {}", (country == anotherCountry));
        LOGGER.info("END");
    }
    
    public static void displayCountries() {
        LOGGER.info("START");
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        List<Country> countries = context.getBean("countryList", List.class);
        LOGGER.debug("Countries : {}", countries.toString());
        LOGGER.info("END");
    }
}
