package advent_of_code_2020.day_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PasswordAnalyzer {
    private final String inputs;

    public PasswordAnalyzer(String inputs) {
        this.inputs = inputs;
    }

    public int getCorrectPasswordCount() {
        List<PasswordPattern> passwordPatterns = parseInput(inputs);
        return (int) passwordPatterns.stream()
                .filter(this::isCorrectPassword)
                .count();
    }

    private boolean isCorrectPassword(PasswordPattern passwordPattern) {
        int characterOccurrence = getCharacterCount(passwordPattern.getPassword(), passwordPattern.getCharacter());
        return characterOccurrence >= passwordPattern.getMinOccurrence()
                && characterOccurrence <= passwordPattern.getMaxOccurrence();
    }

    private int getCharacterCount(String password, String expectedCharacter) {
        return (int) Arrays.stream(password.split(""))
                .filter((character)->character.equals(expectedCharacter))
                .count();
    }

    private List<PasswordPattern> parseInput(String inputs) {
        return Arrays.stream(inputs
                .split("\n"))
                .collect(ArrayList::new, (ArrayList<PasswordPattern> passwords, String input) -> {
                    final String[] password = input.split(" ");
                    final String[] characterOccurrence = password[0].split("-");
                    final String character = password[1].split(":")[0];
                    passwords.add(new PasswordPattern(characterOccurrence, character, password[2]));
                }, ArrayList::addAll);
    }
}
