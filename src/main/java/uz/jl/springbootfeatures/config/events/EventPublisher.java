package uz.jl.springbootfeatures.config.events;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    EventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public <T> void publishCustomEvent(final GenericEvent<T> genericEvent) {
        System.out.println("*************Publishing custom event**********");
        if (genericEvent.success) {
            applicationEventPublisher.publishEvent(genericEvent.getWhat());
        }
    }

}
