package by.bobruisk.homework.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import by.bobruisk.homework.model.SparePart;

public interface ISparePartJdbcDAO extends JpaRepository<SparePart, Long> {
	@Query("SELECT sp FROM SparePart sp WHERE str(sp.id) LIKE :id AND sp.sparePartName LIKE :sparePartName AND sp.imageLink LIKE :imageLink AND str(sp.replacementСost) LIKE :replacementСost")
	List<SparePart> findByFilter(@Param("id") String id, @Param("sparePartName") String sparePartName,
			@Param("imageLink") String imageLink, @Param("replacementСost") String replacementСost, Sort sort);
}
