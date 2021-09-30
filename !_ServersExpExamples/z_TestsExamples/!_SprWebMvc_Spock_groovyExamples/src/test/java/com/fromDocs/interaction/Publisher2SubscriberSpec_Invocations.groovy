package com.fromDocs.interaction

import spock.lang.Specification

class Publisher2SubscriberSpec_Invocations extends Specification {
    def pub = new Publisher()
    def sub1 = Mock(Subscriber)
    def sub2 = Mock(Subscriber)

    def setup() {
        pub.subscribers << sub1 << sub2
    }

    def "delivers messages to all active subscribers"() {
        sub1.active >> true
        sub2.active >> false

        when:
        pub.publish("msg")

        then: "* - that is invocation times"
        1 * sub1.receive("msg")
        0 * sub2.receive(_)
    }
}