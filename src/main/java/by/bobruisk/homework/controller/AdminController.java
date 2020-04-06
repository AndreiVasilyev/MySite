package by.bobruisk.homework.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import by.bobruisk.homework.dao.ICartridgeJdbcDAO;
import by.bobruisk.homework.dao.IPrinterJdbcDAO;
import by.bobruisk.homework.dao.ISparePartJdbcDAO;
import by.bobruisk.homework.model.Cartridge;
import by.bobruisk.homework.model.Printer;
import by.bobruisk.homework.model.SparePart;
import by.bobruisk.homework.model.Vendors;

@Controller
public class AdminController {

	@Autowired
	private IPrinterJdbcDAO printerJdbcDao;
	@Autowired
	private ICartridgeJdbcDAO cartridgeJdbcDao;
	@Autowired
	private ISparePartJdbcDAO sparePartJdbcDao;
	
	@GetMapping("/admin/printers")
	public String getPrintersPage(Model model) {
		model.addAttribute("newPrinter",new Printer());
		model.addAttribute("allCartridges", cartridgeJdbcDao.findAll(Sort.by("modelName")));
		model.addAttribute("allSpareParts",sparePartJdbcDao.findAll(Sort.by("sparePartName")));
		model.addAttribute("allVendors",Vendors.values());
		return "adminPrinters";
	}
	@GetMapping("/admin/cartridges")
	public String getCartridgePage(Model model) {
		model.addAttribute("newCartridge",new Cartridge());
		model.addAttribute("allPrinters",printerJdbcDao.findAll(Sort.by("vendorName")));
		model.addAttribute("allSpareParts",sparePartJdbcDao.findAll(Sort.by("sparePartName")));
		return "adminCartridges";
	}
	@GetMapping("/admin/spareparts")
	public String getSparePartPage(Model model) {
		model.addAttribute("newSparePart",new SparePart());
		model.addAttribute("allPrinters",printerJdbcDao.findAll(Sort.by("vendorName")));
		model.addAttribute("allCartridges", cartridgeJdbcDao.findAll(Sort.by("modelName")));
		return "adminSpareParts";
	}
	@PostMapping("/admin/printers/save")
	public String saveNewPrinter(@ModelAttribute("newPrinter") @Valid Printer newPrinter, BindingResult bindingResult, Model model) {
		System.out.println("in save Prt controller");
		if(!bindingResult.hasErrors()) {
			printerJdbcDao.save(newPrinter);
			System.out.println("data saved");
			return "redirect:/admin/printers";
		}
		model.addAttribute("allCartridges", cartridgeJdbcDao.findAll(Sort.by("modelName")));
		model.addAttribute("allSpareParts",sparePartJdbcDao.findAll(Sort.by("sparePartName")));
		model.addAttribute("allVendors",Vendors.values());
		return "adminPrinters";
	}
	@PostMapping("/admin/cartridges/save")
	public String saveNewCartridge(@ModelAttribute("newCartridge") @Valid Cartridge newCartridge, BindingResult bindingResult, Model model) {
		System.out.println("in save Cart controller");
		if(!bindingResult.hasErrors()) {
			cartridgeJdbcDao.save(newCartridge);
			System.out.println("data saved");
			return "redirect:/admin/cartridges";
		}
		
		model.addAttribute("allPrinters",printerJdbcDao.findAll(Sort.by("vendorName")));
		model.addAttribute("allSpareParts",sparePartJdbcDao.findAll(Sort.by("sparePartName")));
		return "adminCartridges";
	}
	@PostMapping("/admin/spareparts/save")
	public String saveNewSparePart(@ModelAttribute("newSparePart") @Valid SparePart newSparePart, BindingResult bindingResult, Model model) {
		System.out.println("in save Spare controller");
		if(!bindingResult.hasErrors()) {
			sparePartJdbcDao.save(newSparePart);
			System.out.println("data saved");
			return "redirect:/admin/spareparts";
		}		
		model.addAttribute("allPrinters",printerJdbcDao.findAll(Sort.by("vendorName")));
		model.addAttribute("allCartridges", cartridgeJdbcDao.findAll(Sort.by("modelName")));
		return "adminSparePartss";
	}
	
}
