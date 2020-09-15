package by.bobruisk.homework.model.ajax;

public class CartridgesSearchRequest {
	private String id;
	private String modelName;
	private String imageLink;
	private String refillCost;
	private String isChipped;
	private String chipCost;
	private String resource;
	private String tonerCapacity;
	private String orderByField;
	private String orderDirection;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getRefillCost() {
		return refillCost;
	}

	public void setRefillCost(String refillCost) {
		this.refillCost = refillCost;
	}

	public String getIsChipped() {
		return isChipped;
	}

	public void setIsChipped(String isChipped) {
		this.isChipped = isChipped;
	}

	public String getChipCost() {
		return chipCost;
	}

	public void setChipCost(String chipCost) {
		this.chipCost = chipCost;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getTonerCapacity() {
		return tonerCapacity;
	}

	public void setTonerCapacity(String tonerCapacity) {
		this.tonerCapacity = tonerCapacity;
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
		result = prime * result + ((chipCost == null) ? 0 : chipCost.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((imageLink == null) ? 0 : imageLink.hashCode());
		result = prime * result + ((isChipped == null) ? 0 : isChipped.hashCode());
		result = prime * result + ((modelName == null) ? 0 : modelName.hashCode());
		result = prime * result + ((orderByField == null) ? 0 : orderByField.hashCode());
		result = prime * result + ((orderDirection == null) ? 0 : orderDirection.hashCode());
		result = prime * result + ((refillCost == null) ? 0 : refillCost.hashCode());
		result = prime * result + ((resource == null) ? 0 : resource.hashCode());
		result = prime * result + ((tonerCapacity == null) ? 0 : tonerCapacity.hashCode());
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
		CartridgesSearchRequest other = (CartridgesSearchRequest) obj;
		if (chipCost == null) {
			if (other.chipCost != null)
				return false;
		} else if (!chipCost.equals(other.chipCost))
			return false;
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
		if (isChipped == null) {
			if (other.isChipped != null)
				return false;
		} else if (!isChipped.equals(other.isChipped))
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
		if (refillCost == null) {
			if (other.refillCost != null)
				return false;
		} else if (!refillCost.equals(other.refillCost))
			return false;
		if (resource == null) {
			if (other.resource != null)
				return false;
		} else if (!resource.equals(other.resource))
			return false;
		if (tonerCapacity == null) {
			if (other.tonerCapacity != null)
				return false;
		} else if (!tonerCapacity.equals(other.tonerCapacity))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CartridgesSearchRequest [id=" + id + ", modelName=" + modelName + ", imageLink=" + imageLink
				+ ", refillCost=" + refillCost + ", isChipped=" + isChipped + ", chipCost=" + chipCost + ", resource="
				+ resource + ", tonerCapacity=" + tonerCapacity + ", orderByField=" + orderByField + ", orderDirection="
				+ orderDirection + "]";
	}

}
