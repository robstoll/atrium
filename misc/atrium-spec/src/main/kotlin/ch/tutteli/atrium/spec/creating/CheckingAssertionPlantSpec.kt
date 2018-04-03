package ch.tutteli.atrium.spec.creating

import ch.tutteli.atrium.api.cc.en_UK.*
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.creating.CheckingAssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import ch.tutteli.atrium.spec.inCaseOf
import ch.tutteli.atrium.translations.DescriptionAnyAssertion
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.it

abstract class CheckingAssertionPlantSpec(
    verbs: AssertionVerbFactory,
    testeeFactory: (Int) -> CheckingAssertionPlant<Int>,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val subject = 10
    val testee = testeeFactory(subject)

    describeFun(testee::allAssertionsHold.name) {
        inCaseOf("no assertion has been added so far") {
            it("throws an ${IllegalStateException::class.simpleName}") {
                verbs.checkException {
                    testee.allAssertionsHold()
                }.toThrow<IllegalStateException>()
            }
        }
    }

    describeFun(testee::createAndAddAssertion.name) {

        val a = subject
        inCaseOf("an assertion which holds") {
            testee.createAndAddAssertion(DescriptionAnyAssertion.TO_BE, a, { a == subject })
            test("${testee::allAssertionsHold.name} returns `true` and does not throw an Exception") {
                val result = testee.allAssertionsHold()
                verbs.checkImmediately(result).toBe(true)
            }
        }

        inCaseOf("an assertion which fails") {
            testee.createAndAddAssertion(DescriptionAnyAssertion.TO_BE, -12, { a == 0 })
            test("${testee::allAssertionsHold.name} returns `false` and does not throw an Exception") {
                val result = testee.allAssertionsHold()
                verbs.checkImmediately(result).toBe(false)
            }

            test("re-checking the assertions (calling ${testee::allAssertionsHold.name} twice) throws an  ${IllegalStateException::class.simpleName}") {
                verbs.checkException {
                    testee.allAssertionsHold()
                }.toThrow<IllegalStateException> { messageContains("create assertions first") }
            }
        }

    }

    describeFun(testee::addAssertion.name) {
        inCaseOf("a custom assertion which holds") {
            testee.addAssertion(object : Assertion {
                override fun holds() = true
            })
            test("${testee::allAssertionsHold.name} returns `true` and does not throw an Exception") {
                val result = testee.allAssertionsHold()
                verbs.checkImmediately(result).toBe(true)
            }
        }

        inCaseOf("a custom ${DescriptiveAssertion::class.java.simpleName} which fails") {
            testee.addAssertion(
                AssertImpl.builder.descriptive.create(
                    DescriptionAnyAssertion.TO_BE,
                    "my expected result",
                    false
                )
            )
            test("${testee::allAssertionsHold.name} returns `false` and does not throw an Exception") {
                val result = testee.allAssertionsHold()
                verbs.checkImmediately(result).toBe(false)
            }

            test("re-checking the assertions (calling ${testee::allAssertionsHold.name} twice) throws an ${IllegalStateException::class.simpleName}") {
                verbs.checkException {
                    testee.allAssertionsHold()
                }.toThrow<IllegalStateException>()
            }
        }
    }
})
