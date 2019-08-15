package ch.tutteli.atrium.specs.checking

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.FeatureAssertionGroupType
import ch.tutteli.atrium.checking.AssertionChecker
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.BaseAssertionPlant
import ch.tutteli.atrium.reporting.LazyRepresentation
import ch.tutteli.atrium.specs.AssertionVerb
import ch.tutteli.atrium.specs.AssertionVerbFactory
import ch.tutteli.atrium.specs.describeFun
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it

//TODO #116 migrate spek1 to spek2 - move to specs-common
abstract class FeatureAssertionCheckerSpec(
    verbs: AssertionVerbFactory,
    testeeFactory: (subjectFactory: BaseAssertionPlant<Int, *>) -> AssertionChecker,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit) =
        describeFun(describePrefix, funName, body = body)

    val assertions = ArrayList<Assertion>()
    assertions.add(object : Assertion {
        override fun holds() = true
    })
    val assertionVerb = AssertionVerb.VERB
    val valueUnderTest = 1
    val subjectFactory = mock<AssertionPlant<Int>>()
    val testee = testeeFactory(subjectFactory)


    describeFun(testee::check.name) {
        context("creates a ${AssertionGroup::class.simpleName} and passes it to its subjectFactory") {

            testee.check(assertionVerb, { valueUnderTest }, assertions)
            val captor = argumentCaptor<Assertion>()
            verify(subjectFactory).addAssertion(captor.capture())
            it("its type is  ${FeatureAssertionGroupType::class.simpleName}") {
                verbs.check(captor.firstValue).isA<AssertionGroup> {
                    feature(AssertionGroup::type).isA<FeatureAssertionGroupType>()
                }
            }

            it("its ${AssertionGroup::representation.name} corresponds to the passed assertionVerb") {
                verbs.check(captor.firstValue).isA<AssertionGroup> {
                    feature(AssertionGroup::description).toBe(assertionVerb)
                }
            }

            it("its ${AssertionGroup::representation.name} corresponds to the ${LazyRepresentation::class.simpleName} of the passed subject") {
                verbs.check(captor.firstValue).isA<AssertionGroup> {
                    feature(AssertionGroup::representation).isA<LazyRepresentation> {
                        feature(LazyRepresentation::eval).toBe(valueUnderTest)
                    }
                }
            }

            it("copies the assertion") {
                assertions.clear()
                verbs.check(captor.firstValue).isA<AssertionGroup> {
                    feature(AssertionGroup::assertions).hasSize(1).and.isNotSameAs(assertions)
                }
            }
        }
    }
})
