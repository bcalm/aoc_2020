package advent_of_code_2020.day_1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ExpenseCalculator {

    private String inputs;
    private final Integer EXPECTED_SUM = 2020;

    public ExpenseCalculator(String inputs) {
        this.inputs = inputs;
    }


    public Integer getResult() {
        List<Integer> expensesList = this.getDesiredFormat();
        return this.calculate(expensesList);
    }

    private Integer calculate(List<Integer> expensesList) {
        for (Integer entry1 : expensesList) {
            for (Integer entry2 : expensesList) {
                for (Integer entry3 : expensesList) {
                    if (areCorrectEntities(entry1, entry2, entry3)) {
                        return entry1 * entry2 * entry3;
                    }
                }
            }
        }
        return null;
    }

    private boolean areCorrectEntities(Integer entry1, Integer entry2, Integer entry3) {
        return entry1 + entry2 + entry3 == EXPECTED_SUM;
    }

    private List<Integer> getDesiredFormat() {
        String[] inputList = this.inputs.split("\n");
        return Arrays.stream(inputList)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
