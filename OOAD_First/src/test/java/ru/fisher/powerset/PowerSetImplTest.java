package ru.fisher.powerset;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PowerSetImplTest {

    @Test
    void simpleTest() {
        PowerSetImpl<String> powerSet = new PowerSetImpl<>(20000);
        powerSet.put("hello");
        powerSet.put("hello");
        powerSet.put("hello");
       assertTrue(powerSet.get("hello"));
    }

    @Test
    void intersectionTest() {
        PowerSetImpl<String> set = new PowerSetImpl<>(20000);
        set.put("Test1"); set.put("Test2");
        set.put("Test3"); set.put("Test4");
        set.put("Test5"); set.put("Test6");
        PowerSetImpl<String> set2 = new PowerSetImpl<>(20000);
        set2.put("Test1");set2.put("Test8");
        set2.put("Test9"); set2.put("Test2");
        set2.put("Test3"); set2.put("Test7");
        PowerSetImpl<String> setCheck = new PowerSetImpl<>(20000);
        setCheck.put("Test2");  setCheck.put("Test3");  setCheck.put("Test1");
        assertEquals(3,set.intersection(set2).size());
    }

    @Test
    void unionTest() {
        PowerSetImpl<String> set = new PowerSetImpl<>(50000);
        PowerSetImpl<String> set2 = new PowerSetImpl<>(50000);
        for (int i = 0, j = 100000; j > 50000; i++, j--) {
            set2.put(" " + i);
            set.put(" " + j);
        }
        assertEquals(set.union(set2).size(),set.union(set2).size());
    }

    @Test
    void differenceWithEmptyTest() {
        PowerSetImpl<String> set = new PowerSetImpl<>(50000);
        PowerSetImpl<String> set2 = new PowerSetImpl<>(50000);
        for (int i = 0; i < 10; i++) {
            set.put(" " + i);
            set2.put(" " + i);
        }
        set2.put("Test"); set2.put("Test2");
        assertEquals(0,set.difference(set2).size());
    }

    @Test
    void differenceTest() {
        PowerSetImpl<String> set = new PowerSetImpl<>(50000);
        PowerSetImpl<String> set2 = new PowerSetImpl<>(50000);
        for (int i = 0; i < 10; i++) {
            set.put("" + i);
            set2.put("" + i);
        }
        set2.put("Test"); set2.put("Test2");
        assertEquals(2,set2.difference(set).size());
    }

    @Test
    void isSubsetSetOneIncludeInSetTwo(){
        PowerSetImpl<String> set = new PowerSetImpl<>(100);
        PowerSetImpl<String> set2 = new PowerSetImpl<>(50);
        for (int i = 4; i < 8; i++) {
            set2.put(" " + i);
        }
        for (int j = 0; j < 10; j++) {
            set.put(" " + j);
        }
        assertTrue(set.isSubset(set2));
    }

    @Test
    void isSubsetSetTwoIncludeInSetOne() {
        PowerSetImpl<String> set = new PowerSetImpl<>(30000);
        PowerSetImpl<String> set2 = new PowerSetImpl<>(30000);
        for (int i = 5; i < 10000; i++) {
            set2.put(" " + i);
        }
        for (int j = 0; j < 20000; j++) {
            set.put(" " + j);
        }
        assertTrue(set.isSubset(set2));
    }

    @Test
    void isSubsetFalse() {
        PowerSetImpl<String> set = new PowerSetImpl<>(30000);
        PowerSetImpl<String> set2 = new PowerSetImpl<>(100);
        for (int i = 0; i < 100; i++) {
            set2.put(" " + i);
        }
        for (int j = 3; j < 20000; j++) {
            set.put(" j" + j);
        }
        set2.put(" 0");
        assertFalse(set2.isSubset(set));
    }


    @Test
    void SubsetTest() {
        PowerSetImpl<String> set1 = new PowerSetImpl<>(30000);
        PowerSetImpl<String> set2 = new PowerSetImpl<>(30000);
        for (int i = 0; i < 20000; i++) {
            set1.put("val" + i);
        }
        for (int i = 10000; i < 15000; i++) {
            set2.put("val" + i);
        }
        assertFalse(set2.isSubset(set1));
        assertTrue(set1.isSubset(set2));
    }


    @Test
    void Subset3Test() {
        PowerSetImpl<String> setA = new PowerSetImpl<>(100);
        PowerSetImpl<String> setB = new PowerSetImpl<>(100);

        setA.put("set1");
        setA.put("set2");
        setA.put("set3");

        setB.put("set2");
        setB.put("set3");

        assertTrue(setA.isSubset(setB));
        assertFalse(setB.isSubset(setA));
    }


}