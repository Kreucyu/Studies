package model.services;

public interface PaymentServices {

	Double paymentFee(Double amount);
	Double interest(Double amount, Integer months);
}
