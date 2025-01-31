package ru.fisher.bloomfilter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BloomFilterImplTest {

    @Test
    void simpleTest() {
        BloomFilterImpl<String> bloomFilter = new BloomFilterImpl<>(32);
        bloomFilter.add("string one");
        bloomFilter.add("string two");
        bloomFilter.add("string three");
        bloomFilter.add("string four");
        bloomFilter.add("string five");
        bloomFilter.add("string");
        bloomFilter.add("   string цццц   ");
        bloomFilter.add(" string ыыыы  ");
        bloomFilter.add("   string яяяя");
        bloomFilter.add(" string йййй");


        assertTrue(bloomFilter.isValue("string three"));
        assertTrue(bloomFilter.isValue("string two"));
        assertTrue(bloomFilter.isValue("string four"));
        assertTrue(bloomFilter.isValue("string one"));
        assertTrue(bloomFilter.isValue("string five"));
        assertTrue(bloomFilter.isValue(" string йййй"));

        assertFalse(bloomFilter.isValue("strring two"));
        assertFalse(bloomFilter.isValue("string ыыыыы"));
        assertFalse(bloomFilter.isValue("string fiv"));
        assertFalse(bloomFilter.isValue(" string йййй     "));
    }

    @Test
    void sampleTest() {
        BloomFilterImpl<String> filter = new BloomFilterImpl<>(32);

        filter.add("0123456789");
        filter.add("1234567890");
        filter.add("2345678901");
        filter.add("3456789012");
        filter.add("4567890123");
        filter.add("5678901234");
        filter.add("6789012345");
        filter.add("7890123456");
        filter.add("8901234567");
        filter.add("9012345678");
        filter.add("8720951631");

        assertTrue(filter.isValue("0123456789"));
        assertTrue(filter.isValue("1234567890"));
        assertTrue(filter.isValue("8901234567"));
        assertTrue(filter.isValue("5678901234"));
        assertTrue(filter.isValue("8720951631"));

        assertFalse(filter.isValue("8720951634"));
    }


}