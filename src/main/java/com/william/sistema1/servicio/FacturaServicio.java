package com.william.sistema1.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.william.sistema1.entidad.Factura;
import com.william.sistema1.repositorio.FacturaJpaRepository;

@Service("facturaServicio")
public class FacturaServicio implements EntidadServicio<Factura> {
	
	@Autowired
	@Qualifier("facturaJpaRepository")
	private FacturaJpaRepository facturaJpaRepository;

	@Override
	public List<Factura> all() {
		return facturaJpaRepository.findAll();
	}

	@Override
	public Factura get(int id) {
		return facturaJpaRepository.getOne(id);
	}

	@Override
	public Factura add(Factura entidad) {
		return facturaJpaRepository.save(entidad);
	}

	@Override
	public int remove(int id) {
		facturaJpaRepository.deleteById(id);
		return 0;
	}

	@Override
	public Factura update(Factura entidad) {
		return facturaJpaRepository.save(entidad);
	}
	
	public int getLastRowCodigo() {
		return facturaJpaRepository.getLastRowCodigo();
	}

}
