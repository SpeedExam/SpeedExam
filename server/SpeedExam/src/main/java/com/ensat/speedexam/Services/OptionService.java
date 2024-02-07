package com.ensat.speedexam.Services;
import com.ensat.speedexam.Entites.Option;
import com.ensat.speedexam.Entites.Question;
import com.ensat.speedexam.Repositories.OptionRepository;
import com.ensat.speedexam.Repositories.QuestionRepository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.*;


@Service
@RequiredArgsConstructor
public class OptionService {
    /*private final OptionRepository optionRepository;
    private final QuestionRepository questionRepository;

    public Option getOptionById(Long id) {
        Optional<Option> optionalOption = optionRepository.findById(id);
        return optionalOption.orElse(null);
    }

    public Option addOption(Option option) {
        // You can perform any necessary validation or business logic here before saving
        return optionRepository.save(option);
    }

    public Option updateOption(Long id, Option option) {
        Optional<Option> optionalExistingOption = optionRepository.findById(id);
        if (optionalExistingOption.isPresent()) {
            Option existingOption = optionalExistingOption.get();
            // Update the existing option with new data
            existingOption.setContent(option.getContent());
            existingOption.setCorrect(option.getIsCorrect());
            // Update other fields as needed

            // Save the updated option
            return optionRepository.save(existingOption);
        }
        return null; // Handle not found case
    }

    public void deleteOption(Long id) {
        optionRepository.deleteById(id);
    }

    public List<Option> getAllOptions() {
        return optionRepository.findAll();
    }
    public List<Option> getOptionsByQuestionId(Long questionId) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        if (optionalQuestion.isPresent()) {
            Question question = optionalQuestion.get();
            return question.getOptions();
        }
        return Collections.emptyList(); // Handle case where question is not found
    }*/
}
