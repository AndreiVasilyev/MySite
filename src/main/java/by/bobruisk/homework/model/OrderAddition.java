package by.bobruisk.homework.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "orders_addition")
public class OrderAddition {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemId;
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id",referencedColumnName = "id")
	private Order order;
	@Column
	private Long printerId;
	@Column
	private Integer itemsQuantity;
	@Column
	private Double currentPrice;
	@Column
	private Boolean isChips;
	@Column
	private Double chipPrice;
	@Column
	private Boolean isFlash;
	@Column
	private Integer flashQuantity;
	@Column
	private Double flashPrice;
	@Transient
	private String fullName;
	
	

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Long getId() {
		return itemId;
	}

	public void setId(Long id) {
		this.itemId = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Long getPrinterId() {
		return printerId;
	}

	public void setPrinterId(Long printerId) {
		this.printerId = printerId;
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
		this.currentPrice = currentPrice;
	}

	public Boolean getIsChips() {
		return isChips;
	}

	public void setIsChips(Boolean isChips) {
		this.isChips = isChips;
	}

	public Double getChipPrice() {
		return chipPrice;
	}

	public void setChipPrice(Double chipPrice) {
		this.chipPrice = chipPrice;
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

	public Double getFlashPrice() {
		return flashPrice;
	}

	public void setFlashPrice(Double flashPrice) {
		this.flashPrice = flashPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chipPrice == null) ? 0 : chipPrice.hashCode());
		result = prime * result + ((currentPrice == null) ? 0 : currentPrice.hashCode());
		result = prime * result + ((flashPrice == null) ? 0 : flashPrice.hashCode());
		result = prime * result + ((flashQuantity == null) ? 0 : flashQuantity.hashCode());
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
		result = prime * result + ((isChips == null) ? 0 : isChips.hashCode());
		result = prime * result + ((isFlash == null) ? 0 : isFlash.hashCode());
		result = prime * result + ((itemsQuantity == null) ? 0 : itemsQuantity.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((printerId == null) ? 0 : printerId.hashCode());
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
		OrderAddition other = (OrderAddition) obj;
		if (chipPrice == null) {
			if (other.chipPrice != null)
				return false;
		} else if (!chipPrice.equals(other.chipPrice))
			return false;
		if (currentPrice == null) {
			if (other.currentPrice != null)
				return false;
		} else if (!currentPrice.equals(other.currentPrice))
			return false;
		if (flashPrice == null) {
			if (other.flashPrice != null)
				return false;
		} else if (!flashPrice.equals(other.flashPrice))
			return false;
		if (flashQuantity == null) {
			if (other.flashQuantity != null)
				return false;
		} else if (!flashQuantity.equals(other.flashQuantity))
			return false;
		if (itemId == null) {
			if (other.itemId != null)
				return false;
		} else if (!itemId.equals(other.itemId))
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
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (printerId == null) {
			if (other.printerId != null)
				return false;
		} else if (!printerId.equals(other.printerId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderAddition [id=" + itemId + ", order=" + order + ", printerId=" + printerId + ", itemsQuantity="
				+ itemsQuantity + ", currentPrice=" + currentPrice + ", isChips=" + isChips + ", chipPrice=" + chipPrice
				+ ", isFlash=" + isFlash + ", flashQuantity=" + flashQuantity + ", flashPrice=" + flashPrice + "]";
	}

}
