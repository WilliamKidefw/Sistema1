package com.william.sistema1.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class WebViewController {

	private final String HOME_VIEW="dashboard",
			LOGIN_VIEW="login",
			SIGN_UP_VIEW="sign_up",
			PERFIL_USUARIO_VIEW="user_profile",
			REGISTRO_PRODUCTO_VIEW="registro_producto",
			REPORTE_PRODUCTO_VIEW="reporte_producto",
			REPORTE_CLIENTE_VIEW="reporte_cliente",
			REGISTRO_CLIENTE_VIEW="registro_cliente",
			REGISTRO_PROVEEDOR_VIEW="registro_proveedor",
			REGISTRO_VENTA_VIEW="registro_venta",
			GENERAR_FACTURA_VIEW="generar_factura";
	
	@GetMapping("/dashboard")
	public String home() {
		return HOME_VIEW;
	}
	
	@GetMapping("/login")
	public String login() {
		return LOGIN_VIEW;
	}

	@GetMapping("/sign_up")
	public String signUp() {
		return SIGN_UP_VIEW;
	}
	
	@GetMapping("/registro_producto")
	public String registroProducto() {
		return REGISTRO_PRODUCTO_VIEW;
	}
	
	@GetMapping("/reporte_producto")
	public String reporteProducto() {
		return REPORTE_PRODUCTO_VIEW;
	}
	
	@GetMapping("/registro_cliente")
	public String registroCliente() {
		return REGISTRO_CLIENTE_VIEW;
	}
	
	@GetMapping("/registro_proveedor")
	public String registroProvedor() {
		return REGISTRO_PROVEEDOR_VIEW;
	}
	
	@GetMapping("/reporte_cliente")
	public String reporteCliente() {
		return REPORTE_CLIENTE_VIEW;
	}
	
	@GetMapping("/registro_venta")
	public String registroVenta() {
		return REGISTRO_VENTA_VIEW;
	}
	
	@GetMapping("/generar_factura")
	public String generarFactura() {
		return GENERAR_FACTURA_VIEW;
	}
	
	@GetMapping("/user_profile")
	public String perfilUsuario() {
		return PERFIL_USUARIO_VIEW;
	}
}
