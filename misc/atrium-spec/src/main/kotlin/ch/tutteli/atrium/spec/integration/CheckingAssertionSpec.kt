package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.toBe
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.CheckingAssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.spec.AssertionVerbFactory
import org.jetbrains.spek.api.Spek

abstract class CheckingAssertionSpec<T : Any>(
    verbs: AssertionVerbFactory,
    groupPrefix: String,
    vararg assertionCreator: Triple<String, Assert<T>.() -> Unit, Pair<T, T>>
) : Spek({

    group("${groupPrefix}assertion function can be added to ${CheckingAssertionPlant::class.simpleName}") {

        assertionCreator.forEach { (name, createAssertion, holdingAndFailingSubject) ->
            val (holdingSubject, failingSubject) = holdingAndFailingSubject
            group("fun `$name`") {
                test("assertion which holds -- does not throw, returns `true`") {
                    val checkingPlant = AssertImpl.coreFactory.newCheckingPlant({ holdingSubject })
                    checkingPlant.createAssertion()
                    verbs.checkImmediately(checkingPlant.allAssertionsHold()).toBe(true)
                }

                test("assertion which does not hold -- does not throw, returns `false`") {
                    val checkingPlant = AssertImpl.coreFactory.newCheckingPlant({ failingSubject })
                    checkingPlant.createAssertion()
                    verbs.checkImmediately(checkingPlant.allAssertionsHold()).toBe(false)
                }
            }
        }
    }
})

fun <T : Any> checkingTriple(name: String, createAssertion: Assert<T>.() -> Unit, holdingSubject: T, failingSubject: T)
    = Triple(name, createAssertion, holdingSubject to failingSubject)
