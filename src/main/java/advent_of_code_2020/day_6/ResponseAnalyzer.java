package advent_of_code_2020.day_6;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ResponseAnalyzer {
    private final String inputs;

    public ResponseAnalyzer(String inputs) {
        this.inputs = inputs;
    }

    public int getPositiveResponseCount() {
        String[] responseList = parseInput();
        int positiveResponses = 0;
        for (String response : responseList) {
            positiveResponses += getPositiveResponse(response);
        }
        return positiveResponses;
    }

    private int getPositiveResponse(String response) {
        return new HashSet<>(Arrays.asList(response.split(""))).size();
    }

    private String[] parseInput() {
        return this.inputs
                .replace("\n\n", " ")
                .replace("\n", "")
                .split(" ");
    }
}
