package com.exam.suggest.response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.exam.suggest.model.City;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class AutoCompleteSuggestionsResponseTest {
    @Test
    void testCanEqual() {
        assertFalse((new AutoCompleteSuggestionsResponse()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        AutoCompleteSuggestionsResponse autoCompleteSuggestionsResponse = new AutoCompleteSuggestionsResponse();

        AutoCompleteSuggestionsResponse autoCompleteSuggestionsResponse1 = new AutoCompleteSuggestionsResponse();
        autoCompleteSuggestionsResponse1.setSuggestions(new ArrayList<>());
        assertTrue(autoCompleteSuggestionsResponse.canEqual(autoCompleteSuggestionsResponse1));
    }

    @Test
    void testConstructor() {
        AutoCompleteSuggestionsResponse actualAutoCompleteSuggestionsResponse = new AutoCompleteSuggestionsResponse();
        ArrayList<City> cityList = new ArrayList<>();
        actualAutoCompleteSuggestionsResponse.setSuggestions(cityList);
        assertSame(cityList, actualAutoCompleteSuggestionsResponse.getSuggestions());
    }

    @Test
    void testEquals() {
        AutoCompleteSuggestionsResponse autoCompleteSuggestionsResponse = new AutoCompleteSuggestionsResponse();
        autoCompleteSuggestionsResponse.setSuggestions(new ArrayList<>());
        assertNotEquals(autoCompleteSuggestionsResponse, null);
    }

    @Test
    void testEquals2() {
        AutoCompleteSuggestionsResponse autoCompleteSuggestionsResponse = new AutoCompleteSuggestionsResponse();
        autoCompleteSuggestionsResponse.setSuggestions(new ArrayList<>());
        assertNotEquals(autoCompleteSuggestionsResponse, "Different type to AutoCompleteSuggestionsResponse");
    }

    @Test
    void testEquals3() {
        AutoCompleteSuggestionsResponse autoCompleteSuggestionsResponse = new AutoCompleteSuggestionsResponse();
        autoCompleteSuggestionsResponse.setSuggestions(new ArrayList<>());
        assertEquals(autoCompleteSuggestionsResponse, autoCompleteSuggestionsResponse);
        int expectedHashCodeResult = autoCompleteSuggestionsResponse.hashCode();
        assertEquals(expectedHashCodeResult, autoCompleteSuggestionsResponse.hashCode());
    }

    @Test
    void testEquals4() {
        AutoCompleteSuggestionsResponse autoCompleteSuggestionsResponse = new AutoCompleteSuggestionsResponse();
        autoCompleteSuggestionsResponse.setSuggestions(new ArrayList<>());

        AutoCompleteSuggestionsResponse autoCompleteSuggestionsResponse1 = new AutoCompleteSuggestionsResponse();
        autoCompleteSuggestionsResponse1.setSuggestions(new ArrayList<>());
        assertEquals(autoCompleteSuggestionsResponse, autoCompleteSuggestionsResponse1);
        int expectedHashCodeResult = autoCompleteSuggestionsResponse.hashCode();
        assertEquals(expectedHashCodeResult, autoCompleteSuggestionsResponse1.hashCode());
    }

    @Test
    void testEquals5() {
        ArrayList<City> cityList = new ArrayList<>();
        cityList.add(new City("42", "Name", "Ascii", "Alt Name", 10.0f, 10.0f));

        AutoCompleteSuggestionsResponse autoCompleteSuggestionsResponse = new AutoCompleteSuggestionsResponse();
        autoCompleteSuggestionsResponse.setSuggestions(cityList);

        AutoCompleteSuggestionsResponse autoCompleteSuggestionsResponse1 = new AutoCompleteSuggestionsResponse();
        autoCompleteSuggestionsResponse1.setSuggestions(new ArrayList<>());
        assertNotEquals(autoCompleteSuggestionsResponse, autoCompleteSuggestionsResponse1);
    }
}

