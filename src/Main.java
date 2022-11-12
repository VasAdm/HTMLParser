import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        String htmlFile = parseFile("data/courses.html");

//        Document doc = Jsoup.parse(htmlFile);
        Document doc = null;
        try {
            doc = Jsoup.connect("https://skillbox.ru/courses/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements elements = Objects.requireNonNull(doc).select("a.card__title");
        elements.forEach(element -> System.out.println(element.text()));

    }

    private static String parseFile(String path) {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            lines.forEach(line -> builder.append(line).append(System.lineSeparator()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }
}
