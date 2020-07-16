package com.william.sistema1.repositorio;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.william.sistema1.entidad.Producto;

@Repository("productoJpaRepository")
public interface ProductoJpaRepository extends JpaRepository<Producto, Serializable> {

	List<Producto> findById(int id);
	List<Producto> findByDescripcionContaining(String descripcion);
}
