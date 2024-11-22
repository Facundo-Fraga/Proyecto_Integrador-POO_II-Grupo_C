package edu.unam.ecomarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})  
public class EcoMarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcoMarketApplication.class, args);
    }
}
