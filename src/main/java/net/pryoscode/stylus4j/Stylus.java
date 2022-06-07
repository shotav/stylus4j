package net.pryoscode.stylus4j;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptableObject;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Stylus {

    static String css;

    private Stylus() {}

    public static String compile(InputStream inputStream) throws IOException {
        final var bytes = inputStream.readAllBytes();
        final var string = new String(bytes, StandardCharsets.UTF_8);
        return compile(string);
    }

    public static String compile(File file) throws IOException {
        final var string = Files.readString(file.toPath());
        return compile(string);
    }

    public static String compile(String string) throws IOException {
        final var context = Context.enter();
        final var scope = context.initStandardObjects(new Hooks(), true);

        scope.defineFunctionProperties(Hooks.NAMES, scope.getClass(), ScriptableObject.DONTENUM);
        scope.defineProperty("styl", string, ScriptableObject.DONTENUM);

        context.evaluateReader(scope, new InputStreamReader(Stylus.class.getClassLoader().getResourceAsStream("stylus4j.js")), "stylus4j.js", 1, null);

        Context.exit();
        return css;
    }

}