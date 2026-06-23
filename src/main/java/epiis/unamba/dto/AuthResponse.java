package epiis.unamba.dto;

import java.util.Date;

public class AuthResponse {
	private String token;
	private String tipo = "Bearer";
	private String username;
	private String rol;
	private Date fechaEmision;
	private Date fechaExpiracion;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public Date getFechaExpiracion() {
		return fechaExpiracion;
	}

	public void setFechaExpiracion(Date fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}

	public AuthResponse(String token, String username, String rol, Date fechaEmision, Date fechaExpiracion) {
		this.token = token;
		this.username = username;
		this.rol = rol;
		this.fechaEmision = fechaEmision;
		this.fechaExpiracion = fechaExpiracion;
	}
	
}
