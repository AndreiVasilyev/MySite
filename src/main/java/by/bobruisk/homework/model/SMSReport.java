package by.bobruisk.homework.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "smsReports")
public class SMSReport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	Long smsId;
	@Column
	String smsStatus;
	@Column
	Date smsDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSmsId() {
		return smsId;
	}

	public void setSmsId(Long smsId) {
		this.smsId = smsId;
	}

	public String getSmsStatus() {
		return smsStatus;
	}

	public void setSmsStatus(String smsStatus) {
		this.smsStatus = smsStatus;
	}

	public Date getSmsDate() {
		return smsDate;
	}

	public void setSmsDate(Date smsDate) {
		this.smsDate = smsDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((smsDate == null) ? 0 : smsDate.hashCode());
		result = prime * result + ((smsId == null) ? 0 : smsId.hashCode());
		result = prime * result + ((smsStatus == null) ? 0 : smsStatus.hashCode());
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
		SMSReport other = (SMSReport) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (smsDate == null) {
			if (other.smsDate != null)
				return false;
		} else if (!smsDate.equals(other.smsDate))
			return false;
		if (smsId == null) {
			if (other.smsId != null)
				return false;
		} else if (!smsId.equals(other.smsId))
			return false;
		if (smsStatus == null) {
			if (other.smsStatus != null)
				return false;
		} else if (!smsStatus.equals(other.smsStatus))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SMSReport [id=" + id + ", smsId=" + smsId + ", smsStatus=" + smsStatus + ", smsDate=" + smsDate + "]";
	}

}
