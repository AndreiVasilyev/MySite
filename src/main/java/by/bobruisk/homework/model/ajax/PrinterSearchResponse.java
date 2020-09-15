package by.bobruisk.homework.model.ajax;

import java.util.List;

import by.bobruisk.homework.model.Printer;

public class PrinterSearchResponse {
	private List<Printer> printers;
	private Integer totalQuantity;

	public List<Printer> getPrinters() {
		return printers;
	}

	public void setPrinters(List<Printer> printers) {
		this.printers = printers;
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
		result = prime * result + ((printers == null) ? 0 : printers.hashCode());
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
		PrinterSearchResponse other = (PrinterSearchResponse) obj;
		if (printers == null) {
			if (other.printers != null)
				return false;
		} else if (!printers.equals(other.printers))
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
		return "PrinterSearchResponse [printers=" + printers + ", totalQuantity=" + totalQuantity + "]";
	}

}
