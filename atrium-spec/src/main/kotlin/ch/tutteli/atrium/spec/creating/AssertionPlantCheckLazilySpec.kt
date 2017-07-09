package ch.tutteli.atrium.spec.creating

import ch.tutteli.atrium.DescriptionAnyAssertion
import ch.tutteli.atrium.assertions.BasicAssertion
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IBasicAssertion
import ch.tutteli.atrium.contains
import ch.tutteli.atrium.containsDefaultTranslationOf
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantWithCommonFields
import ch.tutteli.atrium.message
import ch.tutteli.atrium.spec.*
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

abstract class AssertionPlantCheckLazilySpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: (IAssertionPlantWithCommonFields.CommonFields<Int>) -> IAssertionPlant<Int>,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun prefixedDescribe(description: String, body: SpecBody.() -> Unit) {
        prefixedDescribe(describePrefix, description, body)
    }

    val assertionVerb = AssertionVerb.VERB
    val subject = 10
    val assertionChecker = verbs.checkLazily(1, {}).commonFields.assertionChecker
    val testee = testeeFactory(IAssertionPlantWithCommonFields.CommonFields(assertionVerb, 10, assertionChecker))

    prefixedDescribe("fun ${testee::createAndAddAssertion.name}") {

        val a = subject
        inCaseOf("assertion which holds") {
            testee.createAndAddAssertion(DescriptionAnyAssertion.TO_BE, a, { a == subject })
            it("does not throw an Exception when checking") {
                testee.checkAssertions()
            }
        }

        setUp("in case of assertion which fails") {
            testee.createAndAddAssertion(DescriptionAnyAssertion.TO_BE, -12, { a == 0 })
            val expectFun = verbs.checkException {
                testee.checkAssertions()
            }
            setUp("throws an AssertionError when checking") {
                context("exception message") {
                    val assertMessage = expectFun.toThrow<AssertionError>().message
                    it("contains the ${testee.commonFields::assertionVerb.name}'") {
                        assertMessage.containsDefaultTranslationOf(assertionVerb)
                    }
                    it("contains the '${testee::subject.name}'") {
                        assertMessage.contains(subject)
                    }
                    it("contains the '${IBasicAssertion::description.name}' of the assertion-message") {
                        assertMessage.containsDefaultTranslationOf(DescriptionAnyAssertion.TO_BE)
                    }
                    it("contains the '${IBasicAssertion::expected.name}' of the assertion-message") {
                        assertMessage.contains(-12)
                    }
                }
                on("re-checking the assertions") {
                    expectFun.toThrow<AssertionError>()
                    it("does not re-throw the exception") {
                        testee.checkAssertions()
                    }
                }
            }
        }

    }

    prefixedDescribe("fun ${testee::addAssertion.name}") {
        inCaseOf("a custom assertion which holds") {
            testee.addAssertion(object : IAssertion {
                override fun holds() = true
            })
            it("does not throw an Exception when checking") {
                testee.checkAssertions()
            }
        }

        setUp("in case of a custom ${IBasicAssertion::class.java.simpleName} which fails") {
            testee.addAssertion(BasicAssertion(DescriptionAnyAssertion.TO_BE, "my expected result", false))
            val expectFun = verbs.checkException {
                testee.checkAssertions()
            }
            setUp("throws an AssertionError") {
                expectFun.toThrow<AssertionError>()
                context("exception message") {
                    it("contains the messages of the custom assertion") {
                        expectFun.toThrow<AssertionError>().message {
                            containsDefaultTranslationOf(DescriptionAnyAssertion.TO_BE)
                            contains("my expected result")
                        }
                    }
                    it("contains the assertionVerb") {
                        expectFun.toThrow<AssertionError>().message {
                            containsDefaultTranslationOf(assertionVerb)
                        }
                    }
                }
                on("re-checking the assertions") {
                    expectFun.toThrow<AssertionError>()
                    it("does not re-throw the exception") {
                        testee.checkAssertions()
                    }
                }
            }
        }
    }
})
