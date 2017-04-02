package ch.tutteli.atrium.creating

import ch.tutteli.atrium.*
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IOneMessageAssertion
import ch.tutteli.atrium.assertions.Message
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

class AssertionPlantCheckLazilySpec : Spek({
    val assertionVerb = "myAssertionVerb"
    val subject = 10
    val plant = AtriumFactory.newCheckLazily(assertionVerb, subject, AtriumReporterSupplier.REPORTER)

    context("fun ${plant::createAndAddAssertion.name}") {

        val a = subject
        inCaseOf("assertion which holds") {
            plant.createAndAddAssertion("is 1", a, { a == subject })
            it("does not throw an Exception when checking") {
                plant.checkAssertions()
            }
        }

        setUp("in case of assertion which fails") {
            plant.createAndAddAssertion("to be", 0, { a == 0 })
            val expectFun = expect {
                plant.checkAssertions()
            }
            setUp("throws an AssertionError when checking") {
                context("exception message") {
                    val assertMessage = expectFun.toThrow<AssertionError>().message
                    it("contains the ${plant.commonFields::assertionVerb.name}'") {
                        assertMessage.contains(assertionVerb)
                    }
                    it("contains the '${plant::subject.name}'") {
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
                        plant.checkAssertions()
                    }
                }
            }
        }

    }

    context("fun ${plant::addAssertion.name}") {
        inCaseOf("a custom assertion which holds") {
            plant.addAssertion(object : IAssertion {
                override fun holds() = true
            })
            it("does not throw an Exception when checking") {
                plant.checkAssertions()
            }
        }

        setUp("in case of a custom ${IOneMessageAssertion::class.java.simpleName} which fails") {
            plant.addAssertion(object : IOneMessageAssertion {
                override val message = Message("a", "b", false)
            })
            val expectFun = expect {
                plant.checkAssertions()
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
                        plant.checkAssertions()
                    }
                }
            }
        }
    }
})
