package by.bobruisk.homework.model.ajax;

public class OrderSearchRequest {

	private String id;
	private String customerName;
	private String phoneNumber;
	private String emailAddress;
	private String customerAddress;
	private String additionalInfo;
	private String deviceModel;
	private String orderCondition;
	private String orderSource;
	private String dateOrder;
	private String orderByField;
	private String orderDirection;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public String getOrderCondition() {
		return orderCondition;
	}

	public void setOrderCondition(String orderCondition) {
		this.orderCondition = orderCondition;
	}

	public String getOrderSource() {
		return orderSource;
	}

	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}

	public String getDateOrder() {
		return dateOrder;
	}

	public void setDateOrder(String dateOrder) {
		this.dateOrder = dateOrder;
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
		result = prime * result + ((additionalInfo == null) ? 0 : additionalInfo.hashCode());
		result = prime * result + ((customerAddress == null) ? 0 : customerAddress.hashCode());
		result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result + ((dateOrder == null) ? 0 : dateOrder.hashCode());
		result = prime * result + ((deviceModel == null) ? 0 : deviceModel.hashCode());
		result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((orderByField == null) ? 0 : orderByField.hashCode());
		result = prime * result + ((orderCondition == null) ? 0 : orderCondition.hashCode());
		result = prime * result + ((orderDirection == null) ? 0 : orderDirection.hashCode());
		result = prime * result + ((orderSource == null) ? 0 : orderSource.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
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
		OrderSearchRequest other = (OrderSearchRequest) obj;
		if (additionalInfo == null) {
			if (other.additionalInfo != null)
				return false;
		} else if (!additionalInfo.equals(other.additionalInfo))
			return false;
		if (customerAddress == null) {
			if (other.customerAddress != null)
				return false;
		} else if (!customerAddress.equals(other.customerAddress))
			return false;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		if (dateOrder == null) {
			if (other.dateOrder != null)
				return false;
		} else if (!dateOrder.equals(other.dateOrder))
			return false;
		if (deviceModel == null) {
			if (other.deviceModel != null)
				return false;
		} else if (!deviceModel.equals(other.deviceModel))
			return false;
		if (emailAddress == null) {
			if (other.emailAddress != null)
				return false;
		} else if (!emailAddress.equals(other.emailAddress))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (orderByField == null) {
			if (other.orderByField != null)
				return false;
		} else if (!orderByField.equals(other.orderByField))
			return false;
		if (orderCondition == null) {
			if (other.orderCondition != null)
				return false;
		} else if (!orderCondition.equals(other.orderCondition))
			return false;
		if (orderDirection == null) {
			if (other.orderDirection != null)
				return false;
		} else if (!orderDirection.equals(other.orderDirection))
			return false;
		if (orderSource == null) {
			if (other.orderSource != null)
				return false;
		} else if (!orderSource.equals(other.orderSource))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderSearchRequest [id=" + id + ", customerName=" + customerName + ", phoneNumber=" + phoneNumber
				+ ", emailAddress=" + emailAddress + ", customerAddress=" + customerAddress + ", additionalInfo="
				+ additionalInfo + ", deviceModel=" + deviceModel + ", orderCondition=" + orderCondition
				+ ", orderSource=" + orderSource + ", dateOrder=" + dateOrder + ", orderByField=" + orderByField
				+ ", orderDirection=" + orderDirection + "]";
	}

}
