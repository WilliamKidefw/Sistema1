package com.william.sistema1.repositorio;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.william.sistema1.entidad.Cliente;

@Repository("clienteJpaRepository")
public interface ClienteJPARepository extends JpaRepository<Cliente,Serializable> {
	
	List<Cliente> findByRucODni(String RucODni);
	List<Cliente> findByNombreCompletoContaining(String nombreCompleto);

}
