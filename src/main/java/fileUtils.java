import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class fileUtils {

    public static void phoneNumber(String url) throws IOException {
        File file = new File(url);

        InputStream inputFile = new FileInputStream(file);

        Pattern numberPattern = Pattern.compile("\\(\\d{3}\\) \\d{3}-\\d{4}|\\d{3}-\\d{3}-\\d{4}");

        Scanner scanner = new Scanner(inputFile);

        Matcher matcher = numberPattern.matcher(scanner.nextLine());

        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }

        inputFile.close();
        scanner.close();
    }

    public static void loadgson(String readFile, String outFile) throws IOException {

        FileInputStream raed = new FileInputStream(readFile);
        FileOutputStream out = new FileOutputStream(outFile);

        List<User> users = new ArrayList<>();
        Scanner scanner = new Scanner(raed);
        String str = scanner.nextLine();

        while (scanner.hasNextLine()) {
            String[] kv = scanner.nextLine().split(" ");
            users.add(new User(kv[0], kv[1]));
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(users);
        out.write(json.getBytes(StandardCharsets.UTF_8));

        scanner.close();
        out.close();

        System.out.println(json);
    }


    public static void printWordFrequencies(String path) throws IOException {
        try (Stream<String> input = Files.lines(Paths.get(path))) {
            input
                    .flatMap(str -> Arrays.stream(str.toLowerCase().split("\\P{L}+"))) // поток слов
                    .collect(Collectors.groupingBy(
                            word -> word,
                            Collectors.summingInt(word -> 1)
                    )) // Map<String, Integer>
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(
                            Comparator.reverseOrder()
                    ))
                    .forEach(entry -> {
                        System.out.println(entry.getKey() + " " + entry.getValue());
                    });
        }
    }
}