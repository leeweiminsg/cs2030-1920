import java.util.ArrayList;
import java.util.PriorityQueue;

class EventHandler {
  PriorityQueue<Event> eventQueue;
  static Statistics stats;

  double nextServeAt;
  boolean hasWaitingCustomer;

  EventHandler() {
    eventQueue = new PriorityQueue<>();
    stats = new Statistics();
    nextServeAt = 0;
    hasWaitingCustomer = false;
  }

  /**
   * Create event.
   * 
   * @param time time
   */
  public void createArrivalEvents(ArrayList<Double> timesArray) {
    // System.out.println("# Adding arrivals");

    for (double time : timesArray) {
      Customer customer = new Customer();
      Event event = new Event(customer, time);
      // System.out.println(event);
      eventQueue.add(event);
    }
  }

  public void processEvents() {
    while (eventQueue.peek() != null) {
      Event event = eventQueue.poll();

      // System.out.println();
      // System.out.println("# Get next event: " + event.toString());

      if (event.isArriveEvent()) {
        processArriveEvent(event);
      } else if (event.isServedEvent()) {
        processServedEvent(event);
      } else if (event.isWaitEvent()) {
        processWaitEvent(event);
      } else if (event.isLeaveEvent()) {
        processLeftEvent(event);
      } else if (event.isDoneEvent()) {
        processDoneEvent(event);
      }

      // ArrayList<Event> eventList = new ArrayList<>();

      // while (!eventQueue.isEmpty()) {
      //   Event e = eventQueue.poll();
      //   System.out.println(e);
      //   eventList.add(e);
      // }

      // eventQueue.addAll(eventList);
    }
  }

  void processArriveEvent(Event arriveEvent) {
    System.out.println(arriveEvent);

    double arriveTime = arriveEvent.getTime();

    if (arriveTime >= nextServeAt) {
      Event servedEvent = Event.serve(arriveEvent);
      stats.addServedCustomersByOne();
      nextServeAt = arriveTime + 1;
      eventQueue.add(servedEvent);
    } else if (arriveTime < nextServeAt) {
      if (hasWaitingCustomer) {
        Event leftEvent = Event.leave(arriveEvent);
        stats.addLeftCustomersByOne();
        eventQueue.add(leftEvent);
      } else {
        Event waitEvent = Event.wait(arriveEvent);
        eventQueue.add(waitEvent);

        stats.addTotalWaitingTime(nextServeAt - arriveTime);
        Event servedEvent = Event.serve(arriveEvent, nextServeAt);
        stats.addServedCustomersByOne();
        eventQueue.add(servedEvent);

        nextServeAt += 1;
        hasWaitingCustomer = true;
      }
    }
  }

  void processServedEvent(Event servedEvent) {
    System.out.println(servedEvent);

    Event doneEvent = Event.done(servedEvent);
    eventQueue.add(doneEvent);
  }
  
  void processLeftEvent(Event leftEvent) {
    System.out.println(leftEvent);
  }

  void processDoneEvent(Event doneEvent) {
    System.out.println(doneEvent);

    if (hasWaitingCustomer) {
      hasWaitingCustomer = false;
    }
  }

  void processWaitEvent(Event waitEvent) {
    System.out.println(waitEvent);
  }

  public void displayStats() {
    stats.displayStats();
  }
}