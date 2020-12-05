package advent_of_code_2020.day_5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SeatFinder {

    private final String seatId;

    public SeatFinder(String seatId) {
        this.seatId = seatId;
    }

    public int getHighestSeatId() {
        return Collections.max(getOthersSeatIds());
    }

    private int calculateSeatNumber(String seatId, double highRange, String lowerSymbol) {
        double lowRange = 0;
        for (String binaryCode : seatId.split("")) {
            if (binaryCode.equals(lowerSymbol)) {
                highRange = Math.floor((lowRange + highRange) / 2);
            } else {
                lowRange = Math.ceil((lowRange + highRange) / 2);
            }
        }
        return (int) lowRange;
    }

    private List<String> parseInput() {
        return Arrays.asList(seatId.split("\n"));
    }

    public int findMySeat() {
        List<Integer> occupiedSeats = getOthersSeatIds();
        int highestSeatId = getHighestSeatId();
        for (int seatNumber = 13; seatNumber <= highestSeatId; seatNumber++) {
            if (!occupiedSeats.contains(seatNumber)) {
                return seatNumber;
            }
        }
        return 0;
    }

    private List<Integer> getOthersSeatIds() {
        List<String> seatIds = parseInput();
        List<Integer> occupiedSeats = new ArrayList<>();
        for (String seat : seatIds) {
            int row = calculateSeatNumber(seat.substring(0, 7), 127, "F");
            int col = calculateSeatNumber(seat.substring(7), 7, "L");
            int seatNumber = row * 8 + col;
            occupiedSeats.add(seatNumber);
        }
        return occupiedSeats;
    }
}
