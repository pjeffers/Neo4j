package com.neo4j.index_team_hiring_exercise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SkipListTest {

    private SkipList skipList;

    @BeforeEach
    void setUp() {
        skipList = new SkipListImpl() ;
        //throw new UnsupportedOperationException("Provide your implementation here");
    }

        @Test
    void newSkipListShouldBeEmpty() {
        assertTrue(skipList.isEmpty());
        assertEquals(0, skipList.size());
        assertFalse(skipList.contains(10));
        skipList.print();
    }

    @Test
    void addSingleElement() {
        assertTrue(skipList.add(42));
        assertFalse(skipList.isEmpty());
        assertEquals(1, skipList.size());
        assertTrue(skipList.contains(42));
        skipList.print();
    }

    @Test
    void addAndDeletedeleteSingleElement() {
        assertTrue(skipList.add(42));
        assertFalse(skipList.isEmpty());
        assertEquals(1, skipList.size());
        assertTrue(skipList.contains(42));

        assertTrue(skipList.delete(42));
        assertTrue(skipList.isEmpty());
        assertEquals(0, skipList.size());
        assertFalse(skipList.contains(42));
        skipList.print();
    }

    @Test
    void addAndDeleteMultipleElementsIncludingBoundaryValues() {
        assertTrue(skipList.add(1));
        assertTrue(skipList.add(2));
        assertTrue(skipList.add(100));
        assertTrue(skipList.add(1000));
        assertTrue(skipList.add(30000));
        assertTrue(skipList.add(-42));
        assertTrue(skipList.add(Long.MIN_VALUE+1));
        assertTrue(skipList.add(Long.MAX_VALUE));

        assertFalse(skipList.isEmpty());
        assertEquals(8, skipList.size());
        assertTrue(skipList.contains(1));
        assertTrue(skipList.contains(2));
        assertTrue(skipList.contains(100));
        assertTrue(skipList.contains(1000));
        assertTrue(skipList.contains(30000));
        assertTrue(skipList.contains(-42));

        //check that boundary values can be inserted
        assertTrue(skipList.contains(Long.MIN_VALUE+1));
        assertTrue(skipList.contains(Long.MAX_VALUE));

        assertTrue(skipList.delete(1000));
        assertTrue(skipList.delete(30000));
        assertFalse(skipList.isEmpty());
        assertEquals(6, skipList.size());

        assertTrue(skipList.contains(Long.MIN_VALUE+1));
        assertTrue(skipList.contains(Long.MAX_VALUE));
        assertFalse(skipList.contains(1000));
        assertFalse(skipList.contains(30000));
        assertTrue(skipList.contains(1));
        assertTrue(skipList.contains(2));
        assertTrue(skipList.contains(100));
        assertTrue(skipList.contains(-42));
        skipList.print();

        // now check that the boundary values can be deleted
        assertTrue(skipList.delete(Long.MIN_VALUE+1));
        assertTrue(skipList.delete(Long.MAX_VALUE));

        assertEquals(4, skipList.size());
        assertFalse(skipList.contains(Long.MIN_VALUE+1));
        assertFalse(skipList.contains(Long.MAX_VALUE));


    }

    @Test
    void addDuplicateElement() {
        assertTrue(skipList.add(42));
        assertFalse(skipList.add(42));
        assertFalse(skipList.add(42));
        assertFalse(skipList.isEmpty());
        assertEquals(1, skipList.size());
        assertTrue(skipList.contains(42));
        assertFalse(skipList.contains(12));
        skipList.print();
    }
    @Test
    void addMultipleDuplicateElementsAtStartOfList() {
        assertTrue(skipList.add(42));
        assertFalse(skipList.add(42));
        assertFalse(skipList.add(42));
        assertFalse(skipList.isEmpty());
        assertTrue(skipList.contains(42));
        assertEquals(1, skipList.size());

        //assert valueNotfound is True as this is the first
        // insertion of this value
        assertTrue(skipList.add(12));

        //assert valueNotfound is false
        assertFalse(skipList.add(12));
        assertFalse(skipList.add(12));

        //check the contents and size of the list
        // there should be no duplicates
        assertFalse(skipList.isEmpty());
        assertTrue(skipList.contains(42));
        assertTrue(skipList.contains(12));
        assertEquals(2, skipList.size());
        skipList.print();
    }

    @Test
    void addMultipleDuplicateElementsAtEndOfList() {
        assertTrue(skipList.add(42));
        assertFalse(skipList.add(42));
        assertFalse(skipList.add(42));
        assertFalse(skipList.isEmpty());
        assertTrue(skipList.contains(42));
        assertEquals(1, skipList.size());

        //assert valueNotfound is True as this is the first
        // insertion of this value
        assertTrue(skipList.add(162));

        //assert valueNotfound is false
        assertFalse(skipList.add(162));
        assertFalse(skipList.add(162));

        //check the contents and size of the list
        // there should be no duplicates
        assertFalse(skipList.isEmpty());
        assertTrue(skipList.contains(42));
        assertTrue(skipList.contains(162));
        assertEquals(2, skipList.size());
        skipList.print();
    }

    @Test
    void addMultipleDuplicateElementsAtMiddleOfList() {
        assertTrue(skipList.add(42));
        assertFalse(skipList.add(42));
        assertFalse(skipList.add(42));
        assertFalse(skipList.isEmpty());
        assertTrue(skipList.contains(42));
        assertEquals(1, skipList.size());

        //assert valueNotfound is True as this is the first
        // insertion of this value
        assertTrue(skipList.add(162));

        //assert valueNotfound is false
        assertFalse(skipList.add(162));
        assertFalse(skipList.add(162));

        //assert valueNotfound is True as this is the first
        // insertion of this value
        assertTrue(skipList.add(96));

        //assert valueNotfound is false
        assertFalse(skipList.add(96));
        assertFalse(skipList.add(96));

        //check the contents and size of the list
        // there should be no duplicates
        assertFalse(skipList.isEmpty());
        assertTrue(skipList.contains(42));
        assertTrue(skipList.contains(96));
        assertTrue(skipList.contains(162));
        assertEquals(3, skipList.size());
        skipList.print();
    }
    @Test
    void searchforValueNotfound() {
        assertTrue(skipList.add(42));
        assertFalse(skipList.add(42));
        assertFalse(skipList.add(42));

        assertFalse(skipList.isEmpty());
        assertEquals(1, skipList.size());
        assertTrue(skipList.contains(42));
        assertFalse(skipList.contains(12));
        skipList.print();
    }
    @Test
    void searchforValuefound2() {
        assertTrue(skipList.add(42));
        assertTrue(skipList.add(12));
        assertFalse(skipList.isEmpty());
        assertEquals(2, skipList.size());
        assertTrue(skipList.contains(42));
        assertTrue(skipList.contains(12));
        skipList.print();
    }

}
