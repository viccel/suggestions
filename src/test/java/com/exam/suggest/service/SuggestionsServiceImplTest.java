package com.exam.suggest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SuggestionsServiceImpl.class})
@ExtendWith(SpringExtension.class)
class SuggestionsServiceImplTest {
    @Autowired
    private SuggestionsServiceImpl suggestionsServiceImpl;

    @Test
    void testAutoCompleteSuggestionsCity() {
        assertEquals(7,
                this.suggestionsServiceImpl.autoCompleteSuggestionsCity("Oxford", 10.0f, 10.0f).getSuggestions().size());
        assertTrue(
                this.suggestionsServiceImpl.autoCompleteSuggestionsCity("./static/data/cities_canada-usa.tsv", 10.0f, 10.0f)
                        .getSuggestions()
                        .isEmpty());
        assertEquals(12,
                this.suggestionsServiceImpl.autoCompleteSuggestionsCity("country", 10.0f, 10.0f).getSuggestions().size());
        assertEquals(19,
                this.suggestionsServiceImpl.autoCompleteSuggestionsCity("lat", 10.0f, 10.0f).getSuggestions().size());
        assertEquals(7,
                this.suggestionsServiceImpl.autoCompleteSuggestionsCity("Oxford", null, 10.0f).getSuggestions().size());
        assertEquals(7,
                this.suggestionsServiceImpl.autoCompleteSuggestionsCity("Oxford", 10.0f, null).getSuggestions().size());
        assertTrue(
                this.suggestionsServiceImpl.autoCompleteSuggestionsCity("./static/data/cities_canada-usa.tsv", null, 10.0f)
                        .getSuggestions()
                        .isEmpty());
    }

}

