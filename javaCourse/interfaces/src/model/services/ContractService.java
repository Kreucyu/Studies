package model.services;

import java.time.LocalDate;
import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
	private PaymentServices pay;

	public void processContract(Contract contract, Integer months) {
		for (int i = 1; i <= months; i++) {
			LocalDate dueDate = contract.getContractDate().plusMonths(i);
			double support = contract.getContractValue() / months;
			pay = new PaypalService();
			double result = pay.interest(support, i);
			result = pay.paymentFee(result);
			contract.addInstallment(new Installment(dueDate, result));
		}
	}
}
