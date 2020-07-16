package com.william.sistema1.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.william.sistema1.entidad.Producto;
import com.william.sistema1.repositorio.ProductoJpaRepository;

@Service("productoServicio")
public class ProductoServicio implements EntidadServicio<Producto> {
	
	@Autowired
	@Qualifier("productoJpaRepository")
	private ProductoJpaRepository productoJpaRepository;
	
	@Override
	public List<Producto> all() {
		return productoJpaRepository.findAll();
	}

	@Override
	public Producto get(int id) {
		return productoJpaRepository.getOne(id);
	}

	@Override
	public Producto add(Producto producto) {
		return productoJpaRepository.save(producto);
	}

	@Override
	public int remove(int id) {
		productoJpaRepository.deleteById(id);
		return 0;
	}

	@Override
	public Producto update(Producto producto) {
		return productoJpaRepository.save(producto);
	}
	
	public List<Producto> findById(int id){
		return productoJpaRepository.findById(id);
	}
	
	public List<Producto> findByNombre(String nombre){
		return productoJpaRepository.findByDescripcionContaining(nombre);
	}

}
