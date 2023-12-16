package com.speedexam.speedex.service;

import com.speedexam.speedex.model.Exam;
import com.speedexam.speedex.model.Option;
import com.speedexam.speedex.model.Question;
import com.speedexam.speedex.repository.ExamRepository;
import com.speedexam.speedex.repository.OptionRepository;
import com.speedexam.speedex.repository.QuestionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {

    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private OptionRepository optionRepository;
    @Mock
    private ExamRepository examRepository;

    private QuestionService underTest ;

    @BeforeEach
    void setUp() {
        underTest = new QuestionService(questionRepository,examRepository,optionRepository);
    }


    @Test
    void testGetQuestionById_WhenQuestionExists() throws Exception {
        Long questionId = 1L;
        Question question = new Question();
        question.setId(questionId);

        Mockito.when(questionRepository.findById(questionId)).thenReturn(Optional.of(question));

        Question result = underTest.getQuestionById(questionId);

        Assertions.assertEquals(question, result);
    }

    @Test
    void testAddQuestion() {
        Question questionToAdd = new Question();

        Mockito.when(questionRepository.save(Mockito.any(Question.class))).thenReturn(questionToAdd);

        Question result = underTest.addQuestion(questionToAdd);

        Assertions.assertEquals(questionToAdd, result);
    }

    @Test
    void testUpdateQuestion_WhenQuestionExists() {
        Long questionId = 1L;
        Question existingQuestion = new Question();
        existingQuestion.setId(questionId);

        Question updatedQuestion = new Question();
        updatedQuestion.setId(questionId);
        updatedQuestion.setName("Updated Name");

        Mockito.when(questionRepository.findById(questionId)).thenReturn(Optional.of(existingQuestion));
        Mockito.when(questionRepository.save(existingQuestion)).thenReturn(updatedQuestion);

        Question result = underTest.updateQuestion(questionId, updatedQuestion);

        Assertions.assertEquals(updatedQuestion.getName(), result.getName());
    }

    @Test
    void testDeleteQuestion() {
        Long questionId = 1L;

        underTest.deleteQuestion(questionId);

        verify(questionRepository, times(1)).deleteById(questionId);
    }

    @Test
    void testGetAllQuestions() {
        List<Question> questions = new ArrayList<>();
        // Populate 'questions' with some test data

        when(questionRepository.findAll()).thenReturn(questions);

        List<Question> result = underTest
                .getAllQuestions();

        assertEquals(questions.size(), result.size());
    }

    @Test
    void testGetQuestionsByExamId_WhenExamExists() {
        Long examId = 1L;
        Exam exam = new Exam();
        // Populate 'exam' with some test data
        Mockito.when(examRepository.findById(examId)).thenReturn(Optional.of(exam));

        List<Question> result = underTest.getQuestionsByExamId(examId);

        // Add assertions based on the behavior of your method
    }

    @Test
    void testAddOptionsToQuestion_WhenQuestionExists() throws Exception {
        Long questionId = 1L;
        Question question = new Question();
        Mockito.when(questionRepository.findById(questionId)).thenReturn(Optional.of(question));

        List<Option> options = new ArrayList<>();
        Option option1 = new Option();
        options.add(option1);

        Option option2 = new Option();
        options.add(option2);

        Mockito.when(optionRepository.save(Mockito.any(Option.class))).thenAnswer(invocation -> {
            Option option = invocation.getArgument(0);
            // Simulate saving the option
            return option;
        });

        Question result = underTest.addOptionsToQuestion(questionId, options);

        Assertions.assertEquals(2, result.getOptions().size()); // Assert that options were added
        Mockito.verify(optionRepository, Mockito.times(2)).save(Mockito.any(Option.class)); // Verify option save calls
    }
}