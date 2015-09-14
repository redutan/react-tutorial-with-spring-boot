package io.redutan.react.tutorial.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static io.redutan.react.tutorial.comment.CommentDto.*;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author redutan
 * @since 2015. 9. 14.
 */
@RestController
public class CommentController {

    @Autowired
    private CommentRepository repository;

    @RequestMapping(value = "/comments", method = GET)
    @ResponseStatus(OK)
    public List<Comment> getCommentList() {
        return repository.findAll();
    }

    @RequestMapping(value = "/comments", method = POST)
    @ResponseStatus(OK)
    @Transactional
    public List<Comment> createComment(@RequestBody @Valid Create create) {
        repository.save(create.toComment());
        return repository.findAll();
    }
}
