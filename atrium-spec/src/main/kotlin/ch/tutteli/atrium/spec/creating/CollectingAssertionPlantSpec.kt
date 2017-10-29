package ch.tutteli.atrium.spec.creating

import ch.tutteli.atrium.api.cc.en_UK.toBe
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.creating.ICollectingAssertionPlant
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.inCaseOf
import ch.tutteli.atrium.spec.prefixedDescribe
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it

abstract class CollectingAssertionPlantSpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: () -> ICollectingAssertionPlant<Int>,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun prefixedDescribe(description: String, body: SpecBody.() -> Unit) {
        prefixedDescribe(describePrefix, description, body)
    }

    val testee = testeeFactory()

    prefixedDescribe("fun ${testee::getAssertions.name}") {
        context("no assertion has been added so far") {
            it("returns an empty list") {
                verbs.checkImmediately(testee.getAssertions()).toBe(listOf())
            }

            inCaseOf("an assertion is added"){
                val assertion: IAssertion = object : IAssertion {
                    override fun holds() = true
                }
                testee.addAssertion(assertion)
                it("returns the assertion") {
                    verbs.checkImmediately(testee.getAssertions()).toBe(listOf(assertion))
                }

                it("returns the assertion when calling ${testee::getAssertions.name} a second time"){
                    verbs.checkImmediately(testee.getAssertions()).toBe(listOf(assertion))
                }
            }
        }
    }
})
