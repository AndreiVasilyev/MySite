package by.bobruisk.homework.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import by.bobruisk.homework.dao.ICartridgeJdbcDAO;
import by.bobruisk.homework.dao.IPrinterJdbcDAO;
import by.bobruisk.homework.dao.ISparePartJdbcDAO;
import by.bobruisk.homework.model.Cartridge;
import by.bobruisk.homework.model.Printer;
import by.bobruisk.homework.model.SparePart;
import by.bobruisk.homework.model.Vendors;
import by.bobruisk.homework.utils.FileStorageUtils;

@Controller
public class AdminAddDataController {

	@Autowired
	private IPrinterJdbcDAO printerJdbcDao;
	@Autowired
	private ICartridgeJdbcDAO cartridgeJdbcDao;
	@Autowired
	private ISparePartJdbcDAO sparePartJdbcDao;

	private static Logger logger = LoggerFactory.getLogger(OrganizationsController.class);

	@GetMapping("/admin/printer/edit")
	public String getPrintersPage(@RequestParam(required = false) Long id, Model model) {
		if (id != null) {
			Printer printer = printerJdbcDao.findById(id).get();
			model.addAttribute("newPrinter", printer);
		} else
			model.addAttribute("newPrinter", new Printer());

		model.addAttribute("allCartridges", cartridgeJdbcDao.findAll(Sort.by("modelName")));
		model.addAttribute("allSpareParts", sparePartJdbcDao.findAll(Sort.by("sparePartName")));
		model.addAttribute("allVendors", Vendors.values());
		return "printerAdmin";
	}

	@GetMapping("/admin/cartridge/edit")
	public String getCartridgePage(@RequestParam(required = false) Long id, Model model) {
		if (id != null) {
			Cartridge cartridge = cartridgeJdbcDao.findById(id).get();
			model.addAttribute("newCartridge", cartridge);
		} else
			model.addAttribute("newCartridge", new Cartridge());
		model.addAttribute("allPrinters", printerJdbcDao.findAll(Sort.by("vendorName")));
		model.addAttribute("allSpareParts", sparePartJdbcDao.findAll(Sort.by("sparePartName")));
		model.addAttribute("allVendors", Vendors.values());
		return "cartridgeAdmin";
	}

	@GetMapping("/admin/part/edit")
	public String getSparePartPage(@RequestParam(required = false) Long id, Model model) {
		if (id != null) {
			SparePart part = sparePartJdbcDao.findById(id).get();
			model.addAttribute("newSparePart", part);
		} else
			model.addAttribute("newSparePart", new SparePart());
		model.addAttribute("allPrinters", printerJdbcDao.findAll(Sort.by("vendorName")));
		model.addAttribute("allCartridges", cartridgeJdbcDao.findAll(Sort.by("modelName")));
		model.addAttribute("allVendors", Vendors.values());
		return "partAdmin";
	}

	@PostMapping("/admin/printers/save")
	public String saveNewPrinter(@ModelAttribute("newPrinter") @Valid Printer newPrinter, BindingResult bindingResult,
			@RequestParam(name = "imageFile", required = false) MultipartFile imageFile, Model model) {
		if (!bindingResult.hasErrors()) {
			if (imageFile != null && !imageFile.isEmpty()) {
				if (!newPrinter.getImageLink().isEmpty()) {
					String sourcePath = FileStorageUtils.DIR_PRINTERS_IMAGE + newPrinter.getImageLink();
					if (!(new File(sourcePath)).exists()) {
						try {
							FileStorageUtils.saveImage(imageFile, sourcePath);
						} catch (IOException e) {
							model.addAttribute("errorMessage", "Ошибка передачи файла с изображением");
							model.addAttribute("allCartridges", cartridgeJdbcDao.findAll(Sort.by("modelName")));
							model.addAttribute("allSpareParts", sparePartJdbcDao.findAll(Sort.by("sparePartName")));
							model.addAttribute("allVendors", Vendors.values());
							model.addAttribute("newPrinter", newPrinter);
							logger.error(e.getLocalizedMessage());
							return "printerAdmin";
						}
					}
				}
			}
			printerJdbcDao.save(preparePrinterEntity(newPrinter));
			return "redirect:/admin";
		}
		model.addAttribute("allCartridges", cartridgeJdbcDao.findAll(Sort.by("modelName")));
		model.addAttribute("allSpareParts", sparePartJdbcDao.findAll(Sort.by("sparePartName")));
		model.addAttribute("allVendors", Vendors.values());
		model.addAttribute("newPrinter", newPrinter);
		return "printerAdmin";
	}

	@PostMapping("/admin/cartridges/save")
	public String saveNewCartridge(@ModelAttribute("newCartridge") @Valid Cartridge newCartridge,
			BindingResult bindingResult, @RequestParam(name = "imageFile", required = false) MultipartFile imageFile,
			Model model) {
		if (!bindingResult.hasErrors()) {
			if (imageFile != null && !imageFile.isEmpty()) {
				if (!newCartridge.getImageLink().isEmpty()) {
					String sourcePath = FileStorageUtils.DIR_CARTRIDGES_IMAGE + newCartridge.getImageLink();
					if (!(new File(sourcePath)).exists()) {
						try {
							FileStorageUtils.saveImage(imageFile, sourcePath);
						} catch (IOException e) {
							model.addAttribute("errorMessage", "Ошибка передачи файла с изображением");
							model.addAttribute("allPrinters", printerJdbcDao.findAll(Sort.by("vendorName")));
							model.addAttribute("allSpareParts", sparePartJdbcDao.findAll(Sort.by("sparePartName")));
							model.addAttribute("allVendors", Vendors.values());
							model.addAttribute("newCartridge", newCartridge);
							logger.error(e.getLocalizedMessage());
							return "cartridgeAdmin";
						}
					}
				}
			}
			Cartridge savedCartridge = cartridgeJdbcDao.save(prepareCartridgeEntity(newCartridge));
			if (newCartridge.getCompatiblePrintersId() != null && !newCartridge.getCompatiblePrintersId().isEmpty()) {
				for (Long id : newCartridge.getCompatiblePrintersId()) {
					Printer printer = printerJdbcDao.getOne(id);
					boolean flag = false;
					for (Cartridge compCartridge : printer.getCompatibleCartridges()) {
						if (compCartridge.getId().equals(savedCartridge.getId()))
							flag = true;
					}
					if (!flag) {
						printer.getCompatibleCartridges().add(savedCartridge);
						printerJdbcDao.save(printer);
					}
				}
			}
			if (newCartridge.getNativePrintersId() != null && !newCartridge.getNativePrintersId().isEmpty()) {
				for (Long id : newCartridge.getNativePrintersId()) {
					Printer printer = printerJdbcDao.getOne(id);
					if (printer.getOriginalCartridge() == null || printer.getOriginalCartridge().getId().equals(5000L))
						printer.setOriginalCartridge(savedCartridge);
					printerJdbcDao.save(printer);
				}
			}
			return "redirect:/admin";
		}
		model.addAttribute("allPrinters", printerJdbcDao.findAll(Sort.by("vendorName")));
		model.addAttribute("allSpareParts", sparePartJdbcDao.findAll(Sort.by("sparePartName")));
		model.addAttribute("allVendors", Vendors.values());
		model.addAttribute("newCartridge", newCartridge);
		return "cartridgeAdmin";
	}

	@PostMapping("/admin/spareparts/save")
	public String saveNewSparePart(@ModelAttribute("newSparePart") @Valid SparePart newSparePart,
			BindingResult bindingResult, @RequestParam(name = "imageFile", required = false) MultipartFile imageFile,
			Model model) {
		if (!bindingResult.hasErrors()) {
			if (imageFile != null && !imageFile.isEmpty()) {
				if (!newSparePart.getImageLink().isEmpty()) {
					String sourcePath = FileStorageUtils.DIR_PARTS_IMAGE + newSparePart.getImageLink();
					if (!(new File(sourcePath)).exists()) {
						try {
							FileStorageUtils.saveImage(imageFile, sourcePath);
						} catch (IOException e) {
							model.addAttribute("errorMessage", "Ошибка передачи файла с изображением");
							model.addAttribute("allPrinters", printerJdbcDao.findAll(Sort.by("vendorName")));
							model.addAttribute("allCartridges", cartridgeJdbcDao.findAll(Sort.by("modelName")));
							model.addAttribute("allVendors", Vendors.values());
							model.addAttribute("newSparePart", newSparePart);
							logger.error(e.getLocalizedMessage());
							return "partAdmin";
						}
					}
				}
			}
			SparePart savedPart = sparePartJdbcDao.save(preparePartEntity(newSparePart));
			if (newSparePart.getCompatiblePrintersId() != null && !newSparePart.getCompatiblePrintersId().isEmpty()) {
				for (Long id : newSparePart.getCompatiblePrintersId()) {
					Printer printer = printerJdbcDao.getOne(id);
					boolean flag = false;
					for (SparePart compPart : printer.getSpareParts()) {
						if (compPart.getId().equals(savedPart.getId()))
							flag = true;
					}
					if (!flag) {
						printer.getSpareParts().add(savedPart);
						printerJdbcDao.save(printer);
					}
				}
			}
			if (newSparePart.getCompatibleCartridgesId() != null
					&& !newSparePart.getCompatibleCartridgesId().isEmpty()) {
				for (Long id : newSparePart.getCompatibleCartridgesId()) {
					Cartridge cartridge = cartridgeJdbcDao.getOne(id);
					boolean flag = false;
					for (SparePart compPart : cartridge.getSpareParts()) {
						if (compPart.getId().equals(savedPart.getId()))
							flag = true;
					}
					if (!flag) {
						cartridge.getSpareParts().add(savedPart);
						cartridgeJdbcDao.save(cartridge);
					}
				}
			}
			return "redirect:/admin";
		}
		model.addAttribute("allPrinters", printerJdbcDao.findAll(Sort.by("vendorName")));
		model.addAttribute("allCartridges", cartridgeJdbcDao.findAll(Sort.by("modelName")));
		return "partAdmin";
	}

	private Printer preparePrinterEntity(Printer printer) {
		if (printer.getImageLink() == null || printer.getImageLink().isEmpty())
			printer.setImageLink("default.jpg");
		if (printer.getPopularityRating() == null)
			printer.setPopularityRating(1L);
		if (printer.getReflashCost() == null)
			printer.setReflashCost(0.0);
		if (printer.getOriginalCartridge() == null)
			printer.setOriginalCartridge(cartridgeJdbcDao.getOne(5000L));
		if (printer.getCompatibleCartridgesId() != null && !printer.getCompatibleCartridgesId().isEmpty()) {
			if (printer.getCompatibleCartridges() == null)
				printer.setCompatibleCartridges(new ArrayList<Cartridge>());
			for (Long id : printer.getCompatibleCartridgesId()) {
				Cartridge cart = cartridgeJdbcDao.getOne(id);
				printer.getCompatibleCartridges().add(cart);
			}
		}
		if (printer.getSparePartsId() != null && !printer.getSparePartsId().isEmpty()) {
			if (printer.getSpareParts() == null)
				printer.setSpareParts(new ArrayList<SparePart>());
			for (Long id : printer.getSparePartsId()) {
				SparePart part = sparePartJdbcDao.getOne(id);
				printer.getSpareParts().add(part);
			}
		}
		return printer;
	}

	private Cartridge prepareCartridgeEntity(Cartridge cartridge) {
		if (cartridge.getImageLink() == null || cartridge.getImageLink().isEmpty())
			cartridge.setImageLink("default.jpg");
		if (cartridge.getRefillCost() == null)
			cartridge.setRefillCost(0.0);
		if (cartridge.getChipCost() == null)
			cartridge.setChipCost(0.0);
		if (cartridge.getResource() == null)
			cartridge.setResource(0);
		if (cartridge.getTonerCapacity() == null)
			cartridge.setTonerCapacity(0);
		if (cartridge.getSparePartsId() != null && !cartridge.getSparePartsId().isEmpty()) {
			if (cartridge.getSpareParts() == null)
				cartridge.setSpareParts(new ArrayList<SparePart>());
			for (Long id : cartridge.getSparePartsId()) {
				SparePart part = sparePartJdbcDao.getOne(id);
				cartridge.getSpareParts().add(part);
			}
		}
		return cartridge;
	}

	private SparePart preparePartEntity(SparePart part) {
		if (part.getImageLink() == null || part.getImageLink().isEmpty())
			part.setImageLink("default.jpg");
		return part;
	}

}
