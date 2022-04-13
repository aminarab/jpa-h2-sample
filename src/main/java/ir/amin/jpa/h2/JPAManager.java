package ir.amin.jpa.h2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import ir.amin.jpa.h2.entity.Car;
import ir.amin.jpa.h2.entity.CarModel;
import ir.amin.jpa.h2.entity.Document;
import ir.amin.jpa.h2.entity.Insurance;
import ir.amin.jpa.h2.entity.Person;

public class JPAManager {

	protected EntityManager entityManager;
	
	public JPAManager(EntityManager entityManager){
		this.entityManager  = entityManager;
	}
	
	public void insertSampleData(){
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		try {
		/*
		 * cars
		 */
		Car bmw = new Car();
		bmw.setModel(CarModel.BMW);

		Car benz = new Car();
		benz.setModel(CarModel.BENZ);

		Car audi = new Car();
		audi.setModel(CarModel.AUDI);

		Car ford = new Car();
		ford.setModel(CarModel.FORD);

		/*
		 * persons
		 */
		Person amin = new Person();
		amin.setName("Amin");

		Person behzad = new Person();
		behzad.setName("Behzad");

		Person saman = new Person();
		saman.setName("Saman");

		/*
		 * documents
		 */
		Document document1 = new Document();
		document1.setProductionYear(new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2010"));

		Document document2 = new Document();
		document2.setProductionYear(new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2010"));

		Document document3 = new Document();
		document3.setProductionYear(new SimpleDateFormat("dd/MM/yyyy").parse("15/01/2015"));

		Document document4 = new Document();
		document4.setProductionYear(new SimpleDateFormat("dd/MM/yyyy").parse("15/01/2020"));

		/*
		 * insurances
		 */
		Insurance insurance1 = new Insurance();
		insurance1.setIssueDate(new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2011"));

		Insurance insurance2 = new Insurance();
		insurance2.setIssueDate(new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2015"));

		Insurance insurance3 = new Insurance();
		insurance3.setIssueDate(new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2021"));

		Insurance insurance4 = new Insurance();
		insurance4.setIssueDate(new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2021"));

		/*
		 * Complete Car entities as relationship owner
		 */
		bmw.setDocument(document1);
		benz.setDocument(document2);
		audi.setDocument(document3);
		ford.setDocument(document4);

		Set<Person> bmwOwners = new HashSet<Person>();
		bmwOwners.add(saman);
		bmwOwners.add(behzad);
		bmw.setOwners(bmwOwners);

		Set<Person> benzOwners = new HashSet<Person>();
		benzOwners.add(saman);
		benzOwners.add(amin);
		benz.setOwners(benzOwners);

		Set<Person> audiOwners = new HashSet<Person>();
		audiOwners.add(saman);
		audiOwners.add(behzad);
		audiOwners.add(amin);
		audi.setOwners(audiOwners);

		Set<Person> fordOwners = new HashSet<Person>();
		fordOwners.add(amin);
		ford.setOwners(fordOwners);

		/*
		 * We need persist document before car (One to One relation owner) OR
		 * use @OneToOne(cascade = CascadeType.ALL)
		 */
//		entityManager.persist(document1);
//		entityManager.persist(document2);
//		entityManager.persist(document3);
//		entityManager.persist(document4);

		/*
		 * We need persist owners before car (One to One relation owner) OR
		 * use @ManyToMany(cascade = CascadeType.ALL)
		 */
//		entityManager.persist(amin);
//		entityManager.persist(behzad);
//		entityManager.persist(saman);

		insert(bmw);
		insert(benz);
		insert(ford);
		insert(audi);

		insurance1.setCar(bmw);
		entityManager.persist(insurance1);

		insurance2.setCar(ford);
		entityManager.persist(insurance2);

		insurance3.setCar(benz);
		entityManager.persist(insurance3);

		insurance4.setCar(benz);
		entityManager.persist(insurance4);
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		transaction.commit();
	}
	
	public void insert(Car car) {
		entityManager.persist(car);
	}
	
	public Integer carCount(){
		Query query = entityManager.createQuery("select count(c) from Car c");
		Object singleResult = query.getSingleResult();
		return Integer.valueOf(singleResult.toString());
	}
	
	public List<Car> findAllCars(){
		Query query = entityManager.createQuery("select c from Car c");
		return query.getResultList();
	}
}
