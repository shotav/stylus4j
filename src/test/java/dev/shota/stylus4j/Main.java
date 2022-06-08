package dev.shota.stylus4j;

import org.junit.jupiter.api.Assertions;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) throws Exception {
        final var css = load("test.css");
        final var styl = load("test.styl");

        final var start = System.currentTimeMillis();
        final var compiled = Stylus.compile(styl);
        System.out.println(String.format("%sms", System.currentTimeMillis() - start));

        Assertions.assertEquals(trim(css), trim(compiled));
    }

    private static String load(String name) throws Exception {
        final var stream = Main.class.getClassLoader().getResourceAsStream(name);
        return new String(stream.readAllBytes(), StandardCharsets.UTF_8);
    }

    private static String trim(String string) {
        return string.replaceAll("\\s+", "");
    }

}