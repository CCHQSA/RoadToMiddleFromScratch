package Java_Collections;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class List_Task {
    static void main() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(8);
        System.out.println(findMax(list));
        System.out.println(findMin(list));
        System.out.println(calculateAvg(list));
        System.out.println(removeDuplicates(list));
        System.out.println(reverse(list));
        System.out.println(secondLargest(list));


    }

    public static  int findMax(List<Integer> list) {
        int max = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) > max) {
                max = list.get(i);
            }
        }
        return max;
    }

    public static  int findMin(List<Integer> list) {
        int min = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) < min) {
                min = list.get(i);
            }
        }
        return min;
    }

    public static  int calculateAvg(List<Integer> list) {
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
        }
        return sum / list.size();
    }

    public static List<Integer> removeDuplicates(List<Integer> list) {
        Set<Integer> set = new HashSet<>(list);
        return new ArrayList<>(set);
    }

    public static List<Integer> reverse(List<Integer> list) {
        List<Integer> reversedList = new ArrayList<>(list);
        for (int i = 0; i < reversedList.size() / 2; i++) {
            int temp = reversedList.get(i);
            reversedList.set(i, reversedList.get(reversedList.size() - i - 1));
            reversedList.set(reversedList.size() - i - 1, temp);
        }
        return reversedList;
    }

    public static int secondLargest(List<Integer> list) {
        int max = Math.max(list.get(0), list.get(1));
        int secondMax = Math.min(list.get(0), list.get(1));
        for (int i = 2; i < list.size(); i++) {
            int current = list.get(i);
            if (current > max) {
                secondMax = max;
                max = current;
            } else if (current > secondMax && current != max) {
                secondMax = current;
            }
        }
        return secondMax;
    }




}
