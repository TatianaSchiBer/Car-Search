package com.bernardi.CarSearch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bernardi.CarSearch.principal.Principal;

@SpringBootApplication
public class CarSearchApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(CarSearchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
		Principal principal = new Principal();
		principal.Menu();
	
	
	
	
	
	}

}
