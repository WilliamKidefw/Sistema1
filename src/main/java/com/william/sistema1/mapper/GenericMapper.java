package com.william.sistema1.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenericMapper  {

	@Autowired
    private ModelMapper modelMapper;
	
	public <T, U> T convertToDto(U entity, Class<T> valueType ) {
		T entidadDto = modelMapper.map(entity, valueType );
	    return entidadDto;
	}
	
	public <T, U> U convertToEntity(T entity, Class<U> valueType ) {
		U entidad = modelMapper.map(entity, valueType );
	    return entidad;
	}
	
	/*public <T> EntityDTO convertToDto(T entidad, EntityDTO mapper) {
    return modelMapper.map(entidad, mapper.getClass());
  	}*/

	/*public <T> T convertToEntity(Class<T> valueType,EntityDTO mapper) {
		T entidad = modelMapper.map(mapper, valueType);
		return entidad;
	}*/
	
	public <T, U> List<T> fromToList(List<U> list,Class<T> valueType) {   
	    return list.stream().map(entity -> convertToDto(entity,(Class<T>) valueType)).collect(Collectors.toList());
	}

	
}
