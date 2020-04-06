package by.bobruisk.homework.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import by.bobruisk.homework.model.SMSReport;

public interface ISmsReportJdbcDAO extends JpaRepository<SMSReport, Long> {

}
