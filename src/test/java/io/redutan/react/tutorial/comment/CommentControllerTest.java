package io.redutan.react.tutorial.comment;

import io.redutan.react.tutorial.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author redutan
 * @since 2015. 9. 14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Transactional
public class CommentControllerTest {

    @Autowired
    WebApplicationContext wac;

    @Before
    public void setUp() throws Exception {

    }

    /**
     * 댓글 목록 API 테스트
     */
    @Test
    public void testGetCommentList() throws Exception {
        // Given
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

        // When
        ResultActions result = mockMvc.perform(get("/comments"));

        // Then
        result.andDo(print());
        result.andExpect(status().isOk());
    }
}