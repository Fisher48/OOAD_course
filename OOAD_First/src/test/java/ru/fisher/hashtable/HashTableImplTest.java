package ru.fisher.hashtable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableImplTest {

    @Test
    void simpleTest() {
        HashTableImpl<Object> hashTable = new HashTableImpl<>(19, 3);

        hashTable.put("slot");
        hashTable.put("probably");
        hashTable.put("few");
        hashTable.put("tests");
        hashTable.put("words");
        hashTable.put("in");
        hashTable.put("hashtable");
        assertEquals(1, hashTable.getPutStatus());

        hashTable.put("tests");
        assertEquals(3, hashTable.getPutStatus());

        hashTable.put("few");
        hashTable.put("words");
        hashTable.put("slot");
        hashTable.put("hashtable");
        assertEquals(3, hashTable.getPutStatus());
        assertEquals(7, hashTable.size());
        assertTrue(hashTable.isContain("in"));

        hashTable.remove("in");
        assertEquals(1, hashTable.getRemoveStatus());
        assertEquals(6, hashTable.size());
        assertFalse(hashTable.isContain("in"));

        assertFalse(hashTable.isContain("this item not in hashtable"));
        hashTable.remove("this item not in hashtable");
        assertEquals(2, hashTable.getRemoveStatus());

    }

}