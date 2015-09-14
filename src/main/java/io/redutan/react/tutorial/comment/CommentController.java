package io.redutan.react.tutorial.comment;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author redutan
 * @since 2015. 9. 14.
 */
@RestController
public class CommentController {

    @RequestMapping(value = "/comments", method = GET)
    @ResponseStatus(OK)
    public List<Comment> getCommentList() {
        List<Comment> commentList = new ArrayList<>();
        commentList.add(new Comment(1L, "Jung Myeongju", "It is comment"));
        commentList.add(new Comment(2L, "Kim Minjeong", "It is *Another* comment"));
        return commentList;
    }
}
