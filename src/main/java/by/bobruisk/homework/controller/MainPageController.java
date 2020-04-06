package by.bobruisk.homework.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import by.bobruisk.homework.dao.ICommentJdbcDAO;
import by.bobruisk.homework.model.Cart;
import by.bobruisk.homework.model.CartItem;



@Controller
@SessionAttributes(names ="cart")
public class MainPageController {

	@Autowired
	private ICommentJdbcDAO commentJdbcDao;

	@GetMapping({ "/", "/index" })
	public String welcomPage(Model model, HttpSession session) {
		model.addAttribute("allComments", commentJdbcDao.findAll());		
		if (session.getAttribute("cart")==null) {
		Cart currentCart = new Cart();		
		List<CartItem> cartItems=new ArrayList<CartItem>();
		currentCart.setCartItems(cartItems);
		currentCart.setTotalCostAndTotalQantity();
		session.setMaxInactiveInterval(-1);
		currentCart.setSessionId(session.getId());
		session.setAttribute("cart", currentCart);			
		} 		
		return "index";
	}
	
}
