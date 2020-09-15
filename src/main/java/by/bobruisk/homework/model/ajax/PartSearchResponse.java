package by.bobruisk.homework.model.ajax;

import java.util.List;

import by.bobruisk.homework.model.SparePart;

public class PartSearchResponse {
	private List<SparePart> parts;
	private Integer totalQuantity;

	public List<SparePart> getParts() {
		return parts;
	}

	public void setParts(List<SparePart> parts) {
		this.parts = parts;
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
		result = prime * result + ((parts == null) ? 0 : parts.hashCode());
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
		PartSearchResponse other = (PartSearchResponse) obj;
		if (parts == null) {
			if (other.parts != null)
				return false;
		} else if (!parts.equals(other.parts))
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
		return "PartSearchResponse [parts=" + parts + ", totalQuantity=" + totalQuantity + "]";
	}

}
