import java.util.ArrayList;
import java.util.PriorityQueue;

class EventHandler {
  PriorityQueue<Event> eventQueue;
  ArrayList<Server> serverList;
  Statistics stats;

  EventHandler(int serverNum) {
    eventQueue = new PriorityQueue<>();
    serverList = new ArrayList<>();
    createServers(serverNum);
    stats = new Statistics();
  }

  /**
   * Create servers.
   * 
   * @param n number of servers
   */
  void createServers(int n) {
    for (int i = 0; i < n; i++) {
      serverList.add(new Server(i + 1));
    }
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
    }
  }

  public void processEvents() {
    while (eventQueue.peek() != null) {
      Event event = eventQueue.poll();

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
    }
  }

  void processArriveEvent(Event arriveEvent) {
    boolean willBeServed = false;
    System.out.println(arriveEvent);

    double arriveTime = arriveEvent.getTime();

    for (Server server : serverList) {
      if (arriveTime >= server.getNextServeTime()) {
        Event servedEvent = Event.serve(arriveEvent, server);
        stats.addServedCustomersByOne();

        eventQueue.add(servedEvent);

        willBeServed = true;
        break;
      }
    }

    if (!willBeServed) {
      for (Server server : serverList) {
        if (!server.hasWaitingCustomer()) {
          stats.addTotalWaitingTime(server.getNextServeTime() - arriveTime);

          Event servedEvent = Event.serve(arriveEvent, server.getNextServeTime(), server);
          stats.addServedCustomersByOne();
          eventQueue.add(servedEvent);

          Event waitEvent = Event.wait(arriveEvent, server);
          eventQueue.add(waitEvent);

          willBeServed = true;
          break;
        }
      }
    }

    if (!willBeServed) {
      Event leftEvent = Event.leave(arriveEvent);
      stats.addLeftCustomersByOne();
      eventQueue.add(leftEvent);
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

    Server server = doneEvent.getServer();

    if (server.hasWaitingCustomer()) {
      server.removeWaitingCustomer();
    }
  }

  void processWaitEvent(Event waitEvent) {
    System.out.println(waitEvent);
  }

  public void displayStats() {
    stats.displayStats();
  }
}