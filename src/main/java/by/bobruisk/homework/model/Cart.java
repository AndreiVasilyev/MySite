package by.bobruisk.homework.model;

import java.util.List;

import by.bobruisk.homework.model.CartItem;

public class Cart {

	private List<CartItem> cartItems;
	private String sessionId;
	private Double totalCost;
	private Integer totalItemsQuantity;

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public Integer getTotalItemsQuantity() {
		return totalItemsQuantity;
	}

	public void setTotalCostAndTotalQantity() {
		this.totalCost = 0.0;
		this.totalItemsQuantity = 0;
		for (int i = 0; i < this.cartItems.size(); i++) {
			this.totalCost += this.cartItems.get(i).getCurrentPrice() * this.cartItems.get(i).getItemsQuantity();
			this.totalItemsQuantity += this.cartItems.get(i).getItemsQuantity();
			if (this.cartItems.get(i).getIsChips()) {
				this.totalCost += this.cartItems.get(i).getItemsQuantity()
						* this.cartItems.get(i).getPrinter().getOriginalCartridge().getChipCost();
			}
			if (this.cartItems.get(i).getIsFlash()) {
				this.totalCost += this.cartItems.get(i).getPrinter().getReflashCost()*this.cartItems.get(i).getFlashQuantity();
				this.totalItemsQuantity+=this.cartItems.get(i).getFlashQuantity();
			}
		}
	}

	@Override
	public String toString() {
		return "Cart [cartItems=" + cartItems + ", sessionId=" + sessionId + ", totalCost=" + totalCost
				+ ", totalItemsQuantity=" + totalItemsQuantity + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cartItems == null) ? 0 : cartItems.hashCode());
		result = prime * result + ((sessionId == null) ? 0 : sessionId.hashCode());
		result = prime * result + ((totalCost == null) ? 0 : totalCost.hashCode());
		result = prime * result + ((totalItemsQuantity == null) ? 0 : totalItemsQuantity.hashCode());
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
		Cart other = (Cart) obj;
		if (cartItems == null) {
			if (other.cartItems != null)
				return false;
		} else if (!cartItems.equals(other.cartItems))
			return false;
		if (sessionId == null) {
			if (other.sessionId != null)
				return false;
		} else if (!sessionId.equals(other.sessionId))
			return false;
		if (totalCost == null) {
			if (other.totalCost != null)
				return false;
		} else if (!totalCost.equals(other.totalCost))
			return false;
		if (totalItemsQuantity == null) {
			if (other.totalItemsQuantity != null)
				return false;
		} else if (!totalItemsQuantity.equals(other.totalItemsQuantity))
			return false;
		return true;
	}

}
