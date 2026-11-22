package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.creating.build
import ch.tutteli.atrium.reporting.AssertionFormatterFacade
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.testfactories.TestFactory

@Suppress("FunctionName")
@OptIn(ExperimentalComponentFactoryContainer::class, ExperimentalWithOptions::class)
abstract class AbstractThirdPartyExpectationsTest(
    private val toHoldThirdPartyExpectationSpec: Fun3<Int, String, Any?, (Int) -> Unit>,
    private val toHoldThirdPartyExpectationNullableSpec: Fun3<Int?, String, Any?, (Int?) -> Unit>,
) : ExpectationFunctionBaseTest() {

    // we don't have an expectationCreatorTest as we don't expect that the third party expectation will
    // append anything but rather throw in case it is NOK or just do nothing

    /* simulating a third party lib */
    private fun <T> assertThat(subject: T) = expect(subject)

    // within `it`, expect(subject) refers to the function of the test executable which does not return a RootExpect,
    // but we need a RootExpect in order to use withOptions
    private fun <T> rootExpect(subject: T) = expect(subject)

    @TestFactory
    fun nonNullableCases() = testFactoryNonNullable(
        toHoldThirdPartyExpectationSpec,
        toHoldThirdPartyExpectationNullableSpec
    ) { toHoldThirdPartyExpectationFun ->

        describe("subject not defined") {
            it("shows the intended third party expectation in reporting") {
                expect {
                    expect(emptyList<Int>()).get(0) {
                        toHoldThirdPartyExpectationFun("(assertJ) is old enough", true) {
                            throw IllegalArgumentException("expect x was y")
                        }
                    }
                }.toThrow<AssertionError>() {
                    message {
                        toContain("(assertJ) is old enough: true")
                        notToContain("expect x was y")
                    }
                }
            }
        }

        describe("subject is defined") {

            describe("expectation holds") {
                it("shows the description and representation of the third party expectation in reporting") {
                    expect {
                        rootExpect(1).withOptions {
                            withComponent(Reporter::class) { c ->
                                val assertionFormatterFacade = c.build<AssertionFormatterFacade>()
                                object : Reporter {
                                    override fun format(assertion: Assertion, sb: StringBuilder): Unit =
                                        assertionFormatterFacade.format(assertion, sb, this::assertionFilter)

                                    private fun assertionFilter(@Suppress("UNUSED_PARAMETER") assertion: Assertion) =
                                        true
                                }
                            }
                        }.toHoldThirdPartyExpectationFun("(assertJ) is equal to", 10) { subject ->
                            assertThat(subject + 9).toEqual(10)
                        } //fails, due to customer Reporter we should also see holding third party expectation from above
                            .toEqual(2)
                    }.toThrow<AssertionError> {
                        message {
                            toContain("(assertJ) is equal to: 10")
                            notToContain(
                                "I expected subject: 10",
                                "$toEqualDescr: 10"
                            )
                        }
                    }
                }
            }

            describe("expectation fails") {
                it("shows the third party expectation as well as the caught Throwable") {
                    expect {
                        expect(1).toHoldThirdPartyExpectationFun("(assertJ) is equal to", 9) { _ ->
                            error("nooooooo......")
                        }
                    }.toThrow<AssertionError> {
                        message {
                            toContain(
                                "(assertJ) is equal to: 9",
                                "Properties of the unexpected IllegalStateException",
                                "nooooooo......",
                            )
                        }
                    }
                }
            }
        }
    }

    @TestFactory
    fun nullableCases() = testFactory(toHoldThirdPartyExpectationNullableSpec) { toHoldThirdPartyExpectationFun ->
        describe("subject is defined") {
            describe("expectation holds") {
                it("extracts the subject even if null") {
                    expect(null as Int?).toHoldThirdPartyExpectationFun("(assertJ) is equal to", null) { subject ->
                        assertThat(subject).toEqual(null)
                    }
                }
            }
        }
    }
}
