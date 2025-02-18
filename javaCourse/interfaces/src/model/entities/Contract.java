package model.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Contract {
	private int contractNumber;
	private LocalDate contractDate;
	private Double contractValue;
	private List<Installment> installments = new ArrayList<>();
	DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public Contract(int contractNumber, LocalDate contractDate, Double contractValue) {
		this.contractNumber = contractNumber;
		this.contractDate = contractDate;
		this.contractValue = contractValue;
	}
	public int getContractNumber() {
		return contractNumber;
	}
	public void setContractNumber(int contractNumber) {
		this.contractNumber = contractNumber;
	}
	public LocalDate getContractDate() {
		return contractDate;
	}
	public void setContractDate(LocalDate contractDate) {
		this.contractDate = contractDate;
	}
	public Double getContractValue() {
		return contractValue;
	}
	public void setContractValue(Double contractValue) {
		this.contractValue = contractValue;
	}
	public List<Installment> getInstallments() {
		return installments;
	}
	public void addInstallment(Installment installment) {
		installments.add(installment);
	}
	public void removeInstallment(Installment installment) {
		installments.remove(installment);
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Parcelas:\n");
		for (Installment i : installments) {
			sb.append(date.format(i.getDueDate()) + " - " + String.format("%.2f", i.getAmount()) + "\n");
		}
		return sb.toString();
	}
}

