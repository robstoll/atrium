package ch.tutteli.atrium.spec.creating

import ch.tutteli.atrium.api.cc.en_UK.contains
import ch.tutteli.atrium.api.cc.en_UK.containsDefaultTranslationOf
import ch.tutteli.atrium.api.cc.en_UK.message
import ch.tutteli.atrium.api.cc.en_UK.toThrow
import ch.tutteli.atrium.assertions.BasicAssertion
import ch.tutteli.atrium.assertions.DescriptionAnyAssertion
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IBasicAssertion
import ch.tutteli.atrium.assertions.throwable.thrown.builders.ThrowableThrownBuilder
import ch.tutteli.atrium.creating.IAssertionPlantWithCommonFields
import ch.tutteli.atrium.creating.IReportingAssertionPlant
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.spec.*
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

abstract class AssertionPlantCheckLazilySpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: (IAssertionPlantWithCommonFields.CommonFields<Int>) -> IReportingAssertionPlant<Int>,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun prefixedDescribe(description: String, body: SpecBody.() -> Unit) {
        prefixedDescribe(describePrefix, description, body)
    }

    val assertionVerb = AssertionVerb.VERB
    val subject = 10
    val assertionChecker = (verbs.checkLazily(1, {}) as IReportingAssertionPlant<Int>).commonFields.assertionChecker
    fun createTestee()
        = testeeFactory(IAssertionPlantWithCommonFields.CommonFields(assertionVerb, 10, assertionChecker, RawString.NULL))

    val plant = createTestee()

    prefixedDescribe("fun ${plant::createAndAddAssertion.name}") {

        val a = subject
        inCaseOf("an assertion which holds") {
            val testee = createTestee()
            testee.createAndAddAssertion(DescriptionAnyAssertion.TO_BE, a, { a == subject })
            it("does not throw an Exception when calling ${testee::checkAssertions.name}") {
                testee.checkAssertions()
            }
        }

        setUp("in case of assertion which fails") {
            setUp("throws an AssertionError when calling ${plant::checkAssertions.name}") {
                fun expectFun(): ThrowableThrownBuilder {
                    val testee = createTestee()
                    testee.createAndAddAssertion(DescriptionAnyAssertion.TO_BE, -12, { a == 0 })
                    return verbs.checkException {
                        testee.checkAssertions()
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
                            message { containsDefaultTranslationOf(DescriptionAnyAssertion.TO_BE) }
                        }
                    }
                    it("contains the '${IBasicAssertion::expected.name}' of the assertion-message") {
                        expectFun().toThrow<AssertionError> {
                            message { contains(-12) }
                        }
                    }
                }
                on("re-checking the assertions (calling ${plant::checkAssertions.name} twice)") {
                    val testee = createTestee()
                    testee.createAndAddAssertion(DescriptionAnyAssertion.TO_BE, -12, { a == 0 })
                    verbs.checkException {
                        testee.checkAssertions()
                    }.toThrow<AssertionError>()
                    it("does not re-throw the exception") {
                        testee.checkAssertions()
                    }
                }
            }
        }

    }

    prefixedDescribe("fun ${plant::addAssertion.name}") {
        inCaseOf("a custom assertion which holds") {
            val testee = createTestee()
            testee.addAssertion(object : IAssertion {
                override fun holds() = true
            })
            it("does not throw an Exception when calling ${testee::checkAssertions.name}") {
                testee.checkAssertions()
            }
        }

        setUp("in case of a custom ${IBasicAssertion::class.java.simpleName} which fails") {
            fun expectFun(): ThrowableThrownBuilder {
                val testee = createTestee()
                testee.addAssertion(BasicAssertion(DescriptionAnyAssertion.TO_BE, "my expected result", false))
                return verbs.checkException {
                    testee.checkAssertions()
                }
            }
            setUp("throws an AssertionError") {
                expectFun().toThrow<AssertionError>()
                context("exception message") {
                    it("contains the messages of the custom assertion") {
                        expectFun().toThrow<AssertionError> {
                            message {
                                containsDefaultTranslationOf(DescriptionAnyAssertion.TO_BE)
                                contains("my expected result")
                            }
                        }
                    }
                    it("contains the assertionVerb") {
                        expectFun().toThrow<AssertionError> {
                            message { containsDefaultTranslationOf(assertionVerb) }
                        }
                    }
                }
                on("re-checking the assertions (calling ${plant::checkAssertions.name} twice)") {
                    val testee = createTestee()
                    testee.addAssertion(BasicAssertion(DescriptionAnyAssertion.TO_BE, "my expected result", false))
                    verbs.checkException {
                        testee.checkAssertions()
                    }.toThrow<AssertionError>()
                    it("does not re-throw the exception") {
                        testee.checkAssertions()
                    }
                }
            }
        }
    }
})
