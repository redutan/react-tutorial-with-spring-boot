package io.redutan.react.tutorial.comment;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.redutan.react.tutorial.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

    @Autowired
    ObjectMapper objectMapper;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    /**
     * 댓글 목록 API 테스트
     */
    @Test
    public void testGetCommentList() throws Exception {
        // Given

        // When
        ResultActions result = mockMvc.perform(get("/comments"));

        // Then
        result.andDo(print());
        result.andExpect(status().isOk());
    }

    @Test
    public void testCreateComment() throws Exception {
        // Given
        final String author = "Jung Myeongju";
        final String text = "It is comment";
        CommentDto.Create create = new CommentDto.Create();
        create.setAuthor(author);
        create.setText(text);

        // When
        ResultActions result = mockMvc.perform(post("/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(create)));

        // Then
        result.andDo(print());
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.[0].author", is(author)));
        result.andExpect(jsonPath("$.[0].text", is(text)));
    }
}