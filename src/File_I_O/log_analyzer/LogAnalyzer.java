package File_I_O.log_analyzer;

import java.io.*;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class LogAnalyzer {
    public static void analyze(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            Map<String, Long> result = reader.lines()
                    .flatMap(line -> Arrays.stream(line.split(" "))
                            .filter(str -> str.toLowerCase().equals("info")
                                    || str.toLowerCase().equals("warning")
                                    || str.toLowerCase().equals("error"))
                            .map(String::toLowerCase)
                    )
                    .collect(Collectors.groupingBy(word -> word, Collectors.counting()));
            System.out.println(result);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void main() throws Exception{
            File tempFile = new File("test_logs.txt");

            try (FileWriter writer = new FileWriter(tempFile)) {
                writer.write("INFO User logged in\n" +
                        "ERROR Database unavailable\n" +
                        "INFO User logged out\n" +
                        "ERROR Connection failed\n" +
                        "WARNING Low memory");
            }

            analyze(tempFile);
            tempFile.delete();
        }

}
