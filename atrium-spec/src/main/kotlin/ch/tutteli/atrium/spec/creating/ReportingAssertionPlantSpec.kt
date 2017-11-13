package ch.tutteli.atrium.spec.creating

import ch.tutteli.atrium.api.cc.en_UK.contains
import ch.tutteli.atrium.api.cc.en_UK.containsDefaultTranslationOf
import ch.tutteli.atrium.api.cc.en_UK.message
import ch.tutteli.atrium.api.cc.en_UK.toThrow
import ch.tutteli.atrium.assertions.DescriptionAnyAssertion.TO_BE
import ch.tutteli.atrium.assertions.IBasicAssertion
import ch.tutteli.atrium.assertions.throwable.thrown.builders.ThrowableThrownBuilder
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantWithCommonFields
import ch.tutteli.atrium.creating.IReportingAssertionPlant
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.spec.AssertionVerb
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.prefixedDescribe
import ch.tutteli.atrium.spec.setUp
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

abstract class ReportingAssertionPlantSpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: (IAssertionPlantWithCommonFields.CommonFields<Int>) -> IReportingAssertionPlant<Int>,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun prefixedDescribe(description: String, body: SpecBody.() -> Unit) {
        prefixedDescribe(describePrefix, description, body)
    }

    val expect = verbs::checkException
    val assertionVerb = AssertionVerb.VERB
    val subject = 10
    val description = TO_BE
    val expected = -12

    val assertionChecker = (verbs.checkLazily(1, {}) as IReportingAssertionPlant<Int>).commonFields.assertionChecker
    fun createTestee()
        = testeeFactory(IAssertionPlantWithCommonFields.CommonFields(assertionVerb, 10, assertionChecker, RawString.NULL))

    val plant = createTestee()

    fun triple(funName: String, holdingFun: IAssertionPlant<Int>.() -> IAssertionPlant<Int>, failingFun: IAssertionPlant<Int>.() -> IAssertionPlant<Int>): Triple<String, IAssertionPlant<Int>.() -> IAssertionPlant<Int>, IAssertionPlant<Int>.() -> IAssertionPlant<Int>>
        = Triple(funName, holdingFun, failingFun)

    val basicAssertionWhichHolds = object : IBasicAssertion {
        override val description = description
        override val expected = expected
        override fun holds() = true
    }
    val basicAssertionWhichFails = object : IBasicAssertion {
        override val description = description
        override val expected = expected
        override fun holds() = false
    }

    listOf(
        triple(
            plant::createAndAddAssertion.name,
            { createAndAddAssertion(description, expected, { true }) },
            { createAndAddAssertion(description, expected, { false }) }
        ),
        triple(
            plant::addAssertion.name,
            { addAssertion(basicAssertionWhichHolds) },
            { addAssertion(basicAssertionWhichFails) }),
        triple(
            "${plant::addAssertionsCreatedBy.name} using ${plant::createAndAddAssertion.name} inside",
            { addAssertionsCreatedBy { createAndAddAssertion(description, expected, { true }) } },
            { addAssertionsCreatedBy { createAndAddAssertion(description, expected, { false }) } }
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
        prefixedDescribe("fun $funName") {
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
                    fun expectFun(): ThrowableThrownBuilder {
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
                                message { contains(subject) }
                            }
                        }
                        it("contains the '${IBasicAssertion::description.name}' of the assertion-message") {
                            expectFun().toThrow<AssertionError> {
                                message { containsDefaultTranslationOf(description) }
                            }
                        }
                        it("contains the '${IBasicAssertion::expected.name}' of the assertion-message") {
                            expectFun().toThrow<AssertionError> {
                                message { contains(expected) }
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
