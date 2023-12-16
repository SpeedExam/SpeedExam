package com.speedexam.speedex.service;

import com.speedexam.speedex.model.Option;
import com.speedexam.speedex.model.Question;
import com.speedexam.speedex.repository.OptionRepository;
import com.speedexam.speedex.repository.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)

class OptionServiceTest {

    @Mock
    private OptionRepository optionRepository;

    @Mock
    private QuestionRepository questionRepository;

    private OptionService optionService;

    @BeforeEach
    void setUp() {
        optionService = new OptionService(optionRepository,questionRepository);
    }


    @Test
    void testGetOptionById() {
        // Mocking data
        Long optionId = 1L;
        Option mockOption = new Option();
        mockOption.setId(optionId);
        when(optionRepository.findById(optionId)).thenReturn(Optional.of(mockOption));

        // Test
        Option result = optionService.getOptionById(optionId);

        // Verify
        assertNotNull(result);
        assertEquals(optionId, result.getId());
        verify(optionRepository, times(1)).findById(optionId);
    }

    @Test
    void testAddOption() {
        // Mocking data
        Option optionToAdd = new Option();

        // Test
        when(optionRepository.save(optionToAdd)).thenReturn(optionToAdd);
        Option result = optionService.addOption(optionToAdd);

        // Verify
        assertNotNull(result);
        assertEquals(optionToAdd, result);
        verify(optionRepository, times(1)).save(optionToAdd);
    }

    @Test
    void testUpdateOption() {
        // Mocking data
        Long optionId = 1L;
        Option existingOption = new Option();
        existingOption.setId(optionId);
        Option updatedOption = new Option();
        updatedOption.setContent("Updated Content");

        // Mock repository behavior
        when(optionRepository.findById(optionId)).thenReturn(Optional.of(existingOption));
        when(optionRepository.save(existingOption)).thenReturn(existingOption);

        // Test
        Option result = optionService.updateOption(optionId, updatedOption);

        // Verify
        assertNotNull(result);
        assertEquals(updatedOption.getContent(), result.getContent());
        verify(optionRepository, times(1)).findById(optionId);
        verify(optionRepository, times(1)).save(existingOption);
    }

    @Test
    void testDeleteOption() {
        // Mocking data
        Long optionId = 1L;

        // Test
        optionService.deleteOption(optionId);

        // Verify
        verify(optionRepository, times(1)).deleteById(optionId);
    }

    @Test
    void testGetAllOptions() {
        // Mocking data
        List<Option> mockOptions = Arrays.asList(new Option(), new Option());

        // Mock repository behavior
        when(optionRepository.findAll()).thenReturn(mockOptions);

        // Test
        List<Option> result = optionService.getAllOptions();

        // Verify
        assertNotNull(result);
        assertEquals(mockOptions.size(), result.size());
        verify(optionRepository, times(1)).findAll();
    }

    @Test
    void testGetOptionsByQuestionId() {
        // Mocking data
        Long questionId = 1L;
        Question question = new Question();
        question.setId(questionId);
        Option option1 = new Option();
        Option option2 = new Option();
        List<Option> mockOptions = Arrays.asList(option1, option2);
        question.setOptions(mockOptions);

        // Mock repository behavior
        when(questionRepository.findById(questionId)).thenReturn(Optional.of(question));


        // Test
        List<Option> result = optionService.getOptionsByQuestionId(questionId);

        // Verify
        assertNotNull(result);
        assertEquals(mockOptions.size(), result.size());
        verify(questionRepository, times(1)).findById(questionId);
    }


    @Test
    void testGetOptionById_NotFound() {
        // Mocking data
        Long optionId = 1L;
        when(optionRepository.findById(optionId)).thenReturn(Optional.empty());

        // Test
        Option result = optionService.getOptionById(optionId);

        // Verify
        assertNull(result);
        verify(optionRepository, times(1)).findById(optionId);
    }

    @Test
    void testUpdateOption_OptionNotFound() {
        // Mocking data
        Long optionId = 1L;
        Option updatedOption = new Option();
        updatedOption.setContent("Updated Content");

        // Mock repository behavior for not finding the option
        when(optionRepository.findById(optionId)).thenReturn(Optional.empty());

        // Test
        Option result = optionService.updateOption(optionId, updatedOption);

        // Verify
        assertNull(result);
        verify(optionRepository, times(1)).findById(optionId);
        verify(optionRepository, never()).save(any());
    }
}

