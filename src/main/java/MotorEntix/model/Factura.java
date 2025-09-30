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
@Table(name = "factura")
public class Factura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer id;
	private String fecha;
	private String total;

	@ManyToOne
	private Usuario usuario;
	@OneToMany(mappedBy = "factura")
	private List<HistorialServicios> historialservicios;

	public Factura(Integer id, String fecha, String total, Usuario usuario,
			List<HistorialServicios> historialservicios) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.total = total;
		this.usuario = usuario;
		this.historialservicios = historialservicios;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<HistorialServicios> getHistorialservicios() {
		return historialservicios;
	}

	public void setHistorialservicios(List<HistorialServicios> historialservicios) {
		this.historialservicios = historialservicios;
	}

	@Override
	public String toString() {
		return "Factura [id=" + id + ", fecha=" + fecha + ", total=" + total + "]";
	}

}