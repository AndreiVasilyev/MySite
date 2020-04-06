package by.bobruisk.homework.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import by.bobruisk.homework.model.Cartridge;

public interface ICartridgeJdbcDAO extends JpaRepository<Cartridge, Long> {

}
