package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageToContain
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionComparableExpectation
import ch.tutteli.atrium.translations.DescriptionComparableExpectation.*
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

abstract class ComparableExpectationsSpec(
    toBeLessThan: Fun1<Int, Int>,
    toBeLessThanOrEqualTo: Fun1<Int, Int>,
    toBeEqualComparingTo: Fun1<Int, Int>,
    toBeGreaterThanOrEqualTo: Fun1<Int, Int>,
    toBeGreaterThan: Fun1<Int, Int>,

    toBeLessThan2: Fun1<DiffEqualsCompareTo, DiffEqualsCompareTo>,
    toBeEqualComparingTo2: Fun1<DiffEqualsCompareTo, DiffEqualsCompareTo>,
    toBeGreaterThanOrEqualTo2: Fun1<DiffEqualsCompareTo, DiffEqualsCompareTo>,

    toBeLessThanOrEqualToDescr: String = TO_BE_LESS_THAN_OR_EQUAL_TO.getDefault(),
    toBeGreaterThanOrEqualToDescr: String = TO_BE_GREATER_THAN_OR_EQUAL_TO.getDefault(),
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<Int>(
        describePrefix,
        toBeLessThan.forSubjectLess(1),
        toBeLessThanOrEqualTo.forSubjectLess(1),
        toBeEqualComparingTo.forSubjectLess(1),
        toBeGreaterThanOrEqualTo.forSubjectLess(1),
        toBeGreaterThan.forSubjectLess(1)
    ) {})

    val toBeLessThanDescr = TO_BE_LESS_THAN.getDefault()
    val toBeGreaterThanDescr = TO_BE_GREATER_THAN.getDefault()
    val toBeEqualComparingToDescr = TO_BE_EQUAL_COMPARING_TO.getDefault()


    describe("$describePrefix context subject is 10") {
        val fluent = expect(10)

        context("${toBeLessThan.name} ...") {
            val toBeLessThanFun = toBeLessThan.lambda

            it("... 11 does not throw") {
                fluent.toBeLessThanFun(11)
            }
            it("... 10 throws an AssertionError containing ${DescriptionComparableExpectation::class.simpleName}.$TO_BE_LESS_THAN and `: 10`") {
                expect {
                    fluent.toBeLessThanFun(10)
                }.toThrow<AssertionError> { messageToContain("$toBeLessThanDescr: 10") }
            }
            it("... 9 throws an AssertionError containing ${DescriptionComparableExpectation::class.simpleName}.$TO_BE_LESS_THAN and `: 10`") {
                expect {
                    fluent.toBeLessThanFun(9)
                }.toThrow<AssertionError> { messageToContain("$toBeLessThanDescr: 9") }
            }
        }

        describe("${toBeLessThanOrEqualTo.name} ...") {
            val toBeLessThanOrEqualToFun = toBeLessThanOrEqualTo.lambda

            it("... 11 does not throw") {
                fluent.toBeLessThanOrEqualToFun(11)
            }
            it("... 10 does not throw") {
                fluent.toBeLessThanOrEqualToFun(10)
            }
            it("... 9 throws an AssertionError containing ${DescriptionComparableExpectation::class.simpleName}.$TO_BE_LESS_THAN_OR_EQUAL_TO and `: 10`") {
                expect {
                    fluent.toBeLessThanOrEqualToFun(9)
                }.toThrow<AssertionError> { messageToContain("$toBeLessThanOrEqualToDescr: 9") }
            }
        }

        describe("${toBeEqualComparingTo.name} ...") {
            val toBeEqualComparingToFun = toBeEqualComparingTo.lambda

            it("... 11 throws an AssertionError containing ${DescriptionComparableExpectation::class.simpleName}.$TO_BE_EQUAL_COMPARING_TO and `: 11`") {
                expect {
                    fluent.toBeEqualComparingToFun(11)
                }.toThrow<AssertionError> { messageToContain("$toBeEqualComparingToDescr: 11") }
            }
            it("... 10 does not throw") {
                fluent.toBeEqualComparingToFun(10)
            }
            it("... 9 throws an AssertionError containing ${DescriptionComparableExpectation::class.simpleName}.$TO_BE_EQUAL_COMPARING_TO and `: 9`") {
                expect {
                    fluent.toBeEqualComparingToFun(9)
                }.toThrow<AssertionError> { messageToContain("$toBeEqualComparingToDescr: 9") }
            }
        }

        describe("${toBeGreaterThanOrEqualTo.name} ...") {
            val toBeGreaterThanOrEqualFun = toBeGreaterThanOrEqualTo.lambda

            it("... 11 throws an AssertionError containing ${DescriptionComparableExpectation::class.simpleName}.$TO_BE_GREATER_THAN_OR_EQUAL_TO and `: 11`") {
                expect {
                    fluent.toBeGreaterThanOrEqualFun(11)
                }.toThrow<AssertionError> { messageToContain("$toBeGreaterThanOrEqualToDescr: 11") }
            }
            it("... 10 does not throw") {
                fluent.toBeGreaterThanOrEqualFun(10)
            }
            it("... 9 does not throw") {
                fluent.toBeGreaterThanOrEqualFun(9)
            }
        }

        describe("${toBeGreaterThan.name} ...") {
            val toBeGreaterThanFun = toBeGreaterThan.lambda

            it("... 11 throws an AssertionError containing ${DescriptionComparableExpectation::class.simpleName}.$TO_BE_GREATER_THAN and `: 11`") {
                expect {
                    fluent.toBeGreaterThanFun(11)
                }.toThrow<AssertionError> { messageToContain("$toBeGreaterThanDescr: 11") }
            }
            it("... 10 throws an AssertionError containing ${DescriptionComparableExpectation::class.simpleName}.$TO_BE_GREATER_THAN and `: 10`") {
                expect {
                    fluent.toBeGreaterThanFun(10)
                }.toThrow<AssertionError> { messageToContain("$toBeGreaterThanDescr: 10") }
            }
            it("... 9 does not throw") {
                fluent.toBeGreaterThanFun(9)
            }
        }
    }
    describe("$describePrefix context subject is a class where equals is different from compareTo") {
        context("${toBeLessThan2.name} ...") {
            val toBeLessThanFun = toBeLessThan2.lambda

            it("expected is same instance but compareTo does not return 0 but 1 - throws AssertionError") {
                expect {
                    val subject = DiffEqualsCompareTo("welcome")
                    expect(subject).toBeLessThanFun(subject)
                }.toThrow<AssertionError> { messageToContain("$toBeLessThanOrEqualToDescr: DiffEqualsCompareTo(s=welcome)") }
            }

            it("expected equals but compareTo does not return 0 but 1- throws AssertionError") {
                expect {
                    expect(DiffEqualsCompareTo("welcome")).toBeLessThanFun(DiffEqualsCompareTo("welcome"))
                }.toThrow<AssertionError> { messageToContain("$toBeLessThanOrEqualToDescr: DiffEqualsCompareTo(s=welcome)") }
            }
            it("expected does not equal but compareTo returns 0 - does not throw") {
                expect(DiffEqualsCompareTo("welcome")).toBeLessThanFun(DiffEqualsCompareTo("hello"))
            }
        }

        describe("${toBeEqualComparingTo2.name} ...") {
            val toBeEqualComparingToFun = toBeEqualComparingTo2.lambda
            it("expected is same instance but compareTo does not return 0 but 1 - throws AssertionError") {
                expect {
                    val subject = DiffEqualsCompareTo("welcome")
                    expect(subject).toBeEqualComparingToFun(subject)
                }.toThrow<AssertionError> { messageToContain("$toBeEqualComparingToDescr: DiffEqualsCompareTo(s=welcome)") }
            }

            it("expected equals but compareTo does not return 0 but 1 - throws AssertionError") {
                expect {
                    expect(DiffEqualsCompareTo("welcome")).toBeEqualComparingToFun(DiffEqualsCompareTo("welcome"))
                }.toThrow<AssertionError> { messageToContain("$toBeEqualComparingToDescr: DiffEqualsCompareTo(s=welcome)") }
            }
            it("expected does not equal but compareTo returns 0 - does not throw") {
                expect(DiffEqualsCompareTo("welcome")).toBeEqualComparingToFun(DiffEqualsCompareTo("hello"))
            }
        }

        describe("${toBeGreaterThanOrEqualTo2.name} ...") {
            val toBeGreaterThanOrEqualFun = toBeGreaterThanOrEqualTo2.lambda
            it("expected is same instance but compareTo does not return 0 but -1 - throws AssertionError") {
                expect {
                    val subject = DiffEqualsCompareTo("allo")
                    expect(subject).toBeGreaterThanOrEqualFun(subject)
                }.toThrow<AssertionError> { messageToContain("$toBeGreaterThanOrEqualToDescr: DiffEqualsCompareTo(s=allo)") }
            }

            it("expected equals but compareTo does not return 0 but -1 - throws AssertionError") {
                expect {
                    expect(DiffEqualsCompareTo("allo")).toBeGreaterThanOrEqualFun(DiffEqualsCompareTo("allo"))
                }.toThrow<AssertionError> { messageToContain("$toBeGreaterThanOrEqualToDescr: DiffEqualsCompareTo(s=allo)") }
            }
            it("expected does not equal but compareTo returns 0 - does not throw") {
                expect(DiffEqualsCompareTo("welcome")).toBeGreaterThanOrEqualFun(DiffEqualsCompareTo("hello"))
            }
        }
    }
})

data class DiffEqualsCompareTo(val s: String) : Comparable<DiffEqualsCompareTo> {
    override fun compareTo(other: DiffEqualsCompareTo): Int = other.s.compareTo("hello")
}

