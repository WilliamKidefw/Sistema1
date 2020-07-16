package com.william.sistema1.servicio;

import java.util.List;

public interface EntidadServicio<T> {
	
	public abstract List<T> all();
	public abstract T get(int id);
	public abstract T add(T entidad);
	public abstract int remove(int id);
	public abstract T update(T entidad);
		
}
