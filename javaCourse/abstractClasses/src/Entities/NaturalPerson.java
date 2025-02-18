package Entities;

public class NaturalPerson extends Person {

	private Double healthExpenses;

	public NaturalPerson() {
	}

	public NaturalPerson(String name, Double anualIncome, Double healthExpenses) {
		super(name, anualIncome);
		this.healthExpenses = healthExpenses;
	}

	public Double getHealthExpenses() {
		return healthExpenses;
	}

	public void setHealthExpenses(Double healthExpenses) {
		this.healthExpenses = healthExpenses;
	}

	@Override
	public Double taxCalculation() {

		Double taxes = 0.0;

		if (getAnualIncome() < 20000) {
			taxes = getAnualIncome() * 0.15;
			if (healthExpenses != 0) {
				taxes -= healthExpenses * 0.5;
			}

		} else if (getAnualIncome() >= 20000) {
			taxes = getAnualIncome() * 0.25;
			if (healthExpenses != 0) {
				taxes -= healthExpenses * 0.5;
			}
		}

		return taxes;
	}

}
