public class BigCruise extends Cruise {
    private final int length, passengerNum;

    BigCruise(String id, int arrivalTime, int length, int passengerNum) {
        super(id, arrivalTime, (int) Math.ceil((double) length / 40), (int) Math.ceil((double) passengerNum / 50));
        this.length = length;
        this.passengerNum = passengerNum;
    }
}