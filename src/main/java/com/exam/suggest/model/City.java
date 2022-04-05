package com.exam.suggest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

/**
 * Cuty Model.
 *
 * @author VICTOR RODRIGUEZ CELIS.
 */
@Data
public class City {
    /**
     * geonameid    : integer id of record in geonames database
     */
    @JsonIgnore
    private String id;

    /**
     * name    : name of geographical point (utf8) varchar(200).
     */
    private String name;

    /**
     * ascii    : name of geographical point in plain ascii characters, varchar(200)
     */
    @JsonIgnore
    private String ascii;

    /**
     * altName    : alternatenames, comma separated varchar(5000)
     */
    @JsonIgnore
    private String altName;

    /**
     * latitude : latitude in decimal degrees (wgs84)
     */
    private float latitude;

    /**
     * longitude    : longitude in decimal degrees (wgs84).
     */
    private float longitude;

    /**
     * Score: Ponderación de acuerdo a la distancia (100,200...).
     */
    private float score;

    /**
     * feature class    : see http://www.geonames.org/export/codes.html, char(1).
     */
    @JsonIgnore
    private char featClass;

    /**
     * feature code  : see http://www.geonames.org/export/codes.html, varchar(10).
     */
    @JsonIgnore
    private String featCode;

    /**
     * country code  : ISO-3166 2-letter country code, 2 characters
     */
    @JsonIgnore
    private String countryCode;

    /**
     * cc2  : alternate country codes, comma separated, ISO-3166 2-letter country code, 60 characters
     */
    @JsonIgnore
    private String cc2;

    /**
     * admin1 code   : fipscode (subject to change to iso code), see exceptions below, see file admin1Codes.txt for display
     * names of this code; varchar(20).
     */
    @JsonIgnore
    private String admin1;

    /**
     * admin2 code   : code for the second administrative division, a county in the US, see file admin2Codes.txt; varchar(80).
     */
    @JsonIgnore
    private String admin2;

    /**
     * admin3 code   : code for third level administrative division, varchar(20).
     */
    @JsonIgnore
    private String admin3;

    /**
     * admin4 code   : code for fourth level administrative division, varchar(20).
     */
    @JsonIgnore
    private String admin4;

    /**
     * population    : bigint (8 byte int).
     */
    @JsonIgnore
    private long population;

    /**
     * elevation    : in meters, integer.
     */
    @JsonIgnore
    private int elevation;

    /**
     * dem  : digital elevation model, srtm3 or gtopo30, average elevation of 3''x3'' (ca 90mx90m) or 30''x30''
     * (ca 900mx900m) area in meters, integer. srtm processed by cgiar/ciat.
     */
    @JsonIgnore
    private int dem;

    /**
     * timezone : the timezone id (see file timeZone.txt) varchar(40).
     */
    @JsonIgnore
    private String timeZone;

    /**
     * modification date : date of last modification in yyyy-MM-dd format
     */
    @JsonIgnore
    private Date modifiedAt;

    /**
     * Distance from city A. This is City B.
     */
    private double distance;

    /**
     * Default empty constructor.
     */
    public City() {
    }

    /**
     * @param id
     * @param name
     * @param ascii
     * @param altName
     * @param latitude
     * @param longitude
     */
    public City(String id, String name, String ascii, String altName, float latitude, float longitude) {
        this.id = id;
        this.name = name;
        this.ascii = ascii;
        this.altName = altName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * @param id
     * @param name
     * @param ascii
     * @param altName
     * @param latitude
     * @param longitude
     * @param featClass
     * @param featCode
     * @param countryCode
     * @param cc2
     * @param admin1
     * @param admin2
     * @param admin3
     * @param admin4
     * @param population
     * @param elevation
     * @param dem
     * @param timeZone
     * @param modifiedAt
     */
    public City(String id, String name, String ascii, String altName, float latitude, float longitude, char featClass,
                String featCode, String countryCode, String cc2, String admin1, String admin2, String admin3,
                String admin4, long population, int elevation, int dem, String timeZone, Date modifiedAt) {
        this.id = id;
        this.name = name;
        this.ascii = ascii;
        this.altName = altName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.featClass = featClass;
        this.featCode = featCode;
        this.countryCode = countryCode;
        this.cc2 = cc2;
        this.admin1 = admin1;
        this.admin2 = admin2;
        this.admin3 = admin3;
        this.admin4 = admin4;
        this.population = population;
        this.elevation = elevation;
        this.dem = dem;
        this.timeZone = timeZone;
        this.modifiedAt = modifiedAt;
    }
}
