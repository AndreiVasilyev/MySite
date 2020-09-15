package by.bobruisk.homework.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import by.bobruisk.homework.model.Cartridge;

public interface ICartridgeJdbcDAO extends JpaRepository<Cartridge, Long> {

	@Query("SELECT c FROM Cartridge c WHERE str(c.id) LIKE :id AND c.modelName LIKE :modelName AND c.imageLink LIKE :imageLink AND str(c.refillCost) LIKE :refillCost AND c.isChipped = :isChipped AND str(c.chipCost) LIKE :chipCost AND str(c.resource) LIKE :resource AND str(c.tonerCapacity) LIKE :tonerCapacity")
	List<Cartridge> findByFilter(@Param("id") String id, @Param("modelName") String modelName,
			@Param("imageLink") String imageLink, @Param("refillCost") String refillCost,
			@Param("isChipped") Boolean isChipped, @Param("chipCost") String chipCost, @Param("resource") String resource,
			@Param("tonerCapacity") String tonerCapacity, Sort sort);

	@Query("SELECT c FROM Cartridge c WHERE str(c.id) LIKE :id AND c.modelName LIKE :modelName AND c.imageLink LIKE :imageLink AND str(c.refillCost) LIKE :refillCost AND str(c.chipCost) LIKE :chipCost AND str(c.resource) LIKE :resource AND str(c.tonerCapacity) LIKE :tonerCapacity")
	List<Cartridge> findByFilter(@Param("id") String id, @Param("modelName") String modelName,
			@Param("imageLink") String imageLink, @Param("refillCost") String refillCost,
			@Param("chipCost") String chipCost, @Param("resource") String resource,
			@Param("tonerCapacity") String tonerCapacity, Sort sort);

}
