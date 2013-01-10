package com.nz.simplecrud.util;

import java.lang.reflect.Field;
import java.util.Comparator;
import org.primefaces.model.SortOrder;

/**
 * Generic sorting utility class 
 * @param <T> 
 */
public class LazySorter<T> implements Comparator<T> {

    private String sortField;
    
    private SortOrder sortOrder;
    
    /**
     * initializing sorting field and sorting order
     * @param sortField
     * @param sortOrder 
     */
    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    /**
     * Comparing object1 and object2 with reflection
     * @param object1
     * @param object2
     * @return 
     */
    @Override
    public int compare(T object1, T object2) {
        try {
            Field field1 = object1.getClass().getDeclaredField(this.sortField);
            Field field2 = object2.getClass().getDeclaredField(this.sortField);
            field1.setAccessible(true);
            field2.setAccessible(true);
            Object value1 = field1.get(object1);
            Object value2 = field2.get(object2);

            int value = ((Comparable)value1).compareTo(value2);
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException();
        }
    }
}