package me.sunny.demo.test

class SearchTest extends spock.lang.Specification {
    void setup() {
    }

    void cleanup() {
    }

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
}
