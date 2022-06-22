package dev.shota.stylus4j;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

public class Hooks extends ScriptableObject {

    public static final String[] NAMES = { "send" };

    protected Hooks() {}

    public static void send(Context cx, Scriptable thisObj, Object[] args, Function funObj) {
        Compiler.css = (String) args[0];
    }

    @Override
    public String getClassName() {
        return "hooks";
    }

}