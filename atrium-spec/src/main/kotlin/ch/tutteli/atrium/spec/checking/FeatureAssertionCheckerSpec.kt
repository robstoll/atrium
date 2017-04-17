package ch.tutteli.atrium.spec.checking

import ch.tutteli.atrium.*
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IFeatureAssertionGroup
import ch.tutteli.atrium.checking.IAssertionChecker
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.check
import ch.tutteli.atrium.spec.setUp
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe

open class FeatureAssertionCheckerSpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: (subjectFactory: IAssertionPlant<Int>) -> IAssertionChecker
) : Spek({

    val assertions = ArrayList<IAssertion>()
    assertions.add(object : IAssertion {
        override fun holds() = true
    })
    val assertionVerb = "verb"
    val subject = 1

    describe("check") {
        setUp("creates a ${IFeatureAssertionGroup::class.java.simpleName} and passes it to its subjectFactory") {
            val subjectFactory = mock<IAssertionPlant<Int>>()
            val testee = testeeFactory(subjectFactory)
            testee.check(assertionVerb, subject, assertions)
            val captor = argumentCaptor<IAssertion>()
            verify(subjectFactory).addAssertion(captor.capture())
            val fluent = verbs.checkImmediately(captor.firstValue).isA<IFeatureAssertionGroup>()
            group("context ${IFeatureAssertionGroup::class.java.simpleName}") {
                check("its ${IFeatureAssertionGroup::featureName.name} corresponds to the passed assertionVerb") {
                    fluent.its(fluent.subject::featureName).toBe(assertionVerb)
                }
                check("its ${IFeatureAssertionGroup::feature.name} corresponds to the passed subject") {
                    fluent.its(fluent.subject::feature).toBe(subject)
                }
                check("copies the assertion") {
                    assertions.clear()
                    fluent.its(fluent.subject::assertions).hasSize(1).and.isNotSame(assertions)
                }
            }

        }
    }
})
