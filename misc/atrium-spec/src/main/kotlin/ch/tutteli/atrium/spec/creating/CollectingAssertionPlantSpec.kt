package ch.tutteli.atrium.spec.creating

import ch.tutteli.atrium.api.cc.en_UK.toBe
import ch.tutteli.atrium.api.cc.en_UK.toThrow
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.CollectingAssertionPlant
import ch.tutteli.atrium.creating.PlantHasNoSubjectException
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.inCaseOf
import ch.tutteli.atrium.spec.prefixedDescribe
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it

abstract class CollectingAssertionPlantSpec(
    verbs: AssertionVerbFactory,
    testeeFactory: (() -> Int) -> CollectingAssertionPlant<Int>,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun prefixedDescribe(description: String, body: SpecBody.() -> Unit)
        = prefixedDescribe(describePrefix, description, body)

    val testee = testeeFactory({ 1 })

    prefixedDescribe("fun ${testee::getAssertions.name}") {

        context("no assertion has been added so far") {
            it("returns an empty list") {
                verbs.checkImmediately(testee.getAssertions()).toBe(listOf())
            }

            inCaseOf("an assertion is added") {
                val assertion: Assertion = object : Assertion {
                    override fun holds() = true
                }
                testee.addAssertion(assertion)
                it("returns the assertion") {
                    verbs.checkImmediately(testee.getAssertions()).toBe(listOf(assertion))
                }

                it("returns the assertion when calling ${testee::getAssertions.name} a second time") {
                    verbs.checkImmediately(testee.getAssertions()).toBe(listOf(assertion))
                }
            }
        }
    }

    prefixedDescribe("accessing ${testee::subject.name}") {
        context("subject was provided") {
            it("does not throw an exception when accessing subject") {
                testee.subject
            }
        }

        context("provider throws an ${PlantHasNoSubjectException::class.simpleName}") {
            val plantHasNoSubjectException = PlantHasNoSubjectException()
            val testeeThrowing = testeeFactory { throw plantHasNoSubjectException }

            it("throws the exception when accessing subject") {
                verbs.checkException {
                    testeeThrowing.subject
                }.toThrow<PlantHasNoSubjectException> { toBe(plantHasNoSubjectException) }
            }
        }
    }


})
