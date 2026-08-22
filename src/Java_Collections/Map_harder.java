package Java_Collections;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Map_harder {
    static void main() {
        Map<String, List<Integer>> studentGrades = new HashMap<>();
        studentGrades.put("John", List.of(90, 80, 70));
        studentGrades.put("Kay", List.of(60, 70, 65));
        studentGrades.put("Zane", List.of(100, 100, 100));

        for (Map.Entry<String, List<Integer>> entry : studentGrades.entrySet()) {
            List<Integer>  grades = entry.getValue();
            System.out.println(grades.stream().mapToInt(Integer::intValue).sum() / grades.size());
        }
    }
}
