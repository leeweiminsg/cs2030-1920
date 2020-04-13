import java.util.ArrayList;
import java.util.PriorityQueue;

class EventHandler {
  PriorityQueue<Event> eventQueue;
  static Statistics stats;

  EventHandler() {
    eventQueue = new PriorityQueue<>();
    stats = new Statistics();
  }

  /**
   * Create event.
   * 
   * @param time time
   */
  public void createArrivalEvents(ArrayList<Double> timesArray) {
    for (double time: timesArray) {
      Customer customer = new Customer();
      Event event = new Event(customer, time);
      eventQueue.add(event);
      stats.addCustomersByOne();
    }
  }

  public void processEvents() {
    while (eventQueue.peek() != null) {
      Event event = eventQueue.poll();

      System.out.println(event);
    }
  }

  public void displayStats() {
    stats.displayStats();
  }
}