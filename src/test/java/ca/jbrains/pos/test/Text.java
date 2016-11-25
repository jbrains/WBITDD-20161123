package ca.jbrains.pos.test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Text {
    // REFACTOR Move this to a reusable library
    public static List<String> lines(String text) {
        return Arrays.asList(text.split(System.lineSeparator()));
    }

    public static String unlines(List<String> lines) {
        return lines.stream()
                .map((each) -> each + System.lineSeparator())
                .collect(Collectors.joining());
    }
}
