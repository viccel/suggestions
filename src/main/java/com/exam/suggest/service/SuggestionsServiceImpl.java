package com.exam.suggest.service;

import com.exam.suggest.response.AutoCompleteSuggestionsResponse;
import com.exam.suggest.model.City;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.exam.suggest.helper.TsvHelper.readTSVWithFilter;

@Service
public class SuggestionsServiceImpl implements SuggestionsService {

    /**
     * @param cityName Complete or partial term (City name).
     * @return List<City>: Cities's list.
     */
    @Override
    public AutoCompleteSuggestionsResponse autoCompleteSuggestionsCity(String cityName) {
        List<City> suggestList = new ArrayList<>();
        City city = new City();
        city.setName("CDMX");
        city.setLatitude(56.897f);
        city.setLongitude(90.5654f);

        suggestList.add(city);
        AutoCompleteSuggestionsResponse response = new AutoCompleteSuggestionsResponse();
        response.setSuggestions(suggestList);

        return response;
    }

    /**
     * @param cityName Complete or partial term (City name).
     * @param lng      : Longitude.
     * @param lat      : Latitude.
     * @return List<City>: Cities's list.
     */
    @Override
    public AutoCompleteSuggestionsResponse autoCompleteSuggestionsCity(final String cityName, final Float lng, final Float lat) {

        if (lng != null && lat != null) {

            try {
                List<City> cities = readTSVWithFilter("src/main/resources/static/data/cities_canada-usa.tsv",
                        cityName, lng, lat);
                AutoCompleteSuggestionsResponse suggestionsResponse = new AutoCompleteSuggestionsResponse();
                suggestionsResponse.setSuggestions(cities);
                return suggestionsResponse;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return null;
    }
}
