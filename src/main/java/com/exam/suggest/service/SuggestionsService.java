package com.exam.suggest.service;

import com.exam.suggest.response.AutoCompleteSuggestionsResponse;

/**
 * Fakade of Suggestions Service.
 */
public interface SuggestionsService {
    /**
     * @param cityName Complete or partial term (City name).
     * @return List<City>: Cities's list.
     */
    AutoCompleteSuggestionsResponse autoCompleteSuggestionsCity(String cityName);

    /**
     * @param cityName Complete or partial term (City name).
     * @param lng      : Longitude.
     * @param lat      : Latitude.
     * @return List<City>: Cities's list.
     */
    AutoCompleteSuggestionsResponse autoCompleteSuggestionsCity(String cityName, Float lng, Float lat);
}
