package net.pryoscode.stylus4j;

import org.mozilla.javascript.Context;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Stylus {

    private Stylus() {}

    public static String compile(InputStream styl) throws Exception {
        final var bytes = styl.readAllBytes();
        final var string = new String(bytes, StandardCharsets.UTF_8);
        return compile(string);
    }

    public static String compile(String styl) throws Exception {
        final var context = Context.enter();
        final var scope = context.initStandardObjects();

        final var result = context.evaluateReader(scope, new InputStreamReader(Stylus.class.getClassLoader().getResourceAsStream("stylus4j.js")), "javascript/stylus4j.js", 1, null);
        System.out.println(Context.toString(result));

        context.exit();
        return styl;
    }

}