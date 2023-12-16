package com.speedexam.speedex.controller;

import com.speedexam.speedex.model.Option;
import com.speedexam.speedex.service.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/option")
@RequiredArgsConstructor
public class OptionController {
    private final OptionService optionService;
    @GetMapping("/{id}")
    public ResponseEntity<Option> getOptionById(@PathVariable Long id) {
        Option option = optionService.getOptionById(id);
        return ResponseEntity.ok(option);
    }

    @PostMapping("/add")
    public ResponseEntity<Option> addOption(@RequestBody Option option) {
        Option newOption = optionService.addOption(option);
        return ResponseEntity.ok(newOption);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Option> updateOption(@PathVariable Long id, @RequestBody Option option) {
        Option updatedOption = optionService.updateOption(id, option);
        return ResponseEntity.ok(updatedOption);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOption(@PathVariable Long id) {
        optionService.deleteOption(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Option>> getAllOptions() {
        List<Option> options = optionService.getAllOptions();
        return ResponseEntity.ok(options);
    }
    @GetMapping("/byQuestion/{questionId}")
    public ResponseEntity<List<Option>> getOptionsByQuestion(@PathVariable Long questionId) {
        List<Option> options = optionService.getOptionsByQuestionId(questionId);
        return ResponseEntity.ok(options);
    }
}
