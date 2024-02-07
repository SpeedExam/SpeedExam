package com.ensat.speedexam.Services;

import com.ensat.speedexam.Entites.Exam;
import com.ensat.speedexam.Entites.Question;

import com.ensat.speedexam.Entites.User;
import com.ensat.speedexam.Repositories.ExamRepository;
import com.ensat.speedexam.Repositories.QuestionRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepository examRepository;
    private final QuestionRepository questionRepository;


    public Exam createExam(Exam exam){
        exam.setPassed(false);
        examRepository.save(exam);
        return exam;
    }


    public List<Exam> getAllExams(){
        return examRepository.findAll();
    }

    public List<Exam> getExamByUserId(User user) {
        return examRepository.findAllByUser(user);
    }
    public List<Exam> getPassedExamByUserId(User user) {
        List<Exam> allExams =  examRepository.findAllByUser(user);
        List<Exam> passedExams = new ArrayList<>();
        for (Exam e:allExams){
            if (e.isPassed()) passedExams.add(e);
        }
        return passedExams;
    }
    public List<Exam> getNonPassedExamByUserId(User user) {
        List<Exam> allExams =  examRepository.findAllByUser(user);
        List<Exam> nonPassedExams = new ArrayList<>();
        for (Exam e:allExams){
            if (!e.isPassed()) nonPassedExams.add(e);
        }
        return nonPassedExams;
    }
    public Exam getExamById(Long id)throws Exception {
        Optional<Exam> optionalExam = examRepository.findById(id);
        if (optionalExam.isPresent()) {
            return optionalExam.get();
        } else {
            throw new Exception("Exam not found for ID: " + id);
        }
    }


    public Exam updateExam(Long id, Exam updatedExam)throws Exception {
        Optional<Exam> optionalExam = examRepository.findById(id);
        if (optionalExam.isPresent()) {
            Exam existingExam = optionalExam.get();
            // Update the existing exam object with the new details
            existingExam.setName(updatedExam.getName());
            // Update other fields as needed
            return examRepository.save(existingExam);
        } else {
            throw new Exception("Exam not found for ID: " + id);
        }
    }

    public void deleteExam(Long id)throws Exception {
        Optional<Exam> optionalExam = examRepository.findById(id);
        if (optionalExam.isPresent()) {
            examRepository.deleteById(id);
        } else {
            throw new Exception("Exam not found for ID: " + id);
        }
    }
    public Exam addQuestionsToExam(Long examId, List<Long> questionIds) throws Exception {
        Exam exam = examRepository.findById(examId).orElse(null);
        if (exam != null) {
            List<Question> questionsToAdd = questionRepository.findAllById(questionIds);
            exam.getQuestions().addAll(questionsToAdd);
            examRepository.save(exam);
            return exam;
        } else {
            throw new Exception("Exam not found for ID: " + examId);
        }
    }
    public Exam addNoteToExam(Long examId, float Note) throws Exception{
        Exam exam = examRepository.findById(examId).orElse(null);
        if (exam != null) {
            exam.setNote(Note);
            exam.setPassed(true);
            examRepository.save(exam);
            return exam;
        } else {
            throw new Exception("Exam not found for ID: " + examId);
        }
    }
    public Exam addExamtimeLimits(Long examId, int duree) throws Exception{
        Exam exam = examRepository.findById(examId).orElse(null);
        if (exam != null) {
            exam.setTimeLimits(duree);
            examRepository.save(exam);
            return exam;
        } else {
            throw new Exception("Exam not found for ID: " + examId);
        }
    }







}
