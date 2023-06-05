package BackEnd.SoloProject_ToDo.junitTester;

import BackEnd.SoloProject_ToDo.todo.controller.TodoController;
import BackEnd.SoloProject_ToDo.todo.entity.Todo;
import BackEnd.SoloProject_ToDo.todo.mapping.TodoMapping;
import BackEnd.SoloProject_ToDo.todo.service.TodoService;
import BackEnd.SoloProject_ToDo.todo.todoDto.TodoPostDto;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import javax.xml.transform.Result;

import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor;

import java.util.List;

import static BackEnd.SoloProject_ToDo.util.ApiDocumentUtils.getResponsePreProcessor;
import static BackEnd.SoloProject_ToDo.util.ApiDocumentUtils.getRequestPreProcessor;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TodoController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureMockMvc
public class TodoControllerMockUpTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private TodoService todoService;

    @MockBean
    private TodoMapping mapping;

    @Test
    void PostTest() throws Exception {
        // given
        TodoPostDto todoPostDto = new TodoPostDto();
        todoPostDto.setTitle("testTitle-1");
        todoPostDto.setTodoOrder(1);

        Todo todo = mapping.todoPostDtoToTodo(todoPostDto);

        given(todoService.createTodo(Mockito.any(Todo.class))).willReturn(todo);

        // when
        String requestBody = gson.toJson(todoPostDto); // todoPostDto를 JSON으로 변환
        ResultActions actions = mockMvc.perform(
                post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        );

        // then
        actions
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", is(startsWith("/"))))
                .andDo(document(
                        "post-todo",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestFields(
                                fieldWithPath("title").type(JsonFieldType.STRING).description("할 일"),
                                fieldWithPath("todoOrder").type(JsonFieldType.NUMBER).description("할 일 번호")
                        ),
                        responseHeaders(
                                headerWithName(HttpHeaders.LOCATION).description("Location header. 등록된 리소스의 URI")
                        )
                ));
    }

}
