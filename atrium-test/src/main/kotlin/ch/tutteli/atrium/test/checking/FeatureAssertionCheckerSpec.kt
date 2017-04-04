package ch.tutteli.atrium.test.checking

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IFeatureAssertionGroup
import ch.tutteli.atrium.assertions.OneMessageAssertion
import ch.tutteli.atrium.checking.IAssertionChecker
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.hasSize
import ch.tutteli.atrium.isA
import ch.tutteli.atrium.its
import ch.tutteli.atrium.test.IAssertionVerbFactory
import ch.tutteli.atrium.test.check
import ch.tutteli.atrium.test.setUp
import ch.tutteli.atrium.toBe
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe

open class FeatureAssertionCheckerSpec(
    val verbs: IAssertionVerbFactory,
    val testeeFactory: (subjectFactory: IAssertionPlant<Int>) -> IAssertionChecker
) : Spek({

    val assertions = ArrayList<IAssertion>()
    assertions.add(OneMessageAssertion("to be", 2, false))
    val assertionVerb = "verb"
    val subject = 1

    describe("check") {
        setUp("creates a featureAssertionGroup and passes it to its subjectFactory") {
            val subjectFactory = mock<IAssertionPlant<Int>>()
            val testee = testeeFactory(subjectFactory)
            testee.check(assertionVerb, subject, assertions)
            val captor = argumentCaptor<IAssertion>()
            verify(subjectFactory).addAssertion(captor.capture())
            val fluent = verbs.checkImmediately(captor.firstValue).isA<IFeatureAssertionGroup>()
            context("featureAssertionGroup") {
                check("its ${IFeatureAssertionGroup::featureName.name} corresponds to the passed assertionVerb") {
                    fluent.its(fluent.subject::featureName).toBe(assertionVerb)
                }
                check("its ${IFeatureAssertionGroup::subSubject.name} corresponds to the passed subject") {
                    fluent.its(fluent.subject::subSubject).toBe(subject)
                }
                check("copies the assertion") {
                    assertions.clear()
                    fluent.its(fluent.subject::assertions).hasSize(1)
                    //TODO replace with 'not' as soon as 'not' is supported
//                    fluent.and(fluent.subject::assertions).not.isSame(assertions)
                }
            }

        }
    }
})
