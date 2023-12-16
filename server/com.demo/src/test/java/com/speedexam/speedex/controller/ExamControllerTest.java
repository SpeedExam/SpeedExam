package com.speedexam.speedex.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.speedexam.speedex.model.Exam;
import com.speedexam.speedex.model.ExamT;
import com.speedexam.speedex.service.ExamService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = ExamController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class ExamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExamService examService;

    private Exam testExam;






    @BeforeEach
    void setUp() {

       testExam = Exam.builder()
                .id(1L)
                .name("TEST")
                .type(ExamT.QCM)
                .build();


    }
    @Test
    void createExam() throws Exception {
       given(examService.createExam(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        // Performing the MockMvc request
        ResultActions response = mockMvc.perform(post("/api/exam/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(testExam))
        );

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(testExam.getName())));

    }

    @Test
    void getAllExams() throws Exception {
        List<Exam> exams = new ArrayList<>();
        exams.add(testExam);
        when(examService.getAllExams()).thenReturn(exams);

        ResultActions response = mockMvc.perform(get("/api/exam/all")
                .contentType(MediaType.APPLICATION_JSON)
        );

        response.andExpect(MockMvcResultMatchers.status().isOk()
        );

    }

    @Test
    void getExamById() throws Exception {
        Long examId = 1L;
        when(examService.getExamById(examId)).thenReturn(testExam);

        ResultActions response = mockMvc.perform(get("/api/exam/1")
                .contentType(MediaType.APPLICATION_JSON)
        );

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(testExam.getName())));
    }


    @Test
    void updateExam() throws Exception {
        Long id = 1L;
        when(examService.getExamById(id)).thenReturn(testExam);
        Exam updatedExam = testExam;
        updatedExam.setName("updated");
        when(examService.updateExam(id,testExam)).thenReturn(updatedExam);

        ResultActions response = mockMvc.perform(put("/api/exam/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(updatedExam))
        );

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name",CoreMatchers.is(updatedExam.getName())))
        ;


    }

    @Test
    void deleteExam() throws Exception {
        Long id = 1L;
        doNothing().when(examService).deleteExam(id);

        ResultActions response = mockMvc.perform(delete("/api/exam/1")
                .contentType(MediaType.APPLICATION_JSON)
        );

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void addQuestionsToExam() throws Exception {
        Long examId = 1L;
        List<Long> questionIds = Arrays.asList(101L, 102L, 103L); // Example list of question IDs

        // Mock the behavior of addQuestionsToExam method
        when(examService.addQuestionsToExam(examId, questionIds)).thenReturn(testExam);

        // Perform the POST request
        ResultActions response = mockMvc.perform(post("/api/exam/1/addQuestions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(questionIds))
        );

        // Validate the response
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(testExam.getName())));
    }

}