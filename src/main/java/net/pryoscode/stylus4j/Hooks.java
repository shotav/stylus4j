package net.pryoscode.stylus4j;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import java.io.InputStreamReader;

public class Hooks extends ScriptableObject {

    public static final String[] NAMES = { "load", "send" };

    protected Hooks() {}

    public static void load(Context cx, Scriptable thisObj, Object[] args, Function funObj) throws Exception {
        Hooks shell = (Hooks) getTopLevelScope(thisObj);
        for (int i = 0; i < args.length; i++) {
                shell.processSource(cx, Context.toString(args[i]));
        }
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