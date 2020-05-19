package com.ufpr.es.divresidapi.converter;

public interface ResourceConverter<TMODEL,TDTO> {
	
	TMODEL convertToModel(TDTO dto);
	TDTO convertToDTO(TMODEL model);
	
}
