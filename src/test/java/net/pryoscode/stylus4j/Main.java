package net.pryoscode.stylus4j;

import org.junit.jupiter.api.Assertions;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) throws Exception {
        final var css = new String(Main.class.getClassLoader().getResourceAsStream("test.css").readAllBytes(), StandardCharsets.UTF_8);
        final var compiled = Stylus.compile(Main.class.getClassLoader().getResourceAsStream("test.styl"));
        Assertions.assertEquals(css, compiled);
    }

}