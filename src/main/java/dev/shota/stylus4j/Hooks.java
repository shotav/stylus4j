package dev.shota.stylus4j;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import java.io.InputStreamReader;

public class Hooks extends ScriptableObject {

    public static final String[] NAMES = { "load", "send" };

    protected Hooks() {}

    public static void load(Context cx, Scriptable thisObj, Object[] args, Function funObj) throws Exception {
        final var hooks = (Hooks) getTopLevelScope(thisObj);
        for (Object o : args)
            hooks.processSource(cx, Context.toString(o));
    }

    public static void send(Context cx, Scriptable thisObj, Object[] args, Function funObj) {
        Stylus.css = (String) args[0];
    }

    private void processSource(Context cx, String filename) throws Exception {
        final var stream = getClass().getClassLoader().getResourceAsStream(filename);
        cx.evaluateReader(this, new InputStreamReader(stream), filename, 1, null);
    }

    @Override
    public String getClassName() {
        return "hooks";
    }

}