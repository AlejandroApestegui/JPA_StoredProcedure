package pe.edu.cibertec.proyemp.jpa.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedQueries({ @NamedQuery(name = "Empleado.getAll", query = "select e from Empleado e"),
		@NamedQuery(name = "Empleado.getPorDepNombre", query = "select e from Empleado e where e.departamento.nombre = :dep") })

@Entity
@Table(name = "tb_empleado")
public class Empleado {

	@Id
	@GeneratedValue
	@Column(name = "EMP_ID", nullable = false, unique = true)
	private Long id;

	@Column(name = "EMP_NOMBRE", nullable = false, length = 200)
	private String nombre;

	@ManyToOne
	@JoinColumn(name = "DEP_ID")
	private Departamento departamento;
	// en bd es departamento_id

	@Column(name = "EMP_SALARIO", precision = 10, scale = 2)
	private BigDecimal salario;

	@Column(name = "EMP_FEC_ING")
	@Temporal(TemporalType.DATE)
	private Date fechaIngreso;

	@Column(name = "EMP_DNI", columnDefinition = "CHAR(8)")
	private String dni;

	public Empleado() {
	}

	public Empleado(String nombre) {
		super();
		this.nombre = nombre;
	}

	public Empleado(String nombre, Departamento departamento) {
		super();
		this.nombre = nombre;
		this.departamento = departamento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = (Long) id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombre=" + nombre + "]";
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

}
