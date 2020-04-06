package by.bobruisk.homework.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

import by.bobruisk.homework.dao.IOrderJdbcDAO;
import by.bobruisk.homework.dao.ISmsReportJdbcDAO;
import by.bobruisk.homework.model.Cart;
import by.bobruisk.homework.model.CartItem;
import by.bobruisk.homework.model.Conditions;
import by.bobruisk.homework.model.Order;
import by.bobruisk.homework.model.OrderAddition;
import by.bobruisk.homework.model.OrderSources;
import by.bobruisk.homework.model.SMSReport;
import by.bobruisk.homework.model.ajax.OrderAjaxResponseBody;
import by.bobruisk.homework.service.EmailServiceImpl;
import by.bobruisk.homework.service.SMSServiceImpl;

@Controller
public class OrderController {

	@Autowired
	private IOrderJdbcDAO orderJdbcDao;
	@Autowired
	private ISmsReportJdbcDAO smsReportJdbcDao;
	@Autowired
	private EmailServiceImpl emailService;
	@Autowired
	private SMSServiceImpl smsService;

	private static Logger logger = LoggerFactory.getLogger(OrganizationsController.class);
	
	private final Long THREE_HOUR=10800000L;

	@ResponseBody
	@PostMapping("/orders")
	public OrderAjaxResponseBody saveNewOrder(@RequestBody Order order, HttpSession session) {
		OrderAjaxResponseBody ajaxResponse = new OrderAjaxResponseBody();
		order.setOrderCondition(Conditions.RECIEVED);
		if (order.getOrderSource().equals(OrderSources.FULL_ORDER) && session.getAttribute("cart") != null) {
			Cart currentCart = (Cart) session.getAttribute("cart");
			if (currentCart.getCartItems().size() > 0) {
				Set<OrderAddition> cartItems = new HashSet<OrderAddition>();
				for (int i = 0; i < currentCart.getCartItems().size(); i++) {
					OrderAddition cartItem = new OrderAddition();
					CartItem cartItemSource = currentCart.getCartItems().get(i);
					cartItem.setPrinterId(cartItemSource.getPrinter().getId());
					cartItem.setItemsQuantity(cartItemSource.getItemsQuantity());
					cartItem.setCurrentPrice(cartItemSource.getCurrentPrice());
					cartItem.setIsChips(cartItemSource.getIsChips());
					cartItem.setChipPrice(
							cartItem.getIsChips() ? cartItemSource.getPrinter().getOriginalCartridge().getChipCost()
									: 0D);
					cartItem.setIsFlash(cartItemSource.getIsFlash());
					cartItem.setFlashQuantity(cartItem.getIsFlash() ? cartItemSource.getFlashQuantity() : 0);
					cartItem.setFlashPrice(cartItem.getIsFlash() ? cartItemSource.getPrinter().getReflashCost() : 0D);
					cartItem.setOrder(order);
					cartItems.add(cartItem);
				}
				order.setOrderAdditions(cartItems);
			}
		}
		order.getDateOrder().setTime(order.getDateOrder().getTime()+THREE_HOUR);
		order = orderJdbcDao.save(order);
		if (order != null) {
			ajaxResponse.setErrorMessage("");
			ajaxResponse.setOrderId(order.getId());
		} else {
			ajaxResponse.setErrorMessage("Ошибка обращения к базе данных. Заказ не сохранен!");
		}
		try {
			emailService.sendSimpleMessage(order.getOrderSource().name(), constructEmailMessage(order));
		} catch (MailSendException e) {
			logger.error(e.getLocalizedMessage());
		}
		try {
			SMSReport report = smsService
					.sendSMS("Поступление заказ №" + order.getId() + ". Тип заказ: " + order.getOrderSource());
			report.setSmsDate(new Date(new Date().getTime()+THREE_HOUR));
			smsReportJdbcDao.save(report);
		} catch (HttpClientErrorException e) {
			logger.error(e.getResponseBodyAsString());
		}
		return ajaxResponse;
	}

	private String constructEmailMessage(Order order) {
		String emailMessage = "Имя:" + order.getCustomerName() + "\n";
		emailMessage += "Адрес:" + order.getCustomerAddress() + "\n";
		emailMessage += "Телефон:" + order.getPhoneNumber() + "\n";
		emailMessage += "Модель:" + order.getDeviceModel() + "\n";
		emailMessage += "Тип заказа:" + order.getOrderSource() + "\n";
		emailMessage += "Дата:" + order.getDateOrder() + "\n";
		return emailMessage;
	}
}
