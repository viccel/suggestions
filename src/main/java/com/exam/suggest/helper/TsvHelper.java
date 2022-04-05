package com.exam.suggest.helper;

import com.exam.suggest.model.City;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class TSVHelper.
 */
@Slf4j
public final class TsvHelper {

    private final static String FILE_NAME = "./static/data/cities_canada-usa.tsv";
    private final static String[] HEADERS = "id\tname\tascii\talt_name\tlat\tlong\tfeat_class\tfeat_code\tcountry\tcc2\tadmin1\tadmin2\tadmin3\tadmin4\tpopulation\televation\tdem\ttz\tmodified_at".split("\t");
    private static List<CSVRecord> RECORDS = null;

    /**
     * Inicializa RECORDS.
     */
    static {
        try {
            RECORDS = getFileTsv();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * Gets the RECORDS.
     *
     * @return Listado de CSVRecord del archivo.
     */
    public static List<CSVRecord> getRECORDS() {
        return RECORDS;
    }

    public static List<CSVRecord> getFileTsv() throws IOException {
        Resource resource = new ClassPathResource(FILE_NAME);
        InputStream inputStream = resource.getInputStream();
        Reader in = new InputStreamReader(inputStream);

        List<CSVRecord> records = CSVFormat.DEFAULT.withDelimiter('\t').withEscape('\\').withHeader(HEADERS).withFirstRecordAsHeader().parse(in).getRecords().stream().collect(Collectors.toList());

        return records;
    }

    /**
     * Private constructor, no se puede instanciar.
     */
    private TsvHelper() {
        throw new UnsupportedOperationException("No se puede instanciar");
    }

    public static List<City> getCitiesFrmHammingDist(final String cityName) {
        List<City> cities = new ArrayList<>();
        getRECORDS().forEach(record -> {
            float score = scoreString1AString2(cityName, record.get("name"));

            if (score >= 0.75f) {
                City city = fillNewCity(record);
                city.setScore(score);

                cities.add(city);
            }

        });

        List<City> orderedCities = cities.stream().
                sorted(Comparator.comparingDouble(City::getScore).reversed())
                .collect(Collectors.toList());

        return orderedCities;
    }

    /**
     * Obtiene el listado de ciudades sugeridas de acuerdo a las coincidencias y su score.
     *
     * @param fileName nombre del archivo de datos.
     * @param cityName nombre de la ciudad o termino buscado.
     * @param lng      longitud del punto.
     * @param lat      latitud del punto.
     * @return Lista de ciudades.
     * @throws IOException throws IOEXception.
     */
    public static List<City> readTSVWithFilter(final String fileName, final String cityName, final Float lng, final Float lat) throws IOException {

        List<CSVRecord> records = getRECORDS().stream().filter(record -> record.get("name").toLowerCase().contains(cityName.toLowerCase())).collect(Collectors.toList());

        List<City> cities = new ArrayList<>();

        records.forEach((record) -> {
            City city = fillNewCity(record);

            double distanciaDosGPS = distanciaDosGPS(city.getLatitude(), city.getLongitude(), lat, lng);
            city.setDistance(distanciaDosGPS);
            cities.add(city);

        });

        List<City> orderedCities = cities.stream().sorted(Comparator.comparingDouble(City::getDistance)).collect(Collectors.toList());

        if (orderedCities.size() > 0) {
            double distance = orderedCities.get(0).getDistance();

            orderedCities.forEach((city -> {
                double distance1 = city.getDistance();
                float score = getScore(distance, distance1);
                city.setScore(score);
                log.info(score + "--");
            }));

        }

        return orderedCities;
    }


    /**
     * Convertir grados a radianes.
     *
     * @param grados cantidad de grados a convertir.
     * @return double que representa el valor en radianes.
     */
    private static double gradoARadianes(final float grados) {
        double radianes = grados * Math.PI / 180;
        return radianes;
    }

    /**
     * Obtiene la distancia entre dos puntos geograficos.
     *
     * @param lat1 Latitud del punto 1.
     * @param lng1 longitud del punto 1.
     * @param lat2 latitud del punto 2.
     * @param lng2 longitud del punto 2.
     * @return double que representa la distancia entre los dos puntos.
     */
    private static double distanciaDosGPS(final Float lat1, final Float lng1, final Float lat2, final Float lng2) {
        final float radioTierra = 6378.4f;

        final double dLat = gradoARadianes(lat2 - lat1);
        final double dLon = gradoARadianes(lng2 - lng1);

        final double lat1Rdn = gradoARadianes(lat1);
        final double lat2Rdn = gradoARadianes(lat2);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1Rdn) * Math.cos(lat2Rdn);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return radioTierra * c;
    }

    /**
     * Obtiene un score basado en distancias.
     *
     * @param distanciaMenor la distancia menor de respecto a la latitud y longitud.
     * @param distancia      la distancia al punto dado con la latiud y longitud.
     * @return float value.
     */
    private static float getScore(final double distanciaMenor, final double distancia) {

        double difDistancias = distancia - distanciaMenor;
        float score = 0;

        if (difDistancias <= 100) {
            score = 0.9f;
        }

        if (difDistancias >= 101 && difDistancias <= 250) {
            score = 0.8f;
        }

        if (difDistancias >= 251 && difDistancias <= 500) {
            score = 0.7f;
        }

        if (difDistancias >= 501 && difDistancias <= 1000) {
            score = 0.6f;
        }

        if (difDistancias >= 1001 && difDistancias <= 1500) {
            score = 0.5f;
        }

        if (difDistancias > 1501 && difDistancias <= 2000) {
            score = 0.4f;
        }

        if (difDistancias >= 2001 && difDistancias <= 2500) {
            score = 0.3f;
        }

        return score;
    }

    /**
     * Obtiene una porcentaje de caracteres coincididos en la misma posición.
     *
     * @param str1 Cadena sobre la cual se contextualiza el match.
     * @param str2 Cadena a probar.
     * @return float score.
     */
    public static float scoreString1AString2(final String str1, final String str2) {

        String tokens[] = str2.toLowerCase().split("[,\\s+|\\s+]");
        float mxChrsMatched = 0;

        for (String token : tokens) {
            if (!token.isEmpty()) {

                int maches = 0;
                int limit = 0;

                if (str1.length() > token.length()) {
                    limit = token.length();
                } else if (token.length() > str1.length()) {
                    limit = str1.length();
                } else {
                    limit = str1.length();
                }

                for (int i = 0; i < limit; i++) {

                    if (token.charAt(i) == str1.toLowerCase().charAt(i)) {
                        maches += 1;
                    }

                }

                if (mxChrsMatched < maches) {
                    mxChrsMatched = maches;
                }

            }
        }

        int len = str1.length();
        float cociente = mxChrsMatched / len;
        return cociente;
    }

    /**
     * Llena una una instancia de City con algunos datos de un Record.
     *
     * @param record SCVRecord de un archivo CSV o TSV.
     * @return City intance con valores.
     */
    private static City fillNewCity(final CSVRecord record) {
        String stateCode = record.get("admin1");
        String countryCode = record.get("country");
        String cName = record.get("name").concat(", ").concat(stateCode).concat(", ").concat(countryCode);
        String strlat = record.get("lat");
        String strLong = record.get("long");

        Float lat = Float.parseFloat(strlat);
        Float lng = Float.parseFloat(strLong);

        City city = new City();
        city.setName(cName);
        city.setLatitude(lat);
        city.setLongitude(lng);

        return city;
    }
}
