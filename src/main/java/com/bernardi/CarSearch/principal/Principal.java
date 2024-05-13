package com.bernardi.CarSearch.principal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.bernardi.CarSearch.model.Dati;
import com.bernardi.CarSearch.model.Modelli;
import com.bernardi.CarSearch.model.Veicolo;
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
		
		System.out.println("\nDigitare un pezzo del nome del veicolo che si desidera cercare:");
		
		var nomeVeicolo = scanner.nextLine();
		
		List<Dati> modelliFiltrati = modelloLista.modelli().stream()
						.filter(m -> m.nome().toLowerCase().contains(nomeVeicolo.toLowerCase()))
						.collect(Collectors.toList());
		
		System.out.println("\nModelli Filtrati: ");
		modelliFiltrati.forEach(System.out::println);
		
		System.out.println("\ninserire il codice del modello da consultare: ");
		
		var codiceModello = scanner.nextLine();
		
		indirizzo = indirizzo + "/" + codiceModello + "/anos";
		json = consumo.getData(indirizzo);
		
		List<Dati> anni = convertitore.getList(json, Dati.class);
		List<Veicolo> veicoli = new ArrayList<>();
		
		for(int i = 0; i < anni.size(); i++) {
			var indirizzoAnni = indirizzo + "/" + anni.get(i).codice();
			json = consumo.getData(indirizzoAnni);
			Veicolo veicolo = convertitore.getData(json, Veicolo.class);
			veicoli.add(veicolo);
			
		
		}
		System.out.println("Ecco l'elenco con il modello di veicolo per anno: ");
		veicoli.forEach(System.out::println);
		
		
		
	}
}
