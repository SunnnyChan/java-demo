package me.sunny.demo.test

import spock.lang.Specification
import spock.lang.Unroll

import java.util.function.Consumer


class SearchTest extends Specification {

    def setup() {}          // run before every feature method
    def cleanup() {}        // run after every feature method
    def setupSpec() {}     // run before the first feature method
    def cleanupSpec() {}   // run after the last feature method

    //expect-where
    def "binarySearch"() {
        expect:
        Search.binarySearch(arr as int[], key) == result

        where:
        // true
        arr       | key | result
        []        | 1   | -1
        [1]       | 1   | 0
        [1]       | 2   | -1
        [3]       | 2   | -1
        [1, 2, 9] | 2   | 1
        [1, 2, 9] | 9   | 2
        [1, 2, 9] | 3   | -1

        // false
        []        | 1   | -2

        //null      | 0   | -1
    }

    // Unroll
    @Unroll
    def "testSearch(#key in #arr index=#result)"() {
        expect:
        Search.binarySearch(arr as int[], key) == result

        where:
        arr       | key | result
        []        | 1   | -1
        [1, 2, 9] | 9   | 2
        [1, 2, 9] | 3   | -1
    }

    // when-then-thrown
    def "testTryDoWithThrown"() {
        when:
        CatchUtil.tryDo(1, { throw new IllegalArgumentException(it.toString())} as Consumer)

        then:
        def ex = thrown(Exception)
        ex.class.name == "java.lang.RuntimeException"
        ex.cause.class.name == "java.lang.IllegalArgumentException"
    }
}
