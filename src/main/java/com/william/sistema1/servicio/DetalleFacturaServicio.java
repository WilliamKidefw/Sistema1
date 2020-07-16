package com.william.sistema1.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.william.sistema1.entidad.DetalleFactura;
import com.william.sistema1.entidad.Factura;
import com.william.sistema1.repositorio.DetalleFacturaJpaRepository;

@Service("detalleFacturaServicio")
public class DetalleFacturaServicio implements EntidadServicio<DetalleFactura> {

	@Autowired
	@Qualifier("detalleFacturaJpaRepository")
	private DetalleFacturaJpaRepository detalleFacturaJpaRepository;
	
	@Override
	public List<DetalleFactura> all() {
		return detalleFacturaJpaRepository.findAll();
	}

	@Override
	public DetalleFactura get(int id) {
		return detalleFacturaJpaRepository.getOne(id);
	}

	@Override
	public DetalleFactura add(DetalleFactura entidad) {
		return detalleFacturaJpaRepository.save(entidad);
	}
	
	@Override
	public int remove(int id) {
		detalleFacturaJpaRepository.deleteById(id);
		return 0;
	}

	@Override
	public DetalleFactura update(DetalleFactura entidad) {
		return detalleFacturaJpaRepository.save(entidad);
	}

	public List<DetalleFactura> findByFacturaId(int facturaid){
		return  detalleFacturaJpaRepository.findByFacturaId(facturaid);
	}
}
