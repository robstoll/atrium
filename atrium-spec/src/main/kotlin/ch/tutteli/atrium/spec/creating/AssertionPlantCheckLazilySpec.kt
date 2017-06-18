package ch.tutteli.atrium.spec.creating

import ch.tutteli.atrium.DescriptionAnyAssertion
import ch.tutteli.atrium.assertions.BasicAssertion
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IBasicAssertion
import ch.tutteli.atrium.contains
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantWithCommonFields
import ch.tutteli.atrium.message
import ch.tutteli.atrium.spec.AssertionVerb
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.inCaseOf
import ch.tutteli.atrium.spec.setUp
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

open class AssertionPlantCheckLazilySpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: (IAssertionPlantWithCommonFields.CommonFields<Int>) -> IAssertionPlant<Int>
) : Spek({
    val assertionVerb = AssertionVerb.VERB
    val subject = 10
    val assertionChecker = verbs.checkLazily(1, {}).commonFields.assertionChecker
    val testee = testeeFactory(IAssertionPlantWithCommonFields.CommonFields(assertionVerb, 10, assertionChecker))

    context("fun ${testee::createAndAddAssertion.name}") {

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
                        assertMessage.contains(assertionVerb)
                    }
                    it("contains the '${testee::subject.name}'") {
                        assertMessage.contains(subject)
                    }
                    it("contains the '${IBasicAssertion::description.name}' of the assertion-message") {
                        assertMessage.contains(DescriptionAnyAssertion.TO_BE.getDefault())
                    }
                    it("contains the '${IBasicAssertion::representation.name}' of the assertion-message") {
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

    context("fun ${testee::addAssertion.name}") {
        inCaseOf("a custom assertion which holds") {
            testee.addAssertion(object : IAssertion {
                override fun holds() = true
            })
            it("does not throw an Exception when checking") {
                testee.checkAssertions()
            }
        }

        setUp("in case of a custom ${IBasicAssertion::class.java.simpleName} which fails") {
            testee.addAssertion(BasicAssertion(DescriptionAnyAssertion.TO_BE, "my representation", false))
            val expectFun = verbs.checkException {
                testee.checkAssertions()
            }
            setUp("throws an AssertionError") {
                expectFun.toThrow<AssertionError>()
                context("exception message") {
                    val assertMessage = expectFun.toThrow<AssertionError>().message
                    it("contains the messages of the custom assertion") {
                        assertMessage.contains(DescriptionAnyAssertion.TO_BE).and.contains("my representation")
                    }
                    it("contains the assertionVerb") {
                        assertMessage.contains(assertionVerb)
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
