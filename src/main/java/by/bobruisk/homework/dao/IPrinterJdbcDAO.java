package by.bobruisk.homework.dao;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import by.bobruisk.homework.model.Printer;
import by.bobruisk.homework.model.Vendors;

public interface IPrinterJdbcDAO extends JpaRepository<Printer, Long> {

	Page<Printer> findByVendorName(Vendors vendorName, Pageable pageNumber);

	Page<Printer> findByModelNameContaining(String modelName, Pageable pageNumber);

	Long countByModelNameContaining(String modelName);

	@Query("SELECT p FROM Printer p WHERE str(p.id) LIKE :id AND str(p.vendorName) LIKE :vendorName AND p.modelName LIKE :modelName AND p.imageLink LIKE :imageLink AND str(p.popularityRating) LIKE :rating AND p.isReflashable = :isReflashable AND str(p.reflashCost) LIKE :reflashCost AND str(p.originalCartridge.id) LIKE :originCartridge")
	List<Printer> findByFilter(@Param("id") String id, @Param("vendorName") String vendorName,
			@Param("modelName") String modelName, @Param("imageLink") String imageLink, @Param("rating") String rating,
			@Param("isReflashable") Boolean isReflashable, @Param("reflashCost") String reflashCost,
			@Param("originCartridge") String originCartridge, Sort sort);

	@Query("SELECT p FROM Printer p WHERE str(p.id) LIKE :id AND str(p.vendorName) LIKE :vendorName AND p.modelName LIKE :modelName AND p.imageLink LIKE :imageLink AND str(p.popularityRating) LIKE :rating AND str(p.reflashCost) LIKE :reflashCost AND str(p.originalCartridge.id) LIKE :originCartridge")
	List<Printer> findByFilter(@Param("id") String id, @Param("vendorName") String vendorName,
			@Param("modelName") String modelName, @Param("imageLink") String imageLink, @Param("rating") String rating,
			@Param("reflashCost") String reflashCost, @Param("originCartridge") String originCartridge, Sort sort);
}
