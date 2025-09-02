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
@Table(name = "Vehiculo")
public class Vehiculo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer id;
	private String placa;
	private String marca;
	private String modelo;
	
	@ManyToOne
	private Usuario usuario;
	@OneToMany(mappedBy = "vehiculo")
	private java.util.List<HistorialServicios> historialservicios;

	public Vehiculo() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Vehiculo [id=" + id + ", placa=" + placa + ", marca=" + marca + ", modelo=" + modelo + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public java.util.List<HistorialServicios> getHistorialservicios() {
		return historialservicios;
	}

	public void setHistorialservicios(java.util.List<HistorialServicios> historialservicios) {
		this.historialservicios = historialservicios;
	}

	public Vehiculo(Integer id, String placa, String marca, String modelo, Usuario usuario,
			List<HistorialServicios> historialservicios) {
		super();
		this.id = id;
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.usuario = usuario;
		this.historialservicios = historialservicios;
	}

}
