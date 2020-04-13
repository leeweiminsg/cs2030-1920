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
    System.out.println("# Adding arrivals");

    for (double time : timesArray) {
      Customer customer = new Customer();
      Event event = new Event(customer, time);
      System.out.println(event);
      eventQueue.add(event);
      stats.addCustomersByOne();
    }
  }

  public void processEvents() {
    while (eventQueue.peek() != null) {
      Event event = eventQueue.poll();

      System.out.println();
      System.out.println("# Get next event: " + event.toString());

      if (event.isArriveEvent()) {
        processArriveEvent(event);
      } else if (event.isServedEvent()) {
        processServedEvent(event);
        // System.out.println(event);
      } else if (event.isLeaveEvent()) {
        // System.out.println(event);
      }

      ArrayList<Event> eventList = new ArrayList<>();

      while (!eventQueue.isEmpty()) {
        Event e = eventQueue.poll();
        System.out.println(e);
        eventList.add(e);
      }

      eventQueue.addAll(eventList);
    }
  }

  void processArriveEvent(Event arriveEvent) {
    // System.out.println(arriveEvent);

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

  void processServedEvent(Event servedEvent) {
    // System.out.println(servedEvent);
    
    Event doneEvent = Event.done(servedEvent);
    eventQueue.add(doneEvent);
  }

  public void displayStats() {
    stats.displayStats();
  }
}