package com.speedexam.speedex.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.speedexam.speedex.model.Option;
import com.speedexam.speedex.service.OptionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OptionControllerTest {

    private MockMvc mockMvc;

    @Mock
    private OptionService optionService;

    @InjectMocks
    private OptionController optionController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(optionController).build();
    }

    @Test
    void getOptionById() throws Exception {
        Long optionId = 1L;
        Option testOption = new Option(); // Replace with your test option

        when(optionService.getOptionById(optionId)).thenReturn(testOption);

        ResultActions response = mockMvc.perform(get("/api/option/1")
                .contentType(MediaType.APPLICATION_JSON)
        );

        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").value(testOption.getContent())); // Replace with your property for comparison
    }

    @Test
    void addOption() throws Exception {
        Option testOption = new Option(); // Replace with your test option

        when(optionService.addOption(any(Option.class))).thenReturn(testOption);

        ResultActions response = mockMvc.perform(post("/api/option/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(testOption))
        );

        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").value(testOption.getContent())); // Replace with your property for comparison
    }

    @Test
    void updateOption() throws Exception {
        Long optionId = 1L;
        Option testOption = new Option(); // Replace with your test option

        when(optionService.updateOption(eq(optionId), any(Option.class))).thenReturn(testOption);

        ResultActions response = mockMvc.perform(put("/api/option/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(testOption))
        );

        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").value(testOption.getContent())); // Replace with your property for comparison
    }

    @Test
    void deleteOption() throws Exception {
        Long optionId = 1L;
        doNothing().when(optionService).deleteOption(optionId);

        ResultActions response = mockMvc.perform(delete("/api/option/delete/1"));

        response.andExpect(status().isOk());
        verify(optionService, times(1)).deleteOption(optionId);
    }

    @Test
    void getAllOptions() throws Exception {
        List<Option> testOptions = Arrays.asList(new Option(), new Option()); // Replace with your test options

        when(optionService.getAllOptions()).thenReturn(testOptions);

        ResultActions response = mockMvc.perform(get("/api/option/all")
                .contentType(MediaType.APPLICATION_JSON)
        );

        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].content").value(testOptions.get(0).getContent())); // Replace with your property for comparison
    }

    @Test
    void getOptionsByQuestion() throws Exception {
        Long questionId = 1L;
        List<Option> testOptions = Arrays.asList(new Option(), new Option()); // Replace with your test options

        when(optionService.getOptionsByQuestionId(questionId)).thenReturn(testOptions);

        ResultActions response = mockMvc.perform(get("/api/option/byQuestion/1")
                .contentType(MediaType.APPLICATION_JSON)
        );

        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].content").value(testOptions.get(0).getContent())); // Replace with your property for comparison
    }
}
