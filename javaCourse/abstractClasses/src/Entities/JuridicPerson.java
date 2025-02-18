package Entities;

public class JuridicPerson extends Person {

	private Integer numberOfEmployees;

	public JuridicPerson(String name, Double anualIncome) {
		super();
	}

	public JuridicPerson(String name, Double anualIncome, Integer numberOfEmployees) {
		super(name, anualIncome);
		this.numberOfEmployees = numberOfEmployees;
	}

	public Integer getNumberOfEmployees() {
		return numberOfEmployees;
	}

	public void setNumberOfEmployees(Integer numberOfEmployees) {
		this.numberOfEmployees = numberOfEmployees;
	}

	@Override
	public Double taxCalculation() {

		Double taxes = 0.0;

		if (numberOfEmployees <= 10) {
			taxes = getAnualIncome() * 0.16;
		} else {
			taxes = getAnualIncome() * 0.14;
		}

		return taxes;
	}

}
