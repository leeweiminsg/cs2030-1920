import java.util.ArrayList;
import java.util.PriorityQueue;

class EventHandler {
  PriorityQueue<Event> eventQueue;
  static Statistics stats;

  double nextServeAt;

  EventHandler() {
    eventQueue = new PriorityQueue<>();
    stats = new Statistics();
    nextServeAt = 0;
  }

  /**
   * Create event.
   * 
   * @param time time
   */
  public void createArrivalEvents(ArrayList<Double> timesArray) {
    for (double time : timesArray) {
      Customer customer = new Customer();
      Event event = new Event(customer, time);
      eventQueue.add(event);
      stats.addCustomersByOne();
    }
  }

  public void processEvents() {
    while (eventQueue.peek() != null) {
      Event event = eventQueue.poll();

      if (event.isArriveEvent()) {
        processArriveEvent(event);
      } else if (event.isServedEvent()) {
        System.out.println(event);
      } else if (event.isLeaveEvent()) {
        System.out.println(event);
      }
    }
  }

  void processArriveEvent(Event arriveEvent) {
    System.out.println(arriveEvent);

    double arriveTime = arriveEvent.getTime();

    if (arriveTime >= nextServeAt) {
      Event servedEvent = Event.serve(arriveEvent);
      nextServeAt = arriveTime + 1;
      eventQueue.add(servedEvent);
    } else if (arriveTime < nextServeAt) {
      Event leftEvent = Event.leave(arriveEvent);
      eventQueue.add(leftEvent);
    }
  }

  public void displayStats() {
    stats.displayStats();
  }
}