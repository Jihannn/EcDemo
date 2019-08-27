package com.jihan.mini_core.delegates.web.event;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jihan
 * @date 2019/8/19
 */
public class EventManager {

    private final Map<String, Event> EVENTS = new HashMap<>();

    private EventManager() {
    }

    private static final class Holder {
        private static final EventManager INSTANCE = new EventManager();
    }

    public static EventManager getInstance() {
        return Holder.INSTANCE;
    }

    public EventManager addEvent(String name,Event event) {
        EVENTS.put(name, event);
        return this;
    }

    public Event createEvent(String action) {
        final Event event = EVENTS.get(action);
        if (event == null) {
            return new UnDefineEvent();
        }
        return event;
    }
}
