package com.fromDocs.interaction;

class Publisher {
    List<Subscriber> subscribers = []

    void publish(String message) {
        for (subscriber in subscribers) {
            try {
                subscriber.receive(message)
            } catch (ignored) {
            }
        }
    }
}