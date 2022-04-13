package com.salah.springrestdocsheadfirst;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class FooControllerMockMvcTests {
    private MockMvc mockMvc;


    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setUp(RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(documentationConfiguration(restDocumentation))
//                .alwaysDo(document("{method-name}",
//                        preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
                .build();
    }

    @Test
    public void should_return_bar_when_calling_foo() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/foo"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("boo"))
                .andDo(document("index"));

    }

    @Test
    public void should_return_person_when_calling_foo() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/person")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"name\":\"foo\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers
                        .content().string("{\"name\":\"foo\",\"surname\":\"bla\"}"))
                .andDo(document("person"
                        ,
                        PayloadDocumentation.requestFields(
                                PayloadDocumentation.fieldWithPath("name")
                                        .type(JsonFieldType.STRING)
                                        .description("person request name")
                        ),
                        PayloadDocumentation.responseFields(
                                PayloadDocumentation.fieldWithPath("name")
                                        .type(JsonFieldType.STRING)
                                        .description("person's name"),
                                PayloadDocumentation.fieldWithPath("surname")
                                        .type(JsonFieldType.STRING)
                                        .description("person's surname")
                        )
                ));


    }
}
