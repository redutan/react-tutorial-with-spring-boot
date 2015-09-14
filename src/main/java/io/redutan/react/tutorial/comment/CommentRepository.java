package io.redutan.react.tutorial.comment;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author redutan
 * @since 2015. 9. 14.
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
