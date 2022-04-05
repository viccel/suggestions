package com.exam.suggest.controller;

import com.exam.suggest.service.SuggestionsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {SuggestionsController.class})
@ExtendWith(SpringExtension.class)
class SuggestionsControllerTest {
    @Autowired
    private SuggestionsController suggestionsController;

    @MockBean
    private SuggestionsService suggestionsService;

    @Test
    void testAutoCompleteSuggestions1() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/suggestions").param("term", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.suggestionsController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}

