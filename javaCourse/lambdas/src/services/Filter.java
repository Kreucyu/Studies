package services;

import java.util.List;
import java.util.function.Predicate;

import entities.Employee;

public class Filter {
	public double filter(List<Employee> employees, Predicate <Employee> rule) {
		double sum = 0;
			for (Employee p : employees) {
				if (rule.test(p)) {
					sum += p.getSalary();
				}
			}
		return sum;
	}

}
