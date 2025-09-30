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
@Table(name = "vehiculo")
public class Vehiculo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer id_vehiculo;
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

	public Vehiculo(Integer id_vehiculo, String placa, String marca, String modelo, Usuario usuario,
			List<HistorialServicios> historialservicios) {
		super();
		this.id_vehiculo = id_vehiculo;
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.usuario = usuario;
		this.historialservicios = historialservicios;
	}

	public Integer getId_vehiculo() {
		return id_vehiculo;
	}

	public void setId_vehiculo(Integer id_vehiculo) {
		this.id_vehiculo = id_vehiculo;
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

	@Override
	public String toString() {
		return "Vehiculo [id_vehiculo=" + id_vehiculo + ", placa=" + placa + ", marca=" + marca + ", modelo=" + modelo
				+ "]";
	}

	

}
