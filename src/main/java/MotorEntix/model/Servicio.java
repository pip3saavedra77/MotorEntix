package MotorEntix.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "servicio")

public class Servicio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer id;
	private String nombre;
	private String descripcion;
	private String precioBase;

	@ManyToOne
	private Trabajador trabajador;

	@OneToMany(mappedBy = "servicio")
	private List<HistorialServicios> historialservicios;

	@OneToMany(mappedBy = "servicio")
	private List<Reserva> reserva;

	@OneToMany(mappedBy = "servicio")
	private List<ServicioRepuestos> serviciorepuestos;

	@Override
	public String toString() {
		return "Servicio [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precioBase="
				+ precioBase + "]";
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPrecioBase() {
		return precioBase;
	}

	public void setPrecioBase(String precioBase) {
		this.precioBase = precioBase;
	}

	public Trabajador getTrabajador() {
		return trabajador;
	}

	public void setTrabajador(Trabajador trabajador) {
		this.trabajador = trabajador;
	}

	public List<HistorialServicios> getHistorialservicios() {
		return historialservicios;
	}

	public void setHistorialservicios(List<HistorialServicios> historialservicios) {
		this.historialservicios = historialservicios;
	}

	public List<Reserva> getReserva() {
		return reserva;
	}

	public void setReserva(List<Reserva> reserva) {
		this.reserva = reserva;
	}

	public List<ServicioRepuestos> getServiciorepuestos() {
		return serviciorepuestos;
	}

	public void setServiciorepuestos(List<ServicioRepuestos> serviciorepuestos) {
		this.serviciorepuestos = serviciorepuestos;
	}

	public Servicio(Integer id, String nombre, String descripcion, String precioBase, Trabajador trabajador,
			List<HistorialServicios> historialservicios, List<Reserva> reserva,
			List<ServicioRepuestos> serviciorepuestos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precioBase = precioBase;
		this.trabajador = trabajador;
		this.historialservicios = historialservicios;
		this.reserva = reserva;
		this.serviciorepuestos = serviciorepuestos;
	}

	public Servicio() {
		super();
		// TODO Auto-generated constructor stub
	}

}
