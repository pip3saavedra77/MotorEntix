
package MotorEntix.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Notificacion")
public class Notificacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer id;
	private String mensaje;
	private String fechaEnvio;
	private String tipo;

	@ManyToOne
	private Usuario usuario;

	@Override
	public String toString() {
		return "Notificacion [id=" + id + ", mensaje=" + mensaje + ", fechaEnvio=" + fechaEnvio + ", tipo=" + tipo
				+ "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getFechaEnvio() {
		return fechaEnvio;
	}

	public void setFechaEnvio(String fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Notificacion(Integer id, String mensaje, String fechaEnvio, String tipo, Usuario usuario) {
		super();
		this.id = id;
		this.mensaje = mensaje;
		this.fechaEnvio = fechaEnvio;
		this.tipo = tipo;
		this.usuario = usuario;
	}

	public Notificacion() {
		super();
		// TODO Auto-generated constructor stub
	}

}
