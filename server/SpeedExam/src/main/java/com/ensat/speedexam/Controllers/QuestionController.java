package com.ensat.speedexam.Controllers;
import com.ensat.speedexam.Entites.Option;
import com.ensat.speedexam.Entites.Question;
import com.ensat.speedexam.Services.QuestionService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/question")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
        Question question = null;
        try {
            question = questionService.getQuestionById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(question);
    }

    @PostMapping("/add")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        Question newQuestion = questionService.addQuestion(question);
        return ResponseEntity.ok(newQuestion);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody Question question) {
        Question updatedQuestion = questionService.updateQuestion(id, question);
        return ResponseEntity.ok(updatedQuestion);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = questionService.getAllQuestions();
        return ResponseEntity.ok(questions);
    }
    @GetMapping("/byExam/{examId}")
    public ResponseEntity<List<Question>> getQuestionsByExam(@PathVariable Long examId) {
        List<Question> questions = questionService.getQuestionsByExamId(examId);
        return ResponseEntity.ok(questions);
    }
    @PostMapping("/{questionId}/addOptions")
    public ResponseEntity<Question> addOptionsToQuestion(
            @PathVariable Long questionId,
            @RequestBody List<Option> options
    ) {
        try {
            Question updatedQuestion = questionService.addOptionsToQuestion(questionId, options);
            return ResponseEntity.ok(updatedQuestion);
        } catch (Exception e) {
            // Handle exceptions (e.g., if questionId is not found)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }






}
