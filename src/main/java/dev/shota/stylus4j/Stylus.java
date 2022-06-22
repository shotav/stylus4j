package dev.shota.stylus4j;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Stylus {

    private Stylus() {}

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
        return Compiler.compile(string);
    }

}