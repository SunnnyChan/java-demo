package me.sunny.demo.basics

import spock.lang.Specification

class TypeCastTest extends Specification {
    def setup() {

    }          // run before every feature method
    def cleanup() {}        // run after every feature method
    def setupSpec() {

    }      // run before the first feature method
    def cleanupSpec() {}    // run after the last feature method

    def "wrapperBooleanToString"() {
        given:
        def TypeCast typeCast = new TypeCast();

        expect:
        typeCast.wrapperBooleanToString(b as Boolean) == result

        where:
        // true
        b      | result
        true   | "true"
        false  | "false"
    }

    def "booleanToString"() {
        given:
        def TypeCast typeCast = new TypeCast();

        expect:
        typeCast.booleanToString(b as boolean) == result

        where:
        // true
        b      | result
        true   | "true"
        false  | "false"
    }
}
