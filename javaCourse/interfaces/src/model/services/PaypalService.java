package model.services;

public class PaypalService implements PaymentServices {

	@Override
	public Double interest(Double amount, Integer months) {
		if (months == 1) {
			return amount + (amount * 0.01);
		}
		else {
			return amount + (amount * months / 100);
		}
	}

	@Override
	public Double paymentFee(Double amount) {
		return amount + (amount * 0.02);

	}

}
