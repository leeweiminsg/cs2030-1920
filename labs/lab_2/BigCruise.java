public class BigCruise extends Cruise {
    private final int length, passengerNum;
    private static final int LENGTH_PER_LOADER = 40;
    private static final int PASSENGERS_PER_MIN = 50;

    BigCruise(String id, int arrivalTime, int length, int passengerNum) {
        super(id, arrivalTime, (int) Math.ceil((double) length / LENGTH_PER_LOADER),
                (int) Math.ceil((double) passengerNum / PASSENGERS_PER_MIN));
        this.length = length;
        this.passengerNum = passengerNum;
    }
}