package com.exam.suggest.service;

import com.exam.suggest.model.City;
import com.exam.suggest.response.AutoCompleteSuggestionsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.exam.suggest.helper.TsvHelper.getCitiesFrmHammingDist;
import static com.exam.suggest.helper.TsvHelper.readTSVWithFilter;

/**
 * Servicio de autocompletado de sugerencia de busqueda de ciudades.
 */
@Service
@Slf4j
public class SuggestionsServiceImpl implements SuggestionsService {
    /**
     * @param cityName Complete or partial term (City name).
     * @param lng      : Longitude.
     * @param lat      : Latitude.
     * @return List<City>: Cities's list.
     */
    @Override
    public AutoCompleteSuggestionsResponse autoCompleteSuggestionsCity(final String cityName, final Float lng, final Float lat) {
        AutoCompleteSuggestionsResponse suggestionsResponse = new AutoCompleteSuggestionsResponse();
        List<City> cities = new ArrayList<>();

        if (lng != null && lat != null) {
            try {
                cities = readTSVWithFilter("./static/data/cities_canada-usa.tsv",
                        cityName, lng, lat);
            } catch (IOException e) {
                log.error(e.getMessage());
            }

        } else {
            cities = getCitiesFrmHammingDist(cityName);
        }
        suggestionsResponse.setSuggestions(cities);

        return suggestionsResponse;
    }
}
