package ch.tutteli.atrium.specs.verbs

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.changeSubject
import ch.tutteli.atrium.logic.creating.RootExpectBuilder
import ch.tutteli.atrium.specs.AssertionVerb
import ch.tutteli.atrium.specs.prefixedDescribeTemplate
import ch.tutteli.atrium.specs.toBeAnInstanceOfDescr
import ch.tutteli.atrium.specs.toEqualDescr
import ch.tutteli.atrium.translations.DescriptionAnyExpectation.TO_BE_AN_INSTANCE_OF
import ch.tutteli.atrium.translations.DescriptionComparableExpectation
import ch.tutteli.atrium.translations.DescriptionComparableExpectation.TO_BE_GREATER_THAN
import ch.tutteli.atrium.translations.DescriptionComparableExpectation.TO_BE_LESS_THAN
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
                assertionVerb(1) { toEqual(1) }
            }
            context("a subsequent assertion holds") {
                it("does not throw an exception") {
                    assertionVerb(1) { toEqual(1) }.toBeLessThan(2)
                }
            }
            context("a subsequent group of assertions hold") {
                it("does not throw an exception") {
                    assertionVerb(1) { toEqual(1) }.and { toBeLessThan(2) }
                }
            }
            context("a subsequent assertion fails") {
                it("throws an AssertionError") {
                    assert {
                        assertionVerb(1) { toEqual(1) }.toBeLessThan(1)
                    }.toThrow<AssertionError> {
                        message {
                            toContain("${TO_BE_LESS_THAN.getDefault()}: 1")
                            notToContain(toEqualDescr)
                        }
                    }
                }
            }

            context("multiple assertions of a subsequent group of assertion fails") {
                it("evaluates all assertions and then throws an AssertionError, reporting only failing") {
                    assert {
                        assertionVerb(1) { toEqual(1) }.and { toBeLessThan(0); toEqual(1); toBeGreaterThan(2) }
                    }.toThrow<AssertionError> {
                        message {
                            toContain(
                                ": 1",
                                "${TO_BE_LESS_THAN.getDefault()}: 0",
                                "${TO_BE_GREATER_THAN.getDefault()}: 2"
                            )
                            notToContain(toEqualDescr)
                        }
                    }
                }
            }
        }

        context("one assertion fails") {
            it("evaluates all assertions and then throws an AssertionError") {
                assert {
                    assertionVerb(1) {
                        toBeLessThan(0)
                        toBeGreaterThan(2)
                    }
                }.toThrow<AssertionError> {
                    message {
                        toContain(": 1")
                        toContain("${TO_BE_LESS_THAN.getDefault()}: 0")
                        toContain("${TO_BE_GREATER_THAN.getDefault()}: 2")
                    }
                }
            }
        }
    }

    prefixedDescribe("assertion verb '${forNullable.first}' which supports nullable subjects") {
        val (_, assertionVerb) = forNullable

        context("subject is null") {
            it("does not throw an exception when calling $toEqualDescr(`null`)") {
                assertionVerb(null).toEqual(null)
            }
            it("throws an AssertionError when calling notToEqualNull") {
                assert {
                    assertionVerb(null).notToEqualNull { toEqual(1) }
                }.toThrow<AssertionError> {
                    @Suppress("DEPRECATION")
                    (messageToContain(
                        toBeAnInstanceOfDescr,
                        "Int", "$toEqualDescr: 1"
                    ))
                }
            }
        }
        context("subject is not null") {
            testNonNullableSubject { subject -> assertionVerb(subject)._logic.changeSubject.unreported { it!! } }
        }
    }

    prefixedDescribe("assertion verb '${forThrowable.first}' which deals with exceptions") {
        val (_, assertionVerb) = forThrowable

        context("an IllegalArgumentException occurs") {
            it("does not throw an exception expecting an IllegalArgumentException") {
                assertionVerb {
                    throw IllegalArgumentException("hello")
                }.toThrow<IllegalArgumentException> {
                    message.toEqual("hello")
                }
            }
            it("throws an AssertionError when expecting an UnsupportedOperationException") {
                assert {
                    assertionVerb {
                        throw IllegalArgumentException()
                    }.toThrow<UnsupportedOperationException> {}
                }.toThrow<AssertionError> {
                    messageToContain(
                        TO_BE_AN_INSTANCE_OF.getDefault(),
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
        assertionVerb(1).toEqual(1)
    }
    it("throws an AssertionError as soon as one assertion fails") {
        assert {
            assertionVerb(1).toBeLessThanOrEqualTo(10).and.toBeLessThanOrEqualTo(0).and.toBeGreaterThanOrEqualTo(2)
        }.toThrow<AssertionError> {
            message {
                toContain(": 1")
                toContain("${DescriptionComparableExpectation.TO_BE_LESS_THAN_OR_EQUAL_TO.getDefault()}: 0")
                notToContain("${DescriptionComparableExpectation.TO_BE_GREATER_THAN_OR_EQUAL_TO.getDefault()}: 2")
            }
        }
    }
}


@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class, ExperimentalComponentFactoryContainer::class)
// does not make sense to test the verbs with the verbs themselves. Thus we create our own assertion verb here
private fun <R> assert(act: () -> R): Expect<() -> R> =
    RootExpectBuilder.forSubject(act)
        .withVerb(AssertionVerb.EXPECT_THROWN)
        .withoutOptions()
        .build()
