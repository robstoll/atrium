package ch.tutteli.atrium.spec.checking

import ch.tutteli.atrium.api.cc.en_UK.*
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.FeatureAssertionGroupType
import ch.tutteli.atrium.checking.AssertionChecker
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.spec.*
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody

abstract class FeatureAssertionCheckerSpec(
    verbs: AssertionVerbFactory,
    testeeFactory: (subjectFactory: AssertionPlant<Int>) -> AssertionChecker,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val assertions = ArrayList<Assertion>()
    assertions.add(object : Assertion {
        override fun holds() = true
    })
    val assertionVerb = AssertionVerb.VERB
    val valueUnderTest = 1
    val subjectFactory = mock<AssertionPlant<Int>>()
    val testee = testeeFactory(subjectFactory)


    describeFun(testee::check.name) {
        setUp("creates a ${AssertionGroup::class.simpleName} and passes it to its subjectFactory") {

            testee.check(assertionVerb, valueUnderTest, assertions)
            val captor = argumentCaptor<Assertion>()
            verify(subjectFactory).addAssertion(captor.capture())
            check("its type is  ${FeatureAssertionGroupType::class.simpleName}") {
                verbs.checkImmediately(captor.firstValue).isA<AssertionGroup> {
                    property(subject::type).isA<FeatureAssertionGroupType> {}
                }
            }

            check("its ${AssertionGroup::subject.name} corresponds to the passed assertionVerb") {
                verbs.checkImmediately(captor.firstValue).isA<AssertionGroup> {
                    property(subject::name).toBe(assertionVerb)
                }
            }
            check("its ${AssertionGroup::subject.name} corresponds to the passed subject") {
                verbs.checkImmediately(captor.firstValue).isA<AssertionGroup> {
                    property(subject::subject).toBe(valueUnderTest)
                }
            }
            check("copies the assertion") {
                assertions.clear()
                verbs.checkImmediately(captor.firstValue).isA<AssertionGroup> {
                    property(subject::assertions).hasSize(1).and.isNotSame(assertions)
                }
            }
        }
    }
})
