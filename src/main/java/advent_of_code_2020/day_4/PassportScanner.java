package advent_of_code_2020.day_4;

import java.util.*;

public class PassportScanner {
    private final int MIN = 0;
    private final int MAX = 1;
    private final int VALID_PID_LENGTH = 9;
    private final String inputs;
    private final String[] MANDATORY_FIELDS = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};
    private final Integer[] BIRTH_YEAR_RANGE = {1920, 2002};
    private final Integer[] ISSUE_YEAR_RANGE = {2010, 2020};
    private final Integer[] EXPIRY_YEAR_RANGE = {2020, 2030};
    private final Integer[] HEIGHT_RANGE_IN_CM = {150, 193};
    private final Integer[] HEIGHT_RANGE_IN_INCH = {59, 76};
    private final Integer[] HCL_RANGE_IN_NUM = {48, 57};
    private final Integer[] HCL_RANGE_IN_CHAR = {97, 102};
    private final List<String> EYE_COLORS = Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth");

    public PassportScanner(String inputs) {
        this.inputs = inputs;
    }

    public int getValidPasswordCount() {
        List<HashMap<String, String>> passports = parseInput();
        return (int) passports.stream()
                .filter(this::isMandatoryFieldsPresent)
                .filter(this::isValidByr)
                .filter(this::isValidEyr)
                .filter(this::isValidIyr)
                .filter(this::isValidHclValue)
                .filter(this::isValidPidValue)
                .filter(this::isValidEclValue)
                .filter(this::isValidHeightValue)
                .count();
    }

    private boolean isValidHeightValue(HashMap<String, String> passport) {
        String value = passport.get("hgt");
        int height = Integer.parseInt(value.substring(0, value.length() - 2));
        return isInRange(height, HEIGHT_RANGE_IN_CM) || isInRange(height, HEIGHT_RANGE_IN_INCH);
    }

    private boolean isValidPidValue(HashMap<String, String> passport) {
        return passport.get("pid").length() == VALID_PID_LENGTH;
    }

    private boolean isValidEclValue(HashMap<String, String> passport) {
        return EYE_COLORS.contains(passport.get("ecl"));
    }

    private boolean isValidHclValue(HashMap<String, String> passport) {
        String value = passport.get("hcl");
        if (!value.startsWith("#")) {
            return false;
        }
        for (int i = 1; i < value.length(); i++) {
            int asciiValue = value.codePointAt(i);
            if (!isInRange(asciiValue, HCL_RANGE_IN_CHAR) && !isInRange(asciiValue, HCL_RANGE_IN_NUM)) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidEyr(HashMap<String, String> passport) {
        int byrValue = Integer.parseInt(passport.get("eyr"));
        return isInRange(byrValue, EXPIRY_YEAR_RANGE);
    }

    private boolean isValidIyr(HashMap<String, String> passport) {
        int byrValue = Integer.parseInt(passport.get("iyr"));
        return isInRange(byrValue, ISSUE_YEAR_RANGE);
    }

    private boolean isValidByr(HashMap<String, String> passport) {
        int byrValue = Integer.parseInt(passport.get("byr"));
        return isInRange(byrValue, BIRTH_YEAR_RANGE);
    }

    public boolean isMandatoryFieldsPresent(HashMap<String, String> passport) {
        return Arrays.stream(MANDATORY_FIELDS)
                .allMatch(passport::containsKey);
    }

    private boolean isInRange(int value, Integer[] range) {
        return value >= range[MIN] && value <= range[MAX];
    }

    private List<HashMap<String, String>> parseInput() {
        List<HashMap<String, String>> passports = new ArrayList<>();
        HashMap<String, String> passport = new HashMap<>();
        for (String input : this.inputs.split("\n")) {
            if (input.equals("")) {
                passports.add(passport);
                passport = new HashMap<>();
            } else {
                HashMap<String, String> finalPassport = passport;
                Arrays.stream(input.split(" "))
                        .forEach(detail -> {
                            String[] details = detail.split(":");
                            finalPassport.put(details[0], details[1]);
                        });
            }
        }
        return passports;
    }

}
