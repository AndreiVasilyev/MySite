package by.bobruisk.homework.model.ajax;

import java.util.List;

import by.bobruisk.homework.model.Cartridge;

public class CartridgeSearchResponse {
	private List<Cartridge> cartridges;
	private Integer totalQuantity;

	public List<Cartridge> getCartridges() {
		return cartridges;
	}

	public void setCartridges(List<Cartridge> cartridges) {
		this.cartridges = cartridges;
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
		result = prime * result + ((cartridges == null) ? 0 : cartridges.hashCode());
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
		CartridgeSearchResponse other = (CartridgeSearchResponse) obj;
		if (cartridges == null) {
			if (other.cartridges != null)
				return false;
		} else if (!cartridges.equals(other.cartridges))
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
		return "CartridgeSearchResponse [cartridges=" + cartridges + ", totalQuantity=" + totalQuantity + "]";
	}

}
