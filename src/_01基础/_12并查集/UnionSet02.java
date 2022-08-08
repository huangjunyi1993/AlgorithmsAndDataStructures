package _01基础._12并查集;

import java.util.HashMap;
import java.util.Map;

/**
 * 一个人有三个id，只有其中一个id相同，就认为是同一个人
 * 给定一个Person数组，问里面有几个人
 */
public class UnionSet02 {

    private static class Person {
        private int id1;
        private int id2;
        private int id3;
    }

    public static int findPersonNum(Person[] persons) {
        //id1和Person的映射
        Map<Integer, Person> id1Map = new HashMap<>();
        //id2和Person的映射
        Map<Integer, Person> id2Map = new HashMap<>();
        //id3和Person的映射
        Map<Integer, Person> id3Map = new HashMap<>();

        int result = 0;

        for (int i = 0; i < persons.length; i++) {
            Person person = persons[i];
            boolean flag = true;
            if (!id1Map.containsKey(person.id1)) {
                id1Map.put(person.id1, person);
            } else {
                flag = false;
            }
            if (!id2Map.containsKey(person.id2)) {
                id2Map.put(person.id2, person);
            } else {
                flag = false;
            }
            if (!id3Map.containsKey(person.id3)) {
                id3Map.put(person.id3, person);
            } else {
                flag = false;
            }
            if (flag) result++;
        }

        return result;
    }

}
