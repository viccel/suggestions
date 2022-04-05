package com.exam.suggest.controller;

import com.exam.suggest.response.AutoCompleteSuggestionsResponse;
import com.exam.suggest.service.SuggestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Rest Controller Suggestions.
 */
@RestController
@CrossOrigin(origins = "*")
public class SuggestionsController {
    /**
     * Service SuggestionsService.
     */
    @Autowired
    private SuggestionsService service;

    /**
     * Obtiene una lista de ciudades sugeridas en orden a la distancia menor.
     *
     * @param term      Termino completo o parcial de busqueda o para auto completar.
     * @param latitude  Latidud de la ciudad a buscar.
     * @param longitude Longitud de la ciudad a buscar.
     * @return AutoCompleteSuggestionsResponse serializado.
     */
    @GetMapping(value = "/suggestions", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public AutoCompleteSuggestionsResponse
    autoCompleteSuggestions1(@RequestParam(value = "q") String term,
                             @RequestParam(value = "latitude", required = false) Float latitude,
                             @RequestParam(value = "longitude", required = false) Float longitude) {

        AutoCompleteSuggestionsResponse response = service.autoCompleteSuggestionsCity(term, latitude, longitude);
        return response;
    }


}
