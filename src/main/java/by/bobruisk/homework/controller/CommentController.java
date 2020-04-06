package by.bobruisk.homework.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import by.bobruisk.homework.dao.ICommentJdbcDAO;
import by.bobruisk.homework.model.Comment;
import by.bobruisk.homework.model.ajax.CommentAjaxResponseBody;

@Controller
public class CommentController {

	@Autowired
	private ICommentJdbcDAO commentJdbcDao;
	private final Integer PAGE_SIZE = 8;

	@GetMapping("/comments")
	public String getCommentsPage(Model model) {
		Page<Comment> page = commentJdbcDao.findAll(PageRequest.of(0, PAGE_SIZE, Sort.by("dateMessage").descending()));
		model.addAttribute("currentPage", 0);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("pageComments", page.toList());
		model.addAttribute("newComment", new Comment());
		return "comments";
	}

	@ResponseBody
	@GetMapping("/comments/{pageNumber}")
	public CommentAjaxResponseBody getCommentsSubPage(@PathVariable Integer pageNumber) {
		CommentAjaxResponseBody commentAjaxResponseBody = new CommentAjaxResponseBody();
		if (pageNumber == null) {
			commentAjaxResponseBody.setErrorMessage("в запросе отсутствует номер страницы...");
		} else {
			Page<Comment> page = commentJdbcDao
					.findAll(PageRequest.of(pageNumber, PAGE_SIZE, Sort.by("dateMessage").descending()));
			commentAjaxResponseBody.setPageNumber(pageNumber);
			commentAjaxResponseBody.setTotalPages(page.getTotalPages());
			commentAjaxResponseBody.setCommentsPage(page.toList());
			commentAjaxResponseBody.setErrorMessage("");
		}
		return commentAjaxResponseBody;
	}

	@PostMapping("/save")
	public String saveNewComment(@ModelAttribute("newComment") @Valid Comment newComment, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			// model.addAttribute("allComments", commentJdbcDao.findAll());
			Page<Comment> page = commentJdbcDao
					.findAll(PageRequest.of(0, PAGE_SIZE, Sort.by("dateMessage").descending()));
			model.addAttribute("currentPage", 0);
			model.addAttribute("totalPages", page.getTotalPages());
			model.addAttribute("pageComments", page.toList());
			return "comments";
		}

		newComment.setDateMessage(new Date());
		newComment.setIsPublic(false);
		commentJdbcDao.save(newComment);
		return "redirect:/comments";
	}
}
