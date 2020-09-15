package by.bobruisk.homework.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.Cookie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import by.bobruisk.homework.dao.ICartridgeJdbcDAO;
import by.bobruisk.homework.dao.ICommentJdbcDAO;
import by.bobruisk.homework.dao.IOrderJdbcDAO;
import by.bobruisk.homework.dao.IPrinterJdbcDAO;
import by.bobruisk.homework.dao.ISparePartJdbcDAO;
import by.bobruisk.homework.model.Cartridge;
import by.bobruisk.homework.model.Comment;
import by.bobruisk.homework.model.Conditions;
import by.bobruisk.homework.model.Order;
import by.bobruisk.homework.model.OrderAddition;
import by.bobruisk.homework.model.OrderSources;
import by.bobruisk.homework.model.Printer;
import by.bobruisk.homework.model.SparePart;
import by.bobruisk.homework.model.ajax.CartridgeSearchResponse;
import by.bobruisk.homework.model.ajax.CartridgesSearchRequest;
import by.bobruisk.homework.model.ajax.CommentSearchRequest;
import by.bobruisk.homework.model.ajax.CommentSearchResponse;
import by.bobruisk.homework.model.ajax.OrderAttributeRequest;
import by.bobruisk.homework.model.ajax.OrderSearchRequest;
import by.bobruisk.homework.model.ajax.OrderSearchResponse;
import by.bobruisk.homework.model.ajax.PartSearchResponse;
import by.bobruisk.homework.model.ajax.PartsSearchRequest;
import by.bobruisk.homework.model.ajax.PrinterSearchResponse;
import by.bobruisk.homework.model.ajax.PrintersSearchRequest;
import by.bobruisk.homework.model.ajax.SimpleResponse;
import by.bobruisk.homework.utils.FileStorageUtils;

@Controller
public class AdminMainController {

	@Autowired
	private IPrinterJdbcDAO printerJdbcDao;
	@Autowired
	private ICartridgeJdbcDAO cartridgeJdbcDao;
	@Autowired
	private ISparePartJdbcDAO sparePartJdbcDao;
	@Autowired
	private ICommentJdbcDAO commentJdbcDao;
	@Autowired
	private IOrderJdbcDAO orderJdbcDao;

	private static Logger logger = LoggerFactory.getLogger(OrganizationsController.class);

	@GetMapping("/admin")
	public String mainAdminPage(Model model, @CookieValue(value = "tab", required = false) Cookie tab) {
		if (tab != null && tab.getValue() != null && !tab.getValue().isEmpty()) {
			model.addAttribute("tab", tab.getValue());
		} else
			model.addAttribute("tab", "printerstab");
		model.addAttribute("conditions", Conditions.values());
		model.addAttribute("ordersources", OrderSources.values());
		return "indexAdmin";
	}

	@ResponseBody
	@PostMapping("/admin/printer/filter")
	public PrinterSearchResponse getSearchResult(@RequestBody PrintersSearchRequest filterValues) {
		PrinterSearchResponse responseData = new PrinterSearchResponse();
		String id = "%" + filterValues.getId() + "%";
		String vendorName = "%" + filterValues.getVendorName() + "%";
		String modelName = "%" + filterValues.getModelName() + "%";
		String imageLink = "%" + filterValues.getImageLink() + "%";
		String rating = "%" + filterValues.getRating() + "%";
		String isReflashable = filterValues.getIsReflashable();
		String reflashCost = "%" + filterValues.getReflashCost() + "%";
		String originCartridge = "%" + filterValues.getOriginCartridge() + "%";
		Direction sortDirection = Direction.ASC;
		if (!filterValues.getOrderDirection().equals("asc")) {
			sortDirection = Direction.DESC;
		}
		List<Printer> printers = null;
		if (isReflashable.equals("all")) {
			printers = printerJdbcDao.findByFilter(id, vendorName, modelName, imageLink, rating, reflashCost,
					originCartridge, Sort.by(sortDirection, filterValues.getOrderByField()));
		} else if (isReflashable.equals("true")) {
			printers = printerJdbcDao.findByFilter(id, vendorName, modelName, imageLink, rating, true, reflashCost,
					originCartridge, Sort.by(sortDirection, filterValues.getOrderByField()));
		} else {
			printers = printerJdbcDao.findByFilter(id, vendorName, modelName, imageLink, rating, false, reflashCost,
					originCartridge, Sort.by(sortDirection, filterValues.getOrderByField()));
		}
		responseData.setPrinters(printers);
		responseData.setTotalQuantity(printers.size());
		return responseData;
	}

	@ResponseBody
	@PostMapping("/admin/cartridge/filter")
	public CartridgeSearchResponse getSearchResult(@RequestBody CartridgesSearchRequest filterValues) {
		CartridgeSearchResponse responseData = new CartridgeSearchResponse();
		String id = "%" + filterValues.getId() + "%";
		String modelName = "%" + filterValues.getModelName() + "%";
		String imageLink = "%" + filterValues.getImageLink() + "%";
		String refillCost = "%" + filterValues.getRefillCost() + "%";
		String isChipped = filterValues.getIsChipped();
		String chipCost = "%" + filterValues.getChipCost() + "%";
		String resource = "%" + filterValues.getResource() + "%";
		String tonerCapacity = "%" + filterValues.getTonerCapacity() + "%";
		Direction sortDirection = Direction.ASC;
		if (!filterValues.getOrderDirection().equals("asc")) {
			sortDirection = Direction.DESC;
		}
		List<Cartridge> cartridges = null;
		if (isChipped.equals("all")) {
			cartridges = cartridgeJdbcDao.findByFilter(id, modelName, imageLink, refillCost, chipCost, resource,
					tonerCapacity, Sort.by(sortDirection, filterValues.getOrderByField()));
		} else if (isChipped.equals("true")) {
			cartridges = cartridgeJdbcDao.findByFilter(id, modelName, imageLink, refillCost, true, chipCost, resource,
					tonerCapacity, Sort.by(sortDirection, filterValues.getOrderByField()));
		} else {
			cartridges = cartridgeJdbcDao.findByFilter(id, modelName, imageLink, refillCost, false, chipCost, resource,
					tonerCapacity, Sort.by(sortDirection, filterValues.getOrderByField()));
		}
		responseData.setCartridges(cartridges);
		responseData.setTotalQuantity(cartridges.size());
		return responseData;
	}

	@ResponseBody
	@PostMapping("/admin/part/filter")
	public PartSearchResponse getSearchResult(@RequestBody PartsSearchRequest filterValues) {
		PartSearchResponse responseData = new PartSearchResponse();
		String id = "%" + filterValues.getId() + "%";
		String sparePartName = "%" + filterValues.getSparePartName() + "%";
		String imageLink = "%" + filterValues.getImageLink() + "%";
		String replacementСost = "%" + filterValues.getReplacementСost() + "%";
		Direction sortDirection = Direction.ASC;
		if (!filterValues.getOrderDirection().equals("asc")) {
			sortDirection = Direction.DESC;
		}
		List<SparePart> parts = null;
		parts = sparePartJdbcDao.findByFilter(id, sparePartName, imageLink, replacementСost,
				Sort.by(sortDirection, filterValues.getOrderByField()));
		responseData.setParts(parts);
		responseData.setTotalQuantity(parts.size());
		return responseData;
	}

	@ResponseBody
	@PostMapping("/admin/comment/filter")
	public CommentSearchResponse getSearchResult(@RequestBody CommentSearchRequest filterValues) {
		CommentSearchResponse responseData = new CommentSearchResponse();
		String id = "%" + filterValues.getId() + "%";
		String authorName = "%" + filterValues.getAuthorName() + "%";
		String companyName = "%" + filterValues.getCompanyName() + "%";
		String dateMessage = "%" + filterValues.getDateMessage() + "%";
		String emailAddress = "%" + filterValues.getEmailAddress() + "%";
		String isPublic = filterValues.getIsPublic();
		String message = "%" + filterValues.getMessage() + "%";
		String phoneNumber = "%" + filterValues.getPhoneNumber() + "%";
		Direction sortDirection = Direction.ASC;
		if (!filterValues.getOrderDirection().equals("asc")) {
			sortDirection = Direction.DESC;
		}
		List<Comment> comments = null;
		if (isPublic.equals("all")) {
			comments = commentJdbcDao.findByFilter(id, authorName, companyName, phoneNumber, emailAddress, message,
					dateMessage, Sort.by(sortDirection, filterValues.getOrderByField()));
		} else if (isPublic.equals("true")) {
			comments = commentJdbcDao.findByFilter(id, authorName, companyName, phoneNumber, emailAddress, message,
					true, dateMessage, Sort.by(sortDirection, filterValues.getOrderByField()));
		} else {
			comments = commentJdbcDao.findByFilter(id, authorName, companyName, phoneNumber, emailAddress, message,
					false, dateMessage, Sort.by(sortDirection, filterValues.getOrderByField()));
		}
		responseData.setComments(comments);
		responseData.setTotalQuantity(comments.size());
		return responseData;
	}

	@ResponseBody
	@PostMapping("/admin/order/filter")
	public OrderSearchResponse getSearchResult(@RequestBody OrderSearchRequest filterValues) {
		OrderSearchResponse responseData = new OrderSearchResponse();
		String id = "%" + filterValues.getId() + "%";
		String customerName = "%" + filterValues.getCustomerName() + "%";
		String phoneNumber = "%" + filterValues.getPhoneNumber() + "%";
		String customerAddress = "%" + filterValues.getCustomerAddress() + "%";
		String emailAddress = "%" + filterValues.getEmailAddress() + "%";
		String additionalInfo = "%" + filterValues.getAdditionalInfo() + "%";
		String deviceModel = "%" + filterValues.getDeviceModel() + "%";
		String orderCondition = "%" + filterValues.getOrderCondition() + "%";
		String orderSource = "%" + filterValues.getOrderSource() + "%";
		String dateOrder = "%" + filterValues.getDateOrder() + "%";
		Direction sortDirection = Direction.ASC;
		if (!filterValues.getOrderDirection().equals("asc")) {
			sortDirection = Direction.DESC;
		}
		System.out.println("cond="+orderCondition);
		List<Order> orders = orderJdbcDao.findByFilter(id, customerName, phoneNumber, emailAddress, customerAddress,
				additionalInfo, deviceModel, orderCondition, orderSource, dateOrder,
				Sort.by(sortDirection, filterValues.getOrderByField()));	
		System.out.println("find finished");
		responseData.setConditions(Arrays.asList(Conditions.values()));
		responseData.setOrdersource(Arrays.asList(OrderSources.values()));
		
		responseData.setTotalQuantity(orders.size());
		for (Order order : orders) {
			if(order.getOrderAdditions()!=null&&order.getOrderAdditions().size()>0) {
				for (OrderAddition orderAddition : order.getOrderAdditions()) {
					Printer printer=printerJdbcDao.findById(orderAddition.getPrinterId()).get();
					orderAddition.setFullName(printer.getFullName());
				}
			}
		}
		responseData.setOrders(orders);
		return responseData;
	}

	@ResponseBody
	@PostMapping("/admin/printer/remove")
	public SimpleResponse removePrinter(@RequestBody Long id) {
		SimpleResponse response = new SimpleResponse();
		Printer currentPrinter = printerJdbcDao.findById(id).get();
		if (!currentPrinter.getImageLink().equals("default.jpg")) {
			String sourcePath = FileStorageUtils.DIR_PRINTERS_IMAGE + currentPrinter.getImageLink();
			if (new File(sourcePath).exists()) {
				try {
					FileStorageUtils.removeImage(sourcePath);
				} catch (IOException e) {
					response.setResponseStatus("ERROR");
					response.setResponseMessage("Error removing image file...");
					logger.error(e.getLocalizedMessage());
					return response;
				}
			}
		}
		printerJdbcDao.deleteById(id);
		response.setResponseStatus("OK");
		return response;
	}

	@ResponseBody
	@PostMapping("/admin/cartridge/remove")
	public SimpleResponse removeCartridge(@RequestBody Long id) {
		SimpleResponse response = new SimpleResponse();
		Cartridge currentCartridge = cartridgeJdbcDao.findById(id).get();
		if (!currentCartridge.getImageLink().equals("default.jpg")) {
			String sourcePath = FileStorageUtils.DIR_CARTRIDGES_IMAGE + currentCartridge.getImageLink();
			if (new File(sourcePath).exists()) {
				try {
					FileStorageUtils.removeImage(sourcePath);
				} catch (IOException e) {
					response.setResponseStatus("ERROR");
					response.setResponseMessage("Error removing image file...");
					logger.error(e.getLocalizedMessage());
					return response;
				}
			}
		}
		cartridgeJdbcDao.deleteById(id);
		response.setResponseStatus("OK");
		return response;
	}

	@ResponseBody
	@PostMapping("/admin/part/remove")
	public SimpleResponse removeSparePart(@RequestBody Long id) {
		SimpleResponse response = new SimpleResponse();
		SparePart currentPart = sparePartJdbcDao.findById(id).get();
		if (!currentPart.getImageLink().equals("default.jpg")) {
			String sourcePath = FileStorageUtils.DIR_PARTS_IMAGE + currentPart.getImageLink();
			if (new File(sourcePath).exists()) {
				try {
					FileStorageUtils.removeImage(sourcePath);
				} catch (IOException e) {
					response.setResponseStatus("ERROR");
					response.setResponseMessage("Error removing image file...");
					logger.error(e.getLocalizedMessage());
					return response;
				}
			}
		}
		sparePartJdbcDao.deleteById(id);
		response.setResponseStatus("OK");
		return response;
	}

	@ResponseBody
	@GetMapping("/admin/image")
	public SimpleResponse findImageFile(@RequestParam(value = "fileName") String fileName,
			@RequestParam(value = "path") String path) {
		SimpleResponse response = new SimpleResponse();
		String fullFilePath;
		switch (path) {
		case "printer":
			fullFilePath = FileStorageUtils.DIR_PRINTERS_IMAGE;
			response.setResponseMessage("/images/printers/" + fileName + ".jpg");
			break;
		case "cartridge":
			fullFilePath = FileStorageUtils.DIR_CARTRIDGES_IMAGE;
			response.setResponseMessage("/images/cartridges/" + fileName + ".jpg");
			break;
		case "part":
			fullFilePath = FileStorageUtils.DIR_PARTS_IMAGE;
			response.setResponseMessage("/images/spareparts/" + fileName + ".jpg");
			break;
		default:
			fullFilePath = FileStorageUtils.DIR_TO_UPLOAD;
		}
		fullFilePath += fileName + ".jpg";
		if (new File(fullFilePath).exists()) {
			response.setResponseStatus("OK");
		} else
			response.setResponseStatus("ERROR");

		System.out.println("filename=" + fullFilePath);
		return response;
	}

	@ResponseBody
	@PostMapping("/admin/comment")
	public SimpleResponse commentChangePublic(@RequestBody Long id) {
		SimpleResponse response = new SimpleResponse();
		Comment comment = commentJdbcDao.findById(id).get();
		if (comment != null) {
			if (comment.getIsPublic()) {
				comment.setIsPublic(false);
			} else
				comment.setIsPublic(true);
			commentJdbcDao.save(comment);
			response.setResponseStatus("OK");
		}
		return response;
	}

	@ResponseBody
	@PostMapping("/admin/order/attributes")
	public SimpleResponse orderChangeAttribute(@RequestBody OrderAttributeRequest orderAttributeRequest) {
		SimpleResponse response = new SimpleResponse();
		Order order = orderJdbcDao.findById(orderAttributeRequest.getId()).get();
		order.setOrderCondition(Conditions.valueOf(orderAttributeRequest.getAttributeValue()));
		orderJdbcDao.save(order); 
		response.setResponseStatus("OK");
		return response;
	}
}
