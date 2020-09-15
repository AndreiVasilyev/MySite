package by.bobruisk.homework.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import by.bobruisk.homework.model.Comment;

public interface ICommentJdbcDAO extends JpaRepository<Comment, Long> {

	@Query("SELECT c FROM Comment c WHERE str(c.id) LIKE :id AND c.authorName LIKE :authorName AND c.companyName LIKE :companyName AND c.phoneNumber LIKE :phoneNumber AND c.emailAddress LIKE :emailAddress AND c.message LIKE :message AND c.isPublic = :isPublic AND str(c.dateMessage) LIKE :dateMessage")
	List<Comment> findByFilter(@Param("id") String id, @Param("authorName") String authorName,
			@Param("companyName") String companyName, @Param("phoneNumber") String phoneNumber,
			@Param("emailAddress") String emailAddress, @Param("message") String message,
			@Param("isPublic") Boolean isPublic, @Param("dateMessage") String dateMessage, Sort sort);

	@Query("SELECT c FROM Comment c WHERE str(c.id) LIKE :id AND c.authorName LIKE :authorName AND c.companyName LIKE :companyName AND c.phoneNumber LIKE :phoneNumber AND c.emailAddress LIKE :emailAddress AND c.message LIKE :message AND str(c.dateMessage) LIKE :dateMessage")
	List<Comment> findByFilter(@Param("id") String id, @Param("authorName") String authorName,
			@Param("companyName") String companyName, @Param("phoneNumber") String phoneNumber,
			@Param("emailAddress") String emailAddress, @Param("message") String message,
			@Param("dateMessage") String dateMessage, Sort sort);

}
