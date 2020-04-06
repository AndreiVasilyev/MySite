package by.bobruisk.homework.dao;







import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import by.bobruisk.homework.model.Printer;
import by.bobruisk.homework.model.Vendors;

public interface IPrinterJdbcDAO extends JpaRepository<Printer, Long> {

	Page<Printer> findByVendorName(Vendors vendorName, Pageable pageNumber);
	Page<Printer> findByModelNameContaining(String modelName, Pageable pageNumber);
	Long countByModelNameContaining(String modelName);
	
	
}
