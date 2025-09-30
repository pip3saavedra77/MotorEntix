package MotorEntix.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "serviciorepuestos")

public class ServicioRepuestos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer id;
	private Integer cantidad;

	@ManyToOne
	private Inventario inventario;

	@ManyToOne
	private Servicio servicio;

	public ServicioRepuestos(Integer id, Integer cantidad, Inventario inventario, Servicio servicio) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.inventario = inventario;
		this.servicio = servicio;
	}

	public ServicioRepuestos() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Inventario getInventario() {
		return inventario;
	}

	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	@Override
	public String toString() {
		return "ServicioRepuestos [id=" + id + ", cantidad=" + cantidad + "]";
	}

}
