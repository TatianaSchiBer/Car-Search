package com.bernardi.CarSearch.principal;

import java.util.Comparator;
import java.util.Scanner;

import com.bernardi.CarSearch.model.Dati;
import com.bernardi.CarSearch.model.Modelli;
import com.bernardi.CarSearch.service.ConsumoAPI;
import com.bernardi.CarSearch.service.ConverteDati;

public class Principal {

	private Scanner scanner = new Scanner(System.in);
	
	private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";
	

	private ConsumoAPI consumo = new ConsumoAPI();
	
	private ConverteDati convertitore = new ConverteDati();
	
	
	public void Menu() {
		
	
		var menu = """
				***OPZIONI***
				   AUTO
				   MOTOCICLETTE
				   CAMION
				*************
				Inserire il tipo di veicolo da consultare:
				""";
		
		System.out.println(menu);
		
		var opzione = scanner.nextLine();
		
		String indirizzo;
		
		if(opzione.toLowerCase().contains("aut")) {
			indirizzo = URL_BASE + "carros/marcas";
		}else if (opzione.toLowerCase().contains("mot")) {
			indirizzo = URL_BASE + "motos/marcas";
		}else {
			indirizzo = URL_BASE + "caminhoes/marcas";
		}
		
		var json = consumo.getData(indirizzo);
		System.out.println(json);
		
		var brands = convertitore.getList(json, Dati.class);
		brands.stream()
				.sorted(Comparator.comparing(Dati::codice))
				.forEach(System.out::println);
		
		System.out.println("\nInserire il codice del marchio per la consultazione: ");
		var codiceMarca = scanner.nextLine();
		
		indirizzo = indirizzo + "/" + codiceMarca + "/modelos";
		
		json = consumo.getData(indirizzo);
		var modelloLista = convertitore.getData(json, Modelli.class);
		
		System.out.println("\nModelli di questa marca: ");
		
		modelloLista.modelli().stream()
						.sorted(Comparator.comparing(Dati::codice))
						.forEach(System.out::println);
		
	}
}
