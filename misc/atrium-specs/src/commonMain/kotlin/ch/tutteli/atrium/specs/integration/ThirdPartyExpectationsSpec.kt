package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.reporting.filters.ReportableFilter
import ch.tutteli.atrium.reporting.reportables.Reportable
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionThrowableProof
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.nonNullableCases
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

@OptIn(ExperimentalComponentFactoryContainer::class, ExperimentalWithOptions::class)
abstract class ThirdPartyExpectationsSpec(

    toHoldThirdPartyExpectation: Fun3<Int, String, Any?, (Int) -> Unit>,
    toHoldThirdPartyExpectationNullable: Fun3<Int?, String, Any?, (Int?) -> Unit>,

    describePrefix: String = "[Atrium] "
) : Spek({

    // we don't have an AssertionCreatorSpec as we don't expect that the third party expectation will
    // append anything but rather throw in case it is NOK or just do nothing

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    /* simulating a third party lib */
    fun <T> assertThat(subject: T) = expect(subject)

    nonNullableCases(
        describePrefix,
        toHoldThirdPartyExpectation,
        toHoldThirdPartyExpectationNullable
    ) { toHoldThirdPartyExpectationFun ->

        context("subject not defined") {
            it("shows the intended third party expectation in reporting") {
                expect {
                    expect(emptyList<Int>()).get(0) {
                        toHoldThirdPartyExpectationFun("(assertJ) is old enough", true) {
                            throw IllegalArgumentException("expect x was y")
                        }
                    }
                }.toThrow<AssertionError> {
                    message {
                        toContain("(assertJ) is old enough : true")
                        notToContain("expect x was y")
                    }
                }
            }
        }

        context("subject is defined") {

            context("expectation holds") {
                it("shows the description and representation of the third party expectation in reporting") {
                    expect {
                        expect(1).withOptions {
                            withComponent(ReportableFilter::class) { _ ->
                                object : ReportableFilter {
                                    override fun includeInReporting(reportable: Reportable): Boolean = true
                                }
                            }
                        }.toHoldThirdPartyExpectationFun("(assertJ) is equal to", 10) { subject ->
                            assertThat(subject + 9).toEqual(10)
                        } //fails, due to custom ReportableFilter we should also see holding third party expectation from above
                            .toEqual(2)
                    }.toThrow<AssertionError> {
                        message {
                            toContain("(assertJ) is equal to : 10")
                            toContainToEqualDescr(2)
                            notToContain.regex(
                                "$expectationVerb\\s+: 10",
                                "$toEqualDescr\\s+: 10"
                            )
                        }
                    }
                }
            }

            context("expectation fails") {
                it("shows the third party expectation as well as the caught Throwable") {
                    expect {
                        expect(1).toHoldThirdPartyExpectationFun("(assertJ) is equal to", 9) { subject ->
                            assertThat(subject + 9).toEqual(9)
                        }
                    }.toThrow<AssertionError> {
                        message {
                            toContain(
                                "(assertJ) is equal to : 9",
                                "$i${DescriptionThrowableProof.OCCURRED_EXCEPTION_PROPERTIES.string} AtriumError",
                                "$expectationVerb : 10",
                            )
                            toContainToEqualDescr(9)
                            toContainRegex(
                                Regex(
                                    "stacktrace : $lineSeparator" +
                                        "$indentG$indentI$indentExplanatory$listBulletPoint.*ThirdPartyExpectationsSpec"
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    describeFun(toHoldThirdPartyExpectationNullable) {
        val toHoldThirdPartyExpectationFun = toHoldThirdPartyExpectationNullable.lambda

        context("subject is defined") {
            context("expectation holds") {
                it("extracts the subject even if null") {
                    expect(null as Int?).toHoldThirdPartyExpectationFun("(assertJ) is equal to", null) { subject ->
                        assertThat(subject).toEqual(null)
                    }
                }
            }
        }
    }
})
