package com.bestofyear.best_of_the_year;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*Questa annotazione dice a Spring:"Ehi, questa è la classe principale, con questa parte inizi tutto il progetto Spring Boot". Include tante configurazioni automatiche (auto-configuration) per semplificarti la vita */
@SpringBootApplication
public class BestOfTheYearApplication {

	public static void main(String[] args) {
		SpringApplication.run(BestOfTheYearApplication.class, args);
	}

}
