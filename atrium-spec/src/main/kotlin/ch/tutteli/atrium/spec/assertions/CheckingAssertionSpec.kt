package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.api.cc.en_UK.isFalse
import ch.tutteli.atrium.api.cc.en_UK.isTrue
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.ICheckingAssertionPlant
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import org.jetbrains.spek.api.Spek

abstract class CheckingAssertionSpec<T : Any>(
    verbs: IAssertionVerbFactory,
    vararg createAssertions: Triple<String, IAssertionPlant<T>.() -> Unit, Pair<T, T>>
) : Spek({

    group("assertion function can be added to ${ICheckingAssertionPlant::class.simpleName}") {

        createAssertions.forEach { (name, createAssertion, holdingAndFailingSubject) ->
            val (holdingSubject, failingSubject) = holdingAndFailingSubject
            group("fun $name") {
                test("assertion which holds -- does not throw, returns `true`") {
                    val checkingPlant = AtriumFactory.newCheckingPlant(holdingSubject)
                    checkingPlant.createAssertion()
                    verbs.checkImmediately(checkingPlant.allAssertionsHold()).isTrue()
                }

                test("assertion which does not hold -- does not throw, returns `false`") {
                    val checkingPlant = AtriumFactory.newCheckingPlant(failingSubject)
                    checkingPlant.createAssertion()
                    verbs.checkImmediately(checkingPlant.allAssertionsHold()).isFalse()
                }
            }
        }
    }
})

fun <T: Any> checkingTriple(name: String, createAssertion: IAssertionPlant<T>.() -> Unit, holdingSubject: T, failingSubject: T)
    = Triple(name, createAssertion, holdingSubject to failingSubject)
