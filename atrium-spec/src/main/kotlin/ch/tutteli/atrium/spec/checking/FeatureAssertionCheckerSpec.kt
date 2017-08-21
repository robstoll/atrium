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

    fun prefixedDescribe(description: String, body: SpecBody.() -> Unit) {
        prefixedDescribe(describePrefix, description, body)
    }

    val assertions = ArrayList<IAssertion>()
    assertions.add(object : IAssertion {
        override fun holds() = true
    })
    val assertionVerb = AssertionVerb.VERB
    val valueUnderTest = 1
    val subjectFactory = mock<IAssertionPlant<Int>>()
    val testee = testeeFactory(subjectFactory)


    prefixedDescribe("fun ${testee::check.name}") {
        setUp("creates a ${IAssertionGroup::class.simpleName} of type ${IFeatureAssertionGroupType::class.simpleName} and passes it to its subjectFactory") {

            testee.check(assertionVerb, valueUnderTest, assertions)
            val captor = argumentCaptor<IAssertion>()
            verify(subjectFactory).addAssertion(captor.capture())
            val fluent = verbs.checkImmediately(captor.firstValue).isA<IAssertionGroup> {
                its(subject::type).isA<IFeatureAssertionGroupType>()
            }
            group("context ${IAssertionGroup::class.simpleName} of type ${IFeatureAssertionGroupType::class.simpleName}") {
                check("its ${IAssertionGroup::subject.name} corresponds to the passed assertionVerb") {
                    fluent.its(fluent.subject::name).toBe(assertionVerb)
                }
                check("its ${IAssertionGroup::subject.name} corresponds to the passed subject") {
                    fluent.its(fluent.subject::subject).toBe(valueUnderTest)
                }
                check("copies the assertion") {
                    assertions.clear()
                    fluent.its(fluent.subject::assertions).hasSize(1).and.isNotSame(assertions)
                }
            }

        }
    }
})
