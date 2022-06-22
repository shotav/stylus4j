package dev.shota.stylus4j;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.ScriptableObject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Compiler {

    protected static String css;
    private static Script stylus;
    private static Script stylus4j;

    private Compiler() {}

    protected static String compile(String string) throws IOException {
        final var context = Context.enter();
        final var scope = context.initStandardObjects(new Hooks(), true);

        scope.defineFunctionProperties(Hooks.NAMES, scope.getClass(), ScriptableObject.DONTENUM);
        scope.defineProperty("code", string, ScriptableObject.DONTENUM);

        if (stylus == null) stylus = context.compileString(read("stylus.js"), "stylus.js", 1, null);
        if (stylus4j == null) stylus4j = context.compileString(read("stylus4j.js"), "stylus4j.js", 1, null);
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