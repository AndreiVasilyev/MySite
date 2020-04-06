package by.bobruisk.homework.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import by.bobruisk.homework.model.Comment;

public interface ICommentJdbcDAO extends JpaRepository<Comment, Long> {

}
