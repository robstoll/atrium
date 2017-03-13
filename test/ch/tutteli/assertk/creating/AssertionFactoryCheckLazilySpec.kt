package ch.tutteli.assertk.creating

import ch.tutteli.assertk.*
import ch.tutteli.assertk.assertions.IAssertion
import ch.tutteli.assertk.assertions.IOneMessageAssertion
import ch.tutteli.assertk.assertions.Message
import ch.tutteli.assertk.verbs.expect.expect
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

class AssertionFactoryCheckLazilySpec : Spek({
    val assertionVerb = "myAssertionVerb"
    val subject = 10
    val factory = AssertionFactory.newCheckLazily(assertionVerb, subject)

    context("fun ${factory::createAndAddAssertion.name}") {

        val a = subject
        inCaseOf("assertion which holds") {
            factory.createAndAddAssertion("is 1", a, { a == subject })
            it("does not throw an Exception when checking") {
                factory.checkAssertions()
            }
        }

        setUp("in case of assertion which fails") {
            factory.createAndAddAssertion("to be", 0, { a == 0 })
            val expectFun = expect {
                factory.checkAssertions()
            }
            setUp("throws an AssertionError when checking") {
                context("exception message") {
                    val assertMessage = expectFun.toThrow<AssertionError>().message
                    it("contains the ${factory::assertionVerb.name}'") {
                        assertMessage.contains(assertionVerb)
                    }
                    it("contains the '${factory::subject.name}'") {
                        assertMessage.contains(subject.toString())
                    }
                    it("contains the '${Message::description.name}' of the assertion-message") {
                        assertMessage.contains("to be")
                    }
                    it("contains the '${Message::representation.name}' of the assertion-message") {
                        assertMessage.contains("0")
                    }
                }
                on("re-checking the assertions") {
                    expectFun.toThrow<AssertionError>()
                    it("does not re-throw the exception") {
                        factory.checkAssertions()
                    }
                }
            }
        }

    }

    context("fun ${factory::addAssertion.name}") {
        inCaseOf("a custom assertion which holds") {
            factory.addAssertion(object : IAssertion {
                override fun holds() = true
            })
            it("does not throw an Exception when checking") {
                factory.checkAssertions()
            }
        }

        setUp("in case of a custom ${IOneMessageAssertion::class.java.simpleName} which fails") {
            factory.addAssertion(object : IOneMessageAssertion {
                override val message = Message("a", "b", false)
            })
            val expectFun = expect {
                factory.checkAssertions()
            }
            setUp("throws an AssertionError") {
                expectFun.toThrow<AssertionError>()
                context("exception message") {
                    val assertMessage = expectFun.toThrow<AssertionError>().message
                    it("contains the messages of the custom assertion") {
                        assertMessage.contains("a", "b")
                    }
                    it("contains the assertionVerb") {
                        assertMessage.contains(assertionVerb)
                    }
                }
                on("re-checking the assertions") {
                    expectFun.toThrow<AssertionError>()
                    it("does not re-throw the exception") {
                        factory.checkAssertions()
                    }
                }
            }
        }
    }
})
