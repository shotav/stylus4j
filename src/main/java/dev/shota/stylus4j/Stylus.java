package dev.shota.stylus4j;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.ScriptableObject;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Stylus {

    static String css;
    private static Script stylus;
    private static Script stylus4j;

    private Stylus() {}

    private static void init(Context context) throws IOException {
        if (stylus == null) stylus = context.compileString(read("stylus.js"), "stylus.js", 1, null);
        if (stylus4j == null) stylus4j = context.compileString(read("stylus4j.js"), "stylus4j.js", 1, null);
    }

    public static String compile(File file) throws IOException {
        final var string = Files.readString(file.toPath());
        return compile(string);
    }

    public static String compile(InputStream inputStream) throws IOException {
        final var bytes = inputStream.readAllBytes();
        return compile(bytes);
    }

    public static String compile(byte[] bytes) throws IOException {
        final var string = new String(bytes, StandardCharsets.UTF_8);
        return compile(string);
    }

    public static String compile(String string) throws IOException {
        final var context = Context.enter();
        final var scope = context.initStandardObjects(new Hooks(), true);

        scope.defineFunctionProperties(Hooks.NAMES, scope.getClass(), ScriptableObject.DONTENUM);
        scope.defineProperty("code", string, ScriptableObject.DONTENUM);

        init(context);
        stylus.exec(context, scope);
        stylus4j.exec(context, scope);

        Context.exit();
        return css;
    }

    private static String read(String file) throws IOException {
        final var path = String.format("stylus4j/%s", file);
        final var stream = Stylus.class.getClassLoader().getResourceAsStream(path);
        return new String(stream.readAllBytes(), StandardCharsets.UTF_8);
    }

}