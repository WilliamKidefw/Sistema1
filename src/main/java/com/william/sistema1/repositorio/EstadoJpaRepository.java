package com.william.sistema1.repositorio;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.william.sistema1.entidad.Estado;

@Repository("estadoJpaRepository")
public interface EstadoJpaRepository extends JpaRepository<Estado,Serializable> {

	List<Estado> findByTabla(String tabla);
}
