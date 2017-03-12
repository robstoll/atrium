package ch.tutteli.assertk.checking

import ch.tutteli.assertk.*
import ch.tutteli.assertk.assertions.IAssertion
import ch.tutteli.assertk.assertions.IFeatureAssertionGroup
import ch.tutteli.assertk.assertions.OneMessageAssertion
import ch.tutteli.assertk.creating.IAssertionFactory
import ch.tutteli.assertk.verbs.assert.assert
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.jetbrains.spek.api.Spek

class FeatureAssertionCheckerSpec : Spek({

    val assertions = ArrayList<IAssertion>()
    assertions.add(OneMessageAssertion("to be", 2, false))
    val assertionVerb = "verb"
    val subject = 1

    describe("check") {
        setUp("creates a featureAssertionGroup and passes it to its subjectFactory") {
            val subjectFactory = mock<IAssertionFactory<Int>>()
            val testee = FeatureAssertionChecker(subjectFactory)
            testee.check(assertionVerb, subject, assertions)
            val captor = argumentCaptor<IAssertion>()
            verify(subjectFactory).addAssertion(captor.capture())
            //TODO replace with the following line as soon as isA is supported
            //val fluent = assert(captor.firstValue).isA<IFeatureAssertionGroup>()
            assert(captor.firstValue is IFeatureAssertionGroup).toBe(true)
            val fluent = assert(captor.firstValue as IFeatureAssertionGroup)
            context("featureAssertionGroup") {
                check("its ${IFeatureAssertionGroup::featureName.name} corresponds to the passed assertionVerb") {
                    fluent.and(fluent.subject::featureName).toBe(assertionVerb)
                }
                check("its ${IFeatureAssertionGroup::subSubject.name} corresponds to the passed subject") {
                    fluent.and(fluent.subject::subSubject).toBe(subject)
                }
                check("copies the assertion") {
                    assertions.clear()
                    fluent.and(fluent.subject::assertions).hasSize(1)
                    //TODO replace with not as soon as not is supported
//                    fluent.and(fluent.subject::assertions).not.isSame(assertions)
                }
            }

        }
    }
})
