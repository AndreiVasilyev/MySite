package by.bobruisk.homework.model.ajax;

import java.util.List;

import by.bobruisk.homework.model.Conditions;
import by.bobruisk.homework.model.Order;
import by.bobruisk.homework.model.OrderSources;

public class OrderSearchResponse {
	private List<Order> orders;
	private List<Conditions> conditions;
	private List<OrderSources> ordersource;
 	private Integer totalQuantity;

 	
	public List<Conditions> getConditions() {
		return conditions;
	}

	public void setConditions(List<Conditions> conditions) {
		this.conditions = conditions;
	}

	public List<OrderSources> getOrdersource() {
		return ordersource;
	}

	public void setOrdersource(List<OrderSources> ordersource) {
		this.ordersource = ordersource;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Integer getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(Integer totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orders == null) ? 0 : orders.hashCode());
		result = prime * result + ((totalQuantity == null) ? 0 : totalQuantity.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderSearchResponse other = (OrderSearchResponse) obj;
		if (orders == null) {
			if (other.orders != null)
				return false;
		} else if (!orders.equals(other.orders))
			return false;
		if (totalQuantity == null) {
			if (other.totalQuantity != null)
				return false;
		} else if (!totalQuantity.equals(other.totalQuantity))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderSearchResponse [orders=" + orders + ", totalQuantity=" + totalQuantity + "]";
	}

}
