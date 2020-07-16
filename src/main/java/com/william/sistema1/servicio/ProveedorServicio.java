package com.william.sistema1.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.william.sistema1.entidad.Proveedor;
import com.william.sistema1.repositorio.ProveedorJpaRepository;

@Service("proveedorServicio")
public class ProveedorServicio implements EntidadServicio<Proveedor>{
	
	@Autowired
	@Qualifier("proveedorJpaRepository")
	private ProveedorJpaRepository proveedorJpaRepository;

	@Override
	public List<Proveedor> all() {
		return proveedorJpaRepository.findAll();
	}

	@Override
	public Proveedor get(int id) {
		return proveedorJpaRepository.getOne(id);
	}

	@Override
	public Proveedor add(Proveedor entidad) {
		return proveedorJpaRepository.save(entidad);
	}

	@Override
	public int remove(int id) {
		proveedorJpaRepository.deleteById(id);
		return 0;
	}

	@Override
	public Proveedor update(Proveedor entidad) {
		return proveedorJpaRepository.save(entidad);
	}

	
	public List<Proveedor> findByRuc(String ruc){
		return proveedorJpaRepository.findByRuc(ruc);
	};
	
	
	public List<Proveedor> findByRazonSocial(String razonSocial){
		return proveedorJpaRepository.findByRazonSocialContaining(razonSocial);
	};
	
}
