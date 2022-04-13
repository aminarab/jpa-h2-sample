package ir.amin.jpa.h2.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;


@Entity
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	@Enumerated
	private CarModel model;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "doc_id", referencedColumnName = "OF1001ID")
	private Document document;

	@OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Insurance> insurances;

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
	@JoinTable(name = "Car_Person", joinColumns = @JoinColumn(name = "car_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"))
	private Set<Person> owners;

	@PrePersist
	public void prePersist() {
		System.out.println("CarCallbackListener.prePersist:" + "Car to be created with car id: ");
	}

	@PostPersist
	public void postPersist() {
		System.out.println("CarCallbackListener.postPersist::" + "Car created with car id: ");
	}

	@PreRemove
	public void preRemove() {
		System.out.println("CarCallbackListener.preRemove:" + " About to delete Car: ");
	}

	@PostRemove
	public void postRemove() {
		System.out.println("CarCallbackListener.postRemove::" + " Deleted Car: ");
	}

	@PreUpdate
	public void preUpdate() {
		System.out.println("CarCallbackListener.preUpdate::" + " About to update Car: ");
	}

	@PostUpdate
	public void postUpdate() {
		System.out.println("CarCallbackListener.postUpdate::" + " Updated Car: ");
	}

	@PostLoad
	public void postLoad() {
		System.out.println("CarCallbackListener.postLoad::" + " Loaded Car: ");
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CarModel getModel() {
		return model;
	}

	public void setModel(CarModel model) {
		this.model = model;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public List<Insurance> getInsurances() {
		return insurances;
	}

	public void setInsurances(List<Insurance> insurances) {
		this.insurances = insurances;
	}

	public Set<Person> getOwners() {
		return owners;
	}

	public void setOwners(Set<Person> owners) {
		this.owners = owners;
	}

	@Override
	public String toString() {
		return "Car=[" + "id : " + id + " model : " + model + " document : " + document + " insurances : " + insurances
				+ " owners : " + owners + "]";
	}
}
