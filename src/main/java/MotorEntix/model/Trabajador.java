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
@Table(name = "Trabajador")
public class Trabajador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer id;
	private String especialidad;
	private String horaIngreso;
	private String horaSalida;

	@ManyToOne
	private Usuario usuario;
	@OneToMany(mappedBy = "trabajador")
	private List<Servicio> servicio;

	public Trabajador() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Trabajador(Integer id, String especialidad, String horaIngreso, String horaSalida, Usuario usuario,
			List<Servicio> servicio) {
		super();
		this.id = id;
		this.especialidad = especialidad;
		this.horaIngreso = horaIngreso;
		this.horaSalida = horaSalida;
		this.usuario = usuario;
		this.servicio = servicio;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String getHoraIngreso() {
		return horaIngreso;
	}

	public void setHoraIngreso(String horaIngreso) {
		this.horaIngreso = horaIngreso;
	}

	public String getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Servicio> getServicio() {
		return servicio;
	}

	public void setServicio(List<Servicio> servicio) {
		this.servicio = servicio;
	}

	@Override
	public String toString() {
		return "Trabajador [id=" + id + ", especialidad=" + especialidad + ", horaIngreso=" + horaIngreso
				+ ", horaSalida=" + horaSalida + "]";
	}

}
