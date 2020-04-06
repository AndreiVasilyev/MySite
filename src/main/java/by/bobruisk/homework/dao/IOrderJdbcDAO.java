package by.bobruisk.homework.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import by.bobruisk.homework.model.Order;

public interface IOrderJdbcDAO extends JpaRepository<Order, Long> {

}
