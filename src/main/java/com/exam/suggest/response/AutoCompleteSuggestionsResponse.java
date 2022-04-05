package com.exam.suggest.response;

import com.exam.suggest.model.City;
import lombok.Data;

import java.util.List;
@Data
public class AutoCompleteSuggestionsResponse {
    /**
     * suggestions: Suggestions list found.
     */
    private List<City> suggestions;
}
