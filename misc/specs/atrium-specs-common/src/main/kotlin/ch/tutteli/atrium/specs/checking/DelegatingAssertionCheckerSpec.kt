//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.specs.checking

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.InvisibleAssertionGroupType
import ch.tutteli.atrium.checking.AssertionChecker
import ch.tutteli.atrium.creating.AssertionHolder
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.AssertionVerb
import ch.tutteli.atrium.specs.describeFunTemplate
import io.mockk.*
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class DelegatingAssertionCheckerSpec(
    testeeFactory: (AssertionHolder) -> AssertionChecker,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val assertions = ArrayList<Assertion>()
    assertions.add(object : Assertion {
        override fun holds() = true
    })
    val assertionVerb = AssertionVerb.VERB
    val assertionWhichFails = object : Assertion {
        override fun holds() = false
    }
    val assertionWhichHolds = object : Assertion {
        override fun holds() = true
    }

    describeFun("check") {
        context("empty assertion list") {
            it("does not throw an exception") {
                val testee = testeeFactory(mockk(relaxed = true))
                testee.check(assertionVerb, 1, listOf())
            }
        }

        mapOf(
            "one assertion which fails" to listOf(assertionWhichFails),
            "one assertion which holds" to listOf(assertionWhichHolds),
            "one assertion which fails and one which holds" to listOf(assertionWhichFails, assertionWhichHolds),
            "one assertion which holds and one which fails" to listOf(assertionWhichHolds, assertionWhichFails)
        ).forEach { (description, assertions) ->
            context(description) {
                it("adds the assertion(s) to the assertion container") {
                    //arrange
                    val expect = mockk<Expect<Int>>()
                    every { expect.addAssertion(any()) } returns expect

                    val testee = testeeFactory(expect)
                    //act
                    testee.check(assertionVerb, 1, assertions)
                    //assert
                    val captor = slot<Assertion>()
                    verify(exactly = 1) { expect.addAssertion(assertion = capture(captor)) }
                    expect(captor.captured).isA<AssertionGroup> {
                        feature(AssertionGroup::type).isA<InvisibleAssertionGroupType>()
                        feature(AssertionGroup::assertions) {
                            contains.inAnyOrder.only.values(assertions.first(), *assertions.drop(1).toTypedArray())
                        }
                    }
                }
            }
        }
    }
})
