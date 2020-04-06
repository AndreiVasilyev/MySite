package by.bobruisk.homework.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "comments")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="Поле обязательное к заполнению")	
	@Size(min=2,message = "Поле должно содержать не менее 2-х символов")
	@Pattern(regexp = "^[a-zA-Zа-яА-Я]+[a-zA-Zа-яА-Я. ]*[a-zA-Zа-яА-Я]+$",message = "Недопустимый формат имени")
	@Column(nullable = false)
	private String authorName;
	@Pattern(regexp = "^(.*\\S.*\\S.*)?$",message = "Поле должно содержать не менее 2-х символов")	
	@Pattern(regexp = "^(\\S+[a-zA-Zа-яА-Я0-9. &!@#$%^_№\"~':,/\\\\|\\*\\)\\(-]*)?$",message = "Недопустимый формат поля")	
	@Column
	private String companyName;
	@NotEmpty(message="Поле обязательное к заполнению")
	@Pattern(regexp="^\\+375\\([1-9]{2}\\) [1-9][0-9]{2}(-[0-9]{2}){2}$",message="Недопустимый формат телефона '+375(ХХ) ХХХ-ХХ-ХХ'")
	@Column(nullable = false)
	private String phoneNumber;
	@Column
	@Email(message="Недопустимый формат элктронного адреса",regexp = "^(.+@.+\\..+)?$")
	private String emailAddress;
	@NotEmpty(message="Поле обязательное к заполнению")
	@Size(min=20,message = "Поле должно содержать не менее 20-ти символов")
	@Column(length = 1500, nullable = false)
	private String message;
	@Column(nullable = false)
	private Date dateMessage;
	@Column
	private Boolean isPublic;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDateMessage() {
		return dateMessage;
	}

	public void setDateMessage(Date dateMessage) {
		this.dateMessage = dateMessage;
	}

	public Boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorName == null) ? 0 : authorName.hashCode());
		result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + ((dateMessage == null) ? 0 : dateMessage.hashCode());
		result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isPublic == null) ? 0 : isPublic.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
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
		Comment other = (Comment) obj;
		if (authorName == null) {
			if (other.authorName != null)
				return false;
		} else if (!authorName.equals(other.authorName))
			return false;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (dateMessage == null) {
			if (other.dateMessage != null)
				return false;
		} else if (!dateMessage.equals(other.dateMessage))
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
		if (isPublic == null) {
			if (other.isPublic != null)
				return false;
		} else if (!isPublic.equals(other.isPublic))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
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
		return "Comment [id=" + id + ", authorName=" + authorName + ", companyName=" + companyName + ", phoneNumber="
				+ phoneNumber + ", emailAddress=" + emailAddress + ", message=" + message + ", dateMessage="
				+ dateMessage + ", isPublic=" + isPublic + "]";
	}

}
