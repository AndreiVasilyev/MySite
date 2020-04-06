package by.bobruisk.homework.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import by.bobruisk.homework.dao.IPrinterJdbcDAO;
import by.bobruisk.homework.model.Printer;
import by.bobruisk.homework.model.Vendors;
import by.bobruisk.homework.model.ajax.PrinterAjaxResponseBody;
import by.bobruisk.homework.model.ajax.PrinterCountAjaxResponseBody;

@Controller
public class CostsController {

	@Autowired
	private IPrinterJdbcDAO printerJdbcDAO;
	private final Integer PAGE_SIZE = 8;

	@GetMapping("/costs")
	public String costsPage(Model model) {
		Page<Printer> printersPage = printerJdbcDAO
				.findAll(PageRequest.of(0, PAGE_SIZE, Sort.by("popularityRating").descending()));
		model.addAttribute("currentPage", 0);
		model.addAttribute("totalPages", printersPage.getTotalPages());
		model.addAttribute("printersPage", printersPage.toList());
		model.addAttribute("allVendors", Vendors.values());				
		return "costs";
	}

	@ResponseBody
	@GetMapping("/costs/{pageNumber}")
	public PrinterAjaxResponseBody costsPageAjaxResponse(@PathVariable Integer pageNumber) {
		PrinterAjaxResponseBody response = new PrinterAjaxResponseBody();
		if (pageNumber == null) {
			response.setErrorMessage("в запросе отсутствует номер страницы...");
		} else {
			Page<Printer> printersPage = printerJdbcDAO
					.findAll(PageRequest.of(pageNumber, PAGE_SIZE, Sort.by("popularityRating").descending()));
			response.setPageNumber(pageNumber);
			response.setTotalPages(printersPage.getTotalPages());
			response.setPrintersPage(printersPage.toList());
			response.setErrorMessage("");
		}
		return response;
	}

	@ResponseBody
	@GetMapping("/costs/{vendorName}/{pageNumber}")
	public PrinterAjaxResponseBody costsPageAjaxResponseByVendor(@PathVariable String vendorName,
			@PathVariable Integer pageNumber) {
		PrinterAjaxResponseBody response = new PrinterAjaxResponseBody();
		if (vendorName == null || vendorName.isEmpty()|| pageNumber==null) {
			response.setErrorMessage("в запросе отсутствует название вендора или номер страницы");
		} else {
			Page<Printer> printersPage = printerJdbcDAO.findByVendorName(Vendors.valueOf(vendorName),
					PageRequest.of(pageNumber, PAGE_SIZE, Sort.by("popularityRating").descending()));
			response.setPageNumber(pageNumber);
			response.setTotalPages(printersPage.getTotalPages());
			response.setPrintersPage(printersPage.toList());
			response.setErrorMessage("");
		}
		return response;
	}

	@ResponseBody
	@GetMapping("/costs/find/{modelName}")
	public PrinterCountAjaxResponseBody foundResultAjaxResponseByModel(@PathVariable String modelName) {
		PrinterCountAjaxResponseBody response = new PrinterCountAjaxResponseBody();
		if (modelName == null || modelName.isEmpty()) {
			response.setErrorMessage("в запросе отсутствует образец для поиска");
		} else {
			Long printersCount = printerJdbcDAO.countByModelNameContaining(modelName);
			response.setPrinterCount(printersCount);
			response.setErrorMessage("");
		}
		return response;
	}

	@ResponseBody
	@GetMapping("/costs/find/{modelName}/{pageNumber}")
	public PrinterAjaxResponseBody costsPageAjaxResponseByModel(@PathVariable String modelName,
			@PathVariable Integer pageNumber) {
		PrinterAjaxResponseBody response = new PrinterAjaxResponseBody();
		if (modelName == null || modelName.isEmpty()||pageNumber==null) {
			response.setErrorMessage("в запросе отсутствует образец для поиска или номер страницы");
		} else {
			Page<Printer> printersPage = printerJdbcDAO.findByModelNameContaining(modelName,
					PageRequest.of(pageNumber, PAGE_SIZE, Sort.by("popularityRating").descending()));
			response.setPageNumber(pageNumber);
			response.setTotalPages(printersPage.getTotalPages());
			response.setPrintersPage(printersPage.toList());
			response.setErrorMessage("");
		}
		return response;
	}	
}
