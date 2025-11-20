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
@Table(name = "inventario")

public class Inventario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@jakarta.persistence.Column(name = "id_repuesto")
	private Integer id;

	private String nombre; // misma columna en BD

	@jakarta.persistence.Column(name = "cantidad_stock")
	private Integer cantidad;

	@jakarta.persistence.Column(name = "precio_unitario")
	private String precioUnitario;

	@ManyToOne
	@jakarta.persistence.JoinColumn(name = "id_proveedor")
	private Proveedor proveedor;

	@OneToMany(mappedBy = "inventario")
	private List<ServicioRepuestos> serviciorepuestos;

	@Override
	public String toString() {
		return "Inventario [id=" + id + ", nombre=" + nombre + ", cantidad=" + cantidad + ", precioUnitario="
				+ precioUnitario + "]";
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

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public String getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(String precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public List<ServicioRepuestos> getServiciorepuestos() {
		return serviciorepuestos;
	}

	public void setServiciorepuestos(List<ServicioRepuestos> serviciorepuestos) {
		this.serviciorepuestos = serviciorepuestos;
	}

	public Inventario(Integer id, String nombre, Integer cantidad, String precioUnitario, Proveedor proveedor,
			List<ServicioRepuestos> serviciorepuestos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.precioUnitario = precioUnitario;
		this.proveedor = proveedor;
		this.serviciorepuestos = serviciorepuestos;
	}

	public Inventario() {
		super();
		// TODO Auto-generated constructor stub
	}

}
