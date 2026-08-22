package Generics;

import java.math.BigDecimal;
import java.util.List;

public class Harder_Generics {
    static void main() {
        sum(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        sum(List.of(1.5, 2.4,3.7));
        sum(List.of(new BigDecimal("1.5"), new BigDecimal("2.4"), new BigDecimal("3.7")));
    }

    public static  <T extends Number> double sum(List<T> list){
        double sum = 0;

        for (T item : list) {
            sum += item.doubleValue();
        }
        System.out.println(sum);
        return sum;
    }
}
