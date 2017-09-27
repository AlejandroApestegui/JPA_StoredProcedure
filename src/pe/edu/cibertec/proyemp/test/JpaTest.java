package pe.edu.cibertec.proyemp.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import pe.edu.cibertec.proyemp.jpa.domain.Empleado;

public class JpaTest {

	private EntityManager manager;

	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}

	public static void main(String[] args) {

		// Patron factory para obtener el EntityManagerFactory
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistenceUnit");

		// Se extrae el EntityManager del factory
		EntityManager em = factory.createEntityManager();

		// Inyeccion de dependencias
		JpaTest test = new JpaTest(em);

		// Se obtiene el objeto EntityTransaction para definir la transaccion
		EntityTransaction tx = em.getTransaction();

		tx.begin();

		test.crearEmpleadoConStoredProcedure("Juan");

		tx.commit();

		test.listarEmpleadosConStoredProcedure();

	}

	@SuppressWarnings("unchecked")
	private void listarEmpleadosConStoredProcedure() {

		List<Empleado> empleados = manager.createNativeQuery("{CALL SP_LISTAR_EMPLEADOS()}", Empleado.class)
				.getResultList();
		for (Empleado emp : empleados) {
			System.out.println(emp);
		}

	}

	private void crearEmpleadoConStoredProcedure(String nombre) {

		Query query = manager.createNativeQuery("{CALL SP_INSERTAR_EMPLEADO(?)}").setParameter(1, nombre);
		query.executeUpdate();

	}

}
