public class Cruise {
    private final String id;
    private final int arrivalTime, loaderNum, serviceTime;

    Cruise(String id, int arrivalTime, int loaderNum, int serviceTime) {
        this.id = id;
        this.arrivalTime = arrivalTime; 
        this.loaderNum = loaderNum;
        this.serviceTime = serviceTime;
    }
    
    public int getArrivalTime() {
        int hours = this.arrivalTime / 100;
        int mins = this.arrivalTime % 100;

        return hours * 60 + mins;
    }

    public int getNumOfLoadersRequired() {
        return this.loaderNum;
    }

    public int getServiceCompletionTime() {
        return this.getArrivalTime() + this.serviceTime;
    }

    @Override
    public String toString() {
        return this.id + "@" + String.format("%04d", arrivalTime);
    }

}
