package ch.tutteli.atrium.specs.creating

import ch.tutteli.atrium.api.fluent.en_GB.messageContains
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ReportingAssertionContainer
import ch.tutteli.atrium.specs.AssertionVerb
import ch.tutteli.atrium.specs.describeFunTemplate
import ch.tutteli.atrium.translations.DescriptionBasic.TO_BE
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class ReportingAssertionContainerSpec(
    testeeFactory: (ReportingAssertionContainer.AssertionCheckerDecorator<Int>) -> ReportingAssertionContainer<Int>,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val assertionVerb = AssertionVerb.VERB
    val subject = 10
    val description = TO_BE
    val expected = -12

    val assertionChecker = (expect(1) as ReportingAssertionContainer<Int>).assertionChecker
    fun createTestee() = testeeFactory(
        ReportingAssertionContainer.AssertionCheckerDecorator.create(
            assertionVerb,
            Some(subject),
            subject,
            assertionChecker
        )
    )

    val container = createTestee()

    fun triple(
        funName: String,
        holdingFun: Expect<Int>.() -> Expect<Int>,
        failingFun: Expect<Int>.() -> Expect<Int>
    ): Triple<String, Expect<Int>.() -> Expect<Int>, Expect<Int>.() -> Expect<Int>> =
        Triple(funName, holdingFun, failingFun)

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

    val trueProvider: (Int) -> Boolean = { true }
    val falseProvider: (Int) -> Boolean = { false }

    listOf(
        triple(
            "createAndAddAssertion",
            { createAndAddAssertion(description, expected, trueProvider) },
            { createAndAddAssertion(description, expected, falseProvider) }
        ),
        triple(
            container::addAssertion.name,
            { addAssertion(basicAssertionWhichHolds) },
            { addAssertion(basicAssertionWhichFails) }),
        triple(
            "${container::addAssertionsCreatedBy.name} using createAndAddAssertion inside",
            { addAssertionsCreatedBy { createAndAddAssertion(description, expected, trueProvider) } },
            { addAssertionsCreatedBy { createAndAddAssertion(description, expected, falseProvider) } }
        ),
        triple(
            "${container::addAssertionsCreatedBy.name} using ${container::addAssertion.name} inside",
            { addAssertionsCreatedBy { addAssertion(basicAssertionWhichHolds) } },
            { addAssertionsCreatedBy { addAssertion(basicAssertionWhichFails) } }
        ),
        triple(
            "${container::addAssertionsCreatedBy.name} using ${container::addAssertionsCreatedBy.name} inside",
            { addAssertionsCreatedBy { addAssertionsCreatedBy { addAssertion(basicAssertionWhichHolds) } } },
            { addAssertionsCreatedBy { addAssertionsCreatedBy { addAssertion(basicAssertionWhichFails) } } }
        )
    ).forEach { (funName, holdingFun, failingFun) ->
        describeFun(funName) {
            context("in case of an assertion which holds") {
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

            context("in case of assertion which fails") {
                context("throws an AssertionError") {
                    fun expectFun(): Expect<() -> Any?> {
                        val testee = createTestee()
                        return expect {
                            testee.failingFun()
                        }
                    }

                    context("exception message") {

                        it("contains the assertion verb") {
                            expectFun().toThrow<AssertionError> { messageContains(assertionVerb.getDefault()) }
                        }
                        it("contains the subject") {
                            expectFun().toThrow<AssertionError> {
                                messageContains(subject)
                            }
                        }
                        it("contains the '${DescriptiveAssertion::description.name}' of the assertion-message") {
                            expectFun().toThrow<AssertionError> { messageContains(description.getDefault()) }
                        }
                        it("contains the '${DescriptiveAssertion::representation.name}' of the assertion-message") {
                            expectFun().toThrow<AssertionError> { messageContains(expected) }
                        }
                    }

                    context("adding a another assertion which holds") {
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
