package com.vo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(classes = CollectionProvider.class)
class CollectionProvider_IntegrationTest extends Specification {
    @Autowired
    private CollectionProvider collectionProvider;
    List<String> list = new ArrayList<String>()

    def setup() {
        list.add('name')
        collectionProvider.setValues(list)
    }

    def "GetValues"() {
        List<String> values = collectionProvider.getListValues()

        expect:
//        collectionProvider.getValues() == ['A', 'B', 'C', 'name'] // err
        values.get(3) == 'name'
        collectionProvider.getListValues() == ['A', 'B', 'C', 'name']
    }

    def "SetValues"() {
        expect:
        collectionProvider.getListValues().contains('name')
    }
}