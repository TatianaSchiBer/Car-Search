package com.bernardi.CarSearch.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

public class ConverteDati implements IConverteDati{

	private ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public <T> T getData(String json, Class<T> classe) {
		
		
		try {
			return mapper.readValue(json, classe);
			
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		
		
	}

	 @Override
	    public <T> List<T> getList(String json, Class<T> classe) {
	        CollectionType lista = mapper.getTypeFactory()
	            .constructCollectionType(List.class, classe);
	        
	        try {
	            return mapper.readValue(json, lista);
	        } catch (JsonProcessingException e) {
	            throw new RuntimeException(e);
	        }
	}

}
