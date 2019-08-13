@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)

package ch.tutteli.atrium.specs.verbs

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.specs.prefixedDescribeTemplate
import ch.tutteli.atrium.specs.toBeDescr
import ch.tutteli.atrium.translations.DescriptionAnyAssertion
import ch.tutteli.atrium.translations.DescriptionComparableAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

// does not make sense to test the verbs with the verbs themselves. Thus we create our own assertion verbs here
private fun <T : Any> assert(subject: T): Expect<T> = ExpectImpl.assertionVerbBuilder(subject)
    .withVerb(AssertionVerb.ASSERT)
    .withCustomReporter(AtriumReporterSupplier.REPORTER)
    .build()

private fun expect(act: () -> Unit) =
    AssertImpl.throwable.thrownBuilder(AssertionVerb.EXPECT_THROWN, act, AtriumReporterSupplier.REPORTER)

private object AtriumReporterSupplier {
    val REPORTER by lazy {
        ExpectImpl.reporterBuilder
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

abstract class VerbSpec(
    forNonNullable: Pair<String, (subject: Int) -> Expect<Int>>,
    forNonNullableCreator: Pair<String, (subject: Int, assertionCreator: Expect<Int>.() -> Unit) -> Expect<Int>>,
    forNullable: Pair<String, (subject: Int?) -> Expect<Int?>>,
    forThrowable: Pair<String, (act: () -> Unit) -> ThrowableThrown.Builder>,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun prefixedDescribe(description: String, body: Suite.() -> Unit) {
        prefixedDescribeTemplate(describePrefix, description, body)
    }

    prefixedDescribe("assertion verb '${forNonNullable.first}' which immediately evaluates assertions") {
        val (_, assertionVerb) = forNonNullable

        it("does not throw an exception in case the assertion holds") {
            assertionVerb(1).toBe(1)
        }
        it("throws an AssertionError as soon as one assertion fails") {
            expect {
                assertionVerb(1).isLessOrEquals(10).and.isLessOrEquals(0).and.isGreaterOrEquals(2)
            }.toThrow<AssertionError> {
                message {
                    contains(": 1")
                    contains("${DescriptionComparableAssertion.IS_LESS_OR_EQUALS.getDefault()}: 0")
                    containsNot("${DescriptionComparableAssertion.IS_GREATER_OR_EQUALS.getDefault()}: 2")
                }
            }
        }
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
                    expect {
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
                    expect {
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
                expect {
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
        context("a nullable subject") {
            val (_, assertionVerb) = forNullable
            it("does not throw an exception when calling toBe(`null`)") {
                assertionVerb(null).toBe(null)
            }
            it("throws an AssertionError when calling notToBeNull") {
                expect {
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
    }

    prefixedDescribe("assertion verb '${forThrowable.first}' which deals with exceptions") {
        context("an IllegalArgumentException occurs") {
            val (_, assertionVerb) = forThrowable
            it("does not throw an exception expecting an IllegalArgumentException") {
                assertionVerb {
                    throw IllegalArgumentException("hello")
                }.toThrow<IllegalArgumentException> {
                    message.toBe("hello")
                }
            }
            it("throws an AssertionError when expecting an UnsupportedOperationException") {
                expect {
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
