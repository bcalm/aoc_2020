package advent_of_code_2020.day_8;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InstructionDecoder {
    private final String inputs;
    private int accumulator = 0;
    private int globalIndex = 0;

    public InstructionDecoder(String inputs) {
        this.inputs = inputs;
    }

    public int getAccumulatorValue() {
        List<String> instructionList = parseInput();
        final List<String> modifiedInstructionList = getModifiedInstructionList(instructionList);

        while (isInfiniteLoop(modifiedInstructionList)) {
            this.accumulator = 0;
            return getAccumulatorValue();
        };
        return this.accumulator;
    }

    private List<String> getModifiedInstructionList(List<String> instructionList) {
        for (int i = this.globalIndex + 1; i < instructionList.size(); i++) {
            String insturction = instructionList.get(i);
            if (insturction.contains("jmp")) {
                instructionList.set(i, insturction.replace("jmp", "nop"));
                this.globalIndex = i;
                break;
            }
            if (insturction.contains("nop")) {
                instructionList.set(i, insturction.replace("nop", "jmp"));
                this.globalIndex = i;
                break;
            }
        }
        return instructionList;
    }

    private boolean isInfiniteLoop(List<String> instructionList) {
        List<Integer> executedInstruction = new ArrayList<>();
        int index = 0;
        while (index < instructionList.size()) {
            if (executedInstruction.contains(index)) {
                return true;
            }
            String[] instruction = instructionList.get(index).split(" ");
            String operator = instruction[1].substring(0, 1);
            int value = Integer.parseInt(instruction[1].substring(1));
            executedInstruction.add(index);
            index = getNextIndex(index, instruction, operator, value);
        }
        return false;
    }

    private int getNextIndex(int index, String[] instruction, String operator, int value) {
        final int addValue = operator.equals("+") ? value : -value;
        switch (instruction[0]) {
            case "acc" -> index = handleAcc(index, addValue);
            case "nop" -> index = handleNop(index);
            case "jmp" -> index = handleJump(index, addValue);
            default -> throw new IllegalStateException("Unexpected value: " + instruction[0]);
        }
        return index;
    }

    private int handleJump(int index, int addValue) {
        return index + addValue;
    }

    private int handleNop(int index) {
        return ++index;
    }

    private int handleAcc(int index, int addValue) {
        this.accumulator += addValue;
        return ++index;
    }


    private List<String> parseInput() {
        return Arrays.stream(this.inputs.split("\n"))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
}
