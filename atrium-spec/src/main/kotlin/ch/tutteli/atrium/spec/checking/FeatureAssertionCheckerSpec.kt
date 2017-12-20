package ch.tutteli.atrium.spec.checking

import ch.tutteli.atrium.api.cc.en_UK.*
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.IFeatureAssertionGroupType
import ch.tutteli.atrium.checking.IAssertionChecker
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.spec.*
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody

abstract class FeatureAssertionCheckerSpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: (subjectFactory: IAssertionPlant<Int>) -> IAssertionChecker,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val assertions = ArrayList<IAssertion>()
    assertions.add(object : IAssertion {
        override fun holds() = true
    })
    val assertionVerb = AssertionVerb.VERB
    val valueUnderTest = 1
    val subjectFactory = mock<IAssertionPlant<Int>>()
    val testee = testeeFactory(subjectFactory)


    describeFun(testee::check.name) {
        setUp("creates a ${IAssertionGroup::class.simpleName} and passes it to its subjectFactory") {

            testee.check(assertionVerb, valueUnderTest, assertions)
            val captor = argumentCaptor<IAssertion>()
            verify(subjectFactory).addAssertion(captor.capture())
            check("its type is  ${IFeatureAssertionGroupType::class.simpleName}") {
                verbs.checkImmediately(captor.firstValue).isA<IAssertionGroup> {
                    property(subject::type).isA<IFeatureAssertionGroupType> {}
                }
            }

            check("its ${IAssertionGroup::subject.name} corresponds to the passed assertionVerb") {
                verbs.checkImmediately(captor.firstValue).isA<IAssertionGroup> {
                    property(subject::name).toBe(assertionVerb)
                }
            }
            check("its ${IAssertionGroup::subject.name} corresponds to the passed subject") {
                verbs.checkImmediately(captor.firstValue).isA<IAssertionGroup> {
                    property(subject::subject).toBe(valueUnderTest)
                }
            }
            check("copies the assertion") {
                assertions.clear()
                verbs.checkImmediately(captor.firstValue).isA<IAssertionGroup> {
                    property(subject::assertions).hasSize(1).and.isNotSame(assertions)
                }
            }
        }
    }
})
