package ca.jbrains.pos.test;

import java.util.Arrays;
import java.util.List;

public class Text {
    // REFACTOR Move this to a reusable library
    public static List<String> lines(String text) {
        return Arrays.asList(text.split(System.lineSeparator()));
    }
}
