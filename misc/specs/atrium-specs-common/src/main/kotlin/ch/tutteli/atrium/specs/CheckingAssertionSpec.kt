package ch.tutteli.atrium.specs

import ch.tutteli.atrium.api.cc.en_GB.toBe
import ch.tutteli.atrium.creating.CheckingAssertionPlant
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.specs.verbs.AssertionVerbFactory
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

abstract class CheckingAssertionSpec<T>(
    verbs: AssertionVerbFactory,
    groupPrefix: String,
    vararg assertionCreator: Triple<String, Expect<T>.() -> Unit, Pair<T, T>>
) : Spek({

    describe("${groupPrefix}assertion function can be added to ${CheckingAssertionPlant::class.simpleName}") {

        assertionCreator.forEach { (name, createAssertion, holdingAndFailingSubject) ->
            val (holdingSubject, failingSubject) = holdingAndFailingSubject
            describe("fun `$name`") {
                it("assertion which holds -- does not throw, returns `true`") {
                    val checkingPlant = AssertImpl.coreFactory.newCheckingAssertionContainer { holdingSubject }
                    checkingPlant.createAssertion()
                    verbs.check(checkingPlant.allAssertionsHold()).toBe(true)
                }

                it("assertion which does not hold -- does not throw, returns `false`") {
                    val checkingPlant = AssertImpl.coreFactory.newCheckingAssertionContainer { failingSubject }
                    checkingPlant.createAssertion()
                    verbs.check(checkingPlant.allAssertionsHold()).toBe(false)
                }
            }
        }
    }
})

fun <T> checkingTriple(name: String, assertionCreator: Expect<T>.() -> Unit, holdingSubject: T, failingSubject: T)
    = Triple(name, assertionCreator, holdingSubject to failingSubject)
