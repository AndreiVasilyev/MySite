package by.bobruisk.homework.model.ajax;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import by.bobruisk.homework.model.Comment;

public class CommentAjaxResponseBody {

	@JsonView()
	private Integer pageNumber;
	@JsonView()
	private Integer totalPages;
	@JsonView()
	private String errorMessage;
	@JsonView()
	private List<Comment> commentsPage;

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

	public List<Comment> getCommentsPage() {
		return commentsPage;
	}

	public void setCommentsPage(List<Comment> commentsPage) {
		this.commentsPage = commentsPage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commentsPage == null) ? 0 : commentsPage.hashCode());
		result = prime * result + ((errorMessage == null) ? 0 : errorMessage.hashCode());
		result = prime * result + ((pageNumber == null) ? 0 : pageNumber.hashCode());
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
		CommentAjaxResponseBody other = (CommentAjaxResponseBody) obj;
		if (commentsPage == null) {
			if (other.commentsPage != null)
				return false;
		} else if (!commentsPage.equals(other.commentsPage))
			return false;
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
		if (totalPages == null) {
			if (other.totalPages != null)
				return false;
		} else if (!totalPages.equals(other.totalPages))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CommentAjaxResponseBody [pageNumber=" + pageNumber + ", totalPages=" + totalPages + ", errorMessage="
				+ errorMessage + ", commentsPage=" + commentsPage + "]";
	}

}
