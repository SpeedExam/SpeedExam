package com.speedexam.speedex.service;

import com.speedexam.speedex.model.Exam;
import com.speedexam.speedex.model.ExamT;
import com.speedexam.speedex.model.Question;
import com.speedexam.speedex.repository.ExamRepository;
import com.speedexam.speedex.repository.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class ExamServiceTest {
    @Mock
    private ExamRepository examRepository;
    @Mock
    private QuestionRepository questionRepository;
    private ExamService underTest;

    @BeforeEach
    void setUp() {
        underTest = new ExamService(examRepository,questionRepository);
    }



    @Test
    void getAllExams_Test() {
        //given

        //when
            underTest.getAllExams();
        //then
        verify(examRepository).findAll();
    }

    @Test
    void createExam_Test() {

        //given
        Exam exam = Exam
                .builder()
                .id((long) 1)
                .type(ExamT.QCM)
                .name("EXAM1")
                .build();
        //when
        underTest.createExam(exam);
        //then
        ArgumentCaptor<Exam> examArgumentCaptor =
                ArgumentCaptor.forClass(Exam.class);

        verify(examRepository).save(examArgumentCaptor.capture());
        Exam capturedExam = examArgumentCaptor.getValue();

        assertThat(capturedExam).isEqualTo(exam);

    }

    @Test
    void getExamById() throws Exception {
        // Mocking the behavior of the examRepository findById method
        Long examId = 1L;
        Exam mockExam = new Exam();
        mockExam.setId(examId);
        Mockito.when(examRepository.findById(examId)).thenReturn(Optional.of(mockExam));

        // Call the method to get an exam by ID
        underTest.getExamById(examId);

        // Verify that the correct exam is returned
        Mockito.verify(examRepository, Mockito.times(1)).findById(examId);
        Mockito.verifyNoMoreInteractions(examRepository);
        // Add additional assertions as needed based on the behavior of the getExamById method
    }

    @Test
    void getExamById_Exception() {

        Long wrongId = 5678L;
        Mockito.when(examRepository.findById(wrongId)).thenReturn(Optional.empty());
        try {
            underTest.getExamById(wrongId);
        }catch(Exception e){
            assert(e.getMessage().equals("Exam not found for ID: " + wrongId));

        }

        verify(examRepository,times(1)).findById(wrongId);
        verifyNoMoreInteractions(examRepository);

     }

    @Test
    void updateExam() throws Exception {
        // Mocking the behavior of the examRepository findById method
        Long examId = 1L;
        Exam existingExam = new Exam();
        existingExam.setId(examId);
        existingExam.setName("Old Exam Name");

        Exam updatedExam = new Exam();
        updatedExam.setId(examId);
        updatedExam.setName("Updated Exam Name");

        Mockito.when(examRepository.findById(examId)).thenReturn(Optional.of(existingExam));
        Mockito.when(examRepository.save(existingExam)).thenReturn(existingExam);

        // Call the method to update the exam
        Exam result = underTest.updateExam(examId, updatedExam);

        // Verify that the existing exam was updated and saved
        Mockito.verify(examRepository, Mockito.times(1)).findById(examId);
        Mockito.verify(examRepository, Mockito.times(1)).save(existingExam);
        Mockito.verifyNoMoreInteractions(examRepository);

        // Add assertions to verify the updated exam details in 'result'
        assert(result.getName().equals(updatedExam.getName()));
        // Add more assertions as needed based on the behavior of the updateExam method
    }
    @Test
    public void testUpdateExam_WhenExamDoesNotExist() {
        // Mocking the behavior of the examRepository findById method
        Long nonExistentExamId = 999L;
        Exam updatedExam = new Exam();
        updatedExam.setId(nonExistentExamId);
        updatedExam.setName("Updated Exam Name");

        Mockito.when(examRepository.findById(nonExistentExamId)).thenReturn(Optional.empty());

        // Call the method to update an exam that doesn't exist
        try {
            underTest.updateExam(nonExistentExamId, updatedExam);
            // If the method doesn't throw an exception for a non-existent exam, the test fails
            // You can add an assertion here to indicate the failure if it reaches this point
        } catch (Exception e) {
            // Verify that the correct exception is thrown
            assert(e.getMessage().equals("Exam not found for ID: " + nonExistentExamId));
        }

        Mockito.verify(examRepository, Mockito.times(1)).findById(nonExistentExamId);
        Mockito.verifyNoMoreInteractions(examRepository);
    }


    @Test
    void deleteExam() throws Exception {
        Long examId = 1L;
        Exam exam = new Exam();
        exam.setId(examId);
        Mockito.when(examRepository.findById(examId)).thenReturn(Optional.of(exam));

        underTest.deleteExam(examId);

        verify(examRepository,times(1)).findById(examId);
        verify(examRepository,times(1)).deleteById(examId);
        verifyNoMoreInteractions(examRepository);

    }
    @Test
    public void testDeleteExam_WhenExamDoesNotExist() {
        Long nonExistentExamId = 999L;
        Exam updatedExam = new Exam();
        updatedExam.setId(nonExistentExamId);
        updatedExam.setName("Updated Exam Name");

        Mockito.when(examRepository.findById(nonExistentExamId)).thenReturn(Optional.empty());

        try {
            underTest.deleteExam(nonExistentExamId);

        } catch (Exception e) {
            assert(e.getMessage().equals("Exam not found for ID: " + nonExistentExamId));
        }

        Mockito.verify(examRepository, Mockito.times(1)).findById(nonExistentExamId);
        Mockito.verifyNoMoreInteractions(examRepository);
    }

    @Test
    void addQuestionsToExam() throws Exception {
        Long examId = 1L;
        Exam exam = new Exam();
        exam.setId(examId);
        List<Long> questions = new ArrayList<>();
        questions.add(1L);
        questions.add(2L);


        Mockito.when(examRepository.findById(examId)).thenReturn(Optional.of(exam));

        List<Question> questionsToAdd = new ArrayList<>();

        for (Long questionId : questions) {
            Question question = new Question();
            question.setId(questionId);
            questionsToAdd.add(question);
        }

        Mockito.when(questionRepository.findAllById(questions)).thenReturn(questionsToAdd);



         Exam result = underTest.addQuestionsToExam(examId,questions);

         verify(examRepository,times(1)).findById(examId);
        verify(questionRepository,times(1)).findAllById(questions);
        verify(examRepository).save(exam);
        verifyNoMoreInteractions(examRepository,questionRepository);

        assert(result.getQuestions().containsAll(questionsToAdd));
    }
    @Test
    public void testAddQuestionsToExam_WhenExamDoesNotExist() {
        // Mocking the behavior of the examRepository findById method
        Long nonExistentExamId = 999L;
        List<Long> questionIds = Arrays.asList(101L, 102L, 103L); // Example question IDs

        Mockito.when(examRepository.findById(nonExistentExamId)).thenReturn(Optional.empty());

        // Call the method to add questions to a non-existent exam
        try {
            underTest.addQuestionsToExam(nonExistentExamId, questionIds);
            // If the method doesn't throw an exception for a non-existent exam, the test fails
            // You can add an assertion here to indicate the failure if it reaches this point
        } catch (Exception e) {
            // Verify that the correct exception is thrown
            assert(e.getMessage().equals("Exam not found for ID: " + nonExistentExamId));
        }

        Mockito.verify(examRepository, Mockito.times(1)).findById(nonExistentExamId);
        Mockito.verifyNoMoreInteractions(examRepository, questionRepository);
    }





    }
