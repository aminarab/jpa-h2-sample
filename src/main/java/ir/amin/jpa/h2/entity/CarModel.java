package ir.amin.jpa.h2.entity;

public enum CarModel {

	BMW(1), BENZ(2), FORD(3), AUDI(4);

	public final Integer order;

	private CarModel(Integer order) {
		this.order = order;
	}
}
