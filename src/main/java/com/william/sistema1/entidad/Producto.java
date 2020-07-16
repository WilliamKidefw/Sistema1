package com.william.sistema1.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="producto")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idproducto")
	private int id;
	
	@Column(name="descripcion")	
	private String descripcion;
	
	@Column(name="preciocompra")	
	private float precioCompra;
	
	@Column(name="precioventa")	
	private float precioVenta;
	
	@Column(name="stock")	
	private float stock;
	
	@Column(name="estado")
	private int estado;
	
	/*Constructors*/	
	public Producto() {
		
	}
	
	
	/**
	 * @param descripcion
	 * @param precioCompra
	 * @param precioVenta
	 * @param stock
	 */
	public Producto(String descripcion, float precioCompra, float precioVenta, float stock) {
		super();
		this.descripcion = descripcion;
		this.precioCompra = precioCompra;
		this.precioVenta = precioVenta;
		this.stock = stock;
	}

	/**
	 * @param id
	 * @param descripcion
	 * @param precioCompra
	 * @param precioVenta
	 * @param stock
	 */
	public Producto(int id, String descripcion, float precioCompra, float precioVenta, float stock) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.precioCompra = precioCompra;
		this.precioVenta = precioVenta;
		this.stock = stock;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(float precioCompra) {
		this.precioCompra = precioCompra;
	}

	public float getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(float precioVenta) {
		this.precioVenta = precioVenta;
	}

	public float getStock() {
		return stock;
	}

	public void setStock(float stock) {
		this.stock = stock;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	
	
}
