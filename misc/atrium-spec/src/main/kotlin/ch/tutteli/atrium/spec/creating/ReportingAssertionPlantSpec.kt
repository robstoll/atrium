package ch.tutteli.atrium.spec.creating

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.core.falseProvider
import ch.tutteli.atrium.core.trueProvider
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantWithCommonFields
import ch.tutteli.atrium.creating.ReportingAssertionPlant
import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.spec.AssertionVerb
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import ch.tutteli.atrium.spec.setUp
import ch.tutteli.atrium.translations.DescriptionAnyAssertion.TO_BE
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

abstract class ReportingAssertionPlantSpec(
    verbs: AssertionVerbFactory,
    testeeFactory: (AssertionPlantWithCommonFields.CommonFields<Int>) -> ReportingAssertionPlant<Int>,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val expect = verbs::checkException
    val assertionVerb = AssertionVerb.VERB
    val subject = 10
    val description = TO_BE
    val expected = -12

    val assertionChecker = (verbs.checkLazily(1, {}) as ReportingAssertionPlant<Int>).commonFields.assertionChecker
    fun createTestee()
        = testeeFactory(AssertionPlantWithCommonFields.CommonFields(assertionVerb, { 10 }, {10}, assertionChecker, RawString.NULL))

    val plant = createTestee()

    fun triple(funName: String, holdingFun: AssertionPlant<Int>.() -> AssertionPlant<Int>, failingFun: AssertionPlant<Int>.() -> AssertionPlant<Int>): Triple<String, AssertionPlant<Int>.() -> AssertionPlant<Int>, AssertionPlant<Int>.() -> AssertionPlant<Int>>
        = Triple(funName, holdingFun, failingFun)

    val basicAssertionWhichHolds = object : DescriptiveAssertion {
        override val description = description
        override val representation = expected
        override fun holds() = true
    }
    val basicAssertionWhichFails = object : DescriptiveAssertion {
        override val description = description
        override val representation = expected
        override fun holds() = false
    }

    listOf(
        triple(
            plant::createAndAddAssertion.name,
            { createAndAddAssertion(description, expected, trueProvider) },
            { createAndAddAssertion(description, expected, falseProvider) }
        ),
        triple(
            plant::addAssertion.name,
            { addAssertion(basicAssertionWhichHolds) },
            { addAssertion(basicAssertionWhichFails) }),
        triple(
            "${plant::addAssertionsCreatedBy.name} using ${plant::createAndAddAssertion.name} inside",
            { addAssertionsCreatedBy { createAndAddAssertion(description, expected, trueProvider) } },
            { addAssertionsCreatedBy { createAndAddAssertion(description, expected, falseProvider) } }
        ),
        triple(
            "${plant::addAssertionsCreatedBy.name} using ${plant::addAssertion.name} inside",
            { addAssertionsCreatedBy { addAssertion(basicAssertionWhichHolds) } },
            { addAssertionsCreatedBy { addAssertion(basicAssertionWhichFails) } }
        ),
        triple(
            "${plant::addAssertionsCreatedBy.name} using ${plant::addAssertionsCreatedBy.name} inside",
            { addAssertionsCreatedBy { addAssertionsCreatedBy { addAssertion(basicAssertionWhichHolds) } } },
            { addAssertionsCreatedBy { addAssertionsCreatedBy { addAssertion(basicAssertionWhichFails) } } }
        )
    ).forEach { (funName, holdingFun, failingFun) ->
        describeFun(funName) {
            setUp("in case of an assertion which holds") {
                val testee = createTestee()
                it("does not throw an Exception") {
                    testee.holdingFun()
                }

                it("throws an AssertionError when an additional assertion does not hold") {
                    expect {
                        testee.failingFun()
                    }.toThrow<AssertionError>()
                }
            }

            setUp("in case of assertion which fails") {
                setUp("throws an AssertionError") {
                    fun expectFun(): ThrowableThrown.Builder {
                        val testee = createTestee()
                        return expect {
                            testee.failingFun()
                        }
                    }

                    context("exception message") {

                        it("contains the ${plant.commonFields::assertionVerb.name}'") {
                            expectFun().toThrow<AssertionError> {
                                message { containsDefaultTranslationOf(assertionVerb) }
                            }
                        }
                        it("contains the '${plant::subject.name}'") {
                            expectFun().toThrow<AssertionError> {
                                messageContains(subject)
                            }
                        }
                        it("contains the '${DescriptiveAssertion::description.name}' of the assertion-message") {
                            expectFun().toThrow<AssertionError> {
                                message { containsDefaultTranslationOf(description) }
                            }
                        }
                        it("contains the '${DescriptiveAssertion::representation.name}' of the assertion-message") {
                            expectFun().toThrow<AssertionError> {
                                messageContains(expected)
                            }
                        }
                    }

                    on("adding a another assertion which holds") {
                        val testee = createTestee()
                        expect {
                            testee.failingFun()
                        }.toThrow<AssertionError>()

                        it("does not re-throw due to the previous failing assertion") {
                            testee.holdingFun()
                        }
                    }
                }
            }
        }
    }
})
