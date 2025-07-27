package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageToContain
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.specs.Fun1
import ch.tutteli.atrium.testfactories.TestFactory

abstract class AbstractRangeExpectationsTest (
    private val toBeInRangeIntSpec: Fun1<Int, IntRange>,
    private val toBeInRangeLongSpec: Fun1<Long, LongRange>,
    private val toBeInRangeCharSpec: Fun1<Char, CharRange>,
) : ExpectationFunctionBaseTest() {

    @TestFactory
    fun toBeInRangeInt() = testFactory(toBeInRangeIntSpec) { toBeInRangeFun ->
        val range = 1..10
        it("5 is in range $range - does not throw") {
            expect(5).toBeInRangeFun(range)
        }

        it("0 is not in range $range - throws") {
            expect {
                expect(0).toBeInRangeFun(range)
            }.toThrow<AssertionError> {
                messageToContain("to be in range")
            }
        }

        it("11 is not in range $range - throws") {
            expect {
                expect(11).toBeInRangeFun(range)
            }.toThrow<AssertionError> {
                messageToContain("to be in range")
            }
        }
    }

    @TestFactory
    fun toBeInRangeLong() = testFactory(toBeInRangeLongSpec) { toBeInRangeFun ->
        val range = 1L..10L
        it("5L is in range $range - does not throw") {
            expect(5L).toBeInRangeFun(range)
        }

        it("0L is not in range $range - throws") {
            expect {
                expect(0L).toBeInRangeFun(range)
            }.toThrow<AssertionError> {
                messageToContain("to be in range")
            }
        }

        it("11L is not in range $range - throws") {
            expect {
                expect(11L).toBeInRangeFun(range)
            }.toThrow<AssertionError> {
                messageToContain("to be in range")
            }
        }
    }

    @TestFactory
    fun toBeInRangeChar() = testFactory(toBeInRangeCharSpec) { toBeInRangeFun ->
        val range = 'a'..'z'
        it("'m' is in range $range - does not throw") {
            expect('m').toBeInRangeFun(range)
        }

        it("'A' is not in range $range - throws") {
            expect {
                expect('A').toBeInRangeFun(range)
            }.toThrow<AssertionError> {
                messageToContain("to be in range")
            }
        }

        it("'0' is not in range $range - throws") {
            expect {
                expect('0').toBeInRangeFun(range)
            }.toThrow<AssertionError> {
                messageToContain("to be in range")
            }
        }
    }
}
