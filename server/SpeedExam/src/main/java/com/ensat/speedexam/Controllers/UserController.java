package com.ensat.speedexam.Controllers;

import com.ensat.speedexam.AuthConfigurations.AuthEntites.ChangePasswordRequest;
import com.ensat.speedexam.Entites.Exam;
import com.ensat.speedexam.Entites.User;
import com.ensat.speedexam.Services.Auth.LogoutService;
import com.ensat.speedexam.Services.ExamService;
import com.ensat.speedexam.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/cp")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ExamService examService;
    private static final Logger logger = LogManager.getLogger(LogoutService.class);

    // Changing password endpoint
    @PatchMapping
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser
    ) throws Exception {
        userService.changePassword(request,connectedUser);
        logger.info("PASSWORD CHANGED");
        return ResponseEntity.ok().build();
    }
    @GetMapping("/exams/{userId}")
    public ResponseEntity<List<Exam>> getExamById(@PathVariable int userId) throws Exception {

        User user = userService.getUserById(userId);
        List<Exam> exams = examService.getExamByUserId(user);
        if (exams != null) {
            return new ResponseEntity<>(exams, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/exams/{userId}/passed")
    public ResponseEntity<List<Exam>> getPassedExamById(@PathVariable int userId) throws Exception {

        User user = userService.getUserById(userId);
        List<Exam> exams = examService.getPassedExamByUserId(user);
        if (exams != null) {
            return new ResponseEntity<>(exams, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/exams/{userId}/nonpassed")
    public ResponseEntity<List<Exam>> getNonPassedExamById(@PathVariable int userId) throws Exception {

        User user = userService.getUserById(userId);
        List<Exam> exams = examService.getNonPassedExamByUserId(user);
        if (exams != null) {
            return new ResponseEntity<>(exams, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
