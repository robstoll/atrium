package ch.tutteli.atrium.specs.verbs

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.reporting.ExpectBuilder
import ch.tutteli.atrium.domain.builders.reporting.ExpectOptions
import ch.tutteli.atrium.domain.builders.reporting.ReporterBuilder
import ch.tutteli.atrium.specs.AssertionVerb
import ch.tutteli.atrium.specs.prefixedDescribeTemplate
import ch.tutteli.atrium.specs.toBeDescr
import ch.tutteli.atrium.translations.DescriptionAnyAssertion
import ch.tutteli.atrium.translations.DescriptionComparableAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class VerbSpec(
    forNonNullable: Pair<String, (subject: Int) -> Expect<Int>>,
    forNonNullableCreator: Pair<String, (subject: Int, assertionCreator: Expect<Int>.() -> Unit) -> Expect<Int>>,
    forNullable: Pair<String, (subject: Int?) -> Expect<Int?>>,
    forThrowable: Pair<String, (act: () -> Any?) -> Expect<() -> Any?>>,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun prefixedDescribe(description: String, body: Suite.() -> Unit) {
        prefixedDescribeTemplate(describePrefix, description, body)
    }

    prefixedDescribe("assertion verb '${forNonNullable.first}' which immediately evaluates assertions") {
        val (_, assertionVerb) = forNonNullable

        testNonNullableSubject { assertionVerb(it) }
    }

    prefixedDescribe("assertion verb '${forNonNullable.first}' which lazily evaluates assertions") {
        val (_, assertionVerb) = forNonNullableCreator

        context("the assertions hold") {
            it("does not throw an exception") {
                assertionVerb(1) { toBe(1) }
            }
            context("a subsequent assertion holds") {
                it("does not throw an exception") {
                    assertionVerb(1) { toBe(1) }.isLessThan(2)
                }
            }
            context("a subsequent group of assertions hold") {
                it("does not throw an exception") {
                    assertionVerb(1) { toBe(1) }.and { isLessThan(2) }
                }
            }
            context("a subsequent assertion fails") {
                it("throws an AssertionError") {
                    assert {
                        assertionVerb(1) { toBe(1) }.isLessThan(1)
                    }.toThrow<AssertionError> {
                        message {
                            contains("${DescriptionComparableAssertion.IS_LESS_THAN.getDefault()}: 1")
                            containsNot(toBeDescr)
                        }
                    }
                }
            }

            context("multiple assertions of a subsequent group of assertion fails") {
                it("evaluates all assertions and then throws an AssertionError, reporting only failing") {
                    assert {
                        assertionVerb(1) { toBe(1) }.and { isLessThan(0); toBe(1); isGreaterThan(2) }
                    }.toThrow<AssertionError> {
                        message {
                            contains(
                                ": 1",
                                "${DescriptionComparableAssertion.IS_LESS_THAN.getDefault()}: 0",
                                "${DescriptionComparableAssertion.IS_GREATER_THAN.getDefault()}: 2"
                            )
                            containsNot(toBeDescr)
                        }
                    }
                }
            }
        }

        context("one assertion fails") {
            it("evaluates all assertions and then throws an AssertionError") {
                assert {
                    assertionVerb(1) {
                        isLessThan(0)
                        isGreaterThan(2)
                    }
                }.toThrow<AssertionError> {
                    message {
                        contains(": 1")
                        contains("${DescriptionComparableAssertion.IS_LESS_THAN.getDefault()}: 0")
                        contains("${DescriptionComparableAssertion.IS_GREATER_THAN.getDefault()}: 2")
                    }
                }
            }
        }
    }

    prefixedDescribe("assertion verb '${forNullable.first}' which supports nullable subjects") {
        val (_, assertionVerb) = forNullable

        context("subject is null") {
            it("does not throw an exception when calling toBe(`null`)") {
                assertionVerb(null).toBe(null)
            }
            it("throws an AssertionError when calling notToBeNull") {
                assert {
                    assertionVerb(null).notToBeNull { toBe(1) }
                }.toThrow<AssertionError> {
                    @Suppress("DEPRECATION")
                    messageContains(
                        DescriptionAnyAssertion.IS_A.getDefault(),
                        "Int",
                        "$toBeDescr: 1"
                    )
                }
            }
        }
        context("subject is not null") {
            testNonNullableSubject { subject -> ExpectImpl.changeSubject(assertionVerb(subject)).unreported { it!! } }
        }
    }

    prefixedDescribe("assertion verb '${forThrowable.first}' which deals with exceptions") {
        val (_, assertionVerb) = forThrowable

        context("an IllegalArgumentException occurs") {
            it("does not throw an exception expecting an IllegalArgumentException") {
                assertionVerb {
                    throw IllegalArgumentException("hello")
                }.toThrow<IllegalArgumentException> {
                    message.toBe("hello")
                }
            }
            it("throws an AssertionError when expecting an UnsupportedOperationException") {
                assert {
                    assertionVerb {
                        throw IllegalArgumentException()
                    }.toThrow<UnsupportedOperationException> {}
                }.toThrow<AssertionError> {
                    messageContains(
                        DescriptionAnyAssertion.IS_A.getDefault(),
                        IllegalArgumentException::class.fullName,
                        UnsupportedOperationException::class.fullName
                    )
                }
            }
        }
    }

})

private fun Suite.testNonNullableSubject(assertionVerb: (Int) -> Expect<Int>) {
    it("does not throw an exception in case the assertion holds") {
        assertionVerb(1).toBe(1)
    }
    it("throws an AssertionError as soon as one assertion fails") {
        assert {
            assertionVerb(1).isLessThanOrEqual(10).and.isLessThanOrEqual(0).and.isGreaterThanOrEqual(2)
        }.toThrow<AssertionError> {
            message {
                contains(": 1")
                contains("${DescriptionComparableAssertion.IS_LESS_THAN_OR_EQUALS.getDefault()}: 0")
                containsNot("${DescriptionComparableAssertion.IS_GREATER_THAN_OR_EQUALS.getDefault()}: 2")
            }
        }
    }
}

// does not make sense to test the verbs with the verbs themselves. Thus we create our own assertion verb here
private fun <R> assert(act: () -> R): Expect<() -> R> =
    ExpectBuilder.forSubject(act)
        .withVerb(AssertionVerb.EXPECT_THROWN)
        .withOptions(ExpectOptions(reporter = AtriumReporterSupplier.REPORTER))
        .build()

private object AtriumReporterSupplier {
    val REPORTER by lazy {
        ReporterBuilder.create()
            .withoutTranslationsUseDefaultLocale()
            .withDetailedObjectFormatter()
            .withDefaultAssertionFormatterController()
            .withDefaultAssertionFormatterFacade()
            .withTextSameLineAssertionPairFormatter()
            .withTextCapabilities()
            .withDefaultAtriumErrorAdjusters()
            .withOnlyFailureReporter()
            .build()
    }
}
