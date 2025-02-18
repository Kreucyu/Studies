package Entites;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import enums.OrderStatus;

public class Order {

	private Date moment;
	private OrderStatus status;
	private Client client;
	private List<OrderItem> items = new ArrayList<>();
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private static SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");

	public Order() {
	}

	public Order(Date moment, OrderStatus status, Client client) {
		this.moment = moment;
		this.status = status;
		this.client = client;
	}

	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void addItem(OrderItem item) {
		items.add(item);
	}

	public void removeItem(OrderItem item) {
		items.remove(item);
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Double total() {
		double sum = 0;
		for (OrderItem I : items) {
			sum += I.subTotal();
		}
		return sum;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nORDER SUMMARY: \n");
		sb.append("Order moment: " + sdf.format(moment) + "\n");
		sb.append("Order Status: " + status + "\n");
		sb.append("Client: " + client.getName() + " (" + sdf1.format(client.getBirthDate()) + ") - " + client.getEmail() + "\n");
		sb.append("Order items:\n");
		for (OrderItem c : items) {
			sb.append(c.getProduct().getName() + ", $" + String.format("%.2f", c.getPrice()) + ", Quantity: " + c.getQuantity() + ", Subtotal: $" + String.format("%.2f", c.subTotal()) + "\n");
		}
		sb.append("Total price: $" + String.format("%.2f", total()) + "\n");
		return sb.toString();
	}
	
	

}
