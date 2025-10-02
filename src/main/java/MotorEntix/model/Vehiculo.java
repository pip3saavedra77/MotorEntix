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
	private String anio;
	private String version;
	private String tipoVehiculo;
	private String transmicion;
	private String combustible;
	private String color;
	private String estadoVehiculo;
	private String descripcion;

	@ManyToOne
	private Usuario usuario;
	@OneToMany(mappedBy = "vehiculo")
	private java.util.List<HistorialServicios> historialservicios;

	/**
	 * 
	 */
	public Vehiculo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id_vehiculo
	 * @param placa
	 * @param marca
	 * @param modelo
	 * @param anio
	 * @param version
	 * @param tipoVehiculo
	 * @param transmicion
	 * @param combustible
	 * @param color
	 * @param estadoVehiculo
	 * @param descripcion
	 * @param usuario
	 * @param historialservicios
	 */
	public Vehiculo(Integer id_vehiculo, String placa, String marca, String modelo, String anio, String version,
			String tipoVehiculo, String transmicion, String combustible, String color, String estadoVehiculo,
			String descripcion, Usuario usuario, List<HistorialServicios> historialservicios) {
		super();
		this.id_vehiculo = id_vehiculo;
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.anio = anio;
		this.version = version;
		this.tipoVehiculo = tipoVehiculo;
		this.transmicion = transmicion;
		this.combustible = combustible;
		this.color = color;
		this.estadoVehiculo = estadoVehiculo;
		this.descripcion = descripcion;
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

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public String getTransmicion() {
		return transmicion;
	}

	public void setTransmicion(String transmicion) {
		this.transmicion = transmicion;
	}

	public String getCombustible() {
		return combustible;
	}

	public void setCombustible(String combustible) {
		this.combustible = combustible;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getEstadoVehiculo() {
		return estadoVehiculo;
	}

	public void setEstadoVehiculo(String estadoVehiculo) {
		this.estadoVehiculo = estadoVehiculo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
				+ ", anio=" + anio + ", version=" + version + ", tipoVehiculo=" + tipoVehiculo + ", transmicion="
				+ transmicion + ", combustible=" + combustible + ", color=" + color + ", estadoVehiculo="
				+ estadoVehiculo + ", descripcion=" + descripcion + "]";
	}

}
