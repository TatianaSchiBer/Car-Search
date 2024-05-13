package com.bernardi.CarSearch.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record Dati(@JsonAlias("codigo") String codice, String nome) {

}
