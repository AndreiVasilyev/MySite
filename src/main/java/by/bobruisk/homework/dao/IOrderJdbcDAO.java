package by.bobruisk.homework.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import by.bobruisk.homework.model.Order;

public interface IOrderJdbcDAO extends JpaRepository<Order, Long> {

	@Query("SELECT o FROM Order o WHERE str(o.id) LIKE :id AND o.customerName LIKE :customerName AND o.phoneNumber LIKE :phoneNumber AND o.emailAddress LIKE :emailAddress AND o.customerAddress LIKE :customerAddress AND o.additionalInfo LIKE :additionalInfo AND o.deviceModel LIKE :deviceModel AND str(o.orderCondition) LIKE :orderCondition AND str(o.orderSource) LIKE :orderSource AND str(o.dateOrder) LIKE :dateOrder")
	List<Order> findByFilter(@Param("id") String id, @Param("customerName") String customerName,
			@Param("phoneNumber") String phoneNumber, @Param("emailAddress") String emailAddress,
			@Param("customerAddress") String customerAddress, @Param("additionalInfo") String additionalInfo,
			@Param("deviceModel") String deviceModel, @Param("orderCondition") String orderCondition,
			@Param("orderSource") String orderSource, @Param("dateOrder") String dateOrder, Sort sort);
}

