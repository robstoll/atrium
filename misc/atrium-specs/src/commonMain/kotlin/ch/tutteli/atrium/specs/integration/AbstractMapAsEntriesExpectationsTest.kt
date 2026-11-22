package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.testfactories.TestFactory

@Suppress("FunctionName")
abstract class AbstractMapAsEntriesExpectationsTest(
    private val asEntriesFeatureSpec: Feature0<Map<String, Int>, Set<Map.Entry<String, Int>>>,
    private val asEntriesSpec: Fun1<Map<String, Int>, Expect<Set<Map.Entry<String, Int>>>.() -> Unit>
) : ExpectationFunctionBaseTest() {

    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        asEntriesFeatureSpec.forSubjectLessTest(),
        asEntriesSpec.forSubjectLessTest { toContain("a" to 1) }
    )

    @TestFactory
    fun asEntries_feature_and_function() = testFactoryForFeatureNonFeature(
        asEntriesFeatureSpec, asEntriesSpec
    ) { name, asEntriesFun, _ ->
        it("$name - transformation can be applied and an assertion made") {
            expect(mapOf("a" to 1, "b" to 2)).asEntriesFun {
                toContain.inAnyOrder.only.entries(
                    { toEqualKeyValue("b", 2) },
                    {
                        key.toStartWith("a")
                        value.toBeGreaterThanOrEqualTo(1)
                    }
                )
            }
        }
    }
}
