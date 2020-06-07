//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.specs.checking

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.FeatureAssertionGroupType
import ch.tutteli.atrium.checking.AssertionChecker
import ch.tutteli.atrium.creating.AssertionHolder
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.AssertionVerb
import ch.tutteli.atrium.specs.describeFunTemplate
import io.mockk.*
import org.spekframework.spek2.Spek
import org.spekframework.spek2.lifecycle.CachingMode
import org.spekframework.spek2.style.specification.Suite

abstract class FeatureAssertionCheckerSpec(
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
    val expect = mockk<Expect<Int>>()
    every { expect.addAssertion(any()) } returns expect
    val testee = testeeFactory(expect)


    describeFun("check") {
        context("creates a ${AssertionGroup::class.simpleName} and passes it to its subjectFactory") {

            val captured by memoized(mode = CachingMode.SCOPE) {
                testee.check(assertionVerb, valueUnderTest, assertions)
                val slot = slot<Assertion>()
                verify(exactly = 1) { expect.addAssertion(capture(slot)) }
                confirmVerified(expect)
                slot.captured
            }

            it("its type is  ${FeatureAssertionGroupType::class.simpleName}") {
                expect(captured).isA<AssertionGroup> {
                    feature { f(it::type) }.isA<FeatureAssertionGroupType>()
                }
            }

            it("its ${AssertionGroup::representation.name} corresponds to the passed assertionVerb") {
                expect(captured).isA<AssertionGroup> {
                    feature { f(it::description) }.toBe(assertionVerb)
                }
            }

            it("its ${AssertionGroup::representation.name} corresponds to the passed subject") {
                expect(captured).isA<AssertionGroup> {
                    feature { f(it::representation) }.toBe(1)
                }
            }

            it("copies the assertion") {
                assertions.clear()
                expect(captured).isA<AssertionGroup> {
                    feature { f(it::assertions) }.hasSize(1).and.isNotSameAs(assertions)
                }
            }
        }
    }
})
