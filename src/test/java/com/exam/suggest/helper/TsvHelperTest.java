package com.exam.suggest.helper;

import java.io.IOException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TsvHelperTest {

    @Test
    void testGetFileTsv() throws IOException {
        assertEquals(7220, TsvHelper.getFileTsv().size());
    }

    @Test
    void testGetCitiesFrmHammingDist() {
        assertEquals(7, TsvHelper.getCitiesFrmHammingDist("Oxford").size());
        assertEquals(5, TsvHelper.getCitiesFrmHammingDist("foo").size());
        assertTrue(TsvHelper.getCitiesFrmHammingDist("./static/data/cities_canada-usa.tsv").isEmpty());
    }

    @Test
    void testReadTSVWithFilter() throws IOException {
        assertEquals(7, TsvHelper.readTSVWithFilter("foo.txt", "Oxford", 10.0f, 10.0f).size());
        assertTrue(TsvHelper.readTSVWithFilter("foo.txt", "name", 10.0f, 10.0f).isEmpty());
        assertEquals(12, TsvHelper.readTSVWithFilter("foo.txt", "country", 10.0f, 10.0f).size());
        assertEquals(19, TsvHelper.readTSVWithFilter("foo.txt", "lat", 10.0f, 10.0f).size());
    }


    @Test
    void testScoreString1AString2() {
        assertEquals(0.75f, TsvHelper.scoreString1AString2("Str1", "Str2"));
        assertEquals(1.0f, TsvHelper.scoreString1AString2("foo", "foo"));
        assertEquals((6f / 7), TsvHelper.scoreString1AString2("manzana", "mzn, manzanita "));
    }
}