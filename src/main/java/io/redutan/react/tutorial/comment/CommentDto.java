package io.redutan.react.tutorial.comment;

import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @author redutan
 * @since 2015. 9. 14.
 */
public class CommentDto {
    @Data
    public static class Create {
        private String author;
        private String text;

        public Comment toComment() {
            Comment comment = new Comment();
            BeanUtils.copyProperties(this, comment);
            return comment;
        }
    }
}
