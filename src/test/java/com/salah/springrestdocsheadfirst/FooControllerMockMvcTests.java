package com.salah.springrestdocsheadfirst;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureRestDocs
@AutoConfigureMockMvc
public class FooControllerMockMvcTests {

    @Autowired
    private MockMvc mockMvc;

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
