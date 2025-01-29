package ru.fisher.nativedictionary;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    Random rand = new Random();

    @Test
    void collisionTest() {
        Map<Object> map = new Map<>(19);
        map.put("test", 2);
        map.put("word", 332);
        map.put("map", 22);

        assertTrue(map.isKey("test"));
        assertEquals(22, map.getValue("word"));
        map.put("map", 11);
        assertEquals(11, map.getValue("map"));
        map.remove("map");
        assertNull(map.getValue("map"));
        assertEquals(1, map.getRemoveStatus());
    }

    @RepeatedTest(1000)
    void oneKeyForALotOfValues() {
        Map<Object> dictionary = new Map<>(17);
        for (int i = 0; i < 10; i++) {
            dictionary.put("keyOne", " " + rand.nextInt(100) + 1);
        }
        assertEquals(2, dictionary.getPutStatus());
    }

    @Test
    void getValueTest() {
        Map<Object> dictionary = new Map<>(17);
        String key = "KeyOne";
        dictionary.put("KeyOne",9876543);
        assertEquals(9876543,dictionary.getValue(key));
        assertTrue(dictionary.isKey(key));
        String key2 = "KeyTwo";
        assertNull(dictionary.getValue(key2));
        assertFalse(dictionary.isKey(key2));
    }

    @Test
    void putDifferentValuesInOneKey() {
        Map<Object> dictionary = new Map<>(17);
        String s = "KeyKey";
        dictionary.put(s,7777);
        assertEquals(7777,dictionary.getValue(s));
        dictionary.put(s,1111);
        assertEquals(1111,dictionary.getValue(s));
        for (int i = 0; i < 100; i++) {
            int value = rand.nextInt(100) + 1;
            dictionary.put(s,value);
            assertEquals(value,dictionary.getValue(s));
        }
    }

}