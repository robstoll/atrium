package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.message
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.reporting.reportables.Description
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionComparableProof
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionComparableProof.*
import ch.tutteli.atrium.specs.*
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

    toBeLessThanOrEqualToDescr: Description = TO_BE_LESS_THAN_OR_EQUAL_TO,
    toBeGreaterThanOrEqualToDescr: Description = TO_BE_GREATER_THAN_OR_EQUAL_TO,
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

    describe("$describePrefix context subject is 10") {

        context("${toBeLessThan.name} ...") {
            val toBeLessThanFun = toBeLessThan.lambda

            it("... 11 does not throw") {
                expect(10).toBeLessThanFun(11)
            }
            it("... 10 throws an AssertionError containing ${DescriptionComparableProof::class.simpleName}.$TO_BE_LESS_THAN and `: 10`") {
                expect {
                    expect(10).toBeLessThanFun(10)
                }.toThrow<AssertionError> {
                    message {
                        toContainSubject("10")
                        toContainDescr(TO_BE_LESS_THAN, 10)
                    }
                }
            }
            it("... 9 throws an AssertionError containing ${DescriptionComparableProof::class.simpleName}.$TO_BE_LESS_THAN and `: 10`") {
                expect {
                    expect(10).toBeLessThanFun(9)
                }.toThrow<AssertionError> { message { toContainDescr(TO_BE_LESS_THAN, 9) } }
            }
        }

        describe("${toBeLessThanOrEqualTo.name} ...") {
            val toBeLessThanOrEqualToFun = toBeLessThanOrEqualTo.lambda

            it("... 11 does not throw") {
                expect(10).toBeLessThanOrEqualToFun(11)
            }
            it("... 10 does not throw") {
                expect(10).toBeLessThanOrEqualToFun(10)
            }
            it("... 9 throws an AssertionError containing ${DescriptionComparableProof::class.simpleName}.$TO_BE_LESS_THAN_OR_EQUAL_TO and `: 10`") {
                expect {
                    expect(10).toBeLessThanOrEqualToFun(9)
                }.toThrow<AssertionError> { message { toContainDescr(toBeLessThanOrEqualToDescr, 9) } }
            }
        }

        describe("${toBeEqualComparingTo.name} ...") {
            val toBeEqualComparingToFun = toBeEqualComparingTo.lambda

            it("... 11 throws an AssertionError containing ${DescriptionComparableProof::class.simpleName}.$TO_BE_EQUAL_COMPARING_TO and `: 11`") {
                expect {
                    expect(10).toBeEqualComparingToFun(11)
                }.toThrow<AssertionError> { message { toContainDescr(TO_BE_EQUAL_COMPARING_TO, 11) } }
            }
            it("... 10 does not throw") {
                expect(10).toBeEqualComparingToFun(10)
            }
            it("... 9 throws an AssertionError containing ${DescriptionComparableProof::class.simpleName}.$TO_BE_EQUAL_COMPARING_TO and `: 9`") {
                expect {
                    expect(10).toBeEqualComparingToFun(9)
                }.toThrow<AssertionError> { message { toContainDescr(TO_BE_EQUAL_COMPARING_TO, 9) } }
            }
        }

        describe("${toBeGreaterThanOrEqualTo.name} ...") {
            val toBeGreaterThanOrEqualFun = toBeGreaterThanOrEqualTo.lambda

            it("... 11 throws an AssertionError containing ${DescriptionComparableProof::class.simpleName}.$TO_BE_GREATER_THAN_OR_EQUAL_TO and `: 11`") {
                expect {
                    expect(10).toBeGreaterThanOrEqualFun(11)
                }.toThrow<AssertionError> { message { toContainDescr(toBeGreaterThanOrEqualToDescr, 11) } }
            }
            it("... 10 does not throw") {
                expect(10).toBeGreaterThanOrEqualFun(10)
            }
            it("... 9 does not throw") {
                expect(10).toBeGreaterThanOrEqualFun(9)
            }
        }

        describe("${toBeGreaterThan.name} ...") {
            val toBeGreaterThanFun = toBeGreaterThan.lambda

            it("... 11 throws an AssertionError containing ${DescriptionComparableProof::class.simpleName}.$TO_BE_GREATER_THAN and `: 11`") {
                expect {
                    expect(10).toBeGreaterThanFun(11)
                }.toThrow<AssertionError> { message { toContainDescr(TO_BE_GREATER_THAN, 11) } }
            }
            it("... 10 throws an AssertionError containing ${DescriptionComparableProof::class.simpleName}.$TO_BE_GREATER_THAN and `: 10`") {
                expect {
                    expect(10).toBeGreaterThanFun(10)
                }.toThrow<AssertionError> { message { toContainDescr(TO_BE_GREATER_THAN, 10) } }
            }
            it("... 9 does not throw") {
                expect(10).toBeGreaterThanFun(9)
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
                }.toThrow<AssertionError> {
                    message {
                        toContainDescr(toBeLessThanOrEqualToDescr, "DiffEqualsCompareTo(s=welcome)")
                    }
                }
            }

            it("expected equals but compareTo does not return 0 but 1- throws AssertionError") {
                expect {
                    expect(DiffEqualsCompareTo("welcome")).toBeLessThanFun(DiffEqualsCompareTo("welcome"))
                }.toThrow<AssertionError> {
                    message {
                        toContainDescr(toBeLessThanOrEqualToDescr, "DiffEqualsCompareTo(s=welcome)")
                    }
                }
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
                }.toThrow<AssertionError> {
                    message {
                        toContainDescr(TO_BE_EQUAL_COMPARING_TO, "DiffEqualsCompareTo(s=welcome)")
                    }
                }
            }

            it("expected equals but compareTo does not return 0 but 1 - throws AssertionError") {
                expect {
                    expect(DiffEqualsCompareTo("welcome")).toBeEqualComparingToFun(DiffEqualsCompareTo("welcome"))
                }.toThrow<AssertionError> {
                    message {
                        toContainDescr(TO_BE_EQUAL_COMPARING_TO, "DiffEqualsCompareTo(s=welcome)")
                    }
                }
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
                }.toThrow<AssertionError> {
                    message {
                        toContainDescr(toBeGreaterThanOrEqualToDescr, "DiffEqualsCompareTo(s=allo)")
                    }
                }
            }

            it("expected equals but compareTo does not return 0 but -1 - throws AssertionError") {
                expect {
                    expect(DiffEqualsCompareTo("allo")).toBeGreaterThanOrEqualFun(DiffEqualsCompareTo("allo"))
                }.toThrow<AssertionError> {
                    message {
                        toContainDescr(toBeGreaterThanOrEqualToDescr, "DiffEqualsCompareTo(s=allo)")
                    }
                }
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

