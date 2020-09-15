package by.bobruisk.homework.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cartridges")
public class Cartridge {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	@NotEmpty(message = "поле обязательно к заполнению")
	private String modelName;
	@Column
	@Pattern(regexp = "^([a-zA-Zа-яА-Я0-9]+\\.((jpg)|(png)|(svg)))*$", message = "неверный формат ввода")
	private String imageLink;
	@ManyToMany(mappedBy = "compatibleCartridges")
	@JsonIgnore
	private Set<Printer> compatiblePrinters;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "originalCartridge")
	@JsonIgnore
	private Set<Printer> nativePrinters;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "cartridges_spareParts", joinColumns = {
			@JoinColumn(name = "cartridge_id") }, inverseJoinColumns = { @JoinColumn(name = "sparePart_id") })
	@JsonIgnore
	private List<SparePart> spareParts;
	@Column
	@DecimalMax(value = "2000", message = "слишком большое значение")
	private Integer tonerCapacity;
	@Column
	private Integer resource;
	@Column
	@NotNull(message = "поле обязательно к заполнению")
	@Digits(fraction = 2, integer = 3, message = "неверный формат ввода")
	private Double refillCost;
	@Column
	private Boolean isChipped;
	@Column
	private String chipName;
	@Column
	@Digits(fraction = 2, integer = 3, message = "неверный формат ввода")
	private Double chipCost;

	@Transient
	private List<Long> compatiblePrintersId;

	@Transient
	private List<Long> nativePrintersId;

	@Transient
	private List<Long> sparePartsId;

	public List<Long> getCompatiblePrintersId() {
		return compatiblePrintersId;
	}

	public void setCompatiblePrintersId(List<Long> compatiblePrintersId) {
		this.compatiblePrintersId = compatiblePrintersId;
	}

	public List<Long> getNativePrintersId() {
		return nativePrintersId;
	}

	public void setNativePrintersId(List<Long> nativePrintersId) {
		this.nativePrintersId = nativePrintersId;
	}

	public List<Long> getSparePartsId() {
		return sparePartsId;
	}

	public void setSparePartsId(List<Long> sparePartsId) {
		this.sparePartsId = sparePartsId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public Set<Printer> getCompatiblePrinters() {
		return compatiblePrinters;
	}

	public void setCompatiblePrinters(Set<Printer> compatiblePrinters) {
		this.compatiblePrinters = compatiblePrinters;
	}

	public Set<Printer> getNativePrinters() {
		return nativePrinters;
	}

	public void setNativePrinters(Set<Printer> nativePrinters) {
		this.nativePrinters = nativePrinters;
	}

	public List<SparePart> getSpareParts() {
		return spareParts;
	}

	public void setSpareParts(List<SparePart> spareParts) {
		this.spareParts = spareParts;
	}

	public Integer getTonerCapacity() {
		return tonerCapacity;
	}

	public void setTonerCapacity(Integer tonerCapacity) {
		this.tonerCapacity = tonerCapacity;
	}

	public Integer getResource() {
		return resource;
	}

	public void setResource(Integer resource) {
		this.resource = resource;
	}

	public Double getRefillCost() {
		return refillCost;
	}

	public void setRefillCost(Double refillCost) {
		this.refillCost = refillCost;
	}

	public Boolean getIsChipped() {
		return isChipped;
	}

	public void setIsChipped(Boolean isChipped) {
		this.isChipped = isChipped;
	}

	public String getChipName() {
		return chipName;
	}

	public void setChipName(String chipName) {
		this.chipName = chipName;
	}

	public Double getChipCost() {
		return chipCost;
	}

	public void setChipCost(Double chipCost) {
		this.chipCost = chipCost;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chipCost == null) ? 0 : chipCost.hashCode());
		result = prime * result + ((chipName == null) ? 0 : chipName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((imageLink == null) ? 0 : imageLink.hashCode());
		result = prime * result + ((isChipped == null) ? 0 : isChipped.hashCode());
		result = prime * result + ((modelName == null) ? 0 : modelName.hashCode());
		result = prime * result + ((refillCost == null) ? 0 : refillCost.hashCode());
		result = prime * result + ((resource == null) ? 0 : resource.hashCode());
		result = prime * result + ((tonerCapacity == null) ? 0 : tonerCapacity.hashCode());
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
		Cartridge other = (Cartridge) obj;
		if (chipCost == null) {
			if (other.chipCost != null)
				return false;
		} else if (!chipCost.equals(other.chipCost))
			return false;
		if (chipName == null) {
			if (other.chipName != null)
				return false;
		} else if (!chipName.equals(other.chipName))
			return false;
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
		if (isChipped == null) {
			if (other.isChipped != null)
				return false;
		} else if (!isChipped.equals(other.isChipped))
			return false;
		if (modelName == null) {
			if (other.modelName != null)
				return false;
		} else if (!modelName.equals(other.modelName))
			return false;
		if (refillCost == null) {
			if (other.refillCost != null)
				return false;
		} else if (!refillCost.equals(other.refillCost))
			return false;
		if (resource == null) {
			if (other.resource != null)
				return false;
		} else if (!resource.equals(other.resource))
			return false;
		if (tonerCapacity == null) {
			if (other.tonerCapacity != null)
				return false;
		} else if (!tonerCapacity.equals(other.tonerCapacity))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cartridge [id=" + id + ", modelName=" + modelName + ", imageLink=" + imageLink + ", tonerCapacity="
				+ tonerCapacity + ", resource=" + resource + ", refillCost=" + refillCost + ", isChipped=" + isChipped
				+ ", chipName=" + chipName + ", chipCost=" + chipCost + "]";
	}

}
