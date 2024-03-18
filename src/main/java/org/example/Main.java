package org.example;

import java.lang.reflect.Field;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {

        User a = new User(55, "hello", "+99800_000_00_00");
        User b = new User(2, "b", "+99800_111_11_11");
        User c = new User(55, "hello", "+99800_000_00_00");
        User d = new User(3, "d", "+99800_222_22_22");
        User e = new User(55, "hello", "+99800_000_00_00");
        User f = new User(4, "f", "+99800_333_33_33");
        User j = new User(55, "hello", "+99800_000_00_00");
        User k = new User(5, "k", "+99800_444_44_44");
        User l = new User(55, "l", "+99800_000_00_00");
        User m = new User(2, "b", "+99800_111_11_11");
        User n = new User(55, "hello", "+99800_000_00_00");

        List<User> users = new ArrayList<>(Arrays.asList(a,b,c,d,e,f,j,k,l,m,n));
        checkDuplicatesValue(users);
    }

    private static <T> void checkDuplicatesValue(Collection<T> collection) throws IllegalAccessException {
        for(Field field: User.class.getDeclaredFields()){
            field.setAccessible(true);
            Map<Object, Integer> countMap = new HashMap<>();
            initCountMap(collection, field, countMap);
            printCountMap(field, countMap);
        }

    }

    private static <T> void initCountMap(Collection<T> collection, Field field, Map<Object, Integer> countMap) throws IllegalAccessException {
        for (T element: collection){
            Object values = field.get(element);
            Integer counterDuplicatesByZero = countMap.getOrDefault(values, 0) + 1;
            countMap.put(values, counterDuplicatesByZero);
        }
    }

    private static void printCountMap(Field field, Map<Object, Integer> countMap){
        for (Map.Entry<Object, Integer> entry : countMap.entrySet()){
            Object duplicates = entry.getKey();
            Integer count = entry.getValue();
            if (count > 1) System.out.println(field.getName() + " -> " + duplicates + ", " + "Count: " + count);
        }
    }
}
