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

    public long nbOfEncounteredTrees() {
        int[][] steps = {{1, 1}, {3, 1}, {5, 1}, {7, 1}, {1, 2}};
        return Arrays.stream(steps)
                .mapToLong(step -> getEncounteredTree(step[0], step[1]))
                .reduce(1, (a, b) -> a * b);
    }

    private int getEncounteredTree(int rightSteps, int downSteps) {
        List<String> trajectory = parseInput(inputs);
        int colIndex = 0;
        int encounteredTree = 0;
        for (int index = 0; index < trajectory.size(); index += downSteps) {
            String path = trajectory.get(index);
            if (isTree(path.split("")[colIndex])) {
                encounteredTree++;
            }
            colIndex += rightSteps;
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
