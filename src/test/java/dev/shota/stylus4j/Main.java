package dev.shota.stylus4j;

import org.junit.jupiter.api.Assertions;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) throws Exception {
        final var css = load("test.css").replaceAll("\\s+", "");
        final var compiled = Stylus.compile(load("test.styl")).replaceAll("\\s+", "");
        Assertions.assertEquals(css, compiled);
    }

    private static String load(String name) throws Exception {
        final var stream = Main.class.getClassLoader().getResourceAsStream(name);
        return new String(stream.readAllBytes(), StandardCharsets.UTF_8);
    }

}