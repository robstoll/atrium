package ch.tutteli.assertk

import ch.tutteli.assertk.assertions.IAssertion
import ch.tutteli.assertk.assertions.IOneMessageAssertion
import ch.tutteli.assertk.assertions.Message
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

class AssertionFactorySpec : Spek({
    val assertionVerb = "myAssertionVerb"

    describe("lazy evaluation") {
        val subject = 10
        val factory = AssertionFactory.new(assertionVerb, subject)
        context(factory::createAndAddAssertion.name) {

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
                    expectFun.toThrow<AssertionError>()
                    context("exception message") {
                        val message = expectFun.throwable!!.message!!
                        it("contains the ${factory::assertionVerb.name}'") {
                            assert(message).contains(assertionVerb)
                        }
                        it("contains the '${factory::subject.name}'") {
                            assert(message).contains(subject.toString())
                        }
                        it("contains the '${Message::description.name}' of the assertion-message") {
                            assert(message).contains("to be")
                        }
                        it("contains the '${Message::representation.name}' of the assertion-message") {
                            assert(message).contains("0")
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

        context(factory::addAssertion.name) {
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
                        val message = expectFun.throwable!!.message!!
                        it("contains the messages of the custom assertion") {
                            assert(message).contains("a", "b")
                        }
                        it("contains the assertionVerb") {
                            assert(message).contains(assertionVerb)
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
    }
})
