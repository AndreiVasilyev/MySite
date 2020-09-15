package by.bobruisk.homework.model.ajax;

public class PartsSearchRequest {
	private String id;
	private String sparePartName;
	private String imageLink;
	private String replacementСost;
	private String orderByField;
	private String orderDirection;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSparePartName() {
		return sparePartName;
	}

	public void setSparePartName(String sparePartName) {
		this.sparePartName = sparePartName;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public String getReplacementСost() {
		return replacementСost;
	}

	public void setReplacementСost(String replacementСost) {
		this.replacementСost = replacementСost;
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
		result = prime * result + ((orderByField == null) ? 0 : orderByField.hashCode());
		result = prime * result + ((orderDirection == null) ? 0 : orderDirection.hashCode());
		result = prime * result + ((replacementСost == null) ? 0 : replacementСost.hashCode());
		result = prime * result + ((sparePartName == null) ? 0 : sparePartName.hashCode());
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
		PartsSearchRequest other = (PartsSearchRequest) obj;
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
		if (replacementСost == null) {
			if (other.replacementСost != null)
				return false;
		} else if (!replacementСost.equals(other.replacementСost))
			return false;
		if (sparePartName == null) {
			if (other.sparePartName != null)
				return false;
		} else if (!sparePartName.equals(other.sparePartName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PartsSearchRequest [id=" + id + ", sparePartName=" + sparePartName + ", imageLink=" + imageLink
				+ ", replacementСost=" + replacementСost + ", orderByField=" + orderByField + ", orderDirection="
				+ orderDirection + "]";
	}

}
