package by.bobruisk.homework.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "printers")
public class Printer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING)
	@Column	
	private Vendors vendorName;
	@Column
	@NotEmpty(message = "поле обязательно к заполнению")
	private String modelName;
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL) 
	@JoinColumn(name="cartridge_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Cartridge originalCartridge;
	@Column
	@Pattern(regexp = "^([a-zA-Zа-яА-Я0-9]+\\.((jpg)|(png)|(svg)))*$",message = "неверный формат ввода")
	private String imageLink;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "printers_cartridges", joinColumns = { @JoinColumn(name = "printer_id") }, inverseJoinColumns = {
			@JoinColumn(name = "cartridge_id") })
	@JsonIgnore
	private List<Cartridge> compatibleCartridges;
	@Column
	private Boolean isReflashable;
	@Column
	@Digits(fraction = 2,integer = 3,message = "неверный формат ввода")
	private Double reflashCost;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "printers_spareParts", joinColumns = { @JoinColumn(name = "printer_id") }, inverseJoinColumns = {
			@JoinColumn(name = "sparePart_id") })
	@JsonIgnore
	private List<SparePart> spareParts;
	@Column
	private Long popularityRating;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Vendors getVendorName() {
		return vendorName;
	}

	public void setVendorName(Vendors vendorName) {
		this.vendorName = vendorName;
	}

	public String getModelName() {
		return modelName;
	}
	
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public Cartridge getOriginalCartridge() {
		return originalCartridge;
	}

	public void setOriginalCartridge(Cartridge originalCartridge) {
		this.originalCartridge = originalCartridge;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public List<Cartridge> getCompatibleCartridges() {
		return compatibleCartridges;
	}

	public void setCompatibleCartridges(List<Cartridge> compatibleCartridges) {
		this.compatibleCartridges = compatibleCartridges;
	}

	public Boolean getIsReflashable() {
		return isReflashable;
	}

	public void setIsReflashable(Boolean isReflashable) {
		this.isReflashable = isReflashable;
	}

	public Double getReflashCost() {
		return reflashCost;
	}

	public void setReflashCost(Double reflashCost) {
		this.reflashCost = reflashCost;
	}

	public List<SparePart> getSpareParts() {
		return spareParts;
	}

	public void setSpareParts(List<SparePart> spareParts) {
		this.spareParts = spareParts;
	}

	public Long getPopularityRating() {
		return popularityRating;
	}

	public void setPopularityRating(Long popularityRating) {
		this.popularityRating = popularityRating;
	}
	
	public String getFullName() {
		return vendorName+" "+modelName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((compatibleCartridges == null) ? 0 : compatibleCartridges.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((imageLink == null) ? 0 : imageLink.hashCode());
		result = prime * result + ((isReflashable == null) ? 0 : isReflashable.hashCode());
		result = prime * result + ((modelName == null) ? 0 : modelName.hashCode());
		result = prime * result + ((originalCartridge == null) ? 0 : originalCartridge.hashCode());
		result = prime * result + ((popularityRating == null) ? 0 : popularityRating.hashCode());
		result = prime * result + ((reflashCost == null) ? 0 : reflashCost.hashCode());
		result = prime * result + ((spareParts == null) ? 0 : spareParts.hashCode());
		result = prime * result + ((vendorName == null) ? 0 : vendorName.hashCode());
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
		Printer other = (Printer) obj;
		if (compatibleCartridges == null) {
			if (other.compatibleCartridges != null)
				return false;
		} else if (!compatibleCartridges.equals(other.compatibleCartridges))
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
		if (isReflashable == null) {
			if (other.isReflashable != null)
				return false;
		} else if (!isReflashable.equals(other.isReflashable))
			return false;
		if (modelName == null) {
			if (other.modelName != null)
				return false;
		} else if (!modelName.equals(other.modelName))
			return false;
		if (originalCartridge == null) {
			if (other.originalCartridge != null)
				return false;
		} else if (!originalCartridge.equals(other.originalCartridge))
			return false;
		if (popularityRating == null) {
			if (other.popularityRating != null)
				return false;
		} else if (!popularityRating.equals(other.popularityRating))
			return false;
		if (reflashCost == null) {
			if (other.reflashCost != null)
				return false;
		} else if (!reflashCost.equals(other.reflashCost))
			return false;
		if (spareParts == null) {
			if (other.spareParts != null)
				return false;
		} else if (!spareParts.equals(other.spareParts))
			return false;
		if (vendorName == null) {
			if (other.vendorName != null)
				return false;
		} else if (!vendorName.equals(other.vendorName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Printer [id=" + id + ", vendorName=" + vendorName + ", modelName=" + modelName + ", originalCartridge="
				+ originalCartridge + ", imageLink=" + imageLink + ", isReflashable=" + isReflashable + ", reflashCost="
				+ reflashCost + ", popularityRating=" + popularityRating + "]";
	}

}
