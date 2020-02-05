public class Loader {
    protected final int id;
    protected final Cruise cruise;

    Loader(int id) {
        this.id = id;
        this.cruise = null;
    }

    Loader(int id, Cruise cruise) {
        this.id = id;
        this.cruise = cruise;
    }

    public Loader serve(Cruise cruise) {
        if (this.canServe(cruise)) {
            Loader loader = new Loader(this.id, cruise);

            return loader;
        } else {
            return null;
        }
    }

    public boolean canServe(Cruise cruise){
        return this.cruise == null || (!this.cruise.equals(cruise) && cruise.getArrivalTime() >= this.cruise.getServiceCompletionTime());
    }

    @Override
    public String toString() {
        if (cruise == null) {
            return "Loader " + this.id;
        } else {
            return "Loader " + this.id + " serving " + cruise.toString();
        }
    }
}
