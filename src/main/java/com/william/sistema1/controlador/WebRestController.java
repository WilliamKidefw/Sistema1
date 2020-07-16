package com.william.sistema1.controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.william.sistema1.dto.ClienteDTO;
import com.william.sistema1.dto.FacturaDTO;
import com.william.sistema1.entidad.Cliente;
import com.william.sistema1.entidad.DetalleFactura;
import com.william.sistema1.entidad.Estado;
import com.william.sistema1.entidad.Factura;
import com.william.sistema1.entidad.Producto;
import com.william.sistema1.entidad.Proveedor;
import com.william.sistema1.entidad.User;
import com.william.sistema1.mapper.GenericMapper;
import com.william.sistema1.modelo.Identificador;
import com.william.sistema1.modelo.Response;
import com.william.sistema1.servicio.ClienteServicio;
import com.william.sistema1.servicio.DetalleFacturaServicio;
import com.william.sistema1.servicio.EstadoServicio;
import com.william.sistema1.servicio.FacturaServicio;
import com.william.sistema1.servicio.ProductoServicio;
import com.william.sistema1.servicio.ProveedorServicio;
import com.william.sistema1.servicio.UsuarioServicio;

@RestController
public class WebRestController {
	
	private final Logger logger = LoggerFactory.getLogger(WebRestController.class);
	
	@Autowired
    private GenericMapper genericMapper;
	
	@Autowired
	@Qualifier("clienteServicio")
	private ClienteServicio clienteServicio;
	
	@Autowired
	@Qualifier("productoServicio")
	private ProductoServicio productoServicio;
	
	@Autowired
	@Qualifier("proveedorServicio")
	private ProveedorServicio proveedorServicio;
	
	@Autowired
	@Qualifier("facturaServicio")
	private FacturaServicio facturaServicio;
	
	@Autowired
	@Qualifier("detalleFacturaServicio")
	private DetalleFacturaServicio detalleFacturaServicio;
	
	@Autowired
	@Qualifier("estadoServicio")
	private EstadoServicio estadoServicio;
	
	@Autowired
	@Qualifier("usuarioServicio")
	private UsuarioServicio usuarioServicio;
	
	/*Api Usuarios*/
	
	/**
	 * Login
	 * @param username del usuario
	 * @param password del usuario 
	 * @return token de la sección actual
	 * */
	@PostMapping("/auth")
	public Response login(@RequestParam(value="token",defaultValue="-1")String token,
		      			  @RequestParam(value="username",defaultValue="-1")String username) {
		
		if(token!="-1") {
			List<User> usuarios=usuarioServicio.findByUsername(username);
			User current=usuarios.get(0);
			current.setAuthToken(token);
			usuarioServicio.update(current);
			return new Response<>(Response.STATUS_OK,"Bienvenido!");
		}
		return new Response<>(Response.STATUS_ERROR,"El usuario no existe");
	}
	
	/**
	 * Obtener todos los usuario
	 * @return listado de usuario
	 * */
	@GetMapping("/usuarios")
	public Response getUsuarios(@RequestParam(value="token",defaultValue="-1")String token) {		
		if(token!="-1") {				
			return new Response<User>(Response.STATUS_OK, usuarioServicio.findByAuthToken(token));
		}
		return new Response<User>(Response.STATUS_ERROR, "Token incorrecto");
	}
	
	/*Api Clientes*/
	
	/**
	 * Obtener todos los clientes
	 * @return listado de clientes 
	 * */
	@GetMapping("/clientes")
	public Response<ClienteDTO> getClientes(@RequestParam(value="valorBusqueda",defaultValue="-1")String valorBusqueda,
			 					@RequestParam(value="filtro",defaultValue="-1")Integer filtro){	
		logger.trace("getClientes");
		List<Cliente> listClient;
		//buscando filtrando
		if(valorBusqueda!="-1" && filtro!=-1)
			switch(filtro){
				//buscar por nombres
				case 2:
					listClient = clienteServicio.findByNombreCompleto(valorBusqueda);
					return new Response<ClienteDTO>(Response.STATUS_OK,genericMapper.fromToList(listClient, ClienteDTO.class));
				default: //por dni o ruc
					listClient = clienteServicio.findByRucODni(valorBusqueda);
					return new Response<ClienteDTO>(Response.STATUS_OK,genericMapper.fromToList(listClient, ClienteDTO.class));
			}		
		//en otro caso, se obtiene la list completa
		listClient = clienteServicio.all();
		return new Response<ClienteDTO>(Response.STATUS_OK,genericMapper.fromToList(listClient, ClienteDTO.class));
	}
	
	/**
	 * Obtener al cliente por dniORuc
	 * @param rucdni del clente
	 * @return listado de clientes
	 */
	@GetMapping("/clientes/{rucdni}")
	public Response getClientesBy(@PathVariable("rucdni")String rucdni){
		logger.trace("getClientesBy");
		return new Response<Cliente>(Response.STATUS_OK,clienteServicio.findByRucODni(rucdni));
	}
	

	@PostMapping("/clientes")
	public Response addcliente(@RequestBody Cliente cliente) {
		logger.trace("addcliente");
		Cliente clienteResultante=clienteServicio.add(cliente);
		List<Identificador> identificadores=new ArrayList<Identificador>();
		identificadores.add(new Identificador(clienteResultante.getId()));
		return new Response<Identificador>(Response.STATUS_OK,"El cliente se ha agregado con éxito",identificadores);
	}
	

	@PutMapping("/clientes")
	public Response updateProducto(@RequestBody Cliente cliente) {
		logger.trace("updateProducto");
		Integer clienteId= cliente.getId();
		//si el usario a asignado el id
		if(clienteId!=null) {
			clienteServicio.update(cliente);			
			return new Response<Cliente>(Response.STATUS_OK,"El cliente se ha modificado con éxito");
		}	
		return new Response(Response.STATUS_ERROR,"El id es necesario para modificar el cliente"); 
	}
	
	/*Api de productos*/
	
	/**
	 * Obtener todos los productos
	 * @return listado de productos
	 * */
	@GetMapping("/productos")
	public Response getProductos(@RequestParam(value="valorBusqueda",defaultValue="-1")String valorBusqueda,
								 @RequestParam(value="filtro",defaultValue="-1")Integer filtro) {
		logger.trace("getProductos");
			//buscando filtrando
			if(valorBusqueda!="-1" && filtro!=-1)
				switch(filtro){
					//buscar por nombre
					case 2:
						return new Response<Producto>(Response.STATUS_OK,productoServicio.findByNombre(valorBusqueda));					
					default: //por id
						return new Response<Producto>(Response.STATUS_OK,productoServicio.findById(Integer.parseInt(valorBusqueda)));
				}
		//en otro caso, se obtiene la lists completa
		return new Response<Producto>(Response.STATUS_OK,productoServicio.all());
	}
	

	@PostMapping("/productos")
	public Response addProducto(@RequestBody Producto producto) {
		logger.trace("addProducto");
		Producto productoResultante=productoServicio.add(producto);
		List<Identificador> identificadores=new ArrayList<>();
		identificadores.add(new Identificador(productoResultante.getId()));
		return new Response<Identificador>(Response.STATUS_OK,"El producto se ha agregado con éxito",identificadores);
	}

	@PutMapping("/productos")
	public Response updateProducto(@RequestBody Producto producto) {
		logger.trace("updateProducto");
		Integer productoId= producto.getId();
		//si el usario a asignado el id
		if(productoId!=null) {
			productoServicio.update(producto);			
			return new Response<Producto>(Response.STATUS_OK,"El producto se ha modificado con éxito");
		}		
		return new Response<>(Response.STATUS_ERROR,"El id es necesario para modificar el producto"); 
	}

	/*Api Proveedores*/
	
	/**
	 * Obtener todos los clientes
	 * @return listado de clientes 
	 * */
	@GetMapping("/proveedores")
	public Response getProveedores(@RequestParam(value="valorBusqueda",defaultValue="-1")String valorBusqueda,
								 @RequestParam(value="filtro",defaultValue="-1")Integer filtro) {
		logger.info("getProveedores");
			//buscando filtrando
			if(valorBusqueda!="-1" && filtro!=-1)
				switch(filtro){
					//buscar por nombre
					case 2:
						return new Response<Proveedor>(Response.STATUS_OK,proveedorServicio.findByRazonSocial(valorBusqueda));					
					default: //por id
						return new Response<Proveedor>(Response.STATUS_OK,proveedorServicio.findByRuc(valorBusqueda));

				}
			
		//en otro caso, se obtiene la lists completa
		return new Response<Proveedor>(Response.STATUS_OK,proveedorServicio.all());
	}
	
	@PostMapping("/proveedores")
	public Response addProveedor(@RequestBody Proveedor proveedor) {
		logger.trace("addProveedor");
		Proveedor proveedorResultante=proveedorServicio.add(proveedor);
		List<Identificador> identificadores=new ArrayList<>();
		identificadores.add(new Identificador(proveedorResultante.getId()));
		return new Response<Identificador>(Response.STATUS_OK,"El proveedor se ha agregado con éxito",identificadores);
	}

	@PutMapping("/proveedores")
	public Response updateProveedor(@RequestBody Proveedor proveedor) {
		logger.trace("updateProveedor");
		Integer proveedorId= proveedor.getId();
		//si el usario a asignado el id
		if(proveedorId!=null) {
			proveedorServicio.update(proveedor);			
			return new Response<Proveedor>(Response.STATUS_OK,"El proveedor se ha modificado con éxito");
		}
		
		return new Response<>(Response.STATUS_ERROR,"El id es necesario para modificar el proveedor"); 
	}
	
	/*Api de facturas*/
	/**
	 * Obtener todas las facturas
	 * @return listado de facturas
	 * */
	@GetMapping("/facturas")
	public Response<FacturaDTO> getFacturas(@RequestParam(value="valorBusqueda",defaultValue="-1")String valorBusqueda,
								 @RequestParam(value="filtro",defaultValue="-1")Integer filtro) {	
		logger.info("getFacturas");
		List<Factura> facturas;		
		//en otro caso, se obtiene la lists completa
		facturas = facturaServicio.all();
		List<FacturaDTO> feFacturaDTO = genericMapper.fromToList(facturas, FacturaDTO.class);
		return new Response<FacturaDTO>(Response.STATUS_OK,feFacturaDTO);
		
	}
	
	
	/*
	 * @return obtener el último índice generado
	 * */
	@GetMapping("/facturas/ultimo-indice")
	public Response getLastFacturaCode() {
		logger.trace("getLastFacturaCode");
		List<Integer> lastRowCodigo=new ArrayList<>();
		lastRowCodigo.add(facturaServicio.getLastRowCodigo());
		
		return new Response<Integer>(Response.STATUS_OK,lastRowCodigo);
	}
	
	@PostMapping("/facturas")
	public Response addFactura(@RequestBody Factura factura,@RequestParam(name = "clienteId", required = false) int clienteId,
			@RequestParam(name = "detalle", required = false) String detalle) {
		logger.trace("addFactura");
		ObjectMapper objectMapper = new ObjectMapper();
		List<Map<String, Object>> mapdetalle = null;
		try {
			
		mapdetalle = objectMapper.readValue(detalle, new TypeReference<List<Map<String, Object>>>(){});
		
		List<DetalleFactura> result = mapdetalle.stream().map(detail -> {
			DetalleFactura obj = new DetalleFactura();
            obj.setCantidad(Integer.parseInt(detail.get("cantidad").toString()));
            obj.setPrecio(Float.parseFloat(detail.get("precioVenta").toString()));
            obj.setProducto(productoServicio.get(Integer.parseInt(detail.get("id").toString())));
            return obj;
        }).collect(Collectors.toList());
		
		factura.setCliente(clienteServicio.get(clienteId));
		factura.setItems(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Factura facturaResultante=facturaServicio.add(factura);	
		List<Identificador> identificadores=new ArrayList<>();
		identificadores.add(new Identificador(facturaResultante.getId()));
		return new Response<Identificador>(Response.STATUS_OK,"La factura se ha agregado con éxito",identificadores);
	}
	
	/**
	 * Obtener todos los detalle de factura de venta
	 * @return listado de detalle de factura de venta
	 * */
	@GetMapping("/facturas/{facturaId}/detalle")
	public Response getFacturaVentaDetalle(@PathVariable("facturaId")int facturaId) {	
		logger.trace("getFacturaVentaDetalle");
		return new Response<DetalleFactura>(Response.STATUS_OK,detalleFacturaServicio.findByFacturaId(facturaId));
	}
	
	/**
	 * registrar detalle de factura
	 * @param id de la factura
	 * */
	//forzar a ser detalle para factura
	@PostMapping("/facturas/{facturaId}/detalle")
	public Response addFacturaDetalle(@PathVariable("facturaId")int facturaId,@RequestBody DetalleFactura detalleVenta) {
		logger.trace("addFacturaDetalle");

		//vamos a restarle el stock
		Producto producto=productoServicio.findById(detalleVenta.getProducto().getId()).get(0);
		producto.setStock(producto.getStock()-detalleVenta.getCantidad());
		productoServicio.update(producto);
		
		//registrar detalle venta
		DetalleFactura detalleVentaResultante=detalleFacturaServicio.add(detalleVenta);
		List<Identificador> identificadores=new ArrayList<>();
		identificadores.add(new Identificador(detalleVentaResultante.getId()));
		return new Response<Identificador>(Response.STATUS_OK,"El detalle venta se ha agregado con en el nota venta éxito",identificadores);
	}
	
	/*Api Estados*/
	
	/*
	 * Obtener todos los estados
	 * @return listado de estado 
	 * */
	@GetMapping("/estados")
	public Response<Estado> getEstados(){
		return new Response<Estado>(Response.STATUS_OK,estadoServicio.all());
	}

	@GetMapping("/estados/{tabla}")
	public Response<Estado> getEstados(@PathVariable("tabla")String tabla){
		return new Response<Estado>(Response.STATUS_OK,estadoServicio.getByTabla(tabla));
	}

}
