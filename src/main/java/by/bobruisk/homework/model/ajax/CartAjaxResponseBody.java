package by.bobruisk.homework.model.ajax;

public class CartAjaxResponseBody {

	private Double totalCost;
	private Integer totalItemsQuantity;
	private String errorMessage;

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public Integer getTotalItemsQuantity() {
		return totalItemsQuantity;
	}

	public void setTotalItemsQuantity(Integer totalItemsQuantity) {
		this.totalItemsQuantity = totalItemsQuantity;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((errorMessage == null) ? 0 : errorMessage.hashCode());
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
		CartAjaxResponseBody other = (CartAjaxResponseBody) obj;
		if (errorMessage == null) {
			if (other.errorMessage != null)
				return false;
		} else if (!errorMessage.equals(other.errorMessage))
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

	@Override
	public String toString() {
		return "CartAjaxResponseBody [totalCost=" + totalCost + ", totalItemsQuantity=" + totalItemsQuantity
				+ ", errorMessage=" + errorMessage + "]";
	}

}
