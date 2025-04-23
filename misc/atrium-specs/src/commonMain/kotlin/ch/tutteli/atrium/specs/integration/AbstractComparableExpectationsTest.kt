package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageToContain
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.specs.Fun1
import ch.tutteli.atrium.specs.forSubjectLessTest
import ch.tutteli.atrium.testfactories.TestFactory
import ch.tutteli.atrium.translations.DescriptionComparableExpectation.*

@Suppress("FunctionName")
abstract class AbstractComparableExpectationsTest(
    private val toBeLessThanSpec: Fun1<Int, Int>,
    private val toBeLessThanOrEqualToSpec: Fun1<Int, Int>,
    private val toBeEqualComparingToSpec: Fun1<Int, Int>,
    private val toBeGreaterThanOrEqualToSpec: Fun1<Int, Int>,
    private val toBeGreaterThanSpec: Fun1<Int, Int>,

    private val toBeLessThanOrEqualTo2Spec: Fun1<DiffEqualsCompareTo, DiffEqualsCompareTo>,
    private val toBeEqualComparingTo2Spec: Fun1<DiffEqualsCompareTo, DiffEqualsCompareTo>,
    private val toBeGreaterThanOrEqualTo2Spec: Fun1<DiffEqualsCompareTo, DiffEqualsCompareTo>,

    private val toBeLessThanOrEqualToDescr: String,
    private val toBeGreaterThanOrEqualToDescr: String,
) : ExpectationFunctionBaseTest() {

    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        toBeLessThanSpec.forSubjectLessTest(1),
        toBeLessThanOrEqualToSpec.forSubjectLessTest(1),
        toBeEqualComparingToSpec.forSubjectLessTest(1),
        toBeGreaterThanOrEqualToSpec.forSubjectLessTest(1),
        toBeGreaterThanSpec.forSubjectLessTest(1),
    )

    val toBeLessThanDescr = TO_BE_LESS_THAN.getDefault()
    val toBeGreaterThanDescr = TO_BE_GREATER_THAN.getDefault()
    val toBeEqualComparingToDescr = TO_BE_EQUAL_COMPARING_TO.getDefault()

    @TestFactory
    fun toBeLessThan__subject_is_10() = testFactory(toBeLessThanSpec) { toBeLessThanFun ->
        it("11 - does not throw") { expect(10).toBeLessThanFun(11) }
        it("10 - throws") {
            expect {
                expect(10).toBeLessThanFun(10)
            }.toThrow<AssertionError> { messageToContain("$toBeLessThanDescr: 10") }
        }
        it(" 9 - throws") {
            expect {
                expect(10).toBeLessThanFun(9)
            }.toThrow<AssertionError> { messageToContain("$toBeLessThanDescr: 9") }
        }
    }

    @TestFactory
    fun toBeLessThanOrEqualTo__subject_is_10() = testFactory(toBeLessThanOrEqualToSpec) { toBeLessThanOrEqualToFun ->
        it("11 - does not throw") { expect(10).toBeLessThanOrEqualToFun(11) }
        it("10 - does not throw") { expect(10).toBeLessThanOrEqualToFun(10) }
        it(" 9 - throws") {
            expect {
                expect(10).toBeLessThanOrEqualToFun(9)
            }.toThrow<AssertionError> { messageToContain("$toBeLessThanOrEqualToDescr: 9") }
        }
    }

    @TestFactory
    fun toBeEqualComparingToLessThanOrEqual__subject_is_10() =
        testFactory(toBeEqualComparingToSpec) { toBeEqualComparingToFun ->
            it("11 - throws") {
                expect {
                    expect(10).toBeEqualComparingToFun(11)
                }.toThrow<AssertionError> { messageToContain("$toBeEqualComparingToDescr: 11") }
            }
            it("10 - does not throw") { expect(10).toBeEqualComparingToFun(10) }
            it(" 9 - throws") {
                expect {
                    expect(10).toBeEqualComparingToFun(9)
                }.toThrow<AssertionError> { messageToContain("$toBeEqualComparingToDescr: 9") }
            }
        }


    @TestFactory
    fun toBeGreaterThanOrEqualTo__subject_is_10() =
        testFactory(toBeGreaterThanOrEqualToSpec) { toBeGreaterThanOrEqualToFun ->
            it("11 - throws") {
                expect {
                    expect(10).toBeGreaterThanOrEqualToFun(11)
                }.toThrow<AssertionError> { messageToContain("$toBeGreaterThanOrEqualToDescr: 11") }
            }
            it("10 - does not throw") {
                expect(10).toBeGreaterThanOrEqualToFun(10)
            }
            it(" 9 - throws") {
                expect(10).toBeGreaterThanOrEqualToFun(9)
            }
        }

    @TestFactory
    fun toBeGreaterThan__subject_is_10() =
        testFactory(toBeGreaterThanSpec) { toBeGreaterThanFun ->
            it("11 - throws") {
                expect {
                    expect(10).toBeGreaterThanFun(11)
                }.toThrow<AssertionError> { messageToContain("$toBeGreaterThanDescr: 11") }
            }
            it("10 - does not throw") {
                expect {
                    expect(10).toBeGreaterThanFun(10)
                }.toThrow<AssertionError> { messageToContain("$toBeGreaterThanDescr: 10") }
            }
            it(" 9 - throws") {
                expect(10).toBeGreaterThanFun(9)
            }
        }


    @TestFactory
    fun toBeLessThanOrEqualTo__subject_class_compareTo_not_same_as_equals() =
        testFactory(toBeLessThanOrEqualTo2Spec) { toBeLessThanOrEqualToFun ->
            it("expected is same instance but compareTo does not return 0 but 1 - throws") {
                val subject = DiffEqualsCompareTo("welcome")
                expect {
                    expect(subject).toBeLessThanOrEqualToFun(subject)
                }.toThrow<AssertionError> { messageToContain("$toBeLessThanOrEqualToDescr: DiffEqualsCompareTo(s=welcome)") }
            }
            it("expected equals but compareTo does not return 0 but 1- throws") {
                expect {
                    expect(DiffEqualsCompareTo("welcome")).toBeLessThanOrEqualToFun(DiffEqualsCompareTo("welcome"))
                }.toThrow<AssertionError> { messageToContain("$toBeLessThanOrEqualToDescr: DiffEqualsCompareTo(s=welcome)") }
            }
            it("expected does not equal but compareTo returns 0 - does not throw") {
                expect(DiffEqualsCompareTo("welcome")).toBeLessThanOrEqualToFun(DiffEqualsCompareTo("hello"))
            }
        }


    @TestFactory
    fun toBeEqualComparingToLessThanOrEqual__subject_class_compareTo_not_same_as_equals() =
        testFactory(toBeEqualComparingTo2Spec) { toBeEqualComparingToFun ->
            it("expected is same instance but compareTo does not return 0 but 1 - throws") {
                expect {
                    val subject = DiffEqualsCompareTo("welcome")
                    expect(subject).toBeEqualComparingToFun(subject)
                }.toThrow<AssertionError> { messageToContain("$toBeEqualComparingToDescr: DiffEqualsCompareTo(s=welcome)") }
            }
            it("expected equals but compareTo does not return 0 but 1 - throws") {
                expect {
                    expect(DiffEqualsCompareTo("welcome")).toBeEqualComparingToFun(DiffEqualsCompareTo("welcome"))
                }.toThrow<AssertionError> { messageToContain("$toBeEqualComparingToDescr: DiffEqualsCompareTo(s=welcome)") }
            }
            it("expected does not equal but compareTo returns 0 - does not throw") {
                expect(DiffEqualsCompareTo("welcome")).toBeEqualComparingToFun(DiffEqualsCompareTo("hello"))
            }
        }


    @TestFactory
    fun toBeGreaterThanOrEqualTo__subject_class_compareTo_not_same_as_equals() =
        testFactory(toBeGreaterThanOrEqualTo2Spec) { toBeGreaterThanOrEqualToFun ->
            it("expected is same instance but compareTo does not return 0 but -1 - throws AssertionError") {
                expect {
                    val subject = DiffEqualsCompareTo("allo")
                    expect(subject).toBeGreaterThanOrEqualToFun(subject)
                }.toThrow<AssertionError> { messageToContain("$toBeGreaterThanOrEqualToDescr: DiffEqualsCompareTo(s=allo)") }
            }

            it("expected equals but compareTo does not return 0 but -1 - throws AssertionError") {
                expect {
                    expect(DiffEqualsCompareTo("allo")).toBeGreaterThanOrEqualToFun(DiffEqualsCompareTo("allo"))
                }.toThrow<AssertionError> { messageToContain("$toBeGreaterThanOrEqualToDescr: DiffEqualsCompareTo(s=allo)") }
            }
            it("expected does not equal but compareTo returns 0 - does not throw") {
                expect(DiffEqualsCompareTo("welcome")).toBeGreaterThanOrEqualToFun(DiffEqualsCompareTo("hello"))
            }
        }
}

data class DiffEqualsCompareTo(val s: String) : Comparable<DiffEqualsCompareTo> {
    override fun compareTo(other: DiffEqualsCompareTo): Int = other.s.compareTo("hello")
}
