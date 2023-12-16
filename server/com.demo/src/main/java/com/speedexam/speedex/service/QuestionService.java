package com.speedexam.speedex.service;

import com.speedexam.speedex.model.Exam;
import com.speedexam.speedex.model.Option;
import com.speedexam.speedex.model.Question;
import com.speedexam.speedex.repository.ExamRepository;
import com.speedexam.speedex.repository.OptionRepository;
import com.speedexam.speedex.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final ExamRepository examRepository;
    private final OptionRepository optionRepository;

    public Question getQuestionById(Long id)throws Exception {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if (optionalQuestion.isPresent()) {
            return optionalQuestion.get();
        } else {
            throw new Exception("Exam not found for ID: " + id);
        }
    }
    public Question addQuestion(Question question) {
        // Add business logic here before saving
        return questionRepository.save(question);
    }
    public Question updateQuestion(Long id, Question question) {
        Optional<Question> optionalExistingQuestion = questionRepository.findById(id);
        if (optionalExistingQuestion.isPresent()) {
            Question existingQuestion = optionalExistingQuestion.get();
            existingQuestion.setName(question.getName());
            existingQuestion.setOptions(question.getOptions());
            // More fields I should add

            return questionRepository.save(existingQuestion);
        }
        return null; // Handle not found case
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
    public List<Question> getQuestionsByExamId(Long examId) {
        Optional<Exam> optionalExam = examRepository.findById(examId);
        if (optionalExam.isPresent()) {
            Exam exam = optionalExam.get();
            return exam.getQuestions(); // Assuming the Exam entity has a method to retrieve questions
        }
        return Collections.emptyList(); // Handle case where exam is not found
    }
    public Question addOptionsToQuestion(Long questionId, List<Option> options)throws Exception {
        Question question = questionRepository.findById(questionId).orElse(null);
        if (question != null) {
            options.forEach(option -> {
                option.setQuestion(question);
                optionRepository.save(option);
                question.getOptions().add(option);
            });
            questionRepository.save(question);
            return question;
        } else {
            throw new Exception("Question not found for ID: " + questionId);
        }
    }



}
