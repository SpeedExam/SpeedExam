package com.ensat.speedexam.Controllers;
import com.ensat.speedexam.Entites.Exam;
import com.ensat.speedexam.Services.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/exam")
@RequiredArgsConstructor
public class ExamController {
    private final ExamService examService;




    // Endpoint to create a new exam
    @PostMapping("/add")
    public ResponseEntity<Exam> createExam(@RequestBody Exam exam) {
        Exam createdExam = examService.createExam(exam);
        return new ResponseEntity<>(createdExam, HttpStatus.CREATED);
    }

    // Endpoint to get all exams
    @GetMapping("/all")
    public ResponseEntity<List<Exam>> getAllExams() {
        List<Exam> exams = examService.getAllExams();
        return new ResponseEntity<>(exams, HttpStatus.OK);
    }

    // Endpoint to get an exam by ID
    @GetMapping("/{examId}")
    public ResponseEntity<Exam> getExamById(@PathVariable Long examId) throws Exception {
        Exam exam = examService.getExamById(examId);
        if (exam != null) {
            return new ResponseEntity<>(exam, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Exam> updateExam(@PathVariable(value = "id") Long id,
                                           @RequestBody Exam updatedExam) throws Exception {
        Exam existingExam = examService.getExamById(id);
        Exam savedExam = examService.updateExam(id, updatedExam);
        return ResponseEntity.ok().body(savedExam);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExam(@PathVariable(value = "id") Long id) throws Exception {
        examService.deleteExam(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/{examId}/addQuestions")
    public ResponseEntity<Exam> addQuestionsToExam(
            @PathVariable Long examId,
            @RequestBody List<Long> questionIds
    ) {
        try {
            Exam updatedExam = examService.addQuestionsToExam(examId, questionIds);
            return ResponseEntity.ok(updatedExam);
        } catch (Exception e) {
            // Handle exceptions (e.g., if examId is not found)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PostMapping("/{examId}/addNote")
    public ResponseEntity<Exam> addNoteToExam(
            @PathVariable Long examId,
            float Note
    ) {
        try {
            Exam updatedExam = examService.addNoteToExam(examId, Note);
            return ResponseEntity.ok(updatedExam);
        } catch (Exception e) {
            // Handle exceptions (e.g., if examId is not found)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PostMapping("/{examId}/TimeLimit")
    public ResponseEntity<Exam> addTimeLimitsToExam(
            @PathVariable Long examId,
            int duree
    ) {
        try {
            Exam updatedExam = examService.addExamtimeLimits(examId, duree);
            return ResponseEntity.ok(updatedExam);
        } catch (Exception e) {
            // Handle exceptions (e.g., if examId is not found)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }



}

