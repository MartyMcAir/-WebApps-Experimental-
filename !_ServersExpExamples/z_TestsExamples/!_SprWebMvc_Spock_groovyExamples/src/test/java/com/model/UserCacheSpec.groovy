package com.model

import spock.lang.Specification

/**
 * @author Ivan Khalopik
 */
// 		○ https://habr.com/ru/post/137561/ - Тестирование в Java. Spock Framework
//https://github.com/sody/samples/tree/master/testing/testing-spock
class UserCacheSpec extends Specification {
    def users = [
            new User(name: "test1", description: "Test User"),
            new User(name: "test2", description: "Test User"),
            new User(name: "test3", description: "Test User")
    ]

    def "dao should not be used until first user search"() {
        setup:
        UserDAO dao = Mock()

        when:
        def cache = new UserCacheImpl(dao)

        then:
        0 * _._
    }

    def "dao should be used only once for all user searches until invalidated"() {
        setup:
        def dao = Mock(UserDAO)
        def cache = new UserCacheImpl(dao)

        when:
        cache.getUser("test1")
        cache.getUser("test2")
        cache.getUser("test3")
        cache.getUser("test4")

        then:
        1 * dao.findAll() >> users
    }

    def "dao should be used for user search after invalidated"() {
        setup:
        def dao = Mock(UserDAO)
        def cache = new UserCacheImpl(dao)

        when:
        cache.getUser("test1")
        cache.invalidate()
        cache.getUser("test2")
        cache.getUser("test3")
        cache.invalidate()
        cache.getUser("test4")

        then:
        3 * _./find.*/() >> users
    }

    def "cache should search users by name"() {
        setup:
        def dao = Mock(UserDAO)
        dao.findAll() >> users
        def cache = new UserCacheImpl(dao)

        expect:
        cache.getUser("test1")
        cache.getUser("test2")
        cache.getUser("test3")
    }

    def "cache should not find missed users"() {
        setup:
        def dao = Mock(UserDAO)
        dao.findAll() >> users
        def cache = new UserCacheImpl(dao)

        expect:
        !cache.getUser("test4")
        !cache.getUser("test")
    }
}
