package ch.tutteli.atrium.specs.checking

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.FeatureAssertionGroupType
import ch.tutteli.atrium.checking.AssertionChecker
import ch.tutteli.atrium.creating.AssertionHolder
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.reporting.LazyRepresentation
import ch.tutteli.atrium.specs.AssertionVerb
import ch.tutteli.atrium.specs.AssertionVerbFactory
import ch.tutteli.atrium.specs.describeFunTemplate
import io.mockk.*
import org.spekframework.spek2.Spek
import org.spekframework.spek2.lifecycle.CachingMode
import org.spekframework.spek2.style.specification.Suite

abstract class FeatureAssertionCheckerSpec(
    verbs: AssertionVerbFactory,
    testeeFactory: (AssertionHolder) -> AssertionChecker,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val assertions = ArrayList<Assertion>()
    assertions.add(object : Assertion {
        override fun holds() = true
    })
    val assertionVerb = AssertionVerb.VERB
    val valueUnderTest = 1
    val subjectFactory = mockk<AssertionPlant<Int>>()
    every { subjectFactory.addAssertion(any()) } returns subjectFactory
    val testee = testeeFactory(subjectFactory)


    describeFun("check") {
        context("creates a ${AssertionGroup::class.simpleName} and passes it to its subjectFactory") {

            val captured by memoized(mode = CachingMode.SCOPE) {
                testee.check(assertionVerb, valueUnderTest, assertions)
                val slot = slot<Assertion>()
                verify(exactly = 1) { subjectFactory.addAssertion(capture(slot)) }
                confirmVerified(subjectFactory)
                slot.captured
            }

            it("its type is  ${FeatureAssertionGroupType::class.simpleName}") {
                verbs.check(captured).isA<AssertionGroup> {
                    feature { f(it::type) }.isA<FeatureAssertionGroupType>()
                }
            }

            it("its ${AssertionGroup::representation.name} corresponds to the passed assertionVerb") {
                verbs.check(captured).isA<AssertionGroup> {
                    feature { f(it::description) }.toBe(assertionVerb)
                }
            }

            it("its ${AssertionGroup::representation.name} corresponds to the passed subject") {
                verbs.check(captured).isA<AssertionGroup> {
                    feature { f(it::representation) }.toBe(1)
                }
            }

            it("copies the assertion") {
                assertions.clear()
                verbs.check(captured).isA<AssertionGroup> {
                    feature { f(it::assertions) }.hasSize(1).and.isNotSameAs(assertions)
                }
            }
        }
    }
})
