public class SmallCruise extends Cruise {
    private static final int LOADER_NUM = 1;
    private static final int SERVICE_TIME = 30;

    SmallCruise(String id, int arrivalTime) {
        super(id, arrivalTime, LOADER_NUM, SERVICE_TIME);
    }
}
