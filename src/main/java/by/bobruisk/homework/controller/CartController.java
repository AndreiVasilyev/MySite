package by.bobruisk.homework.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import by.bobruisk.homework.dao.IPrinterJdbcDAO;
import by.bobruisk.homework.model.Cart;
import by.bobruisk.homework.model.CartItem;
import by.bobruisk.homework.model.Printer;
import by.bobruisk.homework.model.ajax.CartAjaxResponseBody;
import by.bobruisk.homework.model.ajax.CartItemAjaxRequest;
import by.bobruisk.homework.model.ajax.CartItemsAjaxResponseBody;

@Controller
public class CartController {

	@Autowired
	private IPrinterJdbcDAO printerJdbcDAO;

	@GetMapping("/cart")
	public String cartPage(Model model) {
		return "cart";
	}

	@ResponseBody
	@PostMapping("/cart/add")
	public CartAjaxResponseBody addCartItemAjaxResponse(@RequestBody CartItemAjaxRequest item, HttpSession session) {
		CartAjaxResponseBody response = new CartAjaxResponseBody();
		Cart currentCart = new Cart();
		CartItem newItem = new CartItem();
		if (item == null) {
			response.setErrorMessage("отсутствуют товары для добавления в корзину");
			return response;
		} else {
			CartItem existingItem = null;
			if (session.getAttribute("cart") == null) {
				List<CartItem> cartItems = new ArrayList<CartItem>();
				currentCart.setCartItems(cartItems);
				session.setMaxInactiveInterval(-1);
				currentCart.setSessionId(session.getId());
			} else {
				currentCart = (Cart) session.getAttribute("cart");
				existingItem = currentCart.getCartItems().stream()
						.filter(cartItem -> cartItem.getPrinter().getId().equals(item.getPrinterId())).findFirst()
						.orElse(null);
				if (existingItem != null) {
					existingItem.setItemsQuantity(existingItem.getItemsQuantity() + item.getItemsQuantity());
					if (item.getCurrentPrice() != 0 && existingItem.getCurrentPrice() != 0) {
						existingItem.setCurrentPrice(Math.min(existingItem.getCurrentPrice(), item.getCurrentPrice()));
					} else if (existingItem.getCurrentPrice() == 0 && item.getCurrentPrice() != 0) {
						existingItem.setCurrentPrice(item.getCurrentPrice());
					}					
					existingItem.setFlashQuantity(existingItem.getFlashQuantity() + item.getFlashQuantity());
					if (!existingItem.getIsFlash()) {
						existingItem.setIsFlash(item.getIsFlash());
					}
					if (!existingItem.getIsChips()) {
						existingItem.setIsChips(item.getIsChips());
					}
				}

			}
			if (existingItem == null) {
				newItem.setPrinter(printerJdbcDAO.findById(item.getPrinterId()).orElse(new Printer()));
				newItem.setItemsQuantity(item.getItemsQuantity());
				newItem.setCurrentPrice(item.getCurrentPrice());
				newItem.setFlashQuantity(item.getFlashQuantity());
				newItem.setIsChips(item.getIsChips());
				newItem.setIsFlash(item.getIsFlash());
				currentCart.getCartItems().add(newItem);
			}

		}
		currentCart.setTotalCostAndTotalQantity();
		session.removeAttribute("cart");
		session.setAttribute("cart", currentCart);
		response.setErrorMessage("");
		response.setTotalCost(currentCart.getTotalCost());
		response.setTotalItemsQuantity(currentCart.getTotalItemsQuantity());
		return response;
	}

	@ResponseBody
	@GetMapping("/cart/remove/{printerId}/{flag}")
	public CartItemsAjaxResponseBody removeCartItemAjaxResponse(@PathVariable Long printerId,
			@PathVariable Boolean flag, HttpSession session) {
		CartItemsAjaxResponseBody response = new CartItemsAjaxResponseBody();
		Cart currentCart = null;
		if (session.getAttribute("cart") == null) {
			currentCart = new Cart();
			List<CartItem> cartItems = new ArrayList<CartItem>();
			currentCart.setCartItems(cartItems);
			session.setMaxInactiveInterval(-1);
			currentCart.setSessionId(session.getId());
		} else {
			currentCart = (Cart) session.getAttribute("cart");
			CartItem currentItem = currentCart.getCartItems().stream()
					.filter(cartItem -> cartItem.getPrinter().getId().equals(printerId)).findFirst().orElse(null);
			if (flag) {
				if (currentItem.getIsFlash()) {
					currentItem.setItemsQuantity(0);
					currentItem.setCurrentPrice(0D);
					currentItem.setIsChips(false);
				} else {
					currentCart.getCartItems().remove(currentItem);
				}
			} else {
				currentItem.setIsFlash(false);
				currentItem.setFlashQuantity(0);
			}
		}
		currentCart.setTotalCostAndTotalQantity();
		session.removeAttribute("cart");
		session.setAttribute("cart", currentCart);
		response.setErrorMessage("");
		response.setTotalCost(currentCart.getTotalCost());
		response.setTotalItemsQuantity(currentCart.getTotalItemsQuantity());
		response.setCartItems(currentCart.getCartItems());
		return response;
	};

	@ResponseBody
	@GetMapping("/cart/remove")
	public CartItemsAjaxResponseBody removeCartAjaxResponse(HttpSession session) {
		CartItemsAjaxResponseBody response = new CartItemsAjaxResponseBody();
		Cart currentCart = new Cart();
		List<CartItem> cartItems = new ArrayList<CartItem>();
		currentCart.setCartItems(cartItems);
		session.setMaxInactiveInterval(-1);
		currentCart.setSessionId(session.getId());
		currentCart.setTotalCostAndTotalQantity();
		session.removeAttribute("cart");
		session.setAttribute("cart", currentCart);
		response.setErrorMessage("");
		response.setTotalCost(currentCart.getTotalCost());
		response.setTotalItemsQuantity(currentCart.getTotalItemsQuantity());
		response.setCartItems(currentCart.getCartItems());
		return response;
	};

	@ResponseBody
	@PostMapping(value = "/cart/update")
	public CartAjaxResponseBody updateCartAjaxResponse(@RequestBody CartItemAjaxRequest cartItem, HttpSession session) {
		CartAjaxResponseBody response = new CartAjaxResponseBody();
		Cart currentCart = null;	
		if (cartItem != null) {			
			currentCart = (Cart) session.getAttribute("cart");
			Long printerId = cartItem.getPrinterId();
			CartItem existingItem = currentCart.getCartItems().stream()
					.filter(item -> item.getPrinter().getId().equals(printerId)).findFirst().orElse(null);
			if (!cartItem.getIsFlash()) {
				existingItem.setItemsQuantity(cartItem.getItemsQuantity());
				existingItem.setCurrentPrice(cartItem.getCurrentPrice());				
				existingItem.setIsChips(cartItem.getIsChips());
			} else {
				existingItem.setFlashQuantity(cartItem.getFlashQuantity());				
			}		
		}
		currentCart.setTotalCostAndTotalQantity();		
		session.removeAttribute("cart");
		session.setAttribute("cart", currentCart);
		response.setErrorMessage("");
		response.setTotalCost(currentCart.getTotalCost());
		response.setTotalItemsQuantity(currentCart.getTotalItemsQuantity());
		return response;
	}
}
