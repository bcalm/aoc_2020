package advent_of_code_2020.day_7;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BagContainer {
    private final String inputs;
    private int count = 0;

    public BagContainer(String inputs) {
        this.inputs = inputs;
    }

    public int nbOfBagCanContainShinyBag() {
        HashMap<String, String> bags = parseInput();
        int count = 0;
        for (Map.Entry<String, String> stringStringEntry : bags.entrySet()) {
            if (canContainShinyBag(stringStringEntry.getValue(), bags))
                count++;
        }
        return count;
    }

    private boolean canContainShinyBag(String value, HashMap<String, String> bags) {
        String[] containingBag = value.substring(0, value.length() - 1).split(", ");
        return Arrays.stream(containingBag).anyMatch(bagName -> {
            if (bagName.contains("no other bags")) return false;
            if (bagName.contains("shiny gold bag")) return true;
            int bagQuantity = Integer.parseInt(String.valueOf(bagName.charAt(0)));
            String bagType = bagQuantity == 1 ? bagName.substring(2).concat("s") : bagName.substring(2);
            return canContainShinyBag(bags.get(bagType), bags);
        });
    }

    private HashMap<String, String> parseInput() {
        HashMap<String, String> rules = new HashMap<>();
        Arrays.stream(this.inputs.split("\n"))
                .forEach(e -> {
                    String[] bags = e.split(" contain ");
                    rules.put(bags[0], bags[1]);
                });
        return rules;
    }

    public int nbOfContainingBag() {
        HashMap<String, String> bags = parseInput();
        String containingBags = bags.get("shiny gold bags");
        countBags(1, containingBags, bags);
        return this.count;
    }

    private void countBags(int initCount, String containingBags, HashMap<String, String> bags) {
        String[] containingBag = containingBags.substring(0, containingBags.length() - 1).split(", ");
        Arrays.stream(containingBag).forEach(bagName -> {
            if (bagName.contains("no other bags")) return;
            int bagQuantity = Integer.parseInt(String.valueOf(bagName.charAt(0)));
            this.count += (initCount * bagQuantity);
            String bagType = bagQuantity == 1 ? bagName.substring(2).concat("s") : bagName.substring(2);
            countBags(initCount * bagQuantity, bags.get(bagType), bags);
        });
    }
}
