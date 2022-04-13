package ir.amin.jpa.h2;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import ir.amin.jpa.h2.entity.Car;

public class Bootstrap {

	public static void main(String[] args) throws IOException, SQLException {
//		Map<Object,Object> props = new HashMap<Object,Object>();
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2pu");		
			EntityManager entityManager = emf.createEntityManager();
			JPAManager jpaManager = new JPAManager(entityManager);
			jpaManager.insertSampleData();
			System.out.println(jpaManager.carCount());
			List<Car> findAllCars = jpaManager.findAllCars();
			for (Car car : findAllCars) {
				System.out.println(car);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
//		H2Server h2Server = new H2Server();
//		h2Server.createDBByNameAndDefaultUser("jpadb");
//		String url = h2Server.getH2Container().getConnection().getMetaData().getURL();
//		System.out.println(url);
//		String address = "http://127.0.0.1:8082/";
//		Runtime.getRuntime().exec(new String[] { "cmd", "/c", "start chrome " + address });		
		
	}
	
}
