package com.william.sistema1.repositorio;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.william.sistema1.entidad.Factura;

@Repository("facturaJpaRepository")
public interface FacturaJpaRepository extends JpaRepository<Factura,Serializable>{

	@Query(value="SELECT idfactura FROM factura GROUP BY idfactura DESC LIMIT 1",nativeQuery = true)
	int getLastRowCodigo();
}
