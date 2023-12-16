package com.speedexam.speedex.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.speedexam.speedex.model.Option;
import com.speedexam.speedex.model.Question;
import com.speedexam.speedex.service.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = QuestionController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class QuestionControllerTest {

    private MockMvc mockMvc;

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private QuestionController questionController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(questionController).build();
    }

    @Test
    void getQuestionById() throws Exception {
        Long questionId = 1L;
        Question testQuestion = new Question(); // Replace with your test question

        when(questionService.getQuestionById(questionId)).thenReturn(testQuestion);

        ResultActions response = mockMvc.perform(get("/api/question/1")
                .contentType(MediaType.APPLICATION_JSON)
        );

        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(testQuestion.getName())); // Replace with your property for comparison
    }

    @Test
    void addQuestion() throws Exception {
        Question testQuestion = new Question(); // Replace with your test question

        when(questionService.addQuestion(any(Question.class))).thenReturn(testQuestion);

        ResultActions response = mockMvc.perform(post("/api/question/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(testQuestion))
        );

        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(testQuestion.getName())); // Replace with your property for comparison
    }

    @Test
    void updateQuestion() throws Exception {
        Long questionId = 1L;
        Question testQuestion = new Question(); // Replace with your test question

        when(questionService.updateQuestion(eq(questionId), any(Question.class))).thenReturn(testQuestion);

        ResultActions response = mockMvc.perform(put("/api/question/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(testQuestion))
        );

        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(testQuestion.getName())); // Replace with your property for comparison
    }

    @Test
    void deleteQuestion() throws Exception {
        Long questionId = 1L;
        doNothing().when(questionService).deleteQuestion(questionId);

        ResultActions response = mockMvc.perform(delete("/api/question/delete/1"));

        response.andExpect(status().isOk());
        verify(questionService, times(1)).deleteQuestion(questionId);
    }

    @Test
    void getAllQuestions() throws Exception {
        List<Question> testQuestions = Arrays.asList(new Question(), new Question()); // Replace with your test questions

        when(questionService.getAllQuestions()).thenReturn(testQuestions);

        ResultActions response = mockMvc.perform(get("/api/question/all")
                .contentType(MediaType.APPLICATION_JSON)
        );

        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value(testQuestions.get(0).getName())); // Replace with your property for comparison
    }

    @Test
    void getQuestionsByExam() throws Exception {
        Long examId = 1L;
        List<Question> testQuestions = Arrays.asList(new Question(), new Question()); // Replace with your test questions

        when(questionService.getQuestionsByExamId(examId)).thenReturn(testQuestions);

        ResultActions response = mockMvc.perform(get("/api/question/byExam/1")
                .contentType(MediaType.APPLICATION_JSON)
        );

        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value(testQuestions.get(0).getName())); // Replace with your property for comparison
    }

    @Test
    void addOptionsToQuestion() throws Exception {
        Long questionId = 1L;
        List<Option> testOptions = Collections.singletonList(new Option()); // Replace with your test options

        when(questionService.addOptionsToQuestion(questionId, testOptions)).thenReturn(new Question());

        ResultActions response = mockMvc.perform(post("/api/question/1/addOptions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(testOptions))
        );

        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
