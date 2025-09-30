package MotorEntix.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "historialServicios")
public class HistorialServicios {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer id;
	private String fechaInicio;
	private String fechaFin;
	private String diagnostico;
	private String observaciones;
	private String costoTotal;

	@ManyToOne
	private Factura factura;
	
	
	@ManyToOne
	private Vehiculo vehiculo;
	@ManyToOne
	private Servicio servicio;

	@Override
	public String toString() {
		return "HistorialServicios [id=" + id + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin
				+ ", diagnostico=" + diagnostico + ", observaciones=" + observaciones + ", costoTotal=" + costoTotal
				+ "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getCostoTotal() {
		return costoTotal;
	}

	public void setCostoTotal(String costoTotal) {
		this.costoTotal = costoTotal;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
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

	public HistorialServicios(Integer id, String fechaInicio, String fechaFin, String diagnostico, String observaciones,
			String costoTotal, Factura factura, Vehiculo vehiculo, Servicio servicio) {
		super();
		this.id = id;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.diagnostico = diagnostico;
		this.observaciones = observaciones;
		this.costoTotal = costoTotal;
		this.factura = factura;
		this.vehiculo = vehiculo;
		this.servicio = servicio;
	}

	public HistorialServicios() {
		super();
		// TODO Auto-generated constructor stub
	}

}
