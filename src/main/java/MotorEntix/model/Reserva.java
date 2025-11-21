package MotorEntix.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "Reserva")
public class Reserva {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_reserva")
	private Integer id;
	@Column(name = "fecha_reserva")
	private String fechaReserva;
	@Column(name = "hora_reserva")
	private String horaReserva;
	@Column(name = "id_cliente")
	private Integer idCliente;
	private String estado;

	@ManyToOne
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "id_trabajador", nullable = true)
	private Trabajador trabajador;

	@ManyToOne
	@JoinColumn(name = "id_vehiculo", nullable = true)
	private Vehiculo vehiculo;
	@ManyToOne
	@JoinColumn(name = "id_servicio")
	private Servicio servicio;

	public Reserva() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reserva(Integer id, String fechaReserva, String horaReserva, String estado, Usuario usuario,
			Servicio servicio) {
		super();
		this.id = id;
		this.fechaReserva = fechaReserva;
		this.horaReserva = horaReserva;
		this.estado = estado;
		this.usuario = usuario;
		this.servicio = servicio;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(String fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public String getHoraReserva() {
		return horaReserva;
	}

	public void setHoraReserva(String horaReserva) {
		this.horaReserva = horaReserva;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Trabajador getTrabajador() {
		return trabajador;
	}

	public void setTrabajador(Trabajador trabajador) {
		this.trabajador = trabajador;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	@Override
	public String toString() {
		return "Reserva [id=" + id + ", fechaReserva=" + fechaReserva + ", horaReserva=" + horaReserva + ", estado="
				+ estado + "]";
	}

}
