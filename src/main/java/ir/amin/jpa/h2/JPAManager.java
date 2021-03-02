package ir.amin.jpa.h2;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import ir.amin.jpa.h2.entity.Car;

public class JPAManager {

	protected EntityManager entityManager;
	
	public JPAManager(EntityManager entityManager){
		this.entityManager  = entityManager;
	}
	
	public void insertSampleData(){
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		for (int i = 0; i < 5; i++) {
			Car car = new Car();
			car.setName("car"+i);
			entityManager.persist(car);			
		}
		transaction.commit();
	}
	
	public Integer carCount(){
		Query query = entityManager.createQuery("select count(c) from Car c");
		Object singleResult = query.getSingleResult();
		return Integer.valueOf(singleResult.toString());
	}
	
	
}
