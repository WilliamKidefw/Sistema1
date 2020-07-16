package com.william.sistema1.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.william.sistema1.entidad.Cliente;
import com.william.sistema1.repositorio.ClienteJPARepository;

@Service
public class ClienteServicio implements EntidadServicio<Cliente> {
	
	@Autowired
	//@Qualifier("clienteJpaRepository")
	private ClienteJPARepository clienteJpaRepository;

	@Override
	public List<Cliente> all() {
		return clienteJpaRepository.findAll();
	}

	@Override
	public Cliente get(int id) {
		return clienteJpaRepository.getOne(id);
	}

	@Override
	public Cliente add(Cliente cliente) {
		return clienteJpaRepository.save(cliente);
	}

	@Override
	public int remove(int id) {
		clienteJpaRepository.deleteById(id);
		return 0;
	}

	@Override
	public Cliente update(Cliente cliente) {
		return clienteJpaRepository.save(cliente);
	}
	
	public List<Cliente> findByRucODni(String rucODni) {
		return clienteJpaRepository.findByRucODni(rucODni);
	}

	
	public List<Cliente> findByNombreCompleto(String nombre){
		return clienteJpaRepository.findByNombreCompletoContaining(nombre);
	}



}
