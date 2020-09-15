package by.bobruisk.homework.model.ajax;

public class PrintersSearchRequest {
	private String id;
	private String vendorName;
	private String modelName;
	private String imageLink;
	private String rating;
	private String isReflashable;
	private String reflashCost;
	private String originCartridge;
	private String orderByField;
	private String orderDirection;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getIsReflashable() {
		return isReflashable;
	}

	public void setIsReflashable(String isReflashable) {
		this.isReflashable = isReflashable;
	}

	public String getReflashCost() {
		return reflashCost;
	}

	public void setReflashCost(String reflashCost) {
		this.reflashCost = reflashCost;
	}

	public String getOriginCartridge() {
		return originCartridge;
	}

	public void setOriginCartridge(String originCartridge) {
		this.originCartridge = originCartridge;
	}

	public String getOrderByField() {
		return orderByField;
	}

	public void setOrderByField(String orderByField) {
		this.orderByField = orderByField;
	}

	public String getOrderDirection() {
		return orderDirection;
	}

	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((imageLink == null) ? 0 : imageLink.hashCode());
		result = prime * result + ((isReflashable == null) ? 0 : isReflashable.hashCode());
		result = prime * result + ((modelName == null) ? 0 : modelName.hashCode());
		result = prime * result + ((orderByField == null) ? 0 : orderByField.hashCode());
		result = prime * result + ((orderDirection == null) ? 0 : orderDirection.hashCode());
		result = prime * result + ((originCartridge == null) ? 0 : originCartridge.hashCode());
		result = prime * result + ((rating == null) ? 0 : rating.hashCode());
		result = prime * result + ((reflashCost == null) ? 0 : reflashCost.hashCode());
		result = prime * result + ((vendorName == null) ? 0 : vendorName.hashCode());
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
		PrintersSearchRequest other = (PrintersSearchRequest) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (imageLink == null) {
			if (other.imageLink != null)
				return false;
		} else if (!imageLink.equals(other.imageLink))
			return false;
		if (isReflashable == null) {
			if (other.isReflashable != null)
				return false;
		} else if (!isReflashable.equals(other.isReflashable))
			return false;
		if (modelName == null) {
			if (other.modelName != null)
				return false;
		} else if (!modelName.equals(other.modelName))
			return false;
		if (orderByField == null) {
			if (other.orderByField != null)
				return false;
		} else if (!orderByField.equals(other.orderByField))
			return false;
		if (orderDirection == null) {
			if (other.orderDirection != null)
				return false;
		} else if (!orderDirection.equals(other.orderDirection))
			return false;
		if (originCartridge == null) {
			if (other.originCartridge != null)
				return false;
		} else if (!originCartridge.equals(other.originCartridge))
			return false;
		if (rating == null) {
			if (other.rating != null)
				return false;
		} else if (!rating.equals(other.rating))
			return false;
		if (reflashCost == null) {
			if (other.reflashCost != null)
				return false;
		} else if (!reflashCost.equals(other.reflashCost))
			return false;
		if (vendorName == null) {
			if (other.vendorName != null)
				return false;
		} else if (!vendorName.equals(other.vendorName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PrintersSearchRequest [id=" + id + ", vendorName=" + vendorName + ", modelName=" + modelName
				+ ", imageLink=" + imageLink + ", rating=" + rating + ", isReflashable=" + isReflashable
				+ ", reflashCost=" + reflashCost + ", originCartridge=" + originCartridge + ", orderByField="
				+ orderByField + ", orderDirection=" + orderDirection + "]";
	}

}
