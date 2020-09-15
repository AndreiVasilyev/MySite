package by.bobruisk.homework.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "spareParts")
public class SparePart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	@NotEmpty(message = "поле обязательно к заполнению")
	private String sparePartName;
	@Column
	@Pattern(regexp = "^([a-zA-Zа-яА-Я0-9]+\\.((jpg)|(png)|(svg)))*$", message = "неверный формат ввода")
	private String imageLink;
	@Column(name = "replacement_cost")
	@Digits(fraction = 2, integer = 3, message = "неверный формат ввода")
	private Double replacementСost;
	@ManyToMany(mappedBy = "spareParts")
	@JsonBackReference
	private Set<Printer> compatiblePrinters;
	@ManyToMany(mappedBy = "spareParts")
	@JsonBackReference
	private Set<Cartridge> compatibleCartridges;

	@Transient
	private List<Long> compatiblePrintersId;

	@Transient
	private List<Long> compatibleCartridgesId;

	public List<Long> getCompatiblePrintersId() {
		return compatiblePrintersId;
	}

	public void setCompatiblePrintersId(List<Long> compatiblePrintersId) {
		this.compatiblePrintersId = compatiblePrintersId;
	}

	public List<Long> getCompatibleCartridgesId() {
		return compatibleCartridgesId;
	}

	public void setCompatibleCartridgesId(List<Long> compatibleCartridgesId) {
		this.compatibleCartridgesId = compatibleCartridgesId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Double getReplacementСost() {
		return replacementСost;
	}

	public void setReplacementСost(Double replacementСost) {
		this.replacementСost = replacementСost;
	}

	public Set<Printer> getCompatiblePrinters() {
		return compatiblePrinters;
	}

	public void setCompatiblePrinters(Set<Printer> compatiblePrinters) {
		this.compatiblePrinters = compatiblePrinters;
	}

	public Set<Cartridge> getCompatibleCartridges() {
		return compatibleCartridges;
	}

	public void setCompatibleCartridges(Set<Cartridge> compatibleCartridges) {
		this.compatibleCartridges = compatibleCartridges;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((imageLink == null) ? 0 : imageLink.hashCode());
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
		SparePart other = (SparePart) obj;
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
		return "SparePart [id=" + id + ", sparePartName=" + sparePartName + ", imageLink=" + imageLink
				+ ", replacementСost=" + replacementСost + "]";
	}

}
