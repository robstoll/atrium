package ch.tutteli.atrium.spec.checking

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.InvisibleAssertionGroupType
import ch.tutteli.atrium.checking.AssertionChecker
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.BaseAssertionPlant
import ch.tutteli.atrium.spec.AssertionVerb
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it

//TODO #116 migrate spek1 to spek2 - move to specs-common
abstract class DelegatingAssertionCheckerSpec(
    verbs: AssertionVerbFactory,
    testeeFactory: (subjectFactory: BaseAssertionPlant<Int, *>) -> AssertionChecker,
    describePrefix: String = "[Atrium] "
) : Spek({

    val assert: (Assertion) -> AssertionPlant<Assertion> = verbs::checkImmediately

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

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

    describeFun(AssertionChecker::check.name) {
        context("empty assertion list") {
            it("does not throw an exception") {
                val testee = testeeFactory(mock())
                testee.check(assertionVerb, { 1 }, listOf())
            }
        }

        mapOf(
            "one assertion which fails" to listOf(assertionWhichFails),
            "one assertion which holds" to listOf(assertionWhichHolds),
            "one assertion which fails and one which holds" to listOf(assertionWhichFails, assertionWhichHolds),
            "one assertion which holds and one which fails" to listOf(assertionWhichHolds, assertionWhichFails)
        ).forEach { (description, assertions) ->
            context(description) {
                it("adds the assertion(s) to the subject plant") {
                    //arrange
                    val subjectFactory = mock<AssertionPlant<Int>>()
                    val testee = testeeFactory(subjectFactory)
                    //act
                    testee.check(assertionVerb, { 1 }, assertions)
                    //assert
                    val captor = argumentCaptor<Assertion>()
                    verify(subjectFactory).addAssertion(captor.capture())
                    assert(captor.firstValue).isA<AssertionGroup> {
                        property(AssertionGroup::type).isA<InvisibleAssertionGroupType> {}
                        property(AssertionGroup::assertions) {
                            contains.inAnyOrder.only.values(assertions.first(), *assertions.drop(1).toTypedArray())
                        }
                    }
                }
            }
        }
    }
})
