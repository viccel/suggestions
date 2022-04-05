package com.exam.suggest.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.Test;

class CityTest {
    @Test
    void testConstructor() {
        City actualCity = new City();
        assertNull(actualCity.getAdmin1());
        assertNull(actualCity.getTimeZone());
        assertEquals(0.0f, actualCity.getScore());
        assertEquals(0L, actualCity.getPopulation());
        assertNull(actualCity.getName());
        assertNull(actualCity.getModifiedAt());
        assertEquals(0.0f, actualCity.getLongitude());
        assertEquals(0.0f, actualCity.getLatitude());
        assertNull(actualCity.getId());
        assertNull(actualCity.getFeatCode());
        assertEquals('\u0000', actualCity.getFeatClass());
        assertEquals(0, actualCity.getElevation());
        assertEquals(0.0d, actualCity.getDistance());
        assertEquals(0, actualCity.getDem());
        assertNull(actualCity.getCountryCode());
        assertNull(actualCity.getCc2());
        assertNull(actualCity.getAscii());
        assertNull(actualCity.getAltName());
        assertNull(actualCity.getAdmin4());
        assertNull(actualCity.getAdmin3());
        assertNull(actualCity.getAdmin2());
    }

    @Test
    void testConstructor2() {
        City actualCity = new City("42", "Name", "Ascii", "Alt Name", 10.0f, 10.0f);

        assertNull(actualCity.getAdmin1());
        assertNull(actualCity.getTimeZone());
        assertEquals(0.0f, actualCity.getScore());
        assertEquals(0L, actualCity.getPopulation());
        assertEquals("Name", actualCity.getName());
        assertNull(actualCity.getModifiedAt());
        assertEquals(10.0f, actualCity.getLongitude());
        assertEquals(10.0f, actualCity.getLatitude());
        assertEquals("42", actualCity.getId());
        assertNull(actualCity.getFeatCode());
        assertEquals('\u0000', actualCity.getFeatClass());
        assertEquals(0, actualCity.getElevation());
        assertEquals(0.0d, actualCity.getDistance());
        assertEquals(0, actualCity.getDem());
        assertNull(actualCity.getCountryCode());
        assertNull(actualCity.getCc2());
        assertEquals("Ascii", actualCity.getAscii());
        assertEquals("Alt Name", actualCity.getAltName());
        assertNull(actualCity.getAdmin4());
        assertNull(actualCity.getAdmin3());
        assertNull(actualCity.getAdmin2());
    }

    @Test
    void testConstructor3() {
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        City actualCity = new City("42", "Name", "Ascii", "Alt Name", 10.0f, 10.0f, 'A', "Feat Code", "GB", "Cc2", "Admin1",
                "Admin2", "Admin3", "Admin4", 1L, 1, 1, "UTC",
                Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));

        assertEquals("Admin1", actualCity.getAdmin1());
        assertEquals("UTC", actualCity.getTimeZone());
        assertEquals(1L, actualCity.getPopulation());
        assertEquals("Name", actualCity.getName());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals("1969-12-31", simpleDateFormat.format(actualCity.getModifiedAt()));
        assertEquals(10.0f, actualCity.getLongitude());
        assertEquals(10.0f, actualCity.getLatitude());
        assertEquals("42", actualCity.getId());
        assertEquals("Feat Code", actualCity.getFeatCode());
        assertEquals('A', actualCity.getFeatClass());
        assertEquals(1, actualCity.getElevation());
        assertEquals(1, actualCity.getDem());
        assertEquals("GB", actualCity.getCountryCode());
        assertEquals("Cc2", actualCity.getCc2());
        assertEquals("Ascii", actualCity.getAscii());
        assertEquals("Alt Name", actualCity.getAltName());
        assertEquals("Admin4", actualCity.getAdmin4());
        assertEquals("Admin3", actualCity.getAdmin3());
        assertEquals("Admin2", actualCity.getAdmin2());
    }
}

