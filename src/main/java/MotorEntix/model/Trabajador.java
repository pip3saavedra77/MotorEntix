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

	private Integer id_trabajador;
	private String especialidad;
	private String hora_Ingreso;
	private String hora_Salida;

	@ManyToOne
	private Usuario usuario;
	@OneToMany(mappedBy = "trabajador")
	private List<Servicio> servicio;

	public Trabajador() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Trabajador(Integer id_trabajador, String especialidad, String hora_Ingreso, String hora_Salida,
			Usuario usuario, List<Servicio> servicio) {
		super();
		this.id_trabajador = id_trabajador;
		this.especialidad = especialidad;
		this.hora_Ingreso = hora_Ingreso;
		this.hora_Salida = hora_Salida;
		this.usuario = usuario;
		this.servicio = servicio;
	}

	public Integer getId_trabajador() {
		return id_trabajador;
	}

	public void setId_trabajador(Integer id_trabajador) {
		this.id_trabajador = id_trabajador;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String getHora_Ingreso() {
		return hora_Ingreso;
	}

	public void setHora_Ingreso(String hora_Ingreso) {
		this.hora_Ingreso = hora_Ingreso;
	}

	public String getHora_Salida() {
		return hora_Salida;
	}

	public void setHora_Salida(String hora_Salida) {
		this.hora_Salida = hora_Salida;
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
		return "Trabajador [id_trabajador=" + id_trabajador + ", especialidad=" + especialidad + ", hora_Ingreso="
				+ hora_Ingreso + ", hora_Salida=" + hora_Salida + "]";
	}

}
