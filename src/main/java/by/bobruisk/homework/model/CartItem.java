package by.bobruisk.homework.model;

public class CartItem {

	private Printer printer;
	private Integer itemsQuantity;
	private Double currentPrice;
	private Boolean isChips;
	private Boolean isFlash;
	private Integer flashQuantity;

	public Printer getPrinter() {
		return printer;
	}

	public void setPrinter(Printer printer) {
		this.printer = printer;
	}

	public Integer getItemsQuantity() {
		return itemsQuantity;
	}

	public void setItemsQuantity(Integer itemsQuantity) {
		this.itemsQuantity = itemsQuantity;
	}

	public Double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(Double currentPrice) {
		if (this.itemsQuantity != null) {
			if (this.itemsQuantity < 2) {
				this.currentPrice = printer.getOriginalCartridge().getRefillCost();
			} else if (this.itemsQuantity < 5) {
				this.currentPrice = printer.getOriginalCartridge().getRefillCost() - 0.5;
			} else if (this.itemsQuantity < 11) {
				this.currentPrice = printer.getOriginalCartridge().getRefillCost() - 1.5;
			} else if (this.itemsQuantity > 10) {
				this.currentPrice = printer.getOriginalCartridge().getRefillCost() - 2.5;
			}
		} else {
			this.currentPrice = currentPrice;
		}
	}

	public Boolean getIsChips() {
		return isChips;
	}

	public void setIsChips(Boolean isChips) {
		this.isChips = isChips;
	}

	public Boolean getIsFlash() {
		return isFlash;
	}

	public void setIsFlash(Boolean isFlash) {
		this.isFlash = isFlash;
	}

	public Integer getFlashQuantity() {
		return flashQuantity;
	}

	public void setFlashQuantity(Integer flashQuantity) {
		this.flashQuantity = flashQuantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currentPrice == null) ? 0 : currentPrice.hashCode());
		result = prime * result + ((flashQuantity == null) ? 0 : flashQuantity.hashCode());
		result = prime * result + ((isChips == null) ? 0 : isChips.hashCode());
		result = prime * result + ((isFlash == null) ? 0 : isFlash.hashCode());
		result = prime * result + ((itemsQuantity == null) ? 0 : itemsQuantity.hashCode());
		result = prime * result + ((printer == null) ? 0 : printer.hashCode());
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
		CartItem other = (CartItem) obj;
		if (currentPrice == null) {
			if (other.currentPrice != null)
				return false;
		} else if (!currentPrice.equals(other.currentPrice))
			return false;
		if (flashQuantity == null) {
			if (other.flashQuantity != null)
				return false;
		} else if (!flashQuantity.equals(other.flashQuantity))
			return false;
		if (isChips == null) {
			if (other.isChips != null)
				return false;
		} else if (!isChips.equals(other.isChips))
			return false;
		if (isFlash == null) {
			if (other.isFlash != null)
				return false;
		} else if (!isFlash.equals(other.isFlash))
			return false;
		if (itemsQuantity == null) {
			if (other.itemsQuantity != null)
				return false;
		} else if (!itemsQuantity.equals(other.itemsQuantity))
			return false;
		if (printer == null) {
			if (other.printer != null)
				return false;
		} else if (!printer.equals(other.printer))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CartItem [printer=" + printer + ", itemsQuantity=" + itemsQuantity + ", currentPrice=" + currentPrice
				+ ", isChips=" + isChips + ", isFlash=" + isFlash + ", flashQuantity=" + flashQuantity + "]";
	}

}
