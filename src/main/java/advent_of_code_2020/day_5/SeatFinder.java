package advent_of_code_2020.day_5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SeatFinder {

    private final String seatId;

    public SeatFinder(String seatId) {
        this.seatId = seatId;
    }

    public int getHighestSeatId() {
        List<String> seatIds = parseInput();
        int highestSeatId = 0;
        for (String seat : seatIds) {
            int row = calculateRow(seat.substring(0, 7));
            int col = calculateCol(seat.substring(7));
            int seatNumber = row * 8 + col;
            if (seatNumber > highestSeatId) {
                highestSeatId = seatNumber;
            }
        }
        return highestSeatId;
    }

    private int calculateCol(String seat) {
        double lowRange = 0;
        double upperRange = 7;
        for (String binaryCode : seat.split("")) {
            if (binaryCode.equals("L")) {
                upperRange = Math.floor((lowRange + upperRange) / 2);
            } else {
                lowRange = Math.ceil((lowRange + upperRange) / 2);
            }
        }
        return (int) lowRange;
    }

    private int calculateRow(String seat) {
        double lowRange = 0;
        double upperRange = 127;
        for (String binaryCode : seat.split("")) {
            if (binaryCode.equals("F")) {
                upperRange = Math.floor((lowRange + upperRange) / 2);
            } else {
                lowRange = Math.ceil((lowRange + upperRange) / 2);
            }
        }
        return (int) lowRange;
    }

    private List<String> parseInput() {
        return Arrays.asList(seatId.split("\n"));
    }

    public int findMySeat() {
        List<String> seatIds = parseInput();
        List<Integer> existed = new ArrayList<>();
        for (String seat : seatIds) {
            int row = calculateRow(seat.substring(0, 7));
            int col = calculateCol(seat.substring(7));
            int seatNumber = row * 8 + col;
            existed.add(seatNumber);
        }
        return getMissingNumber(getHighestSeatId(), existed);
    }

    private int getMissingNumber(int highestSeatId, List<Integer> existed) {
        List<Integer> seats = new ArrayList<>();
        for (int i = 100; i <= highestSeatId; i++) {
            if (!existed.contains(i)) {
                return i;
            }
        }
        return 0;
    }
}
