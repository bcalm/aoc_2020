package advent_of_code_2020.day_6;

import java.util.Arrays;

public class ResponseAnalyzer {
    private final String inputs;

    public ResponseAnalyzer(String inputs) {
        this.inputs = inputs;
    }

    public int getPositiveResponseCount() {
        return Arrays.stream(parseInput())
                .mapToInt(this::getPositiveResponse)
                .sum();
    }

    private int getPositiveResponse(String response) {
        String[] indResponse = response.split("\n");
        String[] firstPersonResponse = indResponse[0].split("");
        return (int) Arrays.stream(firstPersonResponse)
                .filter(e->Arrays.stream(indResponse)
                        .allMatch(el -> el.contains(e)))
                .count();
    }

    private String[] parseInput() {
        return this.inputs
                .replace("\n\n", " ")
                .split(" ");
    }
}
