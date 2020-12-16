package advent_of_code_2020.day_9;

import java.util.*;

public class PasswordDecoder {
    private final String inputs;
    private final int preamble = 25;

    public PasswordDecoder(String inputs) {
        this.inputs = inputs;
    }

    public Long getExceptionalNumber() {
        List<Long> portOutputs = parseInput();
        final HashMap<Long, List<Long>> integerListHashMap = new HashMap<>();
        for (int index = preamble; index < portOutputs.size(); index++) {
            Long number = portOutputs.get(index);
            List<Long> addNumber = new ArrayList<>();
            for (int i = index - 1; i > index - preamble - 1; i--) {
                addNumber.add(portOutputs.get(i));
            }
            integerListHashMap.put(number, addNumber);
        }

        final Long exceptionalNumber = getExceptionalNumber(integerListHashMap);
        return getConsecutiveNumberSum(exceptionalNumber, portOutputs);
    }


    private Long getExceptionalNumber(HashMap<Long, List<Long>> integerListHashMap) {
        for (Map.Entry<Long, List<Long>> entry : integerListHashMap.entrySet()) {
            Long number = entry.getKey();
            final List<Long> possibleAdditions = getPossibleAdditions(entry.getValue());
            if (!possibleAdditions.contains(number)) {
                return number;
            }
        }
        return 0L;
    }

    private List<Long> getPossibleAdditions(List<Long> belongingNumbers) {
        List<Long> possibleAdditions = new ArrayList<>();
        belongingNumbers.forEach(integer -> {
            belongingNumbers.stream().filter(integer1 -> !integer.equals(integer1))
                    .map(integer1 -> integer + integer1)
                    .forEach(possibleAdditions::add);
        });
        return possibleAdditions;
    }


    private List<Long> parseInput() {
        return Arrays.stream(this.inputs.split("\n"))
                .collect(ArrayList::new, (c, e) -> {
                    c.add(Long.parseLong(e));
                }, ArrayList::addAll);
    }

    private Long getConsecutiveNumberSum(Long exceptionalNumber, List<Long> portOutputs) {
        for (int i = 0; i < portOutputs.size(); i++) {
            Long sum = 0L;
            for (int j = i; sum < exceptionalNumber; j++) {
                sum += portOutputs.get(j);
                if (sum.equals(exceptionalNumber)) {
                    final List<Long> consecutiveNumberSum = portOutputs.subList(i, j);
                    return Collections.max(consecutiveNumberSum) + Collections.min(consecutiveNumberSum);
                }
            }
        }
        return 0L;
    }
}
