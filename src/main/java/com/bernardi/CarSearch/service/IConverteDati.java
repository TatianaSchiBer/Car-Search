package com.bernardi.CarSearch.service;

import java.util.List;

public interface IConverteDati {
	
	<T> T getData(String json, Class<T> classe);
	
	<T> List<T>	getList (String json, Class<T> classe);

}
