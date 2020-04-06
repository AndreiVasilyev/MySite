package by.bobruisk.homework.model.ajax;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import by.bobruisk.homework.model.Printer;

public class PrinterAjaxResponseBody {

	@JsonView
	private Integer pageNumber;
	@JsonView
	private Integer totalPages;
	@JsonView
	private String errorMessage;
	@JsonView
	private List<Printer> printersPage;

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public List<Printer> getPrintersPage() {
		return printersPage;
	}

	public void setPrintersPage(List<Printer> printersPage) {
		this.printersPage = printersPage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((errorMessage == null) ? 0 : errorMessage.hashCode());
		result = prime * result + ((pageNumber == null) ? 0 : pageNumber.hashCode());
		result = prime * result + ((printersPage == null) ? 0 : printersPage.hashCode());
		result = prime * result + ((totalPages == null) ? 0 : totalPages.hashCode());
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
		PrinterAjaxResponseBody other = (PrinterAjaxResponseBody) obj;
		if (errorMessage == null) {
			if (other.errorMessage != null)
				return false;
		} else if (!errorMessage.equals(other.errorMessage))
			return false;
		if (pageNumber == null) {
			if (other.pageNumber != null)
				return false;
		} else if (!pageNumber.equals(other.pageNumber))
			return false;
		if (printersPage == null) {
			if (other.printersPage != null)
				return false;
		} else if (!printersPage.equals(other.printersPage))
			return false;
		if (totalPages == null) {
			if (other.totalPages != null)
				return false;
		} else if (!totalPages.equals(other.totalPages))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PrinterAjaxResponseBody [pageNumber=" + pageNumber + ", totalPages=" + totalPages + ", errorMessage="
				+ errorMessage + "]";
	}

}
