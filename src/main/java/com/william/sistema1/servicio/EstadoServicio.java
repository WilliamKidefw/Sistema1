package com.william.sistema1.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.william.sistema1.entidad.Estado;
import com.william.sistema1.repositorio.EstadoJpaRepository;

@Service("estadoServicio")
public class EstadoServicio implements EntidadServicio<Estado> {
	
	@Autowired
	@Qualifier("estadoJpaRepository")
	private EstadoJpaRepository estadoJpaRepository;

	@Override
	public List<Estado> all() {
		return estadoJpaRepository.findAll();
	}

	@Override
	public Estado get(int id) {
		return estadoJpaRepository.getOne(id);
	}

	@Override
	public Estado add(Estado entidad) {
		return estadoJpaRepository.save(entidad);
	}

	@Override
	public int remove(int id) {
		estadoJpaRepository.deleteById(id);
		return 0;
	}

	@Override
	public Estado update(Estado entidad) {
		return estadoJpaRepository.save(entidad);
	}
	
	public List<Estado> getByTabla(String tabla){
		return estadoJpaRepository.findByTabla(tabla);
	}

}
