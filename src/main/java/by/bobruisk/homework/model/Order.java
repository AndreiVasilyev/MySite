package by.bobruisk.homework.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message="Поле обязательное к заполнению")	
	@Size(min = 2, message = "Поле должно содержать не менее 2-х символов")
	@Pattern(regexp = "^[a-zA-Zа-яА-Я]+[a-zA-Zа-яА-Я. ]*[a-zA-Zа-яА-Я]+$",message = "Недопустимый формат имени")
	@Column(nullable = false)
	private String customerName;
	@NotEmpty(message="Поле обязательное к заполнению")
	@Pattern(regexp="^\\+375\\([1-9]{2}\\) [1-9][0-9]{2}(-[0-9]{2}){2}$",message="Недопустимый формат телефона '+375(ХХ) ХХХ-ХХ-ХХ'")
	@Column(nullable = false)
	private String phoneNumber;	
	@Email(message="Недопустимый формат элктронного адреса",regexp = "^(.+@.+\\..+)?$")
	@Column
	private String emailAddress;	
	@Pattern(regexp = "^[a-zA-Zа-яА-Я0-9\\. #№\\\"':,\\/\\-]*$",message = "Недопустимый формат имени")
	@Column
	private String customerAddress;
	@Column
	private String additionalInfo;
	@Column
	private String deviceModel;
	@Column(nullable = false,columnDefinition = "enum('RECIEVED','ACCEPTED','DELAYED','COMPLETED','CANСELED')")
	@Enumerated(EnumType.STRING)
	private Conditions orderCondition;
	@Column(nullable = false,columnDefinition = "enum('CALL_ORDER','QUICK_ORDER','FULL_ORDER')")
	@Enumerated(EnumType.STRING)
	private OrderSources orderSource;
	@Column(nullable = false)
	private Date dateOrder;
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "order",cascade = CascadeType.ALL)
	private Set<OrderAddition> orderAdditions;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public Conditions getOrderCondition() {
		return orderCondition;
	}
	public void setOrderCondition(Conditions orderCondition) {
		this.orderCondition = orderCondition;
	}
	public OrderSources getOrderSource() {
		return orderSource;
	}
	public void setOrderSource(OrderSources orderSource) {
		this.orderSource = orderSource;
	}
	public Date getDateOrder() {
		return dateOrder;
	}
	public void setDateOrder(Date dateOrder) {
		this.dateOrder = dateOrder;
	}
	
	public Set<OrderAddition> getOrderAdditions() {
		return orderAdditions;
	}
	public void setOrderAdditions(Set<OrderAddition> orderAdditions) {
		this.orderAdditions = orderAdditions;
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
		result = prime * result + ((orderCondition == null) ? 0 : orderCondition.hashCode());
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
		Order other = (Order) obj;
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
		if (orderAdditions == null) {
			if (other.orderAdditions != null)
				return false;
		} else if (!orderAdditions.equals(other.orderAdditions))
			return false;
		if (orderCondition != other.orderCondition)
			return false;
		if (orderSource != other.orderSource)
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
		return "Order [id=" + id + ", customerName=" + customerName + ", phoneNumber=" + phoneNumber + ", emailAddress="
				+ emailAddress + ", customerAddress=" + customerAddress + ", additionalInfo=" + additionalInfo
				+ ", deviceModel=" + deviceModel + ", orderCondition=" + orderCondition + ", orderSource=" + orderSource
				+ ", dateOrder=" + dateOrder + ", orderAdditions=" + orderAdditions + "]";
	}
	
	
}
