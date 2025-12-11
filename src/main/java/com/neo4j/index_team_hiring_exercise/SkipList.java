package com.neo4j.index_team_hiring_exercise;

/**
 * A skip list with set-like characteristics for {@code long} values.
 */
public interface SkipList {

    /**
     * Adds a value to this list if it is not already present.
     *
     * @return {@code true} if the list did not contain the element, otherwise {@code false}.
     */
    boolean add(long value);

    /**
     * Tests if an element is contained in this list.
     *
     * @return {@code true} of the list contains the value, {@code false} if not.
     */
    boolean contains(long value);


    /**
     * delete an element if it is contained in this list.
     *
     * @return {@code true} of the list contained the value, {@code false} if it did not.
     */
    boolean delete(long value);

    /**
     * Returns the number of values in this list.
     *
     * @return the number of values in this list.
     */
    int size();

    /**
     * Tests if this list is empty.
     *
     * @return {@code true} if the list does not contain any values, {@code false} it it does.
     */
    boolean isEmpty();

    public void print();
}
