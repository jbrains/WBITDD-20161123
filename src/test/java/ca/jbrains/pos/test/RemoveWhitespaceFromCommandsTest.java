package ca.jbrains.pos.test;

import ca.jbrains.pos.RemoveWhitespaceFromCommands;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RemoveWhitespaceFromCommandsTest {
    private RemoveWhitespaceFromCommands removeWhitespaceFromCommands;

    @Before
    public void setUp() throws Exception {
        removeWhitespaceFromCommands = new RemoveWhitespaceFromCommands();
    }

    @Test
    public void none() throws Exception {
        final List<String> commands = Arrays.asList(
                "::command with no extra whitespace::",
                "::another command with no extra whitespace::",
                "::one\tstrange\tcommand\twith\tinterior   whitespace::",
                "::yet another command with no extra whitespace::"
        );

        Assert.assertEquals(commands, sanitize(commands));
    }

    @Test
    public void blankLines() throws Exception {
        final List<String> commands = Arrays.asList(
                "",
                "",
                "::command with no extra whitespace::",
                "",
                "",
                "",
                "",
                "::another command with no extra whitespace::",
                "",
                "",
                "::one\tstrange\tcommand\twith\tinterior   whitespace::",
                "",
                "::yet another command with no extra whitespace::",
                "",
                ""
        );

        final List<String> expectedSanitizedCommands = Arrays.asList(
                "::command with no extra whitespace::",
                "::another command with no extra whitespace::",
                "::one\tstrange\tcommand\twith\tinterior   whitespace::",
                "::yet another command with no extra whitespace::"
        );

        Assert.assertEquals(expectedSanitizedCommands, sanitize(commands));
    }

    @Test
    public void leadingAndTrailingWhitespace() throws Exception {
        final List<String> commands = Arrays.asList(
                "  \t  ::command with leading whitespace::",
                "::command with trailing whitespace::   \t     ",
                "  \t     \t  ::command with surrounding whitespace::   \t     \t "
        );

        final List<String> expectedSanitizedCommands = Arrays.asList(
                "::command with leading whitespace::",
                "::command with trailing whitespace::",
                "::command with surrounding whitespace::"
        );

        Assert.assertEquals(expectedSanitizedCommands, sanitize(commands));
    }

    private List<String> sanitize(List<String> commands) {
        return removeWhitespaceFromCommands.sanitizeCommands(commands.stream()).collect(Collectors.toList());
    }
}
