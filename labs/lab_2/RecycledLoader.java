public class RecycledLoader extends Loader {
    RecycledLoader(int id, Cruise cruise) {
        super(id, cruise);
    }

    @Override
    public Loader serve(Cruise cruise) {
        if (this.canServe(cruise)) {
            RecycledLoader loader = new RecycledLoader(this.id, cruise);

            return loader;
        } else {
            return null;
        }
    }

    @Override
    public boolean canServe(Cruise cruise){
        return this.cruise == null || (!this.cruise.equals(cruise) && cruise.getArrivalTime() >= (this.cruise.getServiceCompletionTime() + 60));
    }

    @Override
    public String toString() {
        if (cruise == null) {
            return "Loader " + this.id;
        } else {
            return "Loader " + this.id + " (recycled) serving " + cruise.toString();
        }
    }
}