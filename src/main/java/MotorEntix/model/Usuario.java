package MotorEntix.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer id;
	private String nombre;
	private String apellido;
	private String correo;
	private String direccion;
	private String telefono;
	private String contraseña;
	private String estado;
	private String rol;

	@OneToMany(mappedBy = "usuario")
	private java.util.List<Vehiculo> vehiculo;
	@OneToMany(mappedBy = "usuario")
	private java.util.List<Factura> factura;
	@OneToMany(mappedBy = "usuario")
	private java.util.List<Reserva> reserva;
	@OneToMany(mappedBy = "usuario")
	private java.util.List<Trabajador> trabajador;
	@OneToMany(mappedBy = "usuario")
	private java.util.List<Notificacion> notificacion;

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", correo=" + correo
				+ ", direccion=" + direccion + ", telefono=" + telefono + ", contraseña=" + contraseña + ", estado="
				+ estado + ", rol=" + rol + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public java.util.List<Vehiculo> getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(java.util.List<Vehiculo> vehiculo) {
		this.vehiculo = vehiculo;
	}

	public java.util.List<Factura> getFactura() {
		return factura;
	}

	public void setFactura(java.util.List<Factura> factura) {
		this.factura = factura;
	}

	public java.util.List<Reserva> getReserva() {
		return reserva;
	}

	public void setReserva(java.util.List<Reserva> reserva) {
		this.reserva = reserva;
	}

	public java.util.List<Trabajador> getTrabajador() {
		return trabajador;
	}

	public void setTrabajador(java.util.List<Trabajador> trabajador) {
		this.trabajador = trabajador;
	}

	public java.util.List<Notificacion> getNotificacion() {
		return notificacion;
	}

	public void setNotificacion(java.util.List<Notificacion> notificacion) {
		this.notificacion = notificacion;
	}

	public Usuario(Integer id, String nombre, String apellido, String correo, String direccion, String telefono,
			String contraseña, String estado, String rol, List<Vehiculo> vehiculo, List<Factura> factura,
			List<Reserva> reserva, List<Trabajador> trabajador, List<Notificacion> notificacion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.direccion = direccion;
		this.telefono = telefono;
		this.contraseña = contraseña;
		this.estado = estado;
		this.rol = rol;
		this.vehiculo = vehiculo;
		this.factura = factura;
		this.reserva = reserva;
		this.trabajador = trabajador;
		this.notificacion = notificacion;
	}

	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

}
