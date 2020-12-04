package advent_of_code_2020.day_3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RouteFinder {
    private static final int REPEATED_COUNT = 100;
    private final String TREE = "#";
    private final String inputs;

    public RouteFinder(String inputs) {
        this.inputs = inputs;
    }

    private static String apply(String input) {
        return input.repeat(REPEATED_COUNT);
    }

    public int nbOfEncounteredTrees() {
        List<String> trajectory = parseInput(inputs);
        int colIndex = 0;
        int encounteredTree = 0;
        for (String path : trajectory) {
            if (isTree(path.split("")[colIndex])) {
                encounteredTree++;
            }
            colIndex += 3;
        }
        return encounteredTree;
    }

    private boolean isTree(String place) {
        return place.equals(TREE);

    }

    private List<String> parseInput(String inputs) {
        return Arrays.stream(inputs.split("\n"))
                .map(RouteFinder::apply)
                .collect(Collectors.toList());
    }
}
