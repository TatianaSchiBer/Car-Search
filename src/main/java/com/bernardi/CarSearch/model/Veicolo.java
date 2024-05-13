package com.bernardi.CarSearch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Veicolo(@JsonAlias("Valor") String prezzo, 
						@JsonAlias("Marca") String marca,
						@JsonAlias("Modelo") String modello,
						@JsonAlias("AnoModelo") Integer ano, 
						@JsonAlias("Combustivel") String carburante)



		{

}
