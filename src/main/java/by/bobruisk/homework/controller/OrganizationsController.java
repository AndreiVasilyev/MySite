package by.bobruisk.homework.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import by.bobruisk.homework.model.email.EmailRequestBody;
import by.bobruisk.homework.service.EmailServiceImpl;
import by.bobruisk.homework.utils.FileStorageUtils;

@Controller
public class OrganizationsController {

	@Autowired
	private EmailServiceImpl emailService;

	private static Logger logger = LoggerFactory.getLogger(OrganizationsController.class);

	@GetMapping("/organizations")
	public String organizationsPage(Model model) {
		logger.info("load organization page");
		model.addAttribute("email", new EmailRequestBody());
		return "organizations";
	}

	@PostMapping("/organizations/email")
	public String getEmail(@Valid EmailRequestBody email, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("email", email);
			return "organizations";
		} else {
			if (email.getFiles() != null && !email.getFiles().get(0).isEmpty()) {
				List<Path> filePaths;
				try {
					filePaths = FileStorageUtils.saveTempFilesToStorage(email.getFiles());
					emailService.sendMessageWithAttachment(email.getTitle(), email.getMessage(), filePaths);
					FileStorageUtils.removeTempFilesFromStorage(filePaths);
				} catch (IOException e) {	
					model.addAttribute("errorMessage", "Ошибка передачи файлов на сервер");					
					logger.error(e.getLocalizedMessage());
					
				} catch (MailSendException | MessagingException e) {					
					model.addAttribute("errorMessage", "Ошибка отправки email сервером");
					logger.error(e.getLocalizedMessage());						
				} 
			} else {
				try {
					emailService.sendSimpleMessage(email.getTitle(), email.getMessage());
				} catch (MailSendException e) {
					model.addAttribute("errorMessage", "Ошибка отправки email сервером");
					logger.error(e.getLocalizedMessage());
				}
				
			}
		}
		model.addAttribute("email", new EmailRequestBody());
		return "organizations";
	}
}
